package com.example.popularmovies_stage1.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies_stage1.MovieDetails;
import com.example.popularmovies_stage1.R;
import com.example.popularmovies_stage1.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private final ArrayList<Movie> array_movies;
    private final Context context;

    public MovieAdapter(Context context, ArrayList<Movie> mMovies) {
        this.array_movies = mMovies;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.image_view);
        }
    }
    @NonNull
    @Override
    // Create new views (Invoked by the Layout Manager)
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Create a new view
        View v = LayoutInflater.from(parent.getContext ())
                .inflate (R.layout.layout_movie_card, parent, false);

        ViewHolder viewHolder = new ViewHolder (v);
        return viewHolder;
    }

    @Override
    // Replace the contents of a view (Invoked by the layout manager)
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Picasso.with(context)
                .load(array_movies.get(position).getPosterPath())
                .fit()
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetails.class);
                intent.putExtra("movie", array_movies.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (array_movies == null || array_movies.size() == 0) {
            return -1;
        }

        return array_movies.size();
    }
}
