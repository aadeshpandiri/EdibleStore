package com.example.ecommerce;

import android.util.Log;

import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecommerce.Login.tokenpassing;

public class addtocart {

     static int res=-1;
    public int addtocartfun(String title) {

        Log.i("title atc ", title);
        Log.i("in addtocart method ", "atc");
        JsonObject params=new JsonObject();
        params.addProperty("token",tokenpassing);
        params.addProperty("title",title);

        Call<ResponseBody> call = RetrofitClient2.getInstance().getApi2().addtocart(
                "Bearer "+tokenpassing,params
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("on Response method ","6");
                Log.i("Response call try ","4");
                int s = response.code();
                ResponseBody responseBody=response.body();
                //System.out.println(responseBody.getClass());
                //System.out.println("Response message is: "+j);
                String f= String.valueOf(response.errorBody());
                Log.i("Response  error is ", f);
                Log.i("Response from url ", String.valueOf(s));

                if (response.isSuccessful()) {
                    res = s;
                    System.out.println("on success"+res);
                }
                else
                {
                        res = s;
                        System.out.println("else case"+res);

                }
            }

//
//                }
//                else
//                {
//                    Toast.makeText(Debugcart.this, "Registration not successful", Toast.LENGTH_SHORT).show();
//                }

            // Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Error is ", t.getMessage());
            System.out.println("On fail" +res);

            }


        });
        System.out.println("In function"+res);
        return  res;

    }
}