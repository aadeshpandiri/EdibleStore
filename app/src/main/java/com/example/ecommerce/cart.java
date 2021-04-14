package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.Inet4Address;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecommerce.GotoCart.json;
import static com.example.ecommerce.GotoCart.sjs;
import static com.example.ecommerce.Login.tokenpassing;

public class cart extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView pname1,price1,quan1,pname2,price2,quan2,pname3,price3,quan3;
    ImageView imgprod1,imgprod2,imgprod3,imgprod4;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    Button chkoutbtn;
    String answer;
    public static int lengthofcart;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        chkoutbtn = (Button)findViewById(R.id.checkoutbtn);
        setSupportActionBar(toolbar);
         answer = getIntent().getStringExtra("cartdata");
        //navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.Homepage);


        pname1 = (TextView) findViewById(R.id.prod_name1);
        price1 = (TextView) findViewById(R.id.price1);
        quan1 = (TextView) findViewById(R.id.quant1);
        pname2 = (TextView) findViewById(R.id.prod_name2);
        price2 = (TextView) findViewById(R.id.price2);
        quan2 = (TextView) findViewById(R.id.quant2);
        pname3 = (TextView) findViewById(R.id.prod_name3);
        price3 = (TextView) findViewById(R.id.price3);
        quan3= (TextView) findViewById(R.id.quant3);

        Log.d("json check",String.valueOf(json));
        if(sjs == null) {
        Toast.makeText(this, "sjs is null ", Toast.LENGTH_SHORT).show(); }
        if(json == null)
        {
            Intent ifrui = new Intent(cart.this,fruits.class);
            startActivity(ifrui);
        }else {
            String[] jsonarraytitleretrieval = new String[json.length()];
            String[] jsonarraypriceretrieval = new String[json.length()];
            String[] jsonarrayqtyretrieval = new String[json.length()];
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = null;
                try {
                    obj = json.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jsonarraytitleretrieval[i] = obj.getString("title");
                    jsonarraypriceretrieval[i] = obj.getString("price");
                    jsonarrayqtyretrieval[i] = obj.getString("qty");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //System.out.println(jsonarraytitleretrieval[i]);
            }
            lengthofcart = jsonarraypriceretrieval.length;
            if(jsonarraypriceretrieval.length >= 3) {
                price1.setText("$" + jsonarraypriceretrieval[jsonarraypriceretrieval.length - 3]);
                pname1.setText(jsonarraytitleretrieval[jsonarraytitleretrieval.length - 3]);
                quan1.setText("qty:" + jsonarrayqtyretrieval[jsonarrayqtyretrieval.length - 3]);
//            char k=pname1.getText().toString().charAt(0);
//
//                System.out.println(k+"in k");
//                String uri="@drawable/bananas";
//            int bananaid=getResources().getIdentifier(uri, null, getPackageName());
//            Drawable bananadraw=getResources().getDrawable(bananaid);
//
//            if(k=='B')
//                //System.out.println("inside if k");
//                imgprod1.setImageDrawable(bananadraw);
                price2.setText("$" + jsonarraypriceretrieval[jsonarraypriceretrieval.length - 2]);
                pname2.setText(jsonarraytitleretrieval[jsonarraytitleretrieval.length - 2]);
                quan2.setText("qty:" + jsonarrayqtyretrieval[jsonarrayqtyretrieval.length - 2]);
                price3.setText("$" + jsonarraypriceretrieval[jsonarraypriceretrieval.length - 1]);
                pname3.setText(jsonarraytitleretrieval[jsonarraytitleretrieval.length - 1]);
                quan3.setText("qty:" + jsonarrayqtyretrieval[jsonarrayqtyretrieval.length - 1]);
            }
            else if(jsonarraypriceretrieval.length==2)
            {
                price1.setText("$" + jsonarraypriceretrieval[jsonarraypriceretrieval.length - 2]);
                pname1.setText(jsonarraytitleretrieval[jsonarraytitleretrieval.length - 2]);
                quan1.setText("qty:" + jsonarrayqtyretrieval[jsonarrayqtyretrieval.length - 2]);
                price2.setText("$" + jsonarraypriceretrieval[jsonarraypriceretrieval.length - 1]);
                pname2.setText(jsonarraytitleretrieval[jsonarraytitleretrieval.length - 1]);
                quan2.setText("qty:" + jsonarrayqtyretrieval[jsonarrayqtyretrieval.length - 1]);


            }
            else if(jsonarraypriceretrieval.length == 1)
            {
                price1.setText("$" + jsonarraypriceretrieval[jsonarraypriceretrieval.length - 1]);
                pname1.setText(jsonarraytitleretrieval[jsonarraytitleretrieval.length - 1]);
                quan1.setText("qty:" + jsonarrayqtyretrieval[jsonarrayqtyretrieval.length - 1]);
            }


        }
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
    EditText quants1,quants2,quants3;
    public void gotoCheckout(View view) {
        Intent i111 = new Intent(getApplicationContext(),CheckoutActivity.class);

        i111.putExtra("pname1",pname1.getText().toString());

        i111.putExtra("pname2",pname2.getText().toString());
        i111.putExtra("pname3",pname3.getText().toString());
        i111.putExtra("price1",price1.getText().toString());
        i111.putExtra("price2",price2.getText().toString());
        i111.putExtra("price3",price3.getText().toString());
        quants1=findViewById(R.id.quantity1);
        quants2=findViewById(R.id.quantity2);
        quants3=findViewById(R.id.quantity3);

        i111.putExtra("quant1",quants1.getText().toString());
        i111.putExtra("quant2",quants2.getText().toString());
        i111.putExtra("quant3",quants3.getText().toString());

        startActivity(i111);


        debugqtyfun(tokenpassing,Integer.parseInt(quants1.getText().toString()),pname1.getText().toString());
        debugqtyfun(tokenpassing,Integer.parseInt(quants2.getText().toString()),pname2.getText().toString());
        debugqtyfun(tokenpassing,Integer.parseInt(quants3.getText().toString()),pname3.getText().toString());


    }
    public void debugqtyfun(String tokenpassing1,int quantity,String titlename) {
        JsonObject params = new JsonObject();
        params.addProperty("token", tokenpassing1);
        params.addProperty("qty", quantity);
        params.addProperty("title", titlename);

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
               // Toast.makeText(cart.this,".....", Toast.LENGTH_SHORT).show();

                JsonArray jsonArray;

                //System.out.println(responseBody.getClass());
                //System.out.println("Response message is: "+j);
                String f = String.valueOf(response.errorBody());
                Log.i("Response  error is ", f);
                Log.i("Response from url ", String.valueOf(s));
                if (response.isSuccessful()) {
                    //Toast.makeText(cart.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    try {
                        JSONArray json = new JSONArray(response.body().string());
                        Log.i("UPDATE QTY JSON ", String.valueOf(json));
                       // Toast.makeText(cart.this, String.valueOf(json) , Toast.LENGTH_SHORT).show();
                        String[] heroes = new String[json.length()];
                        for (int i = 0; i < json.length(); i++) {
                            JSONObject obj = json.getJSONObject(i);
                            heroes[i] = obj.getString("amount");
                            System.out.println(heroes[i]);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    //Toast.makeText(cart.this, "Registration not successful", Toast.LENGTH_SHORT).show();
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