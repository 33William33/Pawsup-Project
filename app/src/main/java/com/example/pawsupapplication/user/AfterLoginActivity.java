package com.example.pawsupapplication.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.MainActivity;
import com.example.pawsupapplication.R;
import com.example.pawsupapplication.ui.login.LoginActivity;
import com.example.pawsupapplication.ui.petcard.AddCard;
import com.example.pawsupapplication.ui.products.ChangePrice;
import com.example.pawsupapplication.ui.products.ProductsActivity;
import com.example.pawsupapplication.ui.services.ServiceActivity;
import com.example.pawsupapplication.ui.ratingReview.RecentServices;

/**
 * Class responsible for the main user interface for a logged in user, and contains paths to all functionality.
 * @author Dream Team
 * @version 1.0
 * @since Nov 4th 2021
 */

public class AfterLoginActivity extends AppCompatActivity {
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_after_login);
        userEmail = getIntent().getStringExtra("userEmail");
    }


    // Add Profile Button Later

    // PETCARD Button

    public void launchPetCard(View v){
        Intent i = new Intent(this, AddCard.class);
        i.putExtra("userEmail", userEmail);
        startActivity(i);
    }


    // ACTIVITES Button
    public void launchActivities(View v){
        Intent i = new Intent(this, HistoryActivity.class);
        i.putExtra("userEmail", userEmail);
        startActivity(i);
    }

    // LOGOUT Button
    public void logout(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "Logout Successful!", Toast.LENGTH_LONG).show();
        finish();
    }

    // LOGOUT Button
    public void launchProducts(View v) {
        Intent i = new Intent(this, ProductsActivity.class);
        i.putExtra("userEmail", userEmail);
        startActivity(i);
    }

    // LOGOUT Button
    public void launchServices(View v) {
        Intent i = new Intent(this, ServiceActivity.class);
        i.putExtra("userEmail", userEmail);
        startActivity(i);
    }
}
