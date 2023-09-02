package DataHandler;

import RequestHandler.RequestBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class JsonParser {
    RequestBuilder requestBuilder;


    public JsonParser(RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }



    public String getAuthor(String responseBody) throws UnsupportedEncodingException, JSONException {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray recordings = jsonObject.getJSONArray("recordings");
        String artist = recordings.getJSONObject(0).getJSONArray("artist-credit").getJSONObject(0).getJSONObject("artist").getString("name");
        System.out.println("Artysta: " + artist);
        System.out.println(responseBody);
        return artist;
    }
    public String getAuthorByID(String responseBody) throws JSONException {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray credit = jsonObject.getJSONArray("artist-credit");
        return credit.getJSONObject(0).getString("name");

    }

    public HashMap<String,List<String>> getRandomSongGenre(String responseBody) throws JSONException {
        HashMap<String,List<String>> map = new HashMap<>();

        List<String> songs = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray recordings = jsonObject.getJSONArray("recordings");


        String id;
        String title;
        String artist;

        for (int record= 0;record<recordings.length();record++){
            List<String> content = new ArrayList<>();
            JSONObject recording = recordings.getJSONObject(record);
            id = recording.getString("id");
            title = recording.getString("title");
            artist = recording.getJSONArray("artist-credit").getJSONObject(0).getJSONObject("artist").getString("name");
            content.add(title);
            content.add(artist);
            map.put(id,content);
        }


        return map;
    }

    public HashMap<String,List<String>> getSongDate(String responseBody) throws JSONException {

        HashMap<String,List<String>> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray jsonArray = jsonObject.getJSONArray("recordings");
        for (int i = 0; i< jsonArray.length();i++){
            List<String> list = new ArrayList<>();
            JSONObject recording = jsonArray.getJSONObject(i);
            list.add(recording.getString("title"));
            list.add(recording.getString("first-release-date").substring(0,4));
            map.put(recording.getString("id"),list);
        }
        return map;
    }

    public String getSongByID(String responseBody) throws JSONException {
        JSONObject jsonObject = new JSONObject(responseBody);
        return jsonObject.getString("first-release-date").substring(0,4);
    }



    public List<String> getID(String responseBody) throws JSONException {
        List<String> ids = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray jsonArray = jsonObject.getJSONArray("artists");
        for (int i = 0 ; i< jsonArray.length();i++){
            ids.add(jsonArray.getJSONObject(i).getString("id"));
        }
        return ids;

    }

    public int getAlbumCount(String responseBody) throws JSONException {
        JSONObject jsonObject = new JSONObject(responseBody);
        //int count = jsonObject.getJSONObject("relese-count").getInt("count");
        int count = jsonObject.getInt("count");
        return count;
    }


}
