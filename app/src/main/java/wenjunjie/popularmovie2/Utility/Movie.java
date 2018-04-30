package wenjunjie.popularmovie2.Utility;

import org.json.JSONObject;

import java.io.Serializable;

public class Movie  implements Serializable {
    public String posterPath;
    public String title;
    public String rate;
    public String overview;
    public Movie(JSONObject movieInJSON){
        try {
            posterPath = UrlBuilder.getImageUrl(movieInJSON.getString("poster_path"));
            title = movieInJSON.getString("title");
            rate = movieInJSON.getString("vote_average");
            overview = movieInJSON.getString("overview");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
