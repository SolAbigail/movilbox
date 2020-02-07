package com.movilbox.api.service;


import com.movilbox.api.retrofit.ApiRetrofit;

public class ApiUtils {

    public ApiUtils() {
    }

    public static final String API_URL = "https://jsonplaceholder.typicode.com/";
    public static Services getService(){
        return ApiRetrofit.getClient(API_URL).create(Services.class);
    }
            }
