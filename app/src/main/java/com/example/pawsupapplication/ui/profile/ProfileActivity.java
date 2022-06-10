package com.example.pawsupapplication.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.MainActivity;
import com.example.pawsupapplication.R;
import com.example.pawsupapplication.ui.petcard.PetCards;

import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Intent;


import android.os.Bundle;
import android.widget.Toast;

/*
This class is responsible for displaying the profile, which contains numerous pieces of user
information.

@author Shu Sun
 */
public class ProfileActivity extends AppCompatActivity {
    /*
    Creates user object for temporary data storage.
     */
    public static User u = new User();

    /*
    Displays UI for user profile.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        View t = findViewById(R.id.changefullname);
        ((TextView) t).setText(u.getName());

        View t2 = findViewById(R.id.changeBirthday);
        ((TextView) t2).setText(u.getBirthday());

        View t3 = findViewById(R.id.changelocation);
        ((TextView) t3).setText(u.getLocation());

        View t4 = findViewById(R.id.changeEmail);
        ((TextView) t4).setText(u.getEmail());

        View t5 = findViewById(R.id.changephone);
        ((TextView) t5).setText(u.getPhone());

        View t6 = findViewById(R.id.changepassword);
        ((TextView) t6).setText(u.getPassword());

        View t7 = findViewById(R.id.numPets);
        ((TextView) t6).setText(PetCards.arr3.size());
    }

    /*
    Performs various checks on the profile before saving.
     */
    public void saveProfile(View v){
        EditText t = findViewById(R.id.changefullname);
        String s = t.getText().toString();

        EditText t2 = findViewById(R.id.changeBirthday);
        String s2 = t2.getText().toString();

        EditText t3 = findViewById(R.id.changelocation);
        String s3 = t3.getText().toString();

        if(s.equals("") || s2.equals("") || s3.equals("")){
            Toast.makeText(this, "One of the boxes is empty.", Toast.LENGTH_LONG).show();
        } else {
            ProfileActivity.u.setName(s);
            ProfileActivity.u.setBirthday(s2);
            ProfileActivity.u.setLocation(s3);

            Intent i = new Intent(this, SuccessActivity.class);
            startActivity(i);
        }
    }

    /*
    Sends users to the email page.
     */
    public void email(View v){
        Intent i = new Intent(this, EmailActivity.class);
        startActivity(i);
    }

    /*
    Sends users to the phone number page.
     */
    public void phone(View v){
        Intent i = new Intent(this, PhoneActivity.class);
        startActivity(i);
    }

    /*
    Sends users to the password page.
     */
    public void password(View v){
        Intent i = new Intent(this, PasswordActivity.class);
        startActivity(i);
    }

    public void home(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
