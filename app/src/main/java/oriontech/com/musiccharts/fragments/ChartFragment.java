package oriontech.com.musiccharts.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import oriontech.com.musiccharts.R;
import oriontech.com.musiccharts.adapters.RecyclerViewAdapter;
import oriontech.com.musiccharts.client.ApiClient;
import oriontech.com.musiccharts.client.IRestApi;
import oriontech.com.musiccharts.client.itunesPojos.Repo;
import oriontech.com.musiccharts.client.itunesPojos.Result;
import oriontech.com.musiccharts.utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    private IRestApi restApi;


    private RecyclerView recyclerChartList;
    private RecyclerViewAdapter mAdapter;
    private ArrayList<Result> results = new ArrayList<>();
    private String URL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        URL = getArguments().getString("URL");
        recyclerChartList = view.findViewById(R.id.chart_recycler);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Handler handler = new Handler();
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        prepareData();
                    }
                });
            }
        };

        timer.schedule(timerTask,0);


    }

    private void prepareData() {
        ApiClient apiClient=new ApiClient();

        restApi =  apiClient.getInstance(URL).create(IRestApi.class);
        Call<Repo> call = restApi.getRepo();


        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                Repo repo = response.body();
                for(int i =0; i<repo.feed.results.size();i++){
                    results.add(repo.feed.results.get(i));
                }
                    mAdapter = new RecyclerViewAdapter(results);
                    RecyclerView.LayoutManager  layoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                    recyclerChartList.setLayoutManager(layoutManager);
                    recyclerChartList.setItemAnimator(new DefaultItemAnimator());
                    recyclerChartList.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {

            }
        });



    }

}
