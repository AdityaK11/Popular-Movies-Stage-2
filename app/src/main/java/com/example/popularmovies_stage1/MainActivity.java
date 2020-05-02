package com.example.popularmovies_stage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.popularmovies_stage1.adapters.MovieAdapter;
import com.example.popularmovies_stage1.api.ApiInterface;
import com.example.popularmovies_stage1.models.Movie;
import com.example.popularmovies_stage1.utils.JsonUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Movie> movies;
    MovieAdapter movieAdapter;

    ApiInterface api;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        recyclerView = findViewById (R.id.rv_movies);

        // Using a Grid Layout Manager
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        TextView offline = findViewById(R.id.tv_no_internet);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ApiInterface.class);

        if(isOnline()){
            recyclerView.setVisibility(View.VISIBLE);
            offline.setVisibility(View.GONE);
        }else{
            recyclerView.setVisibility(View.GONE);
            offline.setVisibility(View.VISIBLE);
        }


        // SPINNER METHODS
        Spinner sortSpinner = findViewById(R.id.spinner_sort);
        final int currentSelection = sortSpinner.getSelectedItemPosition();

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (currentSelection == i) {
                    getPopular();
                } else {
                    // If top rated was selected
                    getTopRated();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    void getPopular(){
        final Call<ResponseBody> call = api.getPopular(ApiInterface.API_KEY);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    movies = JsonUtils.getMovies(response.body().string());
                } catch (Exception e) {
                    Log.d("---error---",e.getMessage());
                }
                movieAdapter = new MovieAdapter(getApplicationContext(), movies);
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    void getTopRated(){
        final Call<ResponseBody> call = api.getTopRated(ApiInterface.API_KEY);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    movies = JsonUtils.getMovies(response.body().string());
                } catch (Exception e) {
                    Log.d("---error---",e.getMessage());
                }
                movieAdapter = new MovieAdapter(getApplicationContext(), movies);
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

}

