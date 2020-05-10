package com.aditya.popularmoviesstage2.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    String Base_url = "https://api.themoviedb.org/3/movie/";
    String API_KEY = "64259fa2e2725d5b05cf1f713d5af22f";

    @GET("popular")
    Call<ResponseBody> getPopular(
            @Query("api_key") String api_key
    );

    @GET("top_rated")
    Call<ResponseBody> getTopRated(
            @Query("api_key") String api_key
    );

    @GET("{movie_id}/reviews")
    Call<ResponseBody> getMovieReviews(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("{movie_id}/videos")
    Call<ResponseBody> getMovieTrailers(@Path("movie_id") int movieId, @Query("api_key") String apiKey);
}
