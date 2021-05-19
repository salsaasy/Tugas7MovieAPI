package com.example.tugas7.models.tvshow;

import com.google.gson.annotations.SerializedName;

public class AiringToday {
    private int id;

    @SerializedName("original_name")
    private String name;

    @SerializedName("poster_path")
    private String posterImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }
}
