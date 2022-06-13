package com.example.cashbook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.InputStream;
import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity implements BottomFragment.options{

    ListView helpList;
    Context context;
    Resources resources;
    Boolean langHind;
    String lang;
    SharedPreferences.Editor clickColor;
    ArrayList<String> descriptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        SharedPreferences sharedPreferences = getSharedPreferences("SP",MODE_PRIVATE);
        clickColor = sharedPreferences.edit();
        langHind = sharedPreferences.getBoolean("langHind", false);
        if(langHind)
            lang = "hi";
        else
            lang = "en";

        context = LocaleHelper.setLocale(HelpActivity.this,lang );
        resources = context.getResources();
        setTitle(resources.getString(R.string.HelpandSupport));
        helpList = findViewById(R.id.helplist);
        descriptions = new ArrayList<String>();
        descriptions.add("What is Expense Book?");
        descriptions.add("How to BackDated Entries ?");
        ArrayAdapter ad = new ArrayAdapter(HelpActivity.this, android.R.layout.simple_list_item_1,descriptions);
        helpList.setAdapter(ad);

        helpList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(HelpActivity.this, "Help" + i, Toast.LENGTH_SHORT).show();
                createBottomSheetDialogHelp(i);
            }
        });

        setActionBarColors();
    }

    public void createBottomSheetDialogHelp(int position)
    {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HelpActivity.this);
        bottomSheetDialog.setContentView(R.layout.helpbottomlayout);
        TextView tvTitle = bottomSheetDialog.findViewById(R.id.tvTitle);
        TextView tvAns = bottomSheetDialog.findViewById(R.id.tvAns);
        tvTitle.setText(descriptions.get(position));
        tvAns.setText(R.string.defination);
        bottomSheetDialog.show();
    }

    @Override
    public void OnOptionClicked(ImageView iv1, ImageView iv2, ImageView iv3) {
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickColor.putBoolean("Book", true);
                clickColor.putBoolean("Help", false);
                clickColor.putBoolean("Setting", false);
                clickColor.apply();
                Intent intent = new Intent(HelpActivity.this, com.example.cashbook.MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickColor.putBoolean("Book", false);
                clickColor.putBoolean("Help", true);
                clickColor.putBoolean("Setting", false);
                clickColor.apply();

            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    clickColor.putBoolean("Book", false);
                    clickColor.putBoolean("Help", false);
                    clickColor.putBoolean("Setting", true);
                    clickColor.apply();
                    Intent intent = new Intent(HelpActivity.this, com.example.cashbook.Setting.class);
                    startActivity(intent);
                    finish();

            }
        });


    }

    public void setActionBarColors()
    {
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(getColor(R.color.lightColor));
        ColorDrawable textDrawable = new ColorDrawable(getColor(R.color.textColor));
        actionBar.setBackgroundDrawable(getDrawable(R.drawable.background));
        actionBar.setTitle(HtmlCompat.fromHtml("<font color="+getColor(R.color.action)+">"+resources.getString(R.string.HelpandSupport)+"</font>", HtmlCompat.FROM_HTML_MODE_LEGACY));

    }


}