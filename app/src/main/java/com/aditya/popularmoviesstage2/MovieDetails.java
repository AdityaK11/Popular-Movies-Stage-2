package com.aditya.popularmoviesstage2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.popularmoviesstage2.R;
import com.aditya.popularmoviesstage2.adapters.ReviewAdapter;
import com.aditya.popularmoviesstage2.adapters.TrailerAdapter;
import com.aditya.popularmoviesstage2.api.ApiInterface;
import com.aditya.popularmoviesstage2.models.Movie;
import com.aditya.popularmoviesstage2.models.Review;
import com.aditya.popularmoviesstage2.models.Trailer;
import com.aditya.popularmoviesstage2.utils.JsonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetails extends AppCompatActivity {

    ApiInterface api;
    ArrayList<Trailer> trailers = new ArrayList<>();
    ArrayList<Review> reviews = new ArrayList<>();
    RecyclerView recyclerViewTrailers,recyclerViewReviews;
    TrailerAdapter trailerAdapter;
    ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_movie_details);

        TextView title = findViewById (R.id.tv_title);
        TextView rating = findViewById (R.id.tv_rating);
        TextView release = findViewById (R.id.tv_release);
        TextView overview = findViewById (R.id.tv_overview);
        ImageView poster = findViewById (R.id.iv_poster);

        recyclerViewTrailers = findViewById(R.id.rv_trailers);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewTrailers.setLayoutManager(layoutManager);

        recyclerViewReviews = findViewById(R.id.rv_reviews);
        RecyclerView.LayoutManager layoutManagerReview = new LinearLayoutManager(this);
        recyclerViewReviews.setLayoutManager(layoutManagerReview);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        Movie movie = intent.getParcelableExtra("movie");

        //Initialising Retrofit object
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ApiInterface.class);

        // Setting title
        title.setText(movie.getTitle());

        // Setting rating
        rating.setText (movie.getVoterAverage () + " / 10");

        // Setting poster
        Picasso.with(this)
                .load(movie.getPosterPath())
                .fit()
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(poster);

        // Setting overview
        overview.setText (movie.getOverview ());

        // Setting release
        release.setText (movie.getReleaseDate());

        getTrailers(movie);
        getReviews(movie);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
    }

    void getTrailers(Movie movie){
        final Call<ResponseBody> call = api.getMovieTrailers(movie.getId(),ApiInterface.API_KEY);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    trailers = JsonUtils.getTrailers(response.body().string());
                } catch (Exception e) {
                    Log.d("---error---",e.getMessage());
                }
                trailerAdapter = new TrailerAdapter(MovieDetails.this, trailers);
                recyclerViewTrailers.setAdapter(trailerAdapter);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("---failure---",t.getMessage());
            }
        });
    }

    private void getReviews(Movie movie) {
        final Call<ResponseBody> call = api.getMovieReviews(movie.getId(),ApiInterface.API_KEY);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    reviews = JsonUtils.getReviews(response.body().string());
                    Log.d("---debug---",response.body().string());
                } catch (Exception e) {
                    Log.d("---error---",e.getMessage());
                }
                reviewAdapter = new ReviewAdapter(MovieDetails.this, reviews);
                recyclerViewReviews.setAdapter(reviewAdapter);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("---failure---",t.getMessage());
            }
        });
    }
}
