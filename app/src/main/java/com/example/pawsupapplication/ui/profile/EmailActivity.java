package com.example.pawsupapplication.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
/*
This class is responsible for providing users with the ability to change their emails.
Users need to first enter their current email before entering their new email to confirm
changes.

@author Shu Sun
 */

public class EmailActivity extends AppCompatActivity {

    /*
    Displays UI for changing email.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_mail);
    }

    /*
    Performs various checks on the email before saving.
     */
    public void saveMail(View v){
        EditText t1 = findViewById(R.id.current_email);
        String p1 = t1.getText().toString();

        EditText t2 = findViewById(R.id.new_email);
        String p2 = t2.getText().toString();

        if (p1.equals("") || p2.equals("")) {
            Toast.makeText(this, "Please fill in all boxes", Toast.LENGTH_LONG).show();
        } else if(!p1.equals(ProfileActivity.u.getEmail())) {
            Toast.makeText(this, "Please enter the correct email.", Toast.LENGTH_LONG)
                    .show();
        } else if(!validEmail(p2)){
            Toast.makeText(this, "Please enter a valid email.", Toast.LENGTH_LONG)
                    .show();
        } else if (p1.equals(p2)) {
            Toast.makeText(this, "Please enter a new email.", Toast.LENGTH_LONG).show();
        } else if(p1.equals(ProfileActivity.u.getEmail())){
            ProfileActivity.u.setEmail(p2);
            Intent i = new Intent(this, SuccessActivity.class);
            startActivity(i);
        }
    }

    /*
    Returns to the previous page.
     */
    public void back1(View v){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    /*
    Checks if the new email is valid.
     */
    private boolean validEmail(String s){
        boolean at = false;
        boolean dot = false;
        for(int i=0;i < s.length();i++) {
            if(s.charAt(i) == '@') {
                at = true;
            }
            else if (s.charAt(i) == '.') {
                dot = true;
            }
        }
        if(at && dot)
            return true;
        return false;
    }
}
