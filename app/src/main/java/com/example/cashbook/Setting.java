package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.time.temporal.Temporal;
//
public class Setting extends AppCompatActivity implements BottomFragment.options {

    //https://www.youtube.com/watch?v=hynFpLR5S34
    ImageView personImage, editUser, night;
    TextView nameId, numId;
    Switch s4;
    Boolean darkon;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_login);
        setTitle("Settings");


        personImage = findViewById(R.id.personImage);
        nameId = findViewById(R.id.nameID);
        s4 = findViewById(R.id.switch4);
        numId = findViewById(R.id.numID);
        editUser = findViewById(R.id.editButtonNew);
        night = findViewById(R.id.night);
        relativeLayout = findViewById(R.id.relativeScroll);
        sharedPreferences = getSharedPreferences("SP",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        darkon = sharedPreferences.getBoolean("darkon",false);
        checkDarkMode();
        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameId.setText("Hritvik Gupta");
                numId.setText("954534534");
            }
        });
        if(darkon)
        {
            relativeLayout.setBackgroundResource(R.drawable.backgroundnight);

        }
        else
        {
            relativeLayout.setBackgroundResource(R.drawable.background);

        }
        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    setDarkOn();
                    myEdit.putBoolean("darkon",true);
                    myEdit.apply();
                }
                else
                {
                    setDarkOff();
                    myEdit.putBoolean("darkon",false);
                    myEdit.apply();

                }
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
    public void setDarkOn()
    {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }
    public void setDarkOff()
    {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    public void checkDarkMode()
    {
        if(darkon)
        {
            setDarkOn();
            s4.setChecked(true);
        }
    }
}