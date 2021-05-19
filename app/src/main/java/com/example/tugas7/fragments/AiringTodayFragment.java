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
import com.example.tugas7.activities.TVShowDetailActivity;
import com.example.tugas7.adapters.tvshow.AiringTodayAdapter;
import com.example.tugas7.helper.OnItemClickListener;
import com.example.tugas7.models.tvshow.AiringTodayResponse;
import com.example.tugas7.networks.Const;
import com.example.tugas7.networks.GetRetrofit;
import com.example.tugas7.networks.MovieService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AiringTodayFragment extends Fragment implements OnItemClickListener<Integer> {
    private RecyclerView rvAiringToday;
    private AiringTodayAdapter airingTodayAdapter;

    public AiringTodayFragment() {
    }

    public static AiringTodayFragment newInstance() {
        AiringTodayFragment fragment = new AiringTodayFragment();
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
        View view = inflater.inflate(R.layout.fragment_airing_today, container, false);

        rvAiringToday = view.findViewById(R.id.rv_airing_today);
        rvAiringToday.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvAiringToday.setHasFixedSize(true);

        airingTodayAdapter = new AiringTodayAdapter();
        airingTodayAdapter.setClickListener(this);
        load();
        return view;
    }

    private void load() {
        MovieService service = GetRetrofit.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("api_key", Const.API_KEY);
        params.put("language", Const.LANGUAGE);
        params.put("page", Const.PAGE);
        Call<AiringTodayResponse> call = service.getAiringToday(params);

        call.enqueue(new Callback<AiringTodayResponse>() {
            @Override
            public void onResponse(Call<AiringTodayResponse> call, Response<AiringTodayResponse> response) {
                if(response.isSuccessful() && response.body().getAiringTodays() != null){
                    airingTodayAdapter.setAiringTodayList(response.body().getAiringTodays());
                    rvAiringToday.setAdapter(airingTodayAdapter);
                } else{
                    Log.d(Const.APIERROR, "error");
                }
            }

            @Override
            public void onFailure(Call<AiringTodayResponse> call, Throwable t) {
                Log.d(Const.APIERROR, "error");
            }
        });
    }

    @Override
    public void onClick(Integer integer) {
        Intent intent = new Intent(getActivity(), TVShowDetailActivity.class);
        if (integer != null){
            intent.putExtra("TV ID", integer);
            startActivity(intent);
        }
    }
}