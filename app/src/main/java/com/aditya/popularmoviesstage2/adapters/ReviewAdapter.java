package com.aditya.popularmoviesstage2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.popularmoviesstage2.R;
import com.aditya.popularmoviesstage2.models.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private final ArrayList<Review> array_reviews;
    private final Context context;

    public ReviewAdapter(Context context, ArrayList<Review> array_reviews ) {
        this.array_reviews = array_reviews;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Create a new view
        View v = LayoutInflater.from(parent.getContext ())
                .inflate (R.layout.layout_review_card, parent, false);

        ReviewAdapter.ViewHolder viewHolder = new ReviewAdapter.ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = array_reviews.get(position);

        holder.author.setText(review.getAuthor());
        holder.content.setText(review.getContent());

    }

    @Override
    public int getItemCount() {
        return array_reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView author,content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.tv_review_author);
            content = itemView.findViewById(R.id.tv_review_content);
        }
    }
}
