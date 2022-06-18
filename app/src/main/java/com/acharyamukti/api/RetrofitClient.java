package com.acharyamukti.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String BASE_URL = "https://theacharyamukti.com/";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;
    private final OkHttpClient.Builder builder = new OkHttpClient.Builder();

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

    }

    public static synchronized RetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public ApiInterface getApi() {
        return retrofit.create(ApiInterface.class);

    }
}
