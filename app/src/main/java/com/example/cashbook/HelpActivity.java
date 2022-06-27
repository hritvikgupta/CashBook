package com.example.cashbook;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity implements BottomFragment.options{

    ListView helpList;
    Context context;
    Resources resources;
    Boolean langHind;
    String lang;
    SharedPreferences.Editor clickColor;
    ArrayList<String> descriptions;
    BottomSheetBehavior behavior;
    private int[] answers = {R.string.defination,R.string.create, R.string.edit, R.string.deleterename,R.string.search, R.string.changeLanguage};
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
        descriptions.add("How to create a new expense book?");
        descriptions.add("How to edit entries inside expense book?");
        descriptions.add("How to delete or rename notebook?");
        descriptions.add("How to search added expense books?");
        descriptions.add("How to change language inside application?");



        ArrayAdapter ad = new ArrayAdapter(HelpActivity.this, android.R.layout.simple_list_item_1,descriptions);
        helpList.setAdapter(ad);

        helpList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(HelpActivity.this, "Help" + i, Toast.LENGTH_SHORT).show();
                createBottomSheetDialogHelp(i);
            }
        });

        setActionBarColors();
    }

    public void createBottomSheetDialogHelp(int position)
    {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HelpActivity.this);
        bottomSheetDialog.setContentView(R.layout.helpbottomlayout);
        setupFullHeight(bottomSheetDialog);
        ImageView backBottom = bottomSheetDialog.findViewById(R.id.backBottom);
        backBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        TextView tvTitle = bottomSheetDialog.findViewById(R.id.tvTitle);
        TextView tvAns = bottomSheetDialog.findViewById(R.id.tvAns);
        tvTitle.setText(descriptions.get(position));
        tvAns.setText(answers[position]);
        bottomSheetDialog.show();
    }



    private void setupFullHeight(BottomSheetDialog bottomSheetDialog) {
        FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();

        int windowHeight = getWindowHeight();
        if (layoutParams != null) {
            layoutParams.height = windowHeight;
        }
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }


    private int getWindowHeight() {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        HelpActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
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