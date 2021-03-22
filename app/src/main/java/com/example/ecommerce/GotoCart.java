package com.example.ecommerce;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecommerce.Login.tokenpassing;

public class GotoCart {
    public static   JSONArray json;
    public static String sjs;
    public  static String am = "No";

    public static String debugqtyfun() {

        JsonObject params = new JsonObject();
        params.addProperty("token", tokenpassing);


        Call<ResponseBody> call = RetrofitClient4.getInstance().getApi4().getcartdetails(
                "Bearer "+tokenpassing, params
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("on Response method ", "6");
                Log.i("Response call try ", "4");
                int s = response.code();
                ResponseBody responseBody = response.body();

                String f = String.valueOf(response.errorBody());
                Log.i("Response  error is ", f);
                Log.i("Response from url ", String.valueOf(s));
                if(s == 200) am="Yes";
                if (response.isSuccessful()) {
                    am =  "Yes";
                    try {
                        JSONArray temp = new JSONArray(response.body().string());
                        json = temp;
                         String sjss = String.valueOf(json);
                         sjs = sjss;
                         System.out.println("In Gotocart"+String.valueOf(json));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else am="NO";

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Error is ", t.getMessage());
                am="No";
            }


        });
        System.out.println(am);
        return am;

    }
}
