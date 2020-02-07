package com.movilbox.api.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.MINUTES;

public class ApiRetrofit {
    private static Retrofit instance ;

    public static Retrofit getClient(String url){
        if (instance==null){
            Gson gson = new GsonBuilder()
                    .create();
            instance=new Retrofit.Builder()
                    .client(getClient()).baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return instance;
    }

    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder().connectTimeout(1, MINUTES).readTimeout(1, MINUTES)
                .build();
    }
}