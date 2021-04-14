package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;

public class IntroActivity extends AppCompatActivity {
    EditText etip;
    Button btn;
    SharedPreferences sharedPreferences;
    String keysip = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        etip =  findViewById(R.id.etipaddress);
        btn = findViewById(R.id.btn);
        etip.setText(keysip);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotomain();
            }
        });

        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.ecommerce", Context.MODE_PRIVATE);
        keysip = sharedPreferences.getString("ipkey","");
        sharedPreferences.edit().putString("key","198").apply();


        etip .addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {




                
                sharedPreferences.edit().putString("key", etip.getText().toString()).apply();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });



    }

    private void gotomain() {
        System.out.println(etip.getText().toString());
        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.ecommerce", Context.MODE_PRIVATE);
       sharedPreferences.edit().putString("ipkey",etip.getText().toString()).apply();
         keysip = sharedPreferences.getString("ipkey","");
        System.out.println("ipaddress:"+keysip);


        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);

    }
}







