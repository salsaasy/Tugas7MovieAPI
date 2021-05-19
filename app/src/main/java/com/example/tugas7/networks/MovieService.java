package com.example.tugas7.networks;

import com.example.tugas7.models.movie.MovieDetail;
import com.example.tugas7.models.tvshow.AiringTodayResponse;
import com.example.tugas7.models.movie.NowPlayingResponse;
import com.example.tugas7.models.tvshow.TVShowDetail;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MovieService {
    @GET("movie/now_playing")
    Call<NowPlayingResponse> getNowPlaying(
            @QueryMap Map<String, String> options);

    @GET("movie/{movie_id}")
    Call<MovieDetail> getMovieDetail(
            @Path("movie_id") int movieId,
            @QueryMap Map<String, String> options);

    @GET("tv/airing_today")
    Call<AiringTodayResponse> getAiringToday(
            @QueryMap Map<String, String> options);

    @GET("tv/{tv_id}")
    Call<TVShowDetail> getTVShowDetail(
            @Path("tv_id") int movieId,
            @QueryMap Map<String, String> options);
}
