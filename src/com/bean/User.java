package com.bean;

import java.io.Serializable;

public class User implements Serializable {
    private String UserId;
    private String Password;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
