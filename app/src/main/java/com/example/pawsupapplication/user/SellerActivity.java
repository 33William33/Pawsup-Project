package com.example.pawsupapplication.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.MainActivity;
import com.example.pawsupapplication.R;
import com.example.pawsupapplication.ui.seller.*;

/**
 * Class responsible for the main user interface for a seller/admin, and contains paths to all functionality.
 * @author Yunfei Wang
 * @version 1.0
 * @since Nov 15th 2021
 */

public class SellerActivity extends AppCompatActivity {
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_seller);
    }

    // ADD Button
    public void addShopService(View v){
        Intent i = new Intent(this, AddShop.class);
        startActivity(i);
    }

    // CUSTOMIZE Button
    public void editShopService(View v){
        Intent i = new Intent(this, CustomizeShop.class);
        i.putExtra("userEmail", userEmail);
        startActivity(i);
    }

    // REMOVE Button
    public void removeShopService(View v){
        Intent i = new Intent(this, RemoveShop.class);
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

}
