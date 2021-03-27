package com.example.ecommerce;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

interface Api5 {
    @POST("register")
    Call<ResponseBody> register(
             @Body JsonObject params

    );

}
