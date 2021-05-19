package com.example.tugas7.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tugas7.R;
import com.example.tugas7.models.movie.MovieDetail;
import com.example.tugas7.networks.Const;
import com.example.tugas7.networks.GetRetrofit;
import com.example.tugas7.networks.MovieService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.KeyCharacterMap.load;

public class MovieDetailActivity extends AppCompatActivity {
    ImageView ivPoster;
    TextView tvTitle, tvNumOfEpisodes, tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ivPoster = findViewById(R.id.iv_movie_poster);
        tvTitle = findViewById(R.id.tv_movie_title);
        tvNumOfEpisodes = findViewById(R.id.tv_movie_number_of_episodes);
        tvOverview = findViewById(R.id.tv_movie_overview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Integer tvId = getIntent().getIntExtra("TV ID", 0);
        load(tvId);
    }

    private void load(Integer id){
        MovieService service = GetRetrofit.getInstance();

        Map<String, String> params = new HashMap<>();
        params.put("api_key", Const.API_KEY);
        params.put("language", Const.LANGUAGE);
        Call<MovieDetail> call = service.getMovieDetail(id, params);

        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if(response.isSuccessful() && response.body() != null){
                    MovieDetail movieDetail = response.body();
                    tvTitle.setText(movieDetail.getTitle());
                    String getGenre = "";
                    for (int i = 0; i < movieDetail.getGenres().size(); i++){
                            getGenre += movieDetail.getGenres().get(i).getName() + ", ";

                    }
                    tvNumOfEpisodes.setText(getGenre);
                    String uri = Const.IMAGEBASEURL + movieDetail.getPosterImage();
                    Glide.with(MovieDetailActivity.this).load(uri).into(ivPoster);
                    getSupportActionBar().setTitle(tvTitle.getText());
                    tvOverview.setText(movieDetail.getOverview());
                } else{
                    Log.d(Const.APIERROR, "error");
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.d(Const.APIERROR, "error");
            }
        });
    }
}
