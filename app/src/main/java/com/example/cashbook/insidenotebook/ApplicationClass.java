package com.example.cashbook.insidenotebook;

import android.app.Application;

import com.example.cashbook.Books;
import com.example.cashbook.Help;
import com.example.cashbook.MainActivity;
import com.example.cashbook.UserIdentity;
import com.example.cashbook.insidenotebook.expenseBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApplicationClass extends Application {

    public static ArrayList<Books> book;
    public static ArrayList<com.example.cashbook.insidenotebook.expenseBook> ebook;
    public static ArrayList<com.example.cashbook.insidenotebook.MaintainFinalBalance> mBook;
    public static HashMap<Integer,MaintainFinalBalance> mBook_new;
    public static ArrayList<Integer> in;
    public static ArrayList<Help> helpBook;
    public static ArrayList<UserIdentity> userIdentity;
    public static ArrayList<Books> filteredBooks;
    //public static List<ArrayList<expenseBook>> lol;
    public static HashMap<Integer,ArrayList<expenseBook>> lol2;
    public static int bs;

    public static int getBooksize() {
        return bs;
    }

    public static void setBooksize(int booksize) {
        bs = booksize;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        filteredBooks = new ArrayList<Books>();
        userIdentity = new ArrayList<UserIdentity>();
        userIdentity.add(new UserIdentity("Enter Name", "Enter Number"));
        helpBook = new ArrayList<Help>();
        helpBook.add(new Help("Need Help"));

        book = new ArrayList<Books>();

        lol2 = new HashMap<Integer, ArrayList<expenseBook>>();
        //ebook = new ArrayList<com.example.cashbook.insidenotebook.expenseBook>();
        for(int j=0;j<2000;j++){
            lol2.put(j, new ArrayList<com.example.cashbook.insidenotebook.expenseBook>());
        }

        //lol.add(ebook);
        //lol.add(new ArrayList<com.example.cashbook.insidenotebook.expenseBook>());

        mBook = new ArrayList<MaintainFinalBalance>(bs);
        mBook_new = new HashMap<Integer,MaintainFinalBalance>();
        for(int j=0;j<2000;j++){
            mBook_new.put(j,new MaintainFinalBalance(0,0,0,0,""));
        }

        /*
        for(int i = 0;i<bs;i++)
        {
            mBook.add(new MaintainFinalBalance(0,0,0,0));
        }

         */


    }
}
