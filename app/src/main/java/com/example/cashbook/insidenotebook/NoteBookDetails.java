package com.example.cashbook.insidenotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cashbook.R;

public class NoteBookDetails extends AppCompatActivity  {

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







    }
}