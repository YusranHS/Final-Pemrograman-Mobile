//package com.example.h071211024_finalmobile.data;
//
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class ApiConfig {
//    private static final String BASE_URL = "https://api.themoviedb.org/";
//    private static final String API_KEY = "a9e48b0b228099847dc7187ed9bf76b3";
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
