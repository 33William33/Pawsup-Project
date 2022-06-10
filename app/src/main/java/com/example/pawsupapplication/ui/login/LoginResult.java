package com.example.pawsupapplication.ui.login;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 * @author Android Studio, Wader
 * @version 1.1
 * @since Oct 1st 2021
 */
public class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}