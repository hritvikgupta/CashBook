package com.example.cashbook;

public class Books
{
    private String name;
    private String date;
    private int amount;


    public Books(String name, String date, int amount) {
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
