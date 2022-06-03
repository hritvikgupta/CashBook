package com.example.cashbook;

public class UserIdentity {

    private String UserName;
    private String UserNumber;

    public UserIdentity(String userName, String userNumber) {
        UserName = userName;
        UserNumber = userNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserNumber() {
        return UserNumber;
    }

    public void setUserNumber(String userNumber) {
        UserNumber = userNumber;
    }
}
