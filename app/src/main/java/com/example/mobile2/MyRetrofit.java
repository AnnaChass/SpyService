package com.example.mobile2;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;


public class MyRetrofit {
    private MyRetrofit mInstance;
    private static final String BASE_URL = "http://192.168.0.103:8082";
    private Retrofit mRetrofit;

    public MyRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public MyRetrofit getInstance() {
        if (mInstance == null) {
            mInstance = new MyRetrofit();
        }
        return mInstance;
    }

    public MyApi formData() {
        return mRetrofit.create(MyApi.class);
    }
}


