package com.example.h071211024_finalmobile.data;

import com.example.h071211024_finalmobile.data.model.TvResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TvShowService {
    @GET("tv/on_the_air")
    Call<TvResponse> getAiringTodayTV(
            @Query("api_key") String apiKey
    );
}
