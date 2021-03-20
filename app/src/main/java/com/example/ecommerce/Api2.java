package com.example.ecommerce;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

interface Api2 {
    @POST("addtocart")
    Call<ResponseBody> addtocart(
            @Header("Authorization") String token, @Body JsonObject details
    );
}