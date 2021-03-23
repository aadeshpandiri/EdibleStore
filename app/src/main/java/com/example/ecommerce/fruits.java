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

import com.google.android.material.navigation.NavigationView;

public class fruits extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);
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