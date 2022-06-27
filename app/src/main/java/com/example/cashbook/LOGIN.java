package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cashbook.insidenotebook.ApplicationClass;

public class LOGIN extends AppCompatActivity {
    Button btnloginUser;
    EditText code, number;
    String num;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor checkEdit, logEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnloginUser = findViewById(R.id.btnloginuser);
        number = findViewById(R.id.phone);
        code  =findViewById(R.id.code);
        checkBox = findViewById(R.id.checkBox);


        getSupportActionBar().show();
        getSupportActionBar().setTitle("Login");
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
        checkEdit = sharedPreferences.edit();
        checkEdit.putBoolean("checkLog", false);
        //ApplicationClass.userIdentity.add(new UserIdentity(number.getText().toString(),String.valueOf(number.getText())));
        //ApplicationClass.userIdentity.add(new UserIdentity(String.valueOf(code.getId()), "Enter Number"));
       // ApplicationClass.userIdentity.get(0).setUserName(code.getText().toString()+number.getText().toString());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkBox.setChecked(true);
            }
        });

        btnloginUser = findViewById(R.id.btnloginuser);
        btnloginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ApplicationClass.userIdentity.add(new UserIdentity(String.valueOf(code.getText()), "Enter Number"));
                ///Toast.makeText(LOGIN.this, "Some"+code.getText().toString(),Toast.LENGTH_SHORT).show();
                num =code.getText().toString()+number.getText().toString();
                if(num.isEmpty())
                {
                    Toast.makeText(LOGIN.this, "Please Enter Credentials",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    checkEdit.putBoolean("checkLog", true);
                    checkEdit.apply();
                    ApplicationClass.userIdentity.get(0).setUserNumber(num);
                    logEdit = sharedPreferences.edit();
                    logEdit.putString("Number", num);
                    logEdit.apply();
                    Intent intent=new Intent(LOGIN.this,MainActivity.class);
                    intent.putExtra("number",code.getText().toString()+number.getText().toString());
                    startActivity(intent);
                    finish();
                }


            }
        });

        if(checkBox.isChecked() && !ApplicationClass.userIdentity.isEmpty())
        {
            Intent intent=new Intent(LOGIN.this,MainActivity.class);
            intent.putExtra("number",code.getText().toString()+number.getText().toString());
            startActivity(intent);
            finish();
        }


    }
}