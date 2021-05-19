package com.example.tugas7.models.tvshow;

import com.example.tugas7.helper.Genre;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShowDetail {
    private int id;
    private String overview;

    @SerializedName("name")
    private String title;

    @SerializedName("number_of_episodes")
    private String episodes;

    @SerializedName("poster_path")
    private String posterImage;

    @SerializedName("genres")
    private List<Genre> genres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

}
