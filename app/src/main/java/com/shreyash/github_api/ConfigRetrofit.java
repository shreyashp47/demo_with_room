package com.shreyash.github_api;



import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {
    private static Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(configOkhttp())
            .build();
    public static <S> S configRetrofit(Class<S> service){
        return retrofit.create(service);
    }

    public static OkHttpClient configOkhttp(){
        OkHttpClient.Builder okHttpClientbuilder=new OkHttpClient.Builder();
        okHttpClientbuilder.connectTimeout(90, TimeUnit.SECONDS);
        okHttpClientbuilder.writeTimeout(90, TimeUnit.SECONDS);
        okHttpClientbuilder.readTimeout(90, TimeUnit.SECONDS);
        okHttpClientbuilder.addInterceptor(configOkHttpLoggingIncepter());
        return okHttpClientbuilder.build();

    }

    private static HttpLoggingInterceptor configOkHttpLoggingIncepter(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

}
