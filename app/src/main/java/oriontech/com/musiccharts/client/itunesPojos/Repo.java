package oriontech.com.musiccharts.client.itunesPojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("feed")
    @Expose
    public Feed feed;

}
