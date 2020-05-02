package com.example.popularmovies_stage1.api;

import com.example.popularmovies_stage1.models.Movie;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {
    String Base_url = "https://api.themoviedb.org/3/movie/";
    String API_KEY = "[YOUR_API_KEY]";

    @GET("popular")
    Call<ResponseBody> getPopular(
            @Query("api_key") String api_key
    );

    @GET("top_rated")
    Call<ResponseBody> getTopRated(
            @Query("api_key") String api_key
    );
}
