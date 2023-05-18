package com.nisou624.fably;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
public class PageAdapter extends RecyclerView.Adapter<PageAdapter.PageViewHolder> {
    private final ArrayList<StoryPage> pages;

    public PageAdapter(ArrayList<StoryPage> pages) {
        this.pages = pages;
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PageViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.page, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int position) {
        StoryPage page = pages.get(position);

        if (position == 0) {
            holder.PageText.setText(page.getText());
            holder.PageImage.setVisibility(View.GONE);
        } else {
            holder.PageText.setText(page.getText());
            holder.PageImage.setVisibility(View.VISIBLE);
            holder.PageImage.setImageResource(page.getImage());
        }
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    static class PageViewHolder extends RecyclerView.ViewHolder {
        private final TextView PageText;
        private final ImageView PageImage;

        PageViewHolder(@NonNull View itemView) {
            super(itemView);
            PageText = itemView.findViewById(R.id.page_txt);
            PageImage = itemView.findViewById(R.id.page_bg);
        }
    }
}
