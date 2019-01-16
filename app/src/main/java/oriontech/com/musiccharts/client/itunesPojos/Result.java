package oriontech.com.musiccharts.client.itunesPojos;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable {

    @SerializedName("artistName")
    @Expose
    public String artistName;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("releaseDate")
    @Expose
    public String releaseDate;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("collectionName")
    @Expose
    public String collectionName;
    @SerializedName("kind")
    @Expose
    public String kind;
    @SerializedName("copyright")
    @Expose
    public String copyright;
    @SerializedName("artistId")
    @Expose
    public String artistId;
    @SerializedName("artistUrl")
    @Expose
    public String artistUrl;
    @SerializedName("artworkUrl100")
    @Expose
    public String artworkUrl100;
    @SerializedName("genres")
    @Expose
    public List<Genre> genres = null;
    @SerializedName("url")
    @Expose
    public String url;

}