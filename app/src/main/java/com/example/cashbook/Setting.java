package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cashbook.insidenotebook.ApplicationClass;
import com.google.android.material.bottomsheet.BottomSheetDialog;

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
    ImageView editButtonNew;
    EditText editNumber, editName;
    Button saveEdit, CancelEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_login);
        setTitle("Settings");

        editButtonNew = findViewById(R.id.editButtonNew);
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
        setIdentity();
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


        editButtonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showBottomSheetDialog();



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
            nameId.setText(ApplicationClass.userIdentity.get(1).getUserName());
            numId.setText(ApplicationClass.userIdentity.get(1).getUserNumber());
        }
    }
}