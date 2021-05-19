package com.example.tugas7.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tugas7.R;
import com.example.tugas7.models.movie.MovieDetail;
import com.example.tugas7.models.tvshow.TVShowDetail;
import com.example.tugas7.networks.Const;
import com.example.tugas7.networks.GetRetrofit;
import com.example.tugas7.networks.MovieService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowDetailActivity extends AppCompatActivity {
    ImageView ivPoster;
    TextView tvTitle, tvNumOfEpisodes, tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        ivPoster = findViewById(R.id.iv_tvshow_poster);
        tvTitle = findViewById(R.id.tv_tvshow_title);
        tvNumOfEpisodes = findViewById(R.id.tv_tvshow_number_of_episodes);
        tvOverview = findViewById(R.id.tv_tvshow_overview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Integer tvId = getIntent().getIntExtra("TV ID", 0);
        load(tvId);
    }

    private void load(Integer id) {
        MovieService service = GetRetrofit.getInstance();

        Map<String, String> params = new HashMap<>();
        params.put("api_key", Const.API_KEY);
        params.put("language", Const.LANGUAGE);
        Call<TVShowDetail> call = service.getTVShowDetail(id, params);

        call.enqueue(new Callback<TVShowDetail>() {
            @Override
            public void onResponse(Call<TVShowDetail> call, Response<TVShowDetail> response) {
                if(response.isSuccessful() && response.body() != null){
                    TVShowDetail tvShowDetail =  response.body();
                    tvTitle.setText(tvShowDetail.getTitle());
                    tvNumOfEpisodes.setText(tvShowDetail.getEpisodes());
                    String uri = Const.IMAGEBASEURL + tvShowDetail.getPosterImage();
                    Glide.with(TVShowDetailActivity.this).load(uri).into(ivPoster);
                    getSupportActionBar().setTitle(tvTitle.getText());
                    tvOverview.setText(tvShowDetail.getOverview());
                } else{
                    Log.d(Const.APIERROR, "error");
                }
            }

            @Override
            public void onFailure(Call<TVShowDetail> call, Throwable t) {
                Log.d(Const.APIERROR, t.getMessage());
            }
        });
    }
}