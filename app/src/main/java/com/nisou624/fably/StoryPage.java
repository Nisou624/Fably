package com.nisou624.fably;

import java.util.ArrayList;

public class StoryPage {
    private final int image;
    private String text;
    private final int audio;
    private boolean audioPlayed;
    public int getImage() {
        return image;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getAudio() {
        return audio;
    }

    public StoryPage(int image, String text, int audio) {
        this.image = image;
        this.text = text;
        this.audio = audio;
        this.audioPlayed = false;
    }

    public static void createStoryPage(ArrayList<StoryPage> pages,  Story story){
        int taille = story.getNbPages();
        String[] page_texts = story.getPages();
        ArrayList<Integer> images = story.getImages();
        ArrayList<Integer> audios = story.getAudios();
        for(int i = 0; i < taille; i++){
            if (i == 0) {
                pages.add(new StoryPage(-1, page_texts[i], audios.get(i)));
            } else {
                pages.add(new StoryPage(images.get(i - 1), page_texts[i], audios.get(i)));
            }
        }
    }
}
