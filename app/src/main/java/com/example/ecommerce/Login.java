package com.example.ecommerce;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import  static com.example.ecommerce.MainActivity.ipaddresscode;

public class Login extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    EditText tvemail,tvpass;
    String email,pass;
    Button btLogin;
    String urlsss;
    public  static String tokenpassing;
    public  static String loginaftername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        btLogin = findViewById(R.id.btnlogin);
        tvemail =  findViewById(R.id.tvemail);
        tvpass =  findViewById(R.id.tvpass);

        //getJSON("http://192.168.29.113:8084/rest/users/login?email="+a+"&password="+b);
    }
    public void login(View view){
        //listView = (ListView) findViewById(R.id.listView);192.168.29.113:8084
        //getJSON("http://192.168.29.113:8084/rest/users/login?email=def@gmail.com&password=5678");
        String a= tvemail.getText().toString();
        String b=tvpass.getText().toString();
        Log.d("a value",a);
        Log.d("b value",b);
        Toast.makeText(this, a+""+b, Toast.LENGTH_SHORT).show();
        //192.168.0.5 192.168.0.7  192.168.0.7
       // getJSON("http://192.168.0.7:8084/rest/users/login?email=abc@gmail.com&password=1234");
        //192.168.43.72  192.168.43.40  192.168.0.195
         getJSON("http://"+ipaddresscode+":8081/v1/login?email="+a+"&password="+b);
    }

//        btLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                email = tvemail.getText().toString();
//                pass = tvpass.getText().toString();
//                //192.168.0.6
//                //Toast.makeText(Login.this, email+pass, Toast.LENGTH_SHORT).show();
//                //urlsss = "http://192.168.0.6:8084/rest/users/login?email=abc@gmail.com&password=1234";
//                urlsss = "http://192.168.0.6:8084/rest/users/login?email="+email+"&password="+pass;
//               getJSON(urlsss);
//            }
//        });




    // getJSON("http://localhost:8084/rest/users/login?email=abc@gmail.com&password=1234");



    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Log.d("a value","test");
                System.out.println("Sucesss");
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                if (s != null) {
                    try {
                        loadIntoListView(s);
                        System.out.println("Sucesss2222");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected String doInBackground(Void... voids) {

                try {

                    Log.d("a value","test2");
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;

                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    } //Toast.makeText(Login.this, sb.toString().trim()+"xyz", Toast.LENGTH_SHORT).show();
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        //getJSON.execute();
        getJSON.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR); //problem is here
    }

    private void loadIntoListView(String json) throws JSONException {
        //System.out.println(json);
        JSONObject k=new JSONObject(json);


        String token  = k.getString("token");
        System.out.println(token);
       Toast.makeText(this, k.getString("token"), Toast.LENGTH_LONG).show();
        tokenpassing = k.getString("token");
        Intent i1200 = new Intent(getApplicationContext(),GotoCart.class);
         i1200.putExtra("logintoken",tokenpassing);
       Intent i100 = new Intent(getApplicationContext(),MainActivity.class);
       startActivity(i100);
       String hhh[] = tvemail.getText().toString().split("@");

       loginaftername = hhh[0];



    }



    public void gotoRegister(View v)
    {
        Intent i11=new Intent(getApplicationContext(),Register.class);
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