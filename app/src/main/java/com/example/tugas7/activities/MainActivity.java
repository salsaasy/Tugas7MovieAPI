package com.example.tugas7.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tugas7.R;
import com.example.tugas7.fragments.AiringTodayFragment;
import com.example.tugas7.fragments.NowPlayingFragment;
import com.example.tugas7.fragments.UpcomingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Map<Integer, Fragment> fragmentMap;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentMap = new HashMap<>();
        bottomNav = findViewById(R.id.bottom_nav_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main,
                    new NowPlayingFragment()).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        fragmentMap.put(R.id.item_movie, NowPlayingFragment.newInstance());
        fragmentMap.put(R.id.item_tv_show, AiringTodayFragment.newInstance());
        fragmentMap.put(R.id.item_upcoming, UpcomingFragment.newInstance());

        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.item_movie);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = fragmentMap.get(item.getItemId());
            assert selectedFragment != null;

            switch (item.getItemId()){
                case R.id.item_upcoming:
                    getSupportActionBar().setTitle("Upcoming");
                    break;
                case R.id.item_tv_show:
                    getSupportActionBar().setTitle("TV Show");
                    break;

                case R.id.item_movie:
                    getSupportActionBar().setTitle("Movie");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main, selectedFragment).commit();
            return true;
        }
    };
}