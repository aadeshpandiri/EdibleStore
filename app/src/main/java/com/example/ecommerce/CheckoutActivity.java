package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import static com.example.ecommerce.cart.lengthofcart;

public class CheckoutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    String pname1,price1,quan1,pname2,price2,quan2,pname3,price3,quan3;
    TextView pnames1,prices1,quans1,pnames2,prices2,quans2,pnames3,prices3,quans3;
    TextView FPrice1,FPrice2,FPrice3;
    Button pay;

    int fp1,fp2,fp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.Homepage);


        pnames1=findViewById(R.id.prod_names1);
        pnames2=findViewById(R.id.prod_names2);
        pnames3=findViewById(R.id.prod_names3);

        prices1=findViewById(R.id.prices1);
        prices2=findViewById(R.id.prices2);
        prices3=findViewById(R.id.prices3);

        quans1=findViewById(R.id.quants1);
        quans2=findViewById(R.id.quants2);
        quans3=findViewById(R.id.quants3);

        FPrice1=findViewById(R.id.FP1);
        FPrice2=findViewById(R.id.FP2);
        FPrice3=findViewById(R.id.FP3);

        pay = (Button)findViewById(R.id.paybtn);



        Intent i=getIntent();
        pname1=i.getStringExtra("pname1");
        pname2=i.getStringExtra("pname2");
        pname3=i.getStringExtra("pname3");
        price1=i.getStringExtra("price1");
        price2=i.getStringExtra("price2");
        price3=i.getStringExtra("price3");
        quan1=i.getStringExtra("quant1");
        quan2=i.getStringExtra("quant2");
        quan3=i.getStringExtra("quant3");

        System.out.println("Hello quant here "+quan1+" "+quan2+" "+quan3);

        if(lengthofcart >= 3)
        {
            pnames1.setText(pname1);
            prices1.setText(price1);
            quans1.setText("Quantity : "+quan1);

            pnames2.setText(pname2);
            prices2.setText(price2);
            quans2.setText("Quantity : "+quan2);

            pnames3.setText(pname3);
            prices3.setText(price3);
            quans3.setText("Quantity : "+quan3);
        }
        else if(lengthofcart == 2)
        {
            pnames1.setText(pname1);
            prices1.setText(price1);
            quans1.setText("Quantity : "+quan1);

            pnames2.setText(pname2);
            prices2.setText(price2);
            quans2.setText("Quantity : "+quan2);
        }
        else
        {
            pnames1.setText(pname1);
            prices1.setText(price1);
            quans1.setText("Quantity : "+quan1);
        }





        fp1=Integer.parseInt(price1.substring(1))*Integer.parseInt(quan1);
        fp2=Integer.parseInt(price2.substring(1))*Integer.parseInt(quan2);
        fp3=Integer.parseInt(price3.substring(1))*Integer.parseInt(quan3);
//
//
        FPrice1.setText("$"+String.valueOf(fp1));
        FPrice2.setText("$"+String.valueOf(fp2));
        FPrice3.setText("$"+String.valueOf(fp3));
//        FP3.setText(fp3);

        System.out.println("Im here "+pname1+" "+pname2+" "+pname3+" "+price1+" "+price2+" "+price3);

        //chkoutbtn = (Button)findViewById(R.id.checkoutbtn);


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i133 = new Intent(getApplicationContext(),PaypalActivity.class);
                startActivity(i133);
            }
        });



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