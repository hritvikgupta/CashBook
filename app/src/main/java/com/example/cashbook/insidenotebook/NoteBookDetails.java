package com.example.cashbook.insidenotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cashbook.DialogFragment;
import com.example.cashbook.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NoteBookDetails extends AppCompatActivity  implements DialogInsideFragment.dialogInsideClicked, DatePickerDialog.OnDateSetListener, BalanceFragment.mainBalance{

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






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_book_details);
        if(savedInstanceState != null)
        {
            currentNet = Integer.parseInt(savedInstanceState.getString("Net"));
        }

        fragmentManager = this.getSupportFragmentManager();
        bf = (BalanceFragment) fragmentManager.findFragmentById(R.id.fragmentContainerView);

        //eBook = new ArrayList<expenseBook>();
        index = Integer.parseInt(getIntent().getStringExtra("index"));

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


        eAdapter = new NoteBookDetailsAdapter(ApplicationClass.lol2.get(index));
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

        date = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault()).format(new Date());






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

        if(pos ==1) {
            Dialog dialogview = dialog.getDialog();
            amountInOut = dialogview.findViewById(R.id.amountInOut);
            Tag = dialogview.findViewById(R.id.insideTag);
            createExpense(amountInOut.getText().toString(), Tag.getText().toString(),"Green");
            aI = Integer.parseInt(amountInOut.getText().toString());
            currentin = ApplicationClass.mBook.get(index).getAmountIn();
            currentin = currentin + aI;
            //Toast.makeText(NoteBookDetails.this, "Index"+index, Toast.LENGTH_SHORT).show();
            ApplicationClass.mBook.get(index).setAmountIn(currentin);
            //Use this Method or below line to set the text live rather clicking back and forth again
            //And don't use the interface method of Balance Fragment that was Created if
            //you want to update value live
            bf.inAmount.setText(String.valueOf(currentin));
            setNetBalanceStatement();
            if(currentin>currentout)
            {
                bf.netBalance.setTextColor(Color.parseColor("#4CAF50"));
            }

            //createNetBook();
        }
        else {
            Dialog dV = dialog.getDialog();
            amountInOut = dV.findViewById(R.id.amountInOut);
            Tag = dV.findViewById(R.id.insideTag);
            createExpense(amountInOut.getText().toString(), Tag.getText().toString(),"Red");
            aO = Integer.parseInt(amountInOut.getText().toString());
            //currentout = ApplicationClass.mBook.get(index).getAmountout();
            currentout = currentout - aO;
            ApplicationClass.mBook.get(index).setAmountout(currentout);
            bf.outAmount.setText(String.valueOf(currentout));
            setNetBalanceStatement();
            //createNetBook();

        }
    }

    @Override
    public void onCancelClicked(DialogInsideFragment dialog) {



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
        nb.setText(String.valueOf(ApplicationClass.mBook.get(index).getNetBalance()));
        iA.setText(String.valueOf(ApplicationClass.mBook.get(index).getAmountIn()));
        oA.setText(String.valueOf(ApplicationClass.mBook.get(index).getAmountout()));

    }

    public void setNetBalanceStatement()
    {
        //currentNet = ApplicationClass.mBook.get(index).getNetBalance();
        int in = currentin;
        int out = currentout;
        currentNet = in + out;
        if(currentNet<0)
        {
            bf.netBalance.setTextColor(Color.parseColor("#D32F2F"));
        }
        //Toast.makeText(NoteBookDetails.this,"current" + currentNet,Toast.LENGTH_SHORT).show();
        ApplicationClass.mBook.get(index).setNetBalance(currentNet);
        bf.netBalance.setText(String.valueOf(ApplicationClass.mBook.get(index).getNetBalance()));


    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Net",String.valueOf(currentNet));
    }


}