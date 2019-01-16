package oriontech.com.musiccharts.client.itunesPojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feed {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("author")
    @Expose
    public Author author;
    @SerializedName("links")
    @Expose
    public List<Link> links = null;
    @SerializedName("copyright")
    @Expose
    public String copyright;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("updated")
    @Expose
    public String updated;
    @SerializedName("results")
    @Expose
    public List<Result> results = null;

}
