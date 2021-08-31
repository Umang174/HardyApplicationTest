package com.example.hardyapplicationtest.api.client;

import android.content.Context;

import com.example.hardyapplicationtest.api.RestService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    public static final String BASE_URL = "http://demomyurl.com";
    public static RestService restService = null;


    public static RestService getRestService() {
        if (restService == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit.Builder builder =
                    new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(
                                    GsonConverterFactory.create()
                            );
            Retrofit retrofit = builder.build();
            restService = retrofit.create(RestService.class);
        }
        return restService;
    }

}
