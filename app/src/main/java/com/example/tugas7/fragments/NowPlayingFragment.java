package com.example.tugas7.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tugas7.R;
import com.example.tugas7.activities.MovieDetailActivity;
import com.example.tugas7.adapters.movie.NowPlayingAdapter;
import com.example.tugas7.helper.OnItemClickListener;
import com.example.tugas7.models.movie.NowPlayingResponse;
import com.example.tugas7.networks.Const;
import com.example.tugas7.networks.GetRetrofit;
import com.example.tugas7.networks.MovieService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingFragment extends Fragment implements OnItemClickListener<Integer> {
    private RecyclerView rvNowPlaying;
    private NowPlayingAdapter nowPlayingAdapter;

    public NowPlayingFragment() {
    }

    public static NowPlayingFragment newInstance() {
        NowPlayingFragment fragment = new NowPlayingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        rvNowPlaying = view.findViewById(R.id.rv_now_playing);
        rvNowPlaying.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rvNowPlaying.setHasFixedSize(true);

        nowPlayingAdapter = new NowPlayingAdapter();
        nowPlayingAdapter.setClickListener(this);
        load();
        return view;
    }

    private void load() {
        MovieService service = GetRetrofit.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("api_key", Const.API_KEY);
        params.put("language", Const.LANGUAGE);
        params.put("page", Const.PAGE);
        Call<NowPlayingResponse> call = service.getNowPlaying(params);

        call.enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(Call<NowPlayingResponse> call, Response<NowPlayingResponse> response) {
                if(response.isSuccessful() && response.body().getNowPlayings() != null){
                    nowPlayingAdapter.setNowPlayingList(response.body().getNowPlayings());
                    rvNowPlaying.setAdapter(nowPlayingAdapter);
                } else{
                    Log.d(Const.APIERROR, "error");
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
                Log.d(Const.APIERROR, "error");
            }
        });
    }

    @Override
    public void onClick(Integer integer) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        if(integer != null){
            intent.putExtra("TV ID", integer);
            startActivity(intent);
        }
    }
}