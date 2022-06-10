package com.example.pawsupapplication.ui.purchase;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.user.HistoryActivity;

/**
 * Class responsible for a logged in user to add a product to the shopping cart.
 * @author Dream Team
 * @version 1.0
 * @since Nov 19th 2021
 */

public class addCart extends AppCompatActivity {

    private String userEmail;
    private String itemID;
    private Integer amount;
    private String pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_cart_confirm);
        userEmail = getIntent().getStringExtra("userEmail");
        itemID = getIntent().getStringExtra("itemID");
        amount = getIntent().getIntExtra("amount", 1);
        pet = "N/A";
    }

    public void addProductToCart(View view) {
        DAO database = new DAO(addCart.this);
        database.addPurchase(userEmail,itemID, amount, pet);
        Toast.makeText(getApplicationContext(), "Added to cart!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void backToPreviousPage(View view) {
        finish();
    }

}
