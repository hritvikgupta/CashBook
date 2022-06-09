package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity implements BottomFragment.options{

    ListView helpList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTitle("Help and Support");
        helpList = findViewById(R.id.helplist);
        ArrayList<String> descriptions = new ArrayList<String>();
        descriptions.add("What is CashBook ?");
        descriptions.add("How to BackDated Entries ? ");
        ArrayAdapter ad = new ArrayAdapter(HelpActivity.this, android.R.layout.simple_list_item_1,descriptions);
        helpList.setAdapter(ad);

        helpList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(HelpActivity.this, "Help", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void OnOptionClicked(ImageView iv1, ImageView iv2, ImageView iv3) {
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();

            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(HelpActivity.this, com.example.cashbook.Setting.class);
                    startActivity(intent);
                    finish();

            }
        });


    }

    @Override
    public void changeTextLang(TextView book, TextView help, TextView setting) {

    }
}