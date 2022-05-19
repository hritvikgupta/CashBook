package com.example.cashbook.insidenotebook;

public class expenseBook
{
    private String eName;
    private String eAmount;
    private String eTag;
    private String totalbalanceremain;

    public expenseBook(String eName, String eAmount, String eTag, String totalbalanceremain) {
        this.eName = eName;
        this.eAmount = eAmount;
        this.eTag = eTag;
        this.totalbalanceremain = totalbalanceremain;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteAmount() {
        return eAmount;
    }

    public void seteAmount(String eAmount) {
        this.eAmount = eAmount;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public String getTotalbalanceremain() {
        return totalbalanceremain;
    }

    public void setTotalbalanceremain(String totalbalanceremain) {
        this.totalbalanceremain = totalbalanceremain;
    }
}
