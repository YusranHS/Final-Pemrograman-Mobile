//package com.example.h071211024_finalmobile.data;
//
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class ApiConfig {
//    private static final String BASE_URL = "https://api.themoviedb.org/";
//    private static final String API_KEY = "dad1cd55d3f6d09536f1c6bde1fe8d07";
//
//    public static  getApiService() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit.create(ApiService.class);
//    }
//
//    public static String getApiKey() {
//        return API_KEY;
//    }
//}
//
