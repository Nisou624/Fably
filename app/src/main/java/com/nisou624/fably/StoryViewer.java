package com.nisou624.fably;

import static com.nisou624.fably.StoryPage.createStoryPage;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
public class StoryViewer extends AppCompatActivity {
    Histoire his;
    ArrayList<Story> stories;
    ViewPager2 viewPager;
    ArrayList<StoryPage> pages = new ArrayList<>();
    MediaPlayer mp;
    private static final String STATE_CURRENT_ITEM = "currentItem";
    private int currentlyPlayingPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        his = (Histoire) this.getApplication();
        Intent intent = getIntent();
        stories = Histoire.getHistoires();
        int pos = intent.getIntExtra("id", 1);
        boolean listen = intent.getBooleanExtra("listen", true);
        Story story = stories.get(pos - 1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_story_viewer);
        viewPager = findViewById(R.id.pages_container);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE);
        createStoryPage(pages, story);
        PageAdapter pageAdapter = new PageAdapter(pages);
        viewPager.setAdapter(pageAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE);
                if (listen) {
                    handleAudioPlayback(position);
                }
            }
        });
        if (savedInstanceState != null) {
            int currentItem = savedInstanceState.getInt(STATE_CURRENT_ITEM, 0);
            viewPager.post(() -> viewPager.setCurrentItem(currentItem, false));
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_CURRENT_ITEM, viewPager.getCurrentItem());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int currentItem = savedInstanceState.getInt(STATE_CURRENT_ITEM, 0);
        viewPager.setCurrentItem(currentItem, false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAudioPlayback();
    }

    private void handleAudioPlayback(int position) {
        if (mp != null && mp.isPlaying() && position != currentlyPlayingPosition) {
            stopAudioPlayback();
        }

        int audioId = pages.get(position).getAudio();
        if (audioId != -1) {
            if (mp == null || position != currentlyPlayingPosition) {
                stopAudioPlayback();
                mp = MediaPlayer.create(this, audioId);
                mp.setOnCompletionListener(mediaPlayer -> {
                    stopAudioPlayback();
                    if (position < pages.size() - 1) {
                        viewPager.setCurrentItem(position + 1, true); // Animate to the next page
                    }
                });
                mp.start();
                currentlyPlayingPosition = position;
            }
        }
    }

    private void stopAudioPlayback() {
        if (mp != null && mp.isPlaying()) {
            mp.stop();
            mp.release();
            mp = null;
            currentlyPlayingPosition = -1;
        }
    }
}
