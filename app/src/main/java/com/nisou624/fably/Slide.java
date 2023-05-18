package com.nisou624.fably;

public class Slide {
    private int image;
    private String text;

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Slide(int image, String text) {
        this.image = image;
        this.text = text;
    }
}
