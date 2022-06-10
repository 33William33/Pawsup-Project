package com.example.pawsupapplication.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/*
This class is responsible for providing users with the ability to change their passwords.
Users need to first enter their current password before entering their new password, then they
need to re-enter their password to confirm changes.

@author Shu Sun
 */
public class PasswordActivity extends AppCompatActivity {

    /*
    Displays UI for changing password.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
    }

    /*
    Performs various checks on the password before saving.
     */
    public void savePassword(View v){
        EditText t1 = findViewById(R.id.current_password);
        String p1 = t1.getText().toString();

        EditText t2 = findViewById(R.id.new_password);
        String p2 = t2.getText().toString();

        EditText t3 = findViewById(R.id.confirm_password);
        String p3 = t3.getText().toString();

        if (p1.equals("") || p2.equals("") || p3.equals("")){
            Toast.makeText(this, "Please fill in all boxes", Toast.LENGTH_LONG).show();
        } else if(!validPassword(p2)){
            Toast.makeText(this, "Please enter a valid password.", Toast.LENGTH_LONG)
                    .show();
        } else if (!p1.equals(ProfileActivity.u.getPassword())) {
            Toast.makeText(this, "Please enter the correct password.",
                    Toast.LENGTH_LONG).show();
        } else if (p1.equals(p2)) {
            Toast.makeText(this, "Please enter a different password.",
                    Toast.LENGTH_LONG).show();
        } else if (!p2.equals(p3)) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_LONG).show();
        } else if(p1.equals(ProfileActivity.u.getPassword()) && !p1.equals(p2) && p2.equals(p3)
                && validPassword(p2)) {
            ProfileActivity.u.setPassword(p2);
            Intent i = new Intent(this, SuccessActivity.class);
            startActivity(i);
        }
    }

    /*
    Returns to the previous page.
     */
    public void back2(View v){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    /*
    Checks if the new password is valid.
     */
    private static boolean validPassword(String s) {
        char c;
        boolean capital = false;
        boolean lowerCase = false;
        boolean number = false;
        for(int i=0;i < s.length();i++) {
            c = s.charAt(i);
            if(Character.isDigit(c)) {
                number = true;
            }
            else if (Character.isUpperCase(c)) {
                capital = true;
            } else if (Character.isLowerCase(c)) {
                lowerCase = true;
            }
            if(number && capital && lowerCase)
                return true;
        }
        return false;
    }
}
