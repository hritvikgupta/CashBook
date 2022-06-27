package com.example.cashbook.insidenotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cashbook.DialogFragment;
import com.example.cashbook.LocaleHelper;
import com.example.cashbook.MainActivity;
import com.example.cashbook.R;
import com.example.cashbook.cashBookAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NoteBookDetails extends AppCompatActivity  implements DialogInsideFragment.dialogInsideClicked, DatePickerDialog.OnDateSetListener, BalanceFragment.mainBalance, NoteBookDetailsAdapter.LongClick{

    TextView dateText2;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter eAdapter;
    RecyclerView recyclerView;
    Button cashButtonIn, cashButtonOut,datebutton2;
    EditText amountInOut, Tag;
    BalanceFragment bf;
    FragmentManager fragmentManager;
    String selectedDate;
    int currentin = 0;
    int currentout =0;
    int currentNet =0;
    int aI = 0;
    int nB = 0;
    int aO = 0;
    int sum = 0;
    int sub =0;
    int index;
    Bundle bundle;
    int pos;
    boolean clicked = false;
    String date;
    Boolean longClick = false;
    int removePosition;
    String itemColor;
    int removingCash = 0;
    String itemC;
    int new_in =0 , new_out =0;
    Boolean langHind;
    String lang;
    Context context;
    Resources resources;
    SharedPreferences.Editor mainBalColor;
    Boolean mainColor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_book_details);
        sharedPreferences = getSharedPreferences("SP",MODE_PRIVATE);
        mainBalColor = sharedPreferences.edit();
        langHind = sharedPreferences.getBoolean("langHind", false);
        mainColor = sharedPreferences.getBoolean("Green",false);

        if(langHind)
        {
            lang = "hi";
        }
        else
        {
            lang = "en";
        }
        context = LocaleHelper.setLocale(NoteBookDetails.this,lang);
        resources = context.getResources();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState != null)
        {
            currentNet = Integer.parseInt(savedInstanceState.getString("Net"));
        }

        fragmentManager = this.getSupportFragmentManager();
        bf = (BalanceFragment) fragmentManager.findFragmentById(R.id.fragmentContainerView);

        //eBook = new ArrayList<expenseBook>();
        index = Integer.parseInt(getIntent().getStringExtra("index"));
        actionBar.setTitle(ApplicationClass.book.get(index).getName());
        //Toast.makeText(NoteBookDetails.this, "Clicked" + index,Toast.LENGTH_SHORT).show();

        cashButtonIn = findViewById(R.id.cashInButton);
        cashButtonOut = findViewById(R.id.cashOutButton);

        recyclerView = findViewById(R.id.elist);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(NoteBookDetails.this);
        recyclerView.setLayoutManager(layoutManager);

        bundle = new Bundle();
        bundle.putInt("Index", index);
        bf.setArguments(bundle);

        //When We Pressed Back from the current state then we are not been able to see the
        //varibles values that has been set earlier
        //Therefore to display those values when backPressed Also use setText on MainBalance class here along with
        //setText that was written in saveClick and OtherClick as well


        eAdapter = new NoteBookDetailsAdapter(ApplicationClass.lol2.get(index), this);
        recyclerView.setAdapter(eAdapter);


        //As we can have only one application class in android.manifest file. Therefore to
        //App will stop running when we implement another application class. Therefore,
        //for the app to keep running define new details of book or ebook in one class only
        // and then use it. Another way to do is to extent one ApplicationClass from another
        //Such as ApplictionClassExpenseBook extends ApplicationClass But as this is not working
        // Therefore we stick to first method
        //eAdapter = new NoteBookDetailsAdapter(ApplicationClass.ebook);
        //recyclerView.setAdapter(eAdapter);
        cashButtonIn.setBackgroundColor(Color.parseColor("#388E3C"));
        cashButtonOut.setBackgroundColor(Color.parseColor("#D32F2F"));

        cashButtonIn.setText(resources.getString(R.string.CashIN));
        cashButtonOut.setText(resources.getString(R.string.CashOut));
        cashButtonIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = 1;
                showInsideDialog();
            }
        });

        cashButtonOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos =2;
                showInsideDialog();
            }
        });

        date = new SimpleDateFormat("EEE, MMMM d, yyyy", Locale.getDefault()).format(new Date());
/*
        if(mainColor)
        {
            bf.netBalance.setTextColor(Color.parseColor("#4CAF50"));
        }
        else
        {
            bf.netBalance.setTextColor(Color.parseColor("#D32F2F"));
        }

 */




    }


    public void showInsideDialog()
    {
        DialogInsideFragment dialogInsideFragment =  new DialogInsideFragment();
        dialogInsideFragment.show(getSupportFragmentManager(),"Amount Dialog Fragment");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar mcalender = Calendar.getInstance();
        mcalender.set(Calendar.YEAR, i);
        mcalender.set(Calendar.MONTH, i1);
        mcalender.set(Calendar.DAY_OF_MONTH, i2);
        //Toast.makeText(NoteBookDetails.this,"DateSelected"+selectedDate,Toast.LENGTH_SHORT).show();
        selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mcalender.getTime());
        datebutton2.setText(selectedDate);


    }

    @Override
    public void onSaveClicked(DialogInsideFragment dialog) {
        Dialog dvDismiss = dialog.getDialog();
        amountInOut = dvDismiss.findViewById(R.id.amountInOut);
        Tag = dvDismiss.findViewById(R.id.insideTag);

        if (amountInOut.getText().toString().isEmpty() && Tag.getText().toString().isEmpty()) {
            Toast.makeText(NoteBookDetails.this, "Clicked", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else {

        if (pos == 1 && longClick == false) {
            itemColor = "Green";
            Dialog dialogview = dialog.getDialog();
            amountInOut = dialogview.findViewById(R.id.amountInOut);
            Tag = dialogview.findViewById(R.id.insideTag);
            createExpense(amountInOut.getText().toString(), Tag.getText().toString(), "Green");
            aI = Integer.parseInt(amountInOut.getText().toString());
            currentin = ApplicationClass.mBook_new.get(index).getAmountIn();
            currentin = currentin + aI;
            //Toast.makeText(NoteBookDetails.this, "Index"+index, Toast.LENGTH_SHORT).show();
            ApplicationClass.mBook_new.get(index).setAmountIn(currentin);
            //Use this Method or below line to set the text live rather clicking back and forth again
            //And don't use the interface method of Balance Fragment that was Created if
            //you want to update value live
            bf.inAmount.setText(String.valueOf(currentin));
            setNetBalanceStatement();
            if (currentin > currentout) {
                mainBalColor.putBoolean("Green", true);
                bf.netBalance.setTextColor(Color.parseColor("#4CAF50"));
            }

            //createNetBook();
        } else if (pos == 2 && longClick == false) {
            itemColor = "Red";
            Dialog dV = dialog.getDialog();
            amountInOut = dV.findViewById(R.id.amountInOut);
            Tag = dV.findViewById(R.id.insideTag);
            createExpense(amountInOut.getText().toString(), Tag.getText().toString(), "Red");
            aO = Integer.parseInt(amountInOut.getText().toString());
            //currentout = ApplicationClass.mBook.get(index).getAmountout();
            currentout = currentout - aO;
            ApplicationClass.mBook_new.get(index).setAmountout(currentout);
            bf.outAmount.setText(String.valueOf(currentout));
            setNetBalanceStatement();
            //createNetBook();

        } else if (longClick == true) {

            longClick = false;
            itemC = ApplicationClass.lol2.get(index).get(removePosition).getTotalbalanceremain();
            Dialog dv2 = dialog.getDialog();
            amountInOut = dv2.findViewById(R.id.amountInOut);
            Tag = dv2.findViewById(R.id.insideTag);
            if (!amountInOut.getText().toString().isEmpty() || !Tag.getText().toString().isEmpty()) {
                removingCash = Integer.parseInt(ApplicationClass.lol2.get(index).get(removePosition).geteAmount());
                //Toast.makeText(NoteBookDetails.this, "val"+removingCash,Toast.LENGTH_SHORT).show();
                ApplicationClass.lol2.get(index).remove(removePosition);
                //Toast.makeText(NoteBookDetails.this, "Pos" + ApplicationClass.lol2.get(index),Toast.LENGTH_SHORT).show();
                eAdapter.notifyDataSetChanged();

            }
            if (itemC.equals("Green")) {
                createExpense(amountInOut.getText().toString(), Tag.getText().toString(), itemC);
                aI = Integer.parseInt(amountInOut.getText().toString());
                currentin = ApplicationClass.mBook_new.get(index).getAmountIn();
                new_in = currentin - removingCash;
                new_in = new_in + aI;
                //Toast.makeText(NoteBookDetails.this, "Index"+index, Toast.LENGTH_SHORT).show();
                ApplicationClass.mBook_new.get(index).setAmountIn(new_in);
                //Use this Method or below line to set the text live rather clicking back and forth again
                //And don't use the interface method of Balance Fragment that was Created if
                //you want to update value live
                bf.inAmount.setText(String.valueOf(new_in));
                setNetBalanceStatement();
                if (currentin > currentout) {
                    bf.netBalance.setTextColor(Color.parseColor("#4CAF50"));

                }

            } else if (itemC.equals("Red")) {
                createExpense(amountInOut.getText().toString(), Tag.getText().toString(), "Red");
                aO = Integer.parseInt(amountInOut.getText().toString());
                //currentout = ApplicationClass.mBook.get(index).getAmountout();
                currentout = ApplicationClass.mBook_new.get(index).getAmountout();
                new_out = currentout + removingCash;
                new_out = new_out - aO;
                ApplicationClass.mBook_new.get(index).setAmountout(new_out);
                bf.outAmount.setText(String.valueOf(new_out));
                setNetBalanceStatement();
            }
            //Toast.makeText(NoteBookDetails.this, "Pos" + ApplicationClass.lol2.get(index).get(removePosition).getTotalbalanceremain(),Toast.LENGTH_SHORT).show();


        }
    }

    }

    @Override
    public void onCancelClicked(DialogInsideFragment dialog) {
        dialog.dismiss();


    }

    @Override
    public void dateSetting(Button dateButton2) {
        this.datebutton2 = dateButton2;
        datebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = true;
                com.example.cashbook.DatePicker datePicker = new com.example.cashbook.DatePicker();
                datePicker.show(getSupportFragmentManager(),"Date");
            }
        });

    }

    @Override
    public void onDialogInsideLangSet(DialogInsideFragment dialog) {

        DialogInsideFragment dialogInsideFragment = dialog;
        dialogInsideFragment.dateButton2.setText(resources.getString(R.string.selectDateButton));
        dialogInsideFragment.amountInOut.setHint(resources.getString(R.string.amountinout));
        dialogInsideFragment.insideTag.setHint(resources.getString(R.string.insidetag));
    }

    public void createExpense(String amount, String tag,String color)
    {

        ArrayList<expenseBook> ebook = new ArrayList<expenseBook>();
        if(!clicked)
        {
            selectedDate = date;
        }
        else
        {
            clicked = false;
        }
        expenseBook e1 = new expenseBook(tag, amount, selectedDate, color);
        ApplicationClass.lol2.get(index).add(e1);
        eAdapter.notifyDataSetChanged();

    }

    @Override
    public void onMainBalance(TextView nb, TextView iA, TextView oA) {
        //MaintainFinalBalance m1 = new MaintainFinalBalance(aI,)

        //Don't Use this method if you want to update the values live after clicking
        //Button that is inside fragment
        //This method is initialized once every time we click items on Main Activity
        //Therefore can't be updated always or live


        //When We Pressed Back from the current state then we are not been able to see the
        //varibles values that has been set earlier
        //Therefore to display those values when backPressed Also use this along with
        //setText that was written in saveClick and OtherClick as well
        nb.setText(String.valueOf(ApplicationClass.mBook_new.get(index).getNetBalance()));
        iA.setText(String.valueOf(ApplicationClass.mBook_new.get(index).getAmountIn()));
        oA.setText(String.valueOf(ApplicationClass.mBook_new.get(index).getAmountout()));

    }

    @Override
    public void onMainBalanceLangset(BalanceFragment balance) {
            balance.nB.setText(resources.getString(R.string.NetBalance));
            balance.tIn.setText(resources.getString(R.string.TotalIn));
            balance.tOut.setText(resources.getString(R.string.TotalOut));

    }

    public void setNetBalanceStatement()
    {
        int in;
        int out;
        //currentNet = ApplicationClass.mBook.get(index).getNetBalance();

        in = ApplicationClass.mBook_new.get(index).getAmountIn();
        out = ApplicationClass.mBook_new.get(index).getAmountout();

        currentNet = in+out;
        if(currentNet<0)
        {
            mainBalColor.putBoolean("Green",false);
            bf.netBalance.setTextColor(Color.parseColor("#D32F2F"));

        }

        //Toast.makeText(NoteBookDetails.this,"current" + currentout,Toast.LENGTH_SHORT).show();
        ApplicationClass.mBook_new.get(index).setNetBalance(currentNet);
        bf.netBalance.setText(String.valueOf(ApplicationClass.mBook_new.get(index).getNetBalance()));
        ApplicationClass.book.get(index).setAmount(currentNet);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Net",String.valueOf(currentNet));
    }


    @Override
    public void onLongItemsClicked(int index) {
        //Toast.makeText(NoteBookDetails.this, "Position is " + index, Toast.LENGTH_SHORT).show();
        longClick =true;
        removePosition = index;
        showInsideDialog();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                Intent pass = new Intent(NoteBookDetails.this,MainActivity.class);
                startActivity(pass);
                return true;
        }


        return super.onOptionsItemSelected(item);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(NoteBookDetails.this, MainActivity.class);
        startActivity(back);
    }

    public void removeInsideNotebook(int position)
    {
        //ApplicationClass.lol2.remove(position);
        Toast.makeText(NoteBookDetails.this, "Click", Toast.LENGTH_SHORT).show();
        //ApplicationClass.mBook_new.replace(position,ApplicationClass.mBook_new.get(position+1));
    }
}