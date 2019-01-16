package oriontech.com.musiccharts.client.itunesPojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("self")
    @Expose
    public String self;
    @SerializedName("alternate")
    @Expose
    public String alternate;

}
