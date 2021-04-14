package com.example.ecommerce;

import com.example.ecommerce.addtocart;
import com.example.ecommerce.updatequantity;
import com.example.ecommerce.GotoCart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.ecommerce.Login.loginaftername;
import  static com.example.ecommerce.Login.tokenpassing;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView tv1,tv2,tv3,tv4;
    TextView price1,price2,price3,price4;
    Button b1,b2,b3,b4;

    TextView tv11,tv22,tv33,tv44;
    TextView price11,price22,price33,price44;
    Button b11,b22,b33,b44;
    TextView tchange;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
     public String[] names;
    public  String answer;
    public addtocart atc;
    public static String ipaddresscode = "";



   // String add = pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
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

        String kuuu = getIntent().getStringExtra("passkey");
        System.out.println("in main ipaddress"+kuuu);
        ipaddresscode = kuuu;


         tv1 = (TextView)findViewById(R.id.tv1);
         tv2 = (TextView)findViewById(R.id.tv2);
         tv3 = (TextView)findViewById(R.id.tv3);
         tv4 = (TextView)findViewById(R.id.tv4);
        price1 = (TextView)findViewById(R.id.price1);
        price2 = (TextView)findViewById(R.id.price2);
        price3 = (TextView)findViewById(R.id.price3);
        price4 = (TextView)findViewById(R.id.price4);
        tv11 = (TextView)findViewById(R.id.tv11);
        tv22 = (TextView)findViewById(R.id.tv22);
        tv33 = (TextView)findViewById(R.id.tv33);
        tv44 = (TextView)findViewById(R.id.tv44);
        price11 = (TextView)findViewById(R.id.price11);
        price22 = (TextView)findViewById(R.id.price22);
        price33 = (TextView)findViewById(R.id.price33);
        price44 = (TextView)findViewById(R.id.price44);
        b1 = findViewById(R.id.add11);
        b2 = findViewById(R.id.add22);
        b3 = findViewById(R.id.add33);
        b4 = findViewById(R.id.add44);
        tchange = findViewById(R.id.textchange);

        String temp = tokenpassing;
        getJSON("http://"+ipaddresscode+":8082/rest/products/all");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int ans;
                atc=new addtocart();

                int ans = atc.addtocartfun(names[0]);
                System.out.println("In main "+ans);
                if(ans==200)
                {
                    Toast.makeText(MainActivity.this, "Added to cart Successfully", Toast.LENGTH_SHORT).show();

                }
                else
                {

                    Toast.makeText(MainActivity.this, "Please Login ", Toast.LENGTH_SHORT).show();
                }

                //192.168.0.6
               //getJSONs("http://192.168.0.6:8084/rest/users/addtocart?title="+names[0]+"&name="+tokenpassing);
                //Toast.makeText(MainActivity.this, "http://192.168.0.6:8084/rest/users/addtocart?title="+names[0]+"&name="+tokenpassing, Toast.LENGTH_SHORT).show();
               // Toast.makeText(MainActivity.this, tv11.getText().toString()+" "+price11.getText().toString()+" \n"+tokenpassing, Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int ans;
                atc=new addtocart();

                int ans = atc.addtocartfun(names[10]);
                //System.out.println("b2:"+names[1]);
                System.out.println("In main "+ans);
                if(ans==200)
                {
                    Toast.makeText(MainActivity.this, "Added to cart Successfully", Toast.LENGTH_SHORT).show();

                }
                else
                {


                    Toast.makeText(MainActivity.this, "Please Login ", Toast.LENGTH_SHORT).show();
                }

                //192.168.0.6
                //getJSONs("http://192.168.0.6:8084/rest/users/addtocart?title="+names[0]+"&name="+tokenpassing);
                //Toast.makeText(MainActivity.this, "http://192.168.0.6:8084/rest/users/addtocart?title="+names[0]+"&name="+tokenpassing, Toast.LENGTH_SHORT).show();
                // Toast.makeText(MainActivity.this, tv11.getText().toString()+" "+price11.getText().toString()+" \n"+tokenpassing, Toast.LENGTH_SHORT).show();
            }
        });

        System.out.println("in main:"+ipaddresscode);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int ans;
                atc=new addtocart();

                int ans = atc.addtocartfun(names[2]);
                System.out.println("In main "+ans);
                if(ans==200)
                {
                    Toast.makeText(MainActivity.this, "Added to cart Successfully", Toast.LENGTH_SHORT).show();

                }
                else
                {

                    Toast.makeText(MainActivity.this, "Please Login ", Toast.LENGTH_SHORT).show();
                }

                //192.168.0.6
                //getJSONs("http://192.168.0.6:8084/rest/users/addtocart?title="+names[0]+"&name="+tokenpassing);
                //Toast.makeText(MainActivity.this, "http://192.168.0.6:8084/rest/users/addtocart?title="+names[0]+"&name="+tokenpassing, Toast.LENGTH_SHORT).show();
                // Toast.makeText(MainActivity.this, tv11.getText().toString()+" "+price11.getText().toString()+" \n"+tokenpassing, Toast.LENGTH_SHORT).show();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int ans;
                atc=new addtocart();

                int ans = atc.addtocartfun(names[4]);
                //System.out.println("b2:"+names[1]);
                System.out.println("In main "+ans);
                if(ans==200)
                {
                    Toast.makeText(MainActivity.this, "Added to cart Successfully", Toast.LENGTH_SHORT).show();

                }
                else
                {

                    Toast.makeText(MainActivity.this, "Please Login ", Toast.LENGTH_SHORT).show();
                }

                //192.168.0.6
                //getJSONs("http://192.168.0.6:8084/rest/users/addtocart?title="+names[0]+"&name="+tokenpassing);
                //Toast.makeText(MainActivity.this, "http://192.168.0.6:8084/rest/users/addtocart?title="+names[0]+"&name="+tokenpassing, Toast.LENGTH_SHORT).show();
                // Toast.makeText(MainActivity.this, tv11.getText().toString()+" "+price11.getText().toString()+" \n"+tokenpassing, Toast.LENGTH_SHORT).show();
            }
        });


        //192.168.0.7  192.168.0.7 192.168.0.4   192.168.0.7 192.168.0.7  192.168.43.72  192.168.43.40
       // kiran reomved
        // getJSON("http://"+ipaddresscode+":8082/rest/products/all");
        //getJSON("http://localhost:8084/rest/users/login?email=abc@gmail.com&password=1234");

        if(tokenpassing!=null)
        {
            tchange.setText("hi "+loginaftername+" !");
        }
        else
        {
            tchange.setText("Please Login !");
        }
    }
    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    /*
    private void getJSONs(final String urlWebService1) {

        class GetJSONs extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s11) {
                super.onPostExecute(s11);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    newCls(s11);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url1 = new URL(urlWebService1);
                    HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();
                    StringBuilder sb1 = new StringBuilder();
                    BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(con1.getInputStream()));
                    String json1;
                    while ((json1 = bufferedReader1.readLine()) != null) {
                        sb1.append(json1 + "\n");
                    }
                    return sb1.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSONs getJSONs = new GetJSONs();
        //getJSONs.execute();
        getJSONs.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void newCls(String json1) throws JSONException {
        JSONObject js  = new JSONObject(json1);
        Toast.makeText(this, js.getString("token"), Toast.LENGTH_SHORT).show();




        }
        */

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] heroes = new String[jsonArray.length()];
        names =  new String[jsonArray.length()];
        String[] prices = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes[i] = obj.getString("product_desc");
            names[i] = obj.getString("ptitle");
            prices[i]=obj.getString("product_price");
        }
        tv1.setText(names[0]);
        tv2.setText(names[10]);
        tv3.setText(names[2]);
        tv4.setText(names[4]);

        //tv1.setText(heroes[0].substring(15,22));
        //tv2.setText(heroes[10].substring(6,14));
        //tv3.setText(heroes[2].substring(6,14));
       // tv4.setText(heroes[5].substring(11,18));
        price1.setText("$ "+prices[0]);
        price2.setText("$ "+prices[1]);
        price3.setText("$ "+prices[2]);
        price4.setText("$ "+prices[3]);
        tv11.setText(heroes[0].substring(15,22));
        tv22.setText(heroes[10].substring(6,14));
        tv33.setText(heroes[2].substring(6,14));
        tv44.setText(heroes[5].substring(11,18));
        price11.setText("$ "+prices[0]);
        price22.setText("$ "+prices[1]);
        price33.setText("$ "+prices[2]);
        price44.setText("$ "+prices[3]);


        //Log.d("hshsh",t1);
       // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, heroes);
        //listView.setAdapter(arrayAdapter);

        // ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, prices);
        //listView1.setAdapter(arrayAdapter1);
    }



    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.Homepage:
                break;
            case R.id.nav_fruits:

                Intent intent = new Intent(MainActivity.this, fruits.class);
                startActivity(intent);
                break;
            case R.id.nav_veg:
                Intent intent2 = new Intent(MainActivity.this, vegetables.class);
                startActivity(intent2);
                break;
            case R.id.nav_leaf_veg:
                Intent intent3 = new Intent(MainActivity.this, leafy_vegetables.class);
                startActivity(intent3);
                break;
            case R.id.nav_brands:
                Intent intent4 = new Intent(MainActivity.this, brands.class);
                startActivity(intent4);
                break;
            case R.id.nav_heera:
                Intent intent5 = new Intent(MainActivity.this, heerastore.class);
                startActivity(intent5);
                break;
            case R.id.nav_Retro:
                Intent intent6 = new Intent(MainActivity.this, retrofresh.class);
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
             answer = GotoCart.debugqtyfun();
            System.out.println("Im in mainActivity"+(answer));
            Intent in1111 = new Intent(MainActivity.this,cart.class);

            in1111.putExtra("cartdata",answer);
            startActivity(in1111);
            return true;
        }
        if(id == R.id.id_login){
            Intent intentsettings = new Intent(MainActivity.this , Login.class);
            startActivity(intentsettings);
            return true;
        }
        if(id == R.id.id_register){
            Intent intentdownload = new Intent(MainActivity.this , Register.class);
            startActivity(intentdownload);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit !!!! ");
        builder.setMessage("Do you really want to Exit ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                MainActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }
}