package com.bean;

import java.io.Serializable;

public class Friend implements Serializable {
    private String UserId;
    private String Friend;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getFriend() {
        return Friend;
    }

    public void setFriend(String friend) {
        Friend = friend;
    }
}
