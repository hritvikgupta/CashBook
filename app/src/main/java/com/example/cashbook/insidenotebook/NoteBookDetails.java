package com.example.cashbook.insidenotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cashbook.DialogFragment;
import com.example.cashbook.R;

import java.text.DateFormat;
import java.util.Calendar;

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
    int aI = 0;
    int nB = 0;
    int aO = 0;
    int sum = 0;
    int sub =0;
    int index;
    int pos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_book_details);

        fragmentManager = this.getSupportFragmentManager();
        bf = (BalanceFragment) fragmentManager.findFragmentById(R.id.fragmentContainerView);
        //eBook = new ArrayList<expenseBook>();
        index = getIntent().getIntExtra("index", 0);

        cashButtonIn = findViewById(R.id.cashInButton);
        cashButtonOut = findViewById(R.id.cashOutButton);

        recyclerView = findViewById(R.id.elist);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(NoteBookDetails.this);
        recyclerView.setLayoutManager(layoutManager);



        MaintainFinalBalance m1 = new MaintainFinalBalance(0,0,0);
        ApplicationClass.mBook.add(m1);
        //As we can have only one application class in android.manifest file. Therefore to
        //App will stop running when we implement another application class. Therefore,
        //for the app to keep running define new details of book or ebook in one class only
        // and then use it. Another way to do is to extent one ApplicationClass from another
        //Such as ApplictionClassExpenseBook extends ApplicationClass But as this is not working
        // Therefore we stick to first method
        eAdapter = new NoteBookDetailsAdapter(ApplicationClass.ebook);
        recyclerView.setAdapter(eAdapter);

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
        selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mcalender.getTime());
        //Toast.makeText(NoteBookDetails.this,"DateSelected"+selectedDate,Toast.LENGTH_SHORT).show();
        datebutton2.setText(selectedDate);

    }

    @Override
    public void onSaveClicked(DialogInsideFragment dialog) {

        if(pos ==1) {
            Dialog dialogview = dialog.getDialog();
            amountInOut = dialogview.findViewById(R.id.amountInOut);
            Tag = dialogview.findViewById(R.id.insideTag);
            createExpense(amountInOut.getText().toString(), Tag.getText().toString());
            aI = Integer.parseInt(amountInOut.getText().toString());
            int current = ApplicationClass.mBook.get(index).getAmountIn();
            current = current + aI;
            ApplicationClass.mBook.get(index).setAmountIn(current);
            //Use this Method or below line to set the text live rather clicking back and forth again
            //And don't use the interface method of Balance Fragment that was Created if
            //you want to update value live
            bf.inAmount.setText(String.valueOf(current));
            setNetBalanceStatement();
        }
        else {
            Dialog dV = dialog.getDialog();
            amountInOut = dV.findViewById(R.id.amountInOut);
            Tag = dV.findViewById(R.id.insideTag);
            createExpense(amountInOut.getText().toString(), Tag.getText().toString());
            aO = Integer.parseInt(amountInOut.getText().toString());
            int current = ApplicationClass.mBook.get(index).getAmountout();
            current = current - aO;
            ApplicationClass.mBook.get(index).setAmountout(current);
            bf.outAmount.setText(String.valueOf(current));
            setNetBalanceStatement();

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
                com.example.cashbook.DatePicker datePicker = new com.example.cashbook.DatePicker();
                datePicker.show(getSupportFragmentManager(),"Date");
            }
        });

    }

    public void createExpense(String amount, String tag)
    {
        expenseBook e1 = new expenseBook(tag,amount,selectedDate, "0000");
        ApplicationClass.ebook.add(e1);
        eAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMainBalance(TextView nb, TextView iA, TextView oA) {
        //MaintainFinalBalance m1 = new MaintainFinalBalance(aI,)

        //Don't Use this method if you want to update the values live after clicking
        //Button that is inside fragment
        //This method is initialized once every time we click items on Main Activity
        //Therefore can't be updated always or live

        //nb.setText(String.valueOf(ApplicationClass.mBook.get(index).getNetBalance()));
        //iA.setText(String.valueOf(ApplicationClass.mBook.get(index).getAmountIn()));
        //oA.setText(String.valueOf(ApplicationClass.mBook.get(index).getAmountout()));

    }

    public void setNetBalanceStatement()
    {
        int currentNet = ApplicationClass.mBook.get(index).getNetBalance();
        int in = ApplicationClass.mBook.get(index).getAmountIn();
        int out = ApplicationClass.mBook.get(index).getAmountout();
        currentNet = in + out;
        ApplicationClass.mBook.get(index).setNetBalance(currentNet);
        bf.netBalance.setText(String.valueOf(currentNet));
    }
}