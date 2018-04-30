package wenjunjie.popularmovie2.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtility {
    public static Movie[] FetchMovie(String address){
        String result = "";
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(address);
            urlConnection = (HttpURLConnection)url.openConnection();
            InputStream in = urlConnection.getInputStream();

            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder builder = new StringBuilder();
            String line;
            while((line = r.readLine()) != null){
                builder.append(line).append('\n');
            }
            result = builder.toString();
        }catch (Exception e){
            e.printStackTrace();
            result = null;
        }finally {
            urlConnection.disconnect();
        }
        JSONObject[] moviesInJSON = transferToJSON(result);
        Movie[] movies = new Movie[20];
        for(int i = 0; i < movies.length; ++i){
            movies[i] = new Movie(moviesInJSON[i]);
        }
        return movies;
    }
    public static JSONObject[] transferToJSON(String inputData){
        JSONObject[] result =  new JSONObject[20];
        try{
            JSONObject moviesData = new JSONObject(inputData);
            JSONArray moviesDetails = moviesData.getJSONArray("results");
            for(int i = 0; i < moviesDetails.length(); ++i){
                result[i] = (JSONObject)moviesDetails.get(i);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = null;
        }
        return result;
    }
}
