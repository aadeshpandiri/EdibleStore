package com.example.ecommerce;



import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.ecommerce.MainActivity.ipaddresscode;

public class RetrofitClient5 {  //192.168.43.40
    private static final String BASE_URl="http://"+ipaddresscode+":8081/v1/";
    private static RetrofitClient5 mInstance;
    //private final Object Chain;
    private Retrofit retrofit;



    private RetrofitClient5(){
        Log.i("Retrofit","1");
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient5 getInstance(){
        Log.i("get Instance Retro ", "2");
        if(mInstance==null){
            mInstance = new RetrofitClient5();
        }
        return mInstance;
    }

    public Api5 getApi5(){
        Log.i("GETAPI() ", "3");
        return retrofit.create(Api5.class);
    }
}
