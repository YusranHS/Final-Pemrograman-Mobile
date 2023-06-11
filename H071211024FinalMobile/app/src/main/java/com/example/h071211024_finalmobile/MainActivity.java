package com.example.h071211024_finalmobile;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.h071211024_finalmobile.R;

public class MainActivity extends AppCompatActivity {
    ImageView movie_iv, series_iv, favorites_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movie_iv = findViewById(R.id.movie_iv);
        series_iv = findViewById(R.id.series_iv);
        favorites_iv = findViewById(R.id.favorite_iv);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, new MovieFragment(),
                        MovieFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();

        movie_iv.setOnClickListener(btn -> {
            Drawable drawable = movie_iv.getDrawable();
            drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            movie_iv.setImageDrawable(drawable);
            // Wait for 200 milliseconds
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawable.clearColorFilter();
                    movie_iv.setImageDrawable(drawable);
                }
            }, 200);
            MovieFragment movieFragment = new MovieFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, movieFragment,
                            MovieFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });

        series_iv.setOnClickListener(btn -> {
            Drawable drawable = series_iv.getDrawable();
            drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            series_iv.setImageDrawable(drawable);
            // Wait for 200 milliseconds
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawable.clearColorFilter();
                    series_iv.setImageDrawable(drawable);
                }
            }, 200);
            TvShowFragment tvShow = new TvShowFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, tvShow,
                            TvShowFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });

        favorites_iv.setOnClickListener(btn -> {
            Drawable drawable = favorites_iv.getDrawable();
            drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            favorites_iv.setImageDrawable(drawable);
            // Wait for 200 milliseconds
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawable.clearColorFilter();
                    favorites_iv.setImageDrawable(drawable);
                }
            }, 200);
            FavoriteFragment favorite = new FavoriteFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, favorite,
                            FavoriteFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });
    }

}
