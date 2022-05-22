package com.example.cashbook.insidenotebook;

public class MaintainFinalBalance {
    private int netBalance;
    private int amountIn;
    private int amountout;
    private int itemIndex;

    public MaintainFinalBalance(int netBalance, int amountIn, int amountout, int itemIndex) {
        this.netBalance = netBalance;
        this.amountIn = amountIn;
        this.amountout = amountout;
        this.itemIndex = itemIndex;

    }

    public int getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(int netBalance) {
        this.netBalance = netBalance;
    }

    public int getAmountIn() {
        return amountIn;
    }

    public void setAmountIn(int amountIn) {
        this.amountIn = amountIn;
    }

    public int getAmountout() {
        return amountout;
    }

    public void setAmountout(int amountout) {
        this.amountout = amountout;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
    }
}
