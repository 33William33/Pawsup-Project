package com.example.pawsupapplication.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 * @author Android Studio, Wader
 * @version 1.1
 * @since Oct 1st 2021
 */
public class LoggedInUser {

    private String userId;
    private String email;
    private String password;

    public LoggedInUser(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}