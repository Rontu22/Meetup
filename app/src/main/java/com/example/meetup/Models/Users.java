package com.example.meetup.Models;

public class Users {
    String email;
    String userID;

    public Users(String email, String userID) {
        this.email = email;
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
