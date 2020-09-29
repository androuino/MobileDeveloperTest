package com.josapedmoreno.mobiledevelopertest.ui.base;

import androidx.lifecycle.ViewModel;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.josapedmoreno.mobiledevelopertest.Constant.BASE_URL;

public class BaseViewModel extends ViewModel {
    public Retrofit retrofit;

    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)
                .addInterceptor (chain -> {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", UUID.randomUUID().toString())
                            .build();
                    return chain.proceed(newRequest);
                });
        OkHttpClient client = httpClient.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }
}
