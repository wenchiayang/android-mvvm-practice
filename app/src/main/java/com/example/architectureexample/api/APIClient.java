package com.example.architectureexample.api;

import android.content.Context;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static APIClient instance;
    private Retrofit retrofit;
    private static final String API_BASE_URL = "https://jsonplaceholder.typicode.com";

    public static APIClient getInstance() {
        if (instance == null) {
            synchronized(APIClient.class) {
                instance = new APIClient();
            }
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
