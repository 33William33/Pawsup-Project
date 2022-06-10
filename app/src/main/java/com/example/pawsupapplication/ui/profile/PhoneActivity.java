package com.example.pawsupapplication.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/*
This class is responsible for providing users with the ability to change their phone numbers.
Users need to first enter their current phone number before entering their new phone number
to confirm changes.

@author Shu Sun
 */
public class PhoneActivity extends AppCompatActivity {

    /*
    Displays UI for changing phone number.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_phone);
    }

    /*
    Performs various checks on the phone number before saving.
     */
    public void savePhone(View v){
        EditText t1 = findViewById(R.id.Old_number);
        String p1 = t1.getText().toString();

        EditText t2 = findViewById(R.id.new_number);
        String p2 = t2.getText().toString();

         if (p1.equals("") || p2.equals("")){
            Toast.makeText(this, "Please fill in all boxes", Toast.LENGTH_LONG).show();
        } else if(!p1.equals(ProfileActivity.u.getPhone())) {
            Toast.makeText(this, "Please enter the correct phone number.",
                    Toast.LENGTH_LONG).show();
        } else if(!validPhone(p2)) {
             Toast.makeText(this, "Please enter a valid phone number.",
                     Toast.LENGTH_LONG).show();
         } else if (p1.equals(p2)) {
            Toast.makeText(this, "Phone enter a different phone number.",
                    Toast.LENGTH_LONG).show();
        } else if(p1.equals(ProfileActivity.u.getPhone()) && !p1.equals(p2) && validPhone(p2)){
            ProfileActivity.u.setPhone(p2);
            Intent i = new Intent(this, SuccessActivity.class);
            startActivity(i);
        }
    }

    /*
    Return to the previous page.
     */
    public void back3(View v){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    /*
    Checks if the phone number entered is valid
     */
    private boolean validPhone(String s){
       return s.charAt(3) == '-' && s.charAt(7) == '-';
    }
}
