package com.example.ecommerce;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class NotesEditor extends AppCompatActivity {
    int noteId;
    Button button;
    EditText editText;
     HashSet<String> set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editor);
        button = findViewById(R.id.button);

         editText= findViewById(R.id.editText);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);
        if(noteId!=-1) {
            editText.setText(MainActivityNotes.notes.get(noteId));
        }else {
            MainActivityNotes.notes.add("");
            noteId = MainActivityNotes.notes.size() - 1;
        }



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                MainActivityNotes.notes.set(noteId,String.valueOf(s));
                MainActivityNotes.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);

                set = new HashSet<>(MainActivityNotes.notes);
                System.out.println("String is "+set.toString());




            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 click();
             }
         });






    }

    private void click() {
        onBackPressed();
        System.out.println("notes editor :"+ MainActivityNotes.notes);
        MainActivityNotes.sharedPreferences.edit().putStringSet("notes", set).apply();

    }




}