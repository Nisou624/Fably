package com.nisou624.fably;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class StoryUtils {

    private final SharedPreferences sharedPreferences;

    public StoryUtils(Context context) {
        sharedPreferences = context.getSharedPreferences("StoryPreferences", Context.MODE_PRIVATE);
    }

    public boolean getFavState(String storyId) {
        return sharedPreferences.getBoolean(storyId, false);
    }

    public void saveFavState(String storyId, boolean isFav) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(storyId, isFav);
        editor.apply();
    }


}
