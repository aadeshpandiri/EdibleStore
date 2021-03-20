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

public class updatequantity {
    public  static String am = "No";

    public static String debugqtyfun(String title, int  qty) {

        JsonObject params = new JsonObject();
        params.addProperty("token", tokenpassing);
        params.addProperty("qty", qty);
        params.addProperty("title", title);

        Call<ResponseBody> call = RetrofitClient3.getInstance().getApi3().updateqyt(
                "Bearer "+tokenpassing, params
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("on Response method ", "6");
                Log.i("Response call try ", "4");
                int s = response.code();
                ResponseBody responseBody = response.body();
               // Toast.makeText(Debugqty.this,".....", Toast.LENGTH_SHORT).show();

                JsonArray jsonArray;

                //System.out.println(responseBody.getClass());
                //System.out.println("Response message is: "+j);
                String f = String.valueOf(response.errorBody());
                Log.i("Response  error is ", f);
                Log.i("Response from url ", String.valueOf(s));
                if (response.isSuccessful()) {
                  am =  "Yes";
                }else am="NO";
                   // Toast.makeText(Debugqty.this, "Registered successfully", Toast.LENGTH_SHORT).show();
//                    try {
//                        JSONArray json = new JSONArray(response.body().string());
//                        Log.i("UPDATE QTY JSON ", String.valueOf(json));
//                        //Toast.makeText(Debugqty.this, String.valueOf(json) , Toast.LENGTH_SHORT).show();
//                        String[] heroes = new String[json.length()];
//                        for (int i = 0; i < json.length(); i++) {
//                            JSONObject obj = json.getJSONObject(i);
//                            heroes[i] = obj.getString("amount");
//                            System.out.println(heroes[i]);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

//                } else {
//                    Toast.makeText(Debugqty.this, "Registration not successful", Toast.LENGTH_SHORT).show();
//                }

                // Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
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
