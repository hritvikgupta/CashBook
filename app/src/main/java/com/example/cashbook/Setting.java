package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.temporal.Temporal;

public class Setting extends AppCompatActivity implements BottomFragment.options {

    ImageView personImage, editUser, night;
    TextView nameId, numId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Settings");

        personImage = findViewById(R.id.personImage);
        nameId = findViewById(R.id.nameId);
        numId = findViewById(R.id.numId);
        editUser = findViewById(R.id.editUser);
        night = findViewById(R.id.night);
        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameId.setText("Hritvik Gupta");
                numId.setText("954534534");
            }
        });
        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTheme(R.style.dartTheme);
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
                    Intent intent = new Intent(Setting.this, com.example.cashbook.HelpActivity.class);
                    startActivity(intent);
                    finish();

            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }
}