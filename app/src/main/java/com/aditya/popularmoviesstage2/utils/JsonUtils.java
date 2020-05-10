package com.aditya.popularmoviesstage2.utils;

import android.util.Log;

import com.aditya.popularmoviesstage2.models.Movie;
import com.aditya.popularmoviesstage2.models.Review;
import com.aditya.popularmoviesstage2.models.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<Movie> getMovies(String s) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(s);

        JSONArray results = jsonObject.getJSONArray("results");

        for(int i=0;i<results.length();i++){

            Movie movie = new Movie();

            JSONObject current = results.getJSONObject(i);

            //Getting id
            Integer id = current.getInt("id");

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
            movie.setId(id);
            movie.setTitle(title);
            movie.setOverview(overview);
            movie.setPosterPath(poster);
            movie.setReleaseDate(release);
            movie.setVoterAverage(rating);
            movies.add(movie);


        }
        return movies;
    }

    public static ArrayList<Trailer> getTrailers(String s) throws JSONException {
        ArrayList<Trailer> trailers = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(s);
        JSONArray results = jsonObject.getJSONArray("results");

        for(int i=0;i<results.length();i++){
            Trailer trailer = new Trailer();

            JSONObject current = results.getJSONObject(i);

            //Getting key
            String key = current.getString("key");

            //Getting name
            String name = current.getString("name");

            //Getting site
            String site = current.getString("site");

            //Setting values
            trailer.setKey(key);
            trailer.setName(name);
            trailer.setSite(site);

            trailers.add(trailer);

        }

        return trailers;

    }

    public static ArrayList<Review> getReviews(String s) throws JSONException{

        ArrayList<Review> reviews = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(s);

        JSONArray results = jsonObject.getJSONArray("results");

        for(int i=0;i<results.length();i++){

            Review review = new Review();

            JSONObject current = results.getJSONObject(i);

            //Getting author
            String author = current.getString("author");

            //Getting content
            String content = current.getString("content");

            review.setAuthor(author);
            review.setContent(content);

            reviews.add(review);

        }

        return reviews;
    }

}
