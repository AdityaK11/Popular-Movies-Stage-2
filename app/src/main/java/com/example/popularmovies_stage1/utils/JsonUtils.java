package com.example.popularmovies_stage1.utils;

import android.util.Log;

import com.example.popularmovies_stage1.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<Movie> getMovies(String s) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(s);

        JSONArray result = jsonObject.getJSONArray("results");

        for(int i=0;i<result.length();i++){

            Movie movie = new Movie();

            JSONObject current = result.getJSONObject(i);

            //Getting title
            String title = current.getString("original_title");

            //Getting poster path
            String poster = current.getString("poster_path");

            //Getting overview
            String overview = current.getString("overview");

            //Getting release date
            String release = current.getString("release_date");

            //Getting rating
            Double rating = current.getDouble("vote_average");

            //Setting values
            movie.setTitle(title);
            movie.setOverview(overview);
            movie.setPosterPath(poster);
            movie.setReleaseDate(release);
            movie.setVoterAverage(rating);
            movies.add(movie);


        }
        return movies;
    }

}
