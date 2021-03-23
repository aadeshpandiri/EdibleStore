package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    EditText f_n;
    EditText l_n;
    EditText email;
    EditText password;
    EditText mobile;
    EditText ad_1;
    EditText ad_2;
    Button r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.Homepage);

        f_n=findViewById(R.id.f_n);
        l_n=findViewById(R.id.l_n);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        mobile=findViewById(R.id.mobile);
        ad_1=findViewById(R.id.ad1);
        ad_2=findViewById(R.id.ad2);
        r=findViewById(R.id.register);

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    private void register() {
        System.out.println(f_n.getText().toString());
        JsonObject params=new JsonObject();
        params.addProperty("first_name",f_n.getText().toString());
        params.addProperty("last_name",l_n.getText().toString());
        params.addProperty("email",email.getText().toString());
        params.addProperty("password",password.getText().toString());
        params.addProperty("mobile",mobile.getText().toString());
        params.addProperty("address1",ad_1.getText().toString());
        params.addProperty("address2",ad_2.getText().toString());
        System.out.println(params);
        JsonParser jsonParser = new JsonParser();
        JsonObject gsonObject = (JsonObject) jsonParser.parse(params.toString());
        System.out.println(gsonObject);

        Call<ResponseBody> call = RetrofitClient.getInstance().getApi().register(
                gsonObject
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("on Response method ","6");
                Log.i("Response call try ","4");
                int s = response.code();
                ResponseBody responseBody=response.body();
                System.out.println(responseBody.getClass());
                //System.out.println("Response message is: "+j);
                String f= String.valueOf(response.errorBody());
                Log.i("Response  error is ", f);
                Log.i("Response from url ", String.valueOf(s));
                if(response.isSuccessful())
                {
                    Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    Intent i35 = new Intent(getApplicationContext(),Login.class);
                    startActivity(i35);
                }
                else
                {
                    Toast.makeText(Register.this, "Registration not successfull", Toast.LENGTH_SHORT).show();
                }

                // Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Error is ", t.getMessage());
            }


        });
    }
    public void gotoLogin(View v)
    {
        Intent i11=new Intent(getApplicationContext(),Login.class);
        startActivity(i11);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.Homepage:
                Intent i8 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i8);
                break;
            case R.id.nav_fruits:

                Intent intent = new Intent(getApplicationContext(), fruits.class);
                startActivity(intent);
                break;
            case R.id.nav_veg:
                Intent intent2 = new Intent(getApplicationContext(), vegetables.class);
                startActivity(intent2);
                break;
            case R.id.nav_leaf_veg:
                Intent intent3 = new Intent(getApplicationContext(), leafy_vegetables.class);
                startActivity(intent3);
                break;
            case R.id.nav_brands:
                Intent intent4 = new Intent(getApplicationContext(), brands.class);
                startActivity(intent4);
                break;
            case R.id.nav_heera:
                Intent intent5 = new Intent(getApplicationContext(), heerastore.class);
                startActivity(intent5);
                break;
            case R.id.nav_Retro:
                Intent intent6 = new Intent(getApplicationContext(), retrofresh.class);
                startActivity(intent6);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.id_cart) {
            Intent intentprofile = new Intent(getApplicationContext(), cart.class);
            startActivity(intentprofile);
            return true;
        }
        if(id == R.id.id_login){
            Intent intentsettings = new Intent(getApplicationContext(), Login.class);
            startActivity(intentsettings);
            return true;
        }
        if(id == R.id.id_register){
            Intent intentdownload = new Intent(getApplicationContext(), Register.class);
            startActivity(intentdownload);
            return true;
        }
        return false;
    }
}