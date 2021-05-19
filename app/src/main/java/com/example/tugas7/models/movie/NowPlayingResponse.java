package com.example.tugas7.models.movie;

import com.example.tugas7.models.movie.NowPlaying;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingResponse {

    @SerializedName("results")
    @Expose
    private List<NowPlaying> nowPlayings;

    public List<NowPlaying> getNowPlayings() {
        return nowPlayings;
    }

}
