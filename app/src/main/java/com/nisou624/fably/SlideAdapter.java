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

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder>{

    private final ArrayList<Slide> slides;
    private final ViewPager2 Slider;

    public SlideAdapter(ArrayList<Slide> slides, ViewPager2 slider) {
        this.slides = slides;
        Slider = slider;
    }


    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        holder.setBg(slides.get(position));
        holder.setText(slides.get(position));
        if(position == slides.size()- 2){
            Slider.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return slides.size();
    }

    static class SlideViewHolder extends RecyclerView.ViewHolder {
        private final ImageView bg;
        private final TextView txt;

        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            bg = itemView.findViewById(R.id.slider_image);
            txt = itemView.findViewById(R.id.slider_text);
        }

        void setBg(Slide slide){
            bg.setImageResource(slide.getImage());
        }

        void setText(Slide slide){
            txt.setText(slide.getText());
        }
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {

            slides.addAll(slides);
            notifyDataSetChanged();

        }
    };

}
