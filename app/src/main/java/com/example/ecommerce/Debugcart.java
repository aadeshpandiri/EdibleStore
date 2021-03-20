package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecommerce.Login.*;

public class Debugcart extends AppCompatActivity {
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debugcart);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtocartfun();
            }

        });
    }

    public void addtocartfun(){
        JsonObject params=new JsonObject();
        params.addProperty("token",tokenpassing);
        params.addProperty("title","Apples(kg)-retro");

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
                if(response.isSuccessful())
                {
                    Toast.makeText(Debugcart.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Debugcart.this,Debugqty.class);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(Debugcart.this, "Registration not successful", Toast.LENGTH_SHORT).show();
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