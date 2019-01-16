package oriontech.com.musiccharts.utils;

public class Util {

    public static String YOUTOBE_API="AIzaSyBzFxST5RIgx7vdN_HtwZpO58RUAIo8MR4";

    //https://rss.itunes.apple.com/api/v1/az/itunes-music/new-music/all/50/explicit.json
    public static String URL="https://rss.itunes.apple.com/api/v1/us/";
    public static String SOURCEAPPLE ="/apple-music";
    public static String SOURCEITUNES ="/itunes-music";
    public static String HOT_TRACKS ="/hot-tracks/all/50/explicit.json";
    public static String TOP_SONGS ="/top-songs/all/50/explicit.json";
    public static String NEW_SONGS ="/new-music/all/50/explicit.json";


    public static String COUNTRY_CODE;
    public static void setCountryCode(String countryName){
        switch (countryName){
            case "Turkey":
                COUNTRY_CODE ="tr";
            case "United Kingdom":
                COUNTRY_CODE ="gb";
            case "France":
                COUNTRY_CODE ="fr";
            case "Italy":
                COUNTRY_CODE ="it";
            case "Germany":
                COUNTRY_CODE ="de";
            case "Sweden":
                COUNTRY_CODE ="se";
            default:
                COUNTRY_CODE ="us";
        }

    }
    public static String getCountryCode() {
        return COUNTRY_CODE;
    }

    public static String CHART_TYPE ="top-songs";
    public static void setChartType(String chartType) {
        switch (chartType){
            case "Top Songs":
                CHART_TYPE = "top-songs";
            case "Hot Tracks":
                CHART_TYPE="hot-tracks";
            case "New Releases":
                CHART_TYPE = "new-releases";
            default:
                CHART_TYPE = "top-songs";
        }
    }

    public static String getChartType() {
        return CHART_TYPE;
    }
}
