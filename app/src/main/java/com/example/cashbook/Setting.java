package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cashbook.insidenotebook.ApplicationClass;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//
public class Setting extends AppCompatActivity implements BottomFragment.options {

    //https://www.youtube.com/watch?v=hynFpLR5S34
    ImageView personImage, editUser, night;
    TextView nameId, numId;
    Switch s4;
    Boolean darkon, langHind, colorEng, colorHind;
    Boolean b ,b2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit, langEdit, colorEdit, typeEdit;
    RelativeLayout relativeLayout;
    ImageView editButtonNew;
    EditText editNumber, editName;
    Button saveEdit, CancelEdit;
    TextView languageText, bussiness;
    Context context;
    Resources resources;
    TextView notification, applock,theme,language,share, logout, generalSetting, otherSetting;
    TextView English, Hindi, btmViewLang;
    BottomFragment btmFragment;
    TextView bookFragInst, helpFragInst, settingFragInst;
    FragmentTransaction fragmentTransaction;
    com.example.cashbook.BottomFragment bm;
    com.example.cashbook.DialogFragment df;
    Switch switch2;
    RadioButton rbBussiness, rbPersonal,rbBoth;
    Boolean rbBus, rbPer, rbBo;
    CardView cardView3, cardView4, cardView5, cardView6, cardView7, cardView2;
    ColorStateList s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_login);
        setTitle("Settings");
        bm = new com.example.cashbook.BottomFragment();
        df = new com.example.cashbook.DialogFragment();


        editButtonNew = findViewById(R.id.editButtonNew);
        personImage = findViewById(R.id.personImage);
        nameId = findViewById(R.id.nameID);
        s4 = findViewById(R.id.switch4);
        numId = findViewById(R.id.numID);
        languageText = findViewById(R.id.languageText);
        editUser = findViewById(R.id.editButtonNew);
        notification = findViewById(R.id.notification);
        applock = findViewById(R.id.applock);
        theme = findViewById(R.id.theme);
        language = findViewById(R.id.language);
        share= findViewById(R.id.share);
        logout= findViewById(R.id.logout);
        night = findViewById(R.id.night);
        relativeLayout = findViewById(R.id.relativeScroll);
        switch2 = findViewById(R.id.switch2);
        switch2.setVisibility(View.GONE);
        generalSetting= findViewById(R.id.generalSettings);
        otherSetting = findViewById(R.id.otherSetting);
        bussiness = findViewById(R.id.busTypeText);
        cardView3 = findViewById(R.id.cardView3);
        cardView4 = findViewById(R.id.cardView4);
        cardView5 = findViewById(R.id.cardView5);
        cardView6 = findViewById(R.id.cardView6);
        cardView7 = findViewById(R.id.cardView7);
        cardView2 = findViewById(R.id.cardView2);




        sharedPreferences = getSharedPreferences("SP",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        darkon = sharedPreferences.getBoolean("darkon",false);
        checkDarkMode();
        //setIdentity();

        langEdit = sharedPreferences.edit();
        langHind = sharedPreferences.getBoolean("langHind", false);
        checkLanguage();

        colorEdit = sharedPreferences.edit();
        b2 = sharedPreferences.getBoolean("colorHind", false);
        b = sharedPreferences.getBoolean("colorEng", false);

        typeEdit = sharedPreferences.edit();
        rbBus = sharedPreferences.getBoolean("Bussiness", false);
        rbPer = sharedPreferences.getBoolean("Personal", false);
        rbBo = sharedPreferences.getBoolean("Both", false);
        //checkType();

        setBussinessTypeText();



        if(langHind)
        {
            setLanguage("hi");
            setIdentity();
        }
        else{
            setLanguage("en");
            setIdentity();
        }

        if(darkon)
        {
            relativeLayout.setBackgroundResource(R.drawable.backgroundnight);
            s  = cardView3.getCardBackgroundColor();
        }

        else
        {
            relativeLayout.setBackgroundResource(R.drawable.background);
            checkLanguage();
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


        editButtonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showBottomSheetDialog();



            }
        });

        languageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                languageBottomDialog();

            }


        });
        bussiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Setting.this,"Clicked", Toast.LENGTH_SHORT).show();
                bussinessBottomDialog();
                checkType();

            }
        });


        setCardViewColor();

        //ColorStateList s = cardView3.getCardBackgroundColor();

        //Toast.makeText(Setting.this, ""+cardView3.getCardBackgroundColor(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnOptionClicked(ImageView iv1, ImageView iv2, ImageView iv3) {
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting.this, com.example.cashbook.MainActivity.class);
                startActivity(intent);
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



    public void setLanguage(String lang)
    {
        context = LocaleHelper.setLocale(Setting.this,lang );
        resources = context.getResources();
        nameId.setText(resources.getString(R.string.enter_name));
        numId.setText(resources.getString(R.string.enter_number));
        nameId.setText(resources.getString(R.string.enter_name));
        notification.setText(resources.getString(R.string.notification_widget));
        applock.setText(resources.getString(R.string.app_lock));
        theme.setText(resources.getString(R.string.dark_theme));
        language.setText(resources.getString(R.string.change_language));
        share.setText(resources.getString(R.string.share_with_friends));
        logout.setText(resources.getString(R.string.logout));
        languageText.setText(resources.getString(R.string.selected_language));
        generalSetting.setText(resources.getString(R.string.generalSetting));
        otherSetting.setText(resources.getString(R.string.otherSetting));
        bm.setLanguageBottomFragment(resources.getString(R.string.books),resources.getString(R.string.help),resources.getString(R.string.settings));
        //df.setLanguageDialogFragment(resources.getString(R.string.AddBookTag));

    }


    public void checkLanguage()
    {
        if(langHind)
        {
            setLanguage("hi");
        }
        else
        {
            setLanguage("en");
        }
    }
    private void bussinessBottomDialog() {
        final BottomSheetDialog bottomSheetDialog3 = new BottomSheetDialog(Setting.this);
        bottomSheetDialog3.setContentView(R.layout.bus_type);
        rbBussiness = (RadioButton) bottomSheetDialog3.findViewById(R.id.rbBussiness);
        rbPersonal = bottomSheetDialog3.findViewById(R.id.rbPersonal);
        rbBoth = bottomSheetDialog3.findViewById(R.id.rbBoth);
        /*
        if(rbBus)
        {
            rbBussiness.setChecked(true);
            rbPersonal.setChecked(false);
            rbBoth.setChecked(false);
        }
        else if(rbPer)
        {
            rbBussiness.setChecked(false);
            rbPersonal.setChecked(true);
            rbBoth.setChecked(false);
        }
        else if(rbBo)
        {
            rbBussiness.setChecked(false);
            rbPersonal.setChecked(false);
            rbBoth.setChecked(true);
        }

         */
        //Toast.makeText(Setting.this, ""+rbBus, Toast.LENGTH_SHORT).show();
        rbBussiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Setting.this, "Clicked",Toast.LENGTH_SHORT).show();
                rbBus = true; rbPer = false;  rbBo = false;
                typeEdit.putBoolean("Bussiness", true);
                typeEdit.putBoolean("Personal", false);
                typeEdit.putBoolean("Both", false);
                typeEdit.apply();
                rbBussiness.setChecked(true);
                rbPersonal.setChecked(false);
                rbBoth.setChecked(false);
                setBussinessTypeText();

            }
        });
        rbPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Setting.this, "Clicked",Toast.LENGTH_SHORT).show();
                rbBus = false; rbPer = true;  rbBo = false;
                typeEdit.putBoolean("Bussiness", false).apply();
                typeEdit.putBoolean("Personal", true).apply();
                typeEdit.putBoolean("Both", false).apply();
                rbPersonal.setChecked(true);
                rbBussiness.setChecked(false);
                rbBoth.setChecked(false);
                setBussinessTypeText();

            }
        });
        rbBoth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Setting.this, "Clicked",Toast.LENGTH_SHORT).show();
                rbBus = false; rbPer = false;  rbBo = true;
                typeEdit.putBoolean("Bussiness", false).apply();
                typeEdit.putBoolean("Personal", false).apply();
                typeEdit.putBoolean("Both", true).apply();
                rbPersonal.setChecked(false);
                rbBussiness.setChecked(false);
                rbBoth.setChecked(true);
                setBussinessTypeText();

            }
        });



        bottomSheetDialog3.show();
    }
    public void setBussinessTypeText()
    {
        if(rbBus)
        {
            bussiness.setText("Bussiness");
        }
        else if(rbPer)
        {
            bussiness.setText("Personal");
        }
        else if(rbBo)
        {
            bussiness.setText("Both");
        }
        else
        {
            bussiness.setText("Account");
        }
    }
    public void checkType()
    {
        if(rbBus)
        {
            rbBussiness.setChecked(true);
            rbPersonal.setChecked(false);
            rbBoth.setChecked(false);
        }
        else if(rbPer)
        {
            rbBussiness.setChecked(false);
            rbPersonal.setChecked(true);
            rbBoth.setChecked(false);
        }
        else if(rbBo)
        {
            rbBussiness.setChecked(false);
            rbPersonal.setChecked(false);
            rbBoth.setChecked(true);
        }
    }

        private void languageBottomDialog()
    {
        final BottomSheetDialog bottomSheetDialog2 = new BottomSheetDialog(Setting.this);
        bottomSheetDialog2.setContentView(R.layout.language_dialogue);
        English = bottomSheetDialog2.findViewById(R.id.English);
        Hindi = bottomSheetDialog2.findViewById(R.id.Hindi);

        //Toast.makeText(Setting.this, ""+b+b2,Toast.LENGTH_SHORT).show();
        if(b)
        {
            English.setBackgroundColor(Color.GREEN);
        }
        else if(b2)
        {
            Hindi.setBackgroundColor(Color.GREEN);
        }
        btmViewLang = bottomSheetDialog2.findViewById(R.id.languageChangeBottom);
        English.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLanguage("en");
                b = true;
                b2 = false;
                colorEdit.putBoolean("colorEng", b);
                colorEdit.putBoolean("colorHind", b2);
                colorEdit.apply();
                English.setBackgroundColor(Color.GREEN);
                if(darkon){
                    b = true;
                    Hindi.setBackgroundColor(Color.BLACK);}
                else
                    Hindi.setBackgroundColor(Color.WHITE);
                langEdit.putBoolean("langHind", false);
                btmViewLang.setText("Change Language");
                langEdit.apply();
            }
        });
        Hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLanguage("hi");
                b2 = true;
                b = false;
                colorEdit.putBoolean("colorEng", b);
                colorEdit.putBoolean("colorHind", b2);
                colorEdit.apply();
                langEdit.putBoolean("langHind", true);
                if(darkon){
                    b2 = true;
                    English.setBackgroundColor(Color.BLACK);}
                else{
                    English.setBackgroundColor(Color.WHITE);}
                Hindi.setBackgroundColor(Color.GREEN);
                langEdit.apply();
                btmViewLang.setText("भाषा बदलो");
            }
        });
        bottomSheetDialog2.show();
    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Setting.this);
        bottomSheetDialog.setContentView(R.layout.bottom_dialog);
        editName = bottomSheetDialog.findViewById(R.id.NameEdit);
        editNumber = bottomSheetDialog.findViewById(R.id.NumberEdit);
        Button sEdit = bottomSheetDialog.findViewById(R.id.sEdit);
        Button cancel = bottomSheetDialog.findViewById(R.id.CancelEdit);
        ImageView img = bottomSheetDialog.findViewById(R.id.editImage);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = Environment.getExternalStorageDirectory() + "/" + "Pictures" + "/";
                Uri uri = Uri.parse(path);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(uri, "*/*");
                startActivity(intent);
            }
        });

        sEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //Toast.makeText(Setting.this, "Clicked", Toast.LENGTH_SHORT).show();
                ApplicationClass.userIdentity.add(new UserIdentity(editName.getText().toString(),editNumber.getText().toString()));
                setIdentity();
                bottomSheetDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }
    public void setIdentity()
    {
        if(ApplicationClass.userIdentity.size() == 1)
        {
            nameId.setText(ApplicationClass.userIdentity.get(0).getUserName());
            numId.setText(ApplicationClass.userIdentity.get(0).getUserNumber());
        }
       else
        {
            nameId.setText(ApplicationClass.userIdentity.get(ApplicationClass.userIdentity.size()-1).getUserName());
            numId.setText(ApplicationClass.userIdentity.get(ApplicationClass.userIdentity.size()-1).getUserNumber());
        }
    }

    public void myAlarm() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 47);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTime().compareTo(new Date()) < 0)
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        }

    }

    public void setCardViewColor()
    {
        cardView3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(darkon)
                    {
                        cardView3.setCardBackgroundColor(Color.parseColor("#696969"));
                    }
                    else
                    {
                        cardView3.setCardBackgroundColor(Color.parseColor("#B0C4DE"));

                    }
                    return true;
                } else {
                    if(darkon)
                        cardView3.setCardBackgroundColor(s);
                    else
                        cardView3.setCardBackgroundColor(Color.WHITE);

                }

                return false;
            }
        });
        cardView4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(darkon)
                    {
                        cardView4.setCardBackgroundColor(Color.parseColor("#696969"));
                    }
                    else
                    {
                        cardView4.setCardBackgroundColor(Color.parseColor("#B0C4DE"));

                    }
                    return true;
                } else {
                    if(darkon)
                        cardView4.setCardBackgroundColor(s);
                    else
                        cardView4.setCardBackgroundColor(Color.WHITE);

                }

                return false;
            }
        });
        cardView5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(darkon)
                    {
                        cardView5.setCardBackgroundColor(Color.parseColor("#696969"));
                    }
                    else
                    {
                        cardView5.setCardBackgroundColor(Color.parseColor("#B0C4DE"));

                    }                    return true;
                } else {
                    if(darkon)
                        cardView5.setCardBackgroundColor(s);
                    else
                        cardView5.setCardBackgroundColor(Color.WHITE);

                }

                return false;
            }
        });
        cardView6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(darkon)
                    {
                        cardView6.setCardBackgroundColor(Color.parseColor("#696969"));
                    }
                    else
                    {
                        cardView6.setCardBackgroundColor(Color.parseColor("#B0C4DE"));

                    }                    return true;
                } else {
                    if(darkon)
                        cardView6.setCardBackgroundColor(s);
                    else
                        cardView6.setCardBackgroundColor(Color.WHITE);

                }

                return false;
            }
        });
        cardView7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(darkon)
                    {
                        cardView7.setCardBackgroundColor(Color.parseColor("#696969"));
                    }
                    else
                    {
                        cardView7.setCardBackgroundColor(Color.parseColor("#B0C4DE"));

                    }                    return true;
                } else {
                    if(darkon)
                        cardView7.setCardBackgroundColor(s);
                    else
                        cardView7.setCardBackgroundColor(Color.WHITE);

                }

                return false;
            }
        });
        cardView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(darkon)
                    {
                        cardView2.setCardBackgroundColor(Color.parseColor("#696969"));
                    }
                    else
                    {
                        cardView2.setCardBackgroundColor(Color.parseColor("#B0C4DE"));

                    }                    return true;
                } else {
                    if(darkon)
                        cardView2.setCardBackgroundColor(s);
                    else
                        cardView2.setCardBackgroundColor(Color.WHITE);

                }

                return false;
            }
        });
    }
}