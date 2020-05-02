package com.example.popularmovies_stage1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popularmovies_stage1.models.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_movie_details);

        TextView title = findViewById (R.id.tv_title);
        TextView rating = findViewById (R.id.tv_rating);
        TextView release = findViewById (R.id.tv_release);
        TextView overview = findViewById (R.id.tv_overview);
        ImageView poster = findViewById (R.id.iv_poster);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        Movie movie = intent.getParcelableExtra("movie");

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
    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
    }
}
