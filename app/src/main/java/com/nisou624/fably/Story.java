package com.nisou624.fably;


import java.util.ArrayList;

public class Story{
    private final int Id;
    private final String Title;
    private final String Author;
    private final String type;
    private int nbPages;
    private final int Cover;
    private String[] pages;
    private ArrayList<Integer> audios;
    private ArrayList<Integer> images;
    private Boolean fav;

    public Story(int id, String name,  String author, String type, int cover, boolean fav){
        Id = id;
        Title = name;
        Author = author;
        this.type = type;
        Cover = cover;
        this.fav = fav;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getType() {
        return type;
    }

    public int getNbPages() {
        return nbPages;
    }

    public int getCover() {
        return Cover;
    }

    public String[] getPages() {
        return pages;
    }

    public ArrayList<Integer> getAudios() {
        return audios;
    }

    public ArrayList<Integer> getImages() {
        return images;
    }

    public Boolean getFav() {
        return fav;
    }

    public void setFav(Boolean fav) {
        this.fav = fav;
    }

    public void toggleFav(){
        setFav( !this.getFav() );
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }

    public void setText(String[] text) {
        this.pages = text;
    }
    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public void setAudios(ArrayList<Integer> audios) {
        this.audios = audios;
    }

}


