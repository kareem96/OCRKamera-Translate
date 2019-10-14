package com.abdulkarim.ocrkameratranslate.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.abdulkarim.ocrkameratranslate.Constant.Constant;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofitClient = null;

    private final static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build();

    private final static Gson gson = new GsonBuilder().setLenient().create();

    public static APIServices getRetrofitClient(){
        if (retrofitClient==null){
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(Constant.API_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofitClient.create(APIServices.class);
    }
}
