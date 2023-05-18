package com.nisou624.fably;

import android.app.Application;
import android.content.res.Resources;

import java.util.ArrayList;

public class Histoire extends Application {
    private static ArrayList<Story> histoires = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        setUpStories(histoires);
    }

    public void importImages(Story histoire) {
        ArrayList<Integer> images = new ArrayList<>();
        int id = histoire.getId();
        int taille = histoire.getNbPages() - 1;
        String prefix = "story" + id + "_p";
        Resources resources = getResources();
        String packageName = getPackageName();
        for (int i = 1; i <= taille; i++) {
            int res = resources.getIdentifier(prefix + i, "drawable", packageName);
            images.add(res);
        }
        histoire.setImages(images);
    }

    public void importText(Story histoire) {
        String name = "story_" + histoire.getId();
        int textId = getResources().getIdentifier(name, "array", getPackageName());
        String[] text = getResources().getStringArray(textId);
        histoire.setText(text);
        histoire.setNbPages(text.length);
    }

    public void importAudio(Story histoire) {
        ArrayList<Integer> audios = new ArrayList<>();
        int id = histoire.getId();
        int taille = histoire.getNbPages();
        String prefix = "story" + id + "_audio";
        Resources resources = getResources();
        String packageName = getPackageName();
        for (int i = 1; i <= taille; i++) {
            int res = resources.getIdentifier(prefix + i, "raw", packageName);
            audios.add(res);
        }
        histoire.setAudios(audios);
    }

    public void setUpStories(ArrayList<Story> histoires) {
        Resources resources = getResources();
        String[] storyNames = resources.getStringArray(R.array.Story_names);
        String author = getString(R.string.author);
        String type = getString(R.string.type);

        for (int i = 1; i <= storyNames.length; i++) {
            String coverStr = "story" + i + "_cover";
            int cover = resources.getIdentifier(coverStr, "drawable", getPackageName());
            Story histoire = new Story(i, storyNames[i - 1], author, type, cover, false);
            importText(histoire);
            importImages(histoire);
            importAudio(histoire);
            histoires.add(histoire);
        }
    }

    public static ArrayList<Story> getHistoires() {
        return histoires;
    }

    public static void setHistoires(ArrayList<Story> histoires) {
        Histoire.histoires = histoires;
    }
}
