package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.cashbook.insidenotebook.ApplicationClass;
import com.example.cashbook.insidenotebook.NoteBookDetails;

public class introductionSplash extends AppCompatActivity {


    Handler handler;
    SharedPreferences sharedPreferences;
    Boolean checkLog, mainLog = false, loginLog = false;
    SharedPreferences.Editor checkEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_splash);
        /*
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        SharedPreferences sharedPreferences = getSharedPreferences("SP",MODE_PRIVATE);
        SharedPreferences.Editor clickColor = sharedPreferences.edit();
        if(nightModeFlags == Configuration.UI_MODE_NIGHT_YES)
        {
            //Toast.makeText(MainActivity.this, "hola",Toast.LENGTH_SHORT).show();
            clickColor.putBoolean("darkon", false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }

         */

        sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
        //checkEdit.putBoolean("checkLog", false);
        checkLog = sharedPreferences.getBoolean("checkLog", false);
        if(checkLog)
        {
            mainLog = true;

        }
        else
        {
            loginLog = true;
        }
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }





        //https://abhiandroid.com/programming/splashscreen

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mainLog)
                {
                    //Toast.makeText(introductionSplash.this, "Here"+checkLog, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(introductionSplash.this,MainActivity.class);
                    intent.putExtra("restart", true);
                    ApplicationClass.restart = true;
                    Intent intent_nb = new Intent(introductionSplash.this, NoteBookDetails.class);
                    intent_nb.putExtra("restart", true);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    //Toast.makeText(introductionSplash.this, "NO Here", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(introductionSplash.this,LOGIN.class);
                    startActivity(intent);
                    finish();
                }

            }
        },1500);
    }
}