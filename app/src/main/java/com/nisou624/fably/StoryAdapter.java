package com.nisou624.fably;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    Context context;
    ArrayList<Story> stories;
    private final StoryUtils su;
    private OnFavoriteClickListener favoriteClickListener;

    public StoryAdapter(Context context, ArrayList<Story> histoires) {
        this.context = context;
        this.stories = histoires;
        su = new StoryUtils(context);
    }

    public interface OnFavoriteClickListener {
        void onFavoriteClick(Story story);
    }

    public void setOnFavoriteClickListener(OnFavoriteClickListener listener) {
        favoriteClickListener = listener;
    }

    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder holder, int position) {
        Story histoire = stories.get(position);
        holder.Title.setText(histoire.getTitle());
        holder.Author.setText(histoire.getAuthor());
        holder.Type.setText(histoire.getType());
        holder.Cover.setImageResource(histoire.getCover());

        // Set fav state from StoryManager
        boolean isFav = su.getFavState(String.valueOf(histoire.getId()));
        if (isFav) {
            holder.fav.setImageResource(R.drawable.favoris_y);
        } else {
            holder.fav.setImageResource(R.drawable.favoris_n);
        }

        holder.read.setOnClickListener(view -> {
            Intent intent = new Intent(context, StoryViewer.class);
            intent.putExtra("id", histoire.getId());
            intent.putExtra("listen", false);
            context.startActivity(intent);
        });

        holder.listen.setOnClickListener(view -> {
            Intent intent = new Intent(context, StoryViewer.class);
            intent.putExtra("id", histoire.getId());
            intent.putExtra("listen", true);
            context.startActivity(intent);
        });

        holder.fav.setOnClickListener(view -> {
            histoire.toggleFav();
            if (histoire.getFav()) {
                holder.fav.setImageResource(R.drawable.favoris_y);
            } else {
                holder.fav.setImageResource(R.drawable.favoris_n);
            }
            su.saveFavState(String.valueOf(histoire.getId()), histoire.getFav());

            // Invoke the favorite click listener
            if (favoriteClickListener != null) {
                favoriteClickListener.onFavoriteClick(histoire);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Cover;
        TextView Title, Author, Type;
        ImageButton read, listen, fav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Cover = itemView.findViewById(R.id.Story_cover);
            Title = itemView.findViewById(R.id.Story_title);
            Author = itemView.findViewById(R.id.Story_author);
            Type = itemView.findViewById(R.id.Story_type);
            read = itemView.findViewById(R.id.read);
            listen = itemView.findViewById(R.id.listen);
            fav = itemView.findViewById(R.id.favbtn);
        }
    }
}
