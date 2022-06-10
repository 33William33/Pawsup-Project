package com.example.pawsupapplication.ui.products;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.MainActivity;
import com.example.pawsupapplication.R;

//import com.example.pawsupapplication.SuccessActivity;

/**
 * Class that handles the processing and basic validation of changing the price.
 * @author Wader
 * @version 1.1
 * @since Oct 22st 2021
 */


public class ChangePrice extends AppCompatActivity {


    // Displays UI for changing price.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_price);
    }

    // Get and validate new price.
    public void edit_price(View v) {
        EditText new_price = findViewById(R.id.editTextNumberDecimal);
        String price = new_price.getText().toString();

        int integerPlaces = price.indexOf('.');
        int decimalPlaces = price.length() - integerPlaces - 1;

        if(decimalPlaces > 2) {
            Toast.makeText(this, "Invalid Price", Toast.LENGTH_LONG).show();
        }else {
            // If validation passed, proceed to confirm message.
            Intent i = new Intent(this, ConfirmChange.class);
            startActivity(i);
            finish();
        }
    }

    // Return to previous page
    public void back(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
