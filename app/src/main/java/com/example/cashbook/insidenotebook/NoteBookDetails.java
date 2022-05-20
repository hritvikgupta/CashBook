package com.example.cashbook.insidenotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
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

public class NoteBookDetails extends AppCompatActivity  implements DialogInsideFragment.dialogInsideClicked, DatePickerDialog.OnDateSetListener {

    TextView testing;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter eAdapter;
    RecyclerView recyclerView;
    Button cashButtonIn, cashButtonOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_book_details);

        //eBook = new ArrayList<expenseBook>();
        cashButtonIn = findViewById(R.id.cashInButton);
        cashButtonOut = findViewById(R.id.cashOutButton);

        recyclerView = findViewById(R.id.elist);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(NoteBookDetails.this);
        recyclerView.setLayoutManager(layoutManager);


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

    }

    @Override
    public void onSaveClicked(DialogInsideFragment dialog) {
                Toast.makeText(NoteBookDetails.this, "Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelClicked(DialogInsideFragment dialog) {

    }

    @Override
    public void dateSetting(Button dateButton2) {

    }
}