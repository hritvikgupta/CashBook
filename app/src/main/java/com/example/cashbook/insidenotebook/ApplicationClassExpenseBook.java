package com.example.cashbook.insidenotebook;

import java.util.ArrayList;

public class ApplicationClassExpenseBook extends ApplicationClass {

    public static ArrayList<expenseBook> ebook;

    //This class is not working read the solution in Notebook details comment section
    @Override
    public void onCreate() {
        super.onCreate();


        ebook = new ArrayList<expenseBook>();
        expenseBook e1 = new expenseBook("shop2", "000", "ration", "000");
        ebook.add(e1);


    }
}
