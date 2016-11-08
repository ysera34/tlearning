package com.tacademy.work.ycyoon_melonapi.common;

import com.tacademy.work.ycyoon_melonapi.model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by yoon on 2016. 11. 8..
 */

public class BaseConnection {

    public static final String URL_BASE = "http://apis.skplanetx.com/melon";
    public static final String ADD_URL_CHARTS = "/charts";
    public static final String ADD_URL_GENRES = "/genres";

    public static final String URL_CHARTS_REALTIME = URL_BASE + ADD_URL_CHARTS +
            "/realtime?" + "count=%s&page=%s&version=1";
    public static final String URL_SEARCH_GENRE = URL_BASE + ADD_URL_GENRES + "?version=1";
    public static final String URL_CHARTS_GENRE = URL_BASE + ADD_URL_CHARTS +
            "/topgenres/%s?count=%s&page=%s&version=1";

    public static final String APP_KEY = "19c9546d-a916-329a-a7c6-588c70db1ce3";

    public static ArrayList<Song> requestChartsRealtime(String urlBase, int count, int page) {
        String urlFormat = String.format(urlBase, count, page);
        ArrayList<Song> mSongs = new ArrayList<>();
        try {
            URL url = new URL(urlFormat);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("appKey", APP_KEY);

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n\r");
                }
                br.close();
                httpURLConnection.disconnect();
                String result = sb.toString();

                JSONObject object = new JSONObject(result);
                JSONObject melonObject = object.getJSONObject("melon");
                JSONObject songsObject = melonObject.getJSONObject("songs");
                JSONArray songArray = songsObject.getJSONArray("song");
                if (songArray != null) {
                    for (int i = 0; i < songArray.length(); i++){
                        JSONObject songObject = (JSONObject) songArray.get(i);
                        Song song = new Song();
                        song.setSongId(songObject.getInt("songId"));
                        song.setSongName(songObject.getString("songName"));
                        song.setCurrentRank(songObject.getInt("currentRank"));

                        JSONObject artistsObject = songObject.getJSONObject("artists");
                        JSONArray artistArray = artistsObject.getJSONArray("artist");
                        for (int j = 0; j < artistArray.length(); j++) {
                            JSONObject artistObject = (JSONObject) artistArray.get(j);
                            song.setArtistName(artistObject.getString("artistName"));
                        }
                        mSongs.add(song);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mSongs;
    }

    public static ArrayList<Song> requestChartsGenre(String urlBase, String genreCode, int count, int page) {
        String urlFormat = String.format(urlBase, genreCode, count, page);
        ArrayList<Song> mSongs = new ArrayList<>();
        try {
            URL url = new URL(urlFormat);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("appKey", APP_KEY);

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n\r");
                }
                br.close();
                httpURLConnection.disconnect();
                String result = sb.toString();

                JSONObject object = new JSONObject(result);
                JSONObject melonObject = object.getJSONObject("melon");
                JSONObject songsObject = melonObject.getJSONObject("songs");
                JSONArray songArray = songsObject.getJSONArray("song");
                if (songArray != null) {
                    for (int i = 0; i < songArray.length(); i++){
                        JSONObject songObject = (JSONObject) songArray.get(i);
                        Song song = new Song();
                        song.setSongId(songObject.getInt("songId"));
                        song.setSongName(songObject.getString("songName"));
                        song.setCurrentRank(songObject.getInt("currentRank"));

                        JSONObject artistsObject = songObject.getJSONObject("artists");
                        JSONArray artistArray = artistsObject.getJSONArray("artist");
                        for (int j = 0; j < artistArray.length(); j++) {
                            JSONObject artistObject = (JSONObject) artistArray.get(j);
                            song.setArtistName(artistObject.getString("artistName"));
                        }
                        mSongs.add(song);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mSongs;
    }

}

//http://apis.skplanetx.com/melon/charts/realtime?version={version}&page={page}&count={count}
//http://apis.skplanetx.com/melon/genres?version={version}
//http://apis.skplanetx.com/melon/charts/topgenres/{genreId}?version={version}&page={page}&count={count}
