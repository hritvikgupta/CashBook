package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class introductionSplash extends AppCompatActivity {


    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_splash);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //https://abhiandroid.com/programming/splashscreen

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(introductionSplash.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}