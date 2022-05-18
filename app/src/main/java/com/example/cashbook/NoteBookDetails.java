package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NoteBookDetails extends AppCompatActivity {

    TextView testing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_book_details);
        testing = findViewById(R.id.testing);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Data");
        testing.setText(name);


    }
}