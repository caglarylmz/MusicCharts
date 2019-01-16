package oriontech.com.musiccharts.client;

import java.util.List;


import oriontech.com.musiccharts.client.itunesPojos.Repo;
import retrofit2.Call;
import retrofit2.http.GET;


//https://rss.itunes.apple.com/api/v1/tr/apple-music/hot-tracks/all/10/explicit.json
public interface IRestApi {
    @GET("all/20/explicit.json")
    Call<Repo> getRepo();

}
