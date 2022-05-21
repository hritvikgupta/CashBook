package com.example.cashbook.insidenotebook;

public class MaintainFinalBalance {
    private int netBalance;
    private int amountIn;
    private int amountout;

    public MaintainFinalBalance(int netBalance, int amountIn, int amountout) {
        this.netBalance = netBalance;
        this.amountIn = amountIn;
        this.amountout = amountout;
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
}
