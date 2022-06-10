package com.example.pawsupapplication.ui.purchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.ui.petcard.AddCard;
import com.example.pawsupapplication.user.AfterLoginActivity;
import com.squareup.picasso.Picasso;

/**
 * Class responsible for confirming the checkout action of a user, and updating the database accordingly.
 * @author Dream Team
 * @version 1.0
 * @since Nov 19th 2021
 */

public class PlaceOrder extends AppCompatActivity {

    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_order_confirm);
        userEmail = getIntent().getStringExtra("userEmail");
    }

    public void confirmPlaceOrder(View view) {

        try {
            DAO database = new DAO(PlaceOrder.this);
            database.deletePurchase(userEmail);
            Toast.makeText(this, "Purchase has been made successfully", Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Toast.makeText(this, "An error has occurred with your purchase", Toast.LENGTH_LONG).show();
        }

        // This clears all activities and returns the user to the home page
        Intent intent = new Intent(getApplicationContext(), AfterLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void backToPreviousPage(View view) {
        finish();
    }

}
