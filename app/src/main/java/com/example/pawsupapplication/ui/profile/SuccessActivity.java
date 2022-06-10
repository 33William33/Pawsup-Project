package com.example.pawsupapplication.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/*
This class is responsible for displaying a success message whenever the user
makes a change.

@author Shu Sun
 */
public class SuccessActivity extends AppCompatActivity {

    /*
    Creates UI for displaying success messages.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changes_successful);
    }

    /*
    Returns users to profile.
     */
    public void done(View v){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }
}
