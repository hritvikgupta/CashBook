package com.example.cashbook;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Books> book;

    @Override
    public void onCreate() {
        super.onCreate();

        book = new ArrayList<Books>();
        //To show the empty Page and to start Adding new Books Comment this content
        /*
        Books c1 = new Books("volkawagen", "290499");
        Books c2 = new Books("nissan","08071970" );

        book.add(c1);
        book.add(c2);

         */


    }
}
