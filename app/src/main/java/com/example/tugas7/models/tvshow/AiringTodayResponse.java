package com.example.tugas7.models.tvshow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AiringTodayResponse {
    @SerializedName("results")
    @Expose
    List<AiringToday> airingTodays;

    public List<AiringToday> getAiringTodays() {
        return airingTodays;
    }
}
