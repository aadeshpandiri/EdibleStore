package com.example.ecommerce;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.ecommerce.MainActivity.ipaddresscode;

class RetrofitClient {

    private static final String BASE_URl="http://"+ipaddresscode+":8081/v1/";
    private static RetrofitClient mInstance;
    //private final Object Chain;
    private Retrofit retrofit;



    private RetrofitClient(){
        Log.i("Retrofit","1");
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        Log.i("get Instance Retro ", "2");
        if(mInstance==null){
            mInstance=new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi(){
        Log.i("GETAPI() ", "3");
        return retrofit.create(Api.class);
    }
}