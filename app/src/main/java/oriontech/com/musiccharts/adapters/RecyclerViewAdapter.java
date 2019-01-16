package oriontech.com.musiccharts.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import oriontech.com.musiccharts.R;
import oriontech.com.musiccharts.client.itunesPojos.Result;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ChartsViewHolder> {
    private List<Result> results;
    private ImageView ivAlbumArt;
    private TextView tvSongTitle;
    private TextView tvArtistName;

    public RecyclerViewAdapter(List<Result> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ChartsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chart_list_item,viewGroup,false);
        return new ChartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartsViewHolder chartsViewHolder, int i) {
        Result result =results.get(i);
        Picasso.get().load(result.artworkUrl100).into(ivAlbumArt);
        tvSongTitle.setText(result.name);
        tvArtistName.setText(result.artistName);

    }

    @Override
    public int getItemCount() {
        return results!= null ? results.size():0;
    }

    public class ChartsViewHolder extends RecyclerView.ViewHolder {

        public ChartsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAlbumArt = itemView.findViewById(R.id.chart_ivAlbumArt);
            tvSongTitle = itemView.findViewById(R.id.chart_tvSongTitle);
            tvArtistName = itemView.findViewById(R.id.chart_tv_ArtistName);
        }
    }
}
