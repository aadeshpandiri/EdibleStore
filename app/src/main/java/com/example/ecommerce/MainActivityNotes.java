package com.example.ecommerce;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivityNotes extends AppCompatActivity {
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    static SharedPreferences sharedPreferences;
    public static  String  k ="";
    String pass;
    ListView listView;
    String temp;
    View t;
    HashSet<String> ste = new HashSet<>();


    Button button;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addnotes,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.add_note){

            Intent intent= new Intent(getApplicationContext(),NotesEditor.class);
            startActivity(intent);

            return true;
        }
        return  false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notes);

        button =  findViewById(R.id.button);
        listView = findViewById(R.id.listview);


        ListView list = findViewById(R.id.listview);
         sharedPreferences= getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes",null);
        if(set == null)
            notes.add("Example Note");
        else{
            notes = new ArrayList(set);
        }
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);
        list.setAdapter(arrayAdapter);
        pass= arrayAdapter.getItem(0).toString();
        System.out.println( "use0:"+arrayAdapter.getItem(0).toString());




        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),NotesEditor.class);
                intent.putExtra("noteId",position);
                startActivity(intent);

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(MainActivityNotes.this).setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are You Sure")
                        .setMessage("Do you want to delete this note")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(position);
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);

                                HashSet<String> set = new HashSet<>(MainActivityNotes.notes);

                                //System.out.println( "use1:"+pass);

                                sharedPreferences.edit().putStringSet("notes", set).apply();


                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            }
        });


        //pass = getIntent().getStringExtra("pass");

        System.out.println("notes"+pass);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in3 = new Intent(getApplicationContext(),MainActivity.class);

                //String def = notes.get(notes.size() - 1);
                //in3.putExtra("passkey",sharedPreferences.getString("notes",def));

                pass= arrayAdapter.getItem(0).toString();

                in3.putExtra("passkey",pass);

                pass= arrayAdapter.getItem(0).toString();

                startActivity(in3);
            }
        });

    }


}