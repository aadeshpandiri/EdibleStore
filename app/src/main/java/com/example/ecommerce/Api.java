package com.example.ecommerce;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

interface Api {
    @Headers("Content-Type:application/json")
    //@FormUrlEncoded
    @POST("register")
    Call<ResponseBody> register(
            @Body JsonObject jsonObject
    );


}
