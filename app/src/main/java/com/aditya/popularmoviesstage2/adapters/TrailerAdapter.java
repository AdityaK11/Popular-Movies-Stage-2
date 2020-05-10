package com.aditya.popularmoviesstage2.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.popularmoviesstage2.R;
import com.aditya.popularmoviesstage2.models.Trailer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private final ArrayList<Trailer> array_trailers;
    private final Context context;

    public TrailerAdapter(Context context, ArrayList<Trailer> array_trailers) {
        this.array_trailers = array_trailers;
        this.context = context;
    }

    @NonNull
    @Override
    public TrailerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext ())
                .inflate (R.layout.layout_trailer_card, parent, false);

        TrailerAdapter.ViewHolder viewHolder = new TrailerAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.ViewHolder holder, final int position) {

        Trailer trailer = array_trailers.get(position);

        if(trailer.getSite().equals("YouTube")){
            Uri uri = Uri.parse("https://img.youtube.com/vi/" + trailer.getKey() + "/mqdefault.jpg");
            Picasso.with(context)
                    .load(uri)
                    .fit()
                    .error(R.color.red)
                    .placeholder(R.color.black)
                    .into(holder.thumbnail);
        }

        holder.name.setText(trailer.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Trailer video = array_trailers.get(position);
                if (video != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(context.getResources().getString(R.string.YOUTUBE_BASE_VIDEO_URL) + video.getKey()));
                    if (intent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "Error playing the video", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (array_trailers == null || array_trailers.size() == 0) {
            return -1;
        }

        return array_trailers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        ImageView thumbnail;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.iv_thumbnail);
            name = itemView.findViewById(R.id.tv_trailer_name);
        }
    }
}
