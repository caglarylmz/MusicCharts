package oriontech.com.musiccharts;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;
import oriontech.com.musiccharts.adapters.ViewPagerAdapter;
import oriontech.com.musiccharts.fragments.ChartFragment;
import oriontech.com.musiccharts.utils.Util;


public class MainActivity extends AppCompatActivity {
//tag
    private static final String TAG = MainActivity.class.getSimpleName();
    private static String URL = "https://rss.itunes.apple.com/api/v1/us/";


//views
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private Spinner spnCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
        init();
        spnCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SPINNER",parent.getSelectedItem().toString());
                switch (parent.getSelectedItem().toString()){
                    case "Turkey":
                        URL = "https://rss.itunes.apple.com/api/v1/tr/";
                        refreshUI(URL);
                        return;
                    case "United Kingdom":
                        URL = "https://rss.itunes.apple.com/api/v1/gb/";
                        refreshUI(URL);
                        return;
                    case "France":
                        URL = "https://rss.itunes.apple.com/api/v1/fr/";
                        refreshUI(URL);
                        return;
                    case "Italy":
                        URL = "https://rss.itunes.apple.com/api/v1/it/";
                        refreshUI(URL);
                        return;
                    case "Germany":
                        URL = "https://rss.itunes.apple.com/api/v1/de/";
                        refreshUI(URL);
                        return;
                    case "Sweden":
                        URL = "https://rss.itunes.apple.com/api/v1/se/";
                        refreshUI(URL);
                        return;
                    case "United States":
                        URL = "https://rss.itunes.apple.com/api/v1/us/";
                        refreshUI(URL);
                        return;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    /**
     * Perimissions
     * */
    protected void checkPermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.INTERNET,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()){
                            Log.i(TAG,"All permissions granted");
                        }
                        if (report.isAnyPermissionPermanentlyDenied()){
                            // permission is denied permenantly, navigate user to app settings
                            Log.i(TAG,"some permission denied");
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Need Permission")
                                    .setMessage("This app needs permission to use this feature.You can grant them in app settings.")
                                    .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                                            intent.setData(uri);
                                            startActivityForResult(intent, 100);
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    })
                                    .show();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }

                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(MainActivity.this, "Error ocurred!", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();

    }
    /**
     * Inıtialize MainLayout Views
     * */
    private void init(){

        spnCountries = findViewById(R.id.spinnerCountries);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ViewPagerAdapter(getApplicationContext(),getSupportFragmentManager());

        Bundle argsTopSong = new Bundle();
        Bundle argsHotTracks = new Bundle();
        Bundle argsNewReleases = new Bundle();
        String URL_TOPSONGS= URL+"apple-music/top-songs/";
        String URL_HOTTRACKS = URL + "apple-music/hot-tracks/";
        String URL_NEWRELEASES = URL + "apple-music/new-releases/";
        //apple-music/top-songs/
        argsTopSong.putString("URL",URL_TOPSONGS);
        argsHotTracks.putString("URL",URL_HOTTRACKS);
        argsNewReleases.putString("URL",URL_NEWRELEASES);
        ChartFragment topSongsFragment = new ChartFragment();
        topSongsFragment.setArguments(argsTopSong);
        ChartFragment hotTracksFragment = new ChartFragment();
        hotTracksFragment.setArguments(argsHotTracks);
        ChartFragment newReleaseFragment = new ChartFragment();
        newReleaseFragment.setArguments(argsNewReleases);
        pagerAdapter.addFragment(topSongsFragment,"Top Songs");
        pagerAdapter.addFragment(hotTracksFragment,"Hot Tracks");
        pagerAdapter.addFragment(newReleaseFragment, "New Releases");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    /**
     * Refresh Fragments for selected country
     * */
    private void refreshUI(String url){
        Bundle argsTopSong = new Bundle();
        Bundle argsHotTracks = new Bundle();
        Bundle argsNewReleases = new Bundle();
        String URL_TOPSONGS= url+"apple-music/top-songs/";
        String URL_HOTTRACKS = url + "apple-music/hot-tracks/";
        String URL_NEWRELEASES = url + "apple-music/new-releases/";
        //apple-music/top-songs/
        argsTopSong.putString("URL",URL_TOPSONGS);
        argsHotTracks.putString("URL",URL_HOTTRACKS);
        argsNewReleases.putString("URL",URL_NEWRELEASES);

        ChartFragment topSongsFragment = new ChartFragment();
        topSongsFragment.setArguments(argsTopSong);
        ChartFragment hotTracksFragment = new ChartFragment();
        hotTracksFragment.setArguments(argsHotTracks);
        ChartFragment newReleaseFragment = new ChartFragment();
        newReleaseFragment.setArguments(argsNewReleases);
        pagerAdapter.removeFragment();
        viewPager.removeAllViews();

        pagerAdapter.addFragment(topSongsFragment,"Top Songs");
        pagerAdapter.addFragment(hotTracksFragment,"Hot Tracks");
        pagerAdapter.addFragment(newReleaseFragment, "New Releases");
        pagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(pagerAdapter);
    }


}


