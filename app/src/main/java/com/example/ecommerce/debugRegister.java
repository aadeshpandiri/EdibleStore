package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.ecommerce.RetrofitClient3;

import static com.example.ecommerce.Login.tokenpassing;

public class debugRegister extends AppCompatActivity {
String email;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debugcart);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                debugqtyfun();
            }

        });
    }

    public void debugqtyfun() {
        JsonObject params = new JsonObject();
        params.addProperty("token", tokenpassing);
        params.addProperty("qty", 10);
        params.addProperty("title", "Apples(kg)-retro");

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
                Toast.makeText(debugRegister.this,".....", Toast.LENGTH_SHORT).show();

                JsonArray jsonArray;

                //System.out.println(responseBody.getClass());
                //System.out.println("Response message is: "+j);
                String f = String.valueOf(response.errorBody());
                Log.i("Response  error is ", f);
                Log.i("Response from url ", String.valueOf(s));
                if (response.isSuccessful()) {

                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        Log.i("Register ", String.valueOf(json));
                        Toast.makeText(debugRegister.this, String.valueOf(json) , Toast.LENGTH_SHORT).show();


                            email = json.getString("emial");

                            if(email.equals("User with this emailID already exists")){
                                Toast.makeText(debugRegister.this, "Email already exists. Try again", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(debugRegister.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(debugRegister.this,Login.class);
                                startActivity(i);

                            }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(debugRegister.this, "Registration not successful", Toast.LENGTH_SHORT).show();
                }

                // Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Error is ", t.getMessage());
            }


        });
    }
}