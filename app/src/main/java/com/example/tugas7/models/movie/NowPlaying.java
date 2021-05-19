package com.example.tugas7.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NowPlaying {
    private int id;
    private String title;

    @SerializedName("poster_path")
    private String posterImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }
}
