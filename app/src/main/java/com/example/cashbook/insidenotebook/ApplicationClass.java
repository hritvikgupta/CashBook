package com.example.cashbook.insidenotebook;

import android.app.Application;

import com.example.cashbook.Books;
import com.example.cashbook.insidenotebook.expenseBook;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Books> book;
    public static ArrayList<com.example.cashbook.insidenotebook.expenseBook> ebook;
    public static ArrayList<com.example.cashbook.insidenotebook.MaintainFinalBalance> mBook;

    @Override
    public void onCreate() {
        super.onCreate();

        book = new ArrayList<Books>();

        Books c1 = new Books("volkawagen", "290499");
        Books c2 = new Books("nissan","08071970" );

        book.add(c1);
        book.add(c2);

        ebook = new ArrayList<com.example.cashbook.insidenotebook.expenseBook>();


        mBook = new ArrayList<MaintainFinalBalance>();
        MaintainFinalBalance m1 = new MaintainFinalBalance(0,0,0);
        mBook.add(m1);



    }
}
