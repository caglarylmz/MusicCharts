package oriontech.com.musiccharts.client;


import okhttp3.OkHttpClient;
import oriontech.com.musiccharts.utils.Util;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //farklı URL kullancağımdan statik instance kullanmıyorum
    public Retrofit instance;

    public ApiClient() {
    }

    //public static String URL="https://rss.itunes.apple.com/api/v1/" + Util.getCountryCode() + "/apple-music/"+ Util.getChartType()+"/";
    public Retrofit getInstance(String URL){

            instance=new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();

        return instance;
    }

}
