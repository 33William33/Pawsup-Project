package com.example.pawsupapplication.ui.purchase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.data.adapter.DeleteAdapter;
import com.example.pawsupapplication.ui.petcard.UpdateCards;

import java.util.ArrayList;

/**
 * Class responsible for a logged in user to add a service to the shopping cart.
 * @author Dream Team
 * @version 1.0
 * @since Nov 19th 2021
 */

public class AddServiceToCart extends AppCompatActivity {

    private String userEmail;
    private String itemID;
    private Integer amount;
    private String pet = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_to_cart);
        userEmail = getIntent().getStringExtra("userEmail");
        itemID = getIntent().getStringExtra("itemID");
        amount = getIntent().getIntExtra("amount", 1);
        DAO dbh = new DAO(AddServiceToCart.this);
        ArrayList<String> petNames = dbh.getPetsName(userEmail);

        DeleteAdapter a = new DeleteAdapter(this, petNames.toArray());
        ListView cardList = (ListView)findViewById(R.id.addLister);
        cardList.setAdapter(a);

    }

    public void addServiceToCart(View v) {

        Button b = (Button)v;
        String name = b.getText().toString();

        DAO database = new DAO(AddServiceToCart.this);
        database.addPurchase(userEmail,itemID, amount, name);
        Toast.makeText(getApplicationContext(), "Added to cart!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void backToPreviousPage() {
        finish();
    }

}