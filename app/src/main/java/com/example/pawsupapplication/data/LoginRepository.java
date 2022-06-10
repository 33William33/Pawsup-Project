package com.example.pawsupapplication.data;

import com.example.pawsupapplication.data.model.LoggedInUser;

import java.util.ArrayList;
import java.util.Map;

/**
 * Class that requests authentication and user information from the remote data source (Sqlite) and
 * maintains an in-memory cache of login status and user credentials information.
 * @author Android Studio, Wader
 * @version 1.1
 * @since Oct 1st 2021
 */
public class LoginRepository {
    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInUser> login(String username, String password, Map<String, ArrayList<String>> users) {
        // handle login
        Result<LoggedInUser> result = dataSource.login(username, password, users);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }
}