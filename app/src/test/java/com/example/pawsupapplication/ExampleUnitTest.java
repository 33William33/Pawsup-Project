package com.example.pawsupapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.pawsupapplication.data.LoginDataSource;
import com.example.pawsupapplication.data.Result;
import com.example.pawsupapplication.data.model.LoggedInUser;


/**
 * Example local unit test, which will execute on the development machine (host).
 * Tests basic functions of login related java files.
 * @author Wader
 * @version 1.0
 * @since Oct 8th 2021
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void LoggedInUser_isCorrect() {
        LoggedInUser fakeUser = new LoggedInUser("12345", "fakename", "tempPass");
        boolean pass = false;
        String id = fakeUser.getUserId();
        String name = fakeUser.getEmail();
        if(id.equals("12345") && name.equals("fakename")) {
            pass = true;
        }
        assertTrue(pass);
    }
}