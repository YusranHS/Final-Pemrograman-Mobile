package com.example.h071211024_finalmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.h071211024_finalmobile.data.model.Favorite;
import com.example.h071211024_finalmobile.data.model.Movie;
import com.example.h071211024_finalmobile.data.model.Tv;
import com.example.h071211024_finalmobile.database.DatabaseHelper;

public class Film_Detail extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ImageButton backButton, favoriteButton;
    private ImageView backdropImageView, posterImageView, typeImageView, filledFavorite;
    private TextView titleTextView, releaseDateTextView, ratingTextView, synopsisTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        backdropImageView = findViewById(R.id.iv_backdrop);
        backButton = findViewById(R.id.btn_back);
        favoriteButton = findViewById(R.id.btn_favorite);
        filledFavorite = findViewById(R.id.btn_favoritefilled);
        posterImageView = findViewById(R.id.iv_poster);
        titleTextView = findViewById(R.id.tv_title);
        releaseDateTextView = findViewById(R.id.tv_release_date);
        ratingTextView = findViewById(R.id.tv_rating);
        typeImageView = findViewById(R.id.iv_type);
        synopsisTextView = findViewById(R.id.tv_synopsis);

        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent.getParcelableExtra("movie") != null) {
            Movie movie = intent.getParcelableExtra("movie");
            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getBackdropUrl();
            titleTextView.setText(movie.getTitle());
            ratingTextView.setText(movie.getVoteAverage().toString());
            Glide.with(this)
                    .load(posterUrl)
                    .into(posterImageView);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(backdropImageView);
            synopsisTextView.setText(movie.getOverview());

            if (!dbHelper.isMovieInFavorites(movie.getTitle())) {
                filledFavorite.setVisibility(View.INVISIBLE);
            } else {
                filledFavorite.setVisibility(View.VISIBLE);
            }

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dbHelper.isMovieInFavorites(movie.getTitle())) {
                        addMovieToFavorites(movie.getId(), movie.getOverview(), posterUrl, movie.getReleaseDate(), movie.getTitle(), movie.getVoteAverage(), backdropUrl);
                        filledFavorite.setVisibility(View.VISIBLE);
                    } else {
                        deleteMovieToFavorites(movie.getTitle());
                        filledFavorite.setVisibility(View.INVISIBLE);
                    }
                }
            });


        } else if (intent.getParcelableExtra("show") != null) {
            Tv show = intent.getParcelableExtra("show");
            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + show.getPosterUrl();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + show.getBackdropUrl();
            titleTextView.setText(show.getName());
            ratingTextView.setText(show.getVoteAverage().toString());
            Glide.with(this)
                    .load(posterUrl)
                    .into(posterImageView);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(backdropImageView);
            synopsisTextView.setText(show.getOverview());

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dbHelper.isMovieInFavorites(show.getName())) {
                        addMovieToFavorites(show.getId(), show.getOverview(), posterUrl, show.getName(), show.getName(), show.getVoteAverage(), backdropUrl);
                        filledFavorite.setVisibility(View.VISIBLE);
                    } else {
                        deleteMovieToFavorites(show.getName());
                        filledFavorite.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
        else {
            Favorite favorite = intent.getParcelableExtra("favorite");
            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + favorite.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + favorite.getBackdropUrl();
            titleTextView.setText(favorite.getTitle());
            ratingTextView.setText(favorite.getVoteAverage().toString());
            Glide.with(this)
                    .load(posterUrl)
                    .into(posterImageView);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(backdropImageView);
            synopsisTextView.setText(favorite.getOverview());

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dbHelper.isMovieInFavorites(favorite.getTitle())) {
                        addMovieToFavorites(favorite.getId(), favorite.getOverview(), posterUrl, favorite.getTitle(), favorite.getTitle(), favorite.getVoteAverage(), backdropUrl);
                        filledFavorite.setVisibility(View.VISIBLE);
                    } else {
                        deleteMovieToFavorites(favorite.getTitle());
                        filledFavorite.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }

    private void addMovieToFavorites(int id, String overview, String posterUrl, String releaseDate, String title, double voteAverage, String backdropUrl) {
        Movie movie = new Movie(id, overview, posterUrl, releaseDate, title, voteAverage, backdropUrl);
        long result = dbHelper.insertMovie(movie);
        if (result != -1) {
            Toast.makeText(this, "Movie has been added to favorite", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to favorite movie", Toast.LENGTH_SHORT).show();
        }
    }
    private void deleteMovieToFavorites(String nama) {
        long result = dbHelper.deleteMovie(nama);
        if (result != -1) {
            Toast.makeText(this, "Movie has been removed from favorite", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to remove movie", Toast.LENGTH_SHORT).show();
        }
    }
}
