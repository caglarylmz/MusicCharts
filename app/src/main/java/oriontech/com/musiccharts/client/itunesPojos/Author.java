package oriontech.com.musiccharts.client.itunesPojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("uri")
    @Expose
    public String uri;

}

