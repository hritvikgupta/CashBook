package com.example.cashbook.insidenotebook;

import android.app.Application;

import com.example.cashbook.Books;
import com.example.cashbook.insidenotebook.expenseBook;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Books> book;
    public static ArrayList<com.example.cashbook.insidenotebook.expenseBook> ebook;

    @Override
    public void onCreate() {
        super.onCreate();

        book = new ArrayList<Books>();

        Books c1 = new Books("volkawagen", "290499");
        Books c2 = new Books("nissan","08071970" );

        book.add(c1);
        book.add(c2);

        ebook = new ArrayList<com.example.cashbook.insidenotebook.expenseBook>();
        expenseBook e1 = new expenseBook("shop2", "000","ration","000");
        expenseBook e2 = new expenseBook("shop2", "000","ration","000");
        expenseBook e3 = new expenseBook("shop2", "000","ration","000");
        expenseBook e4 = new expenseBook("shop2", "000","ration","000");
        expenseBook e5 = new expenseBook("shop2", "000","ration","000");


        ebook.add(e1);
        ebook.add(e2);
        ebook.add(e3);
        ebook.add(e4);
        ebook.add(e5);




    }
}
