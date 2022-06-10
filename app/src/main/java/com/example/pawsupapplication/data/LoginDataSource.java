package com.example.pawsupapplication.data;


import com.example.pawsupapplication.data.model.LoggedInUser;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 * @author Android Studio, Wader
 * @version 1.1
 * @since Oct 1st 2021
 */
public class LoginDataSource {

    //HashMap<String, String> testData = new HashMap<>();

    public Result<LoggedInUser> login(String username, String password, Map<String,ArrayList<String>> users) {
        LoggedInUser currentUser;
        /**
         * Currently employs a hashmap to store mock data for authentication purposes.
         */
        // Mock data starts
        //testData.put("Testbot1@inbox.com", "Aa12345!");
        //testData.put("Testbot2@mail.ca", "12345Aa!");
        //testData.put("Testbot3@gmail.com", "yY!12345");
        //testData.put("Testbot4@mail.ca", "!aGoodTime1");
        //testData.put("Testbot5@inbox.com", "Yb567411!");
        // Mock Data ends here.
        boolean pass = false;
        try {
            // TODO: handle loggedInUser authentication
            if(users.get(username) != null && users.get(username).get(0).equals(password)) {
                pass = true;
            }
            if(pass) {
                currentUser =
                        new LoggedInUser(
                                java.util.UUID.randomUUID().toString(), username, password);
                return new Result.Success<>(currentUser);
            }
            return null;
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}