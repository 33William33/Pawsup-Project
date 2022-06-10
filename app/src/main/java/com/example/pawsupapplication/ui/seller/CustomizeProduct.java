package com.example.pawsupapplication.ui.seller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.data.model.product.Product;
import com.example.pawsupapplication.user.SellerActivity;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Class responsible for editing selected product in shop as a seller/admin, and contains paths to all functionality.
 * @author Yunfei Wang
 * @version 1.0
 * @since Nov 15th 2021
 */

public class CustomizeProduct extends AppCompatActivity {
    protected EditText proName;
    protected EditText qty;
    protected EditText price;
    protected EditText picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customize_product);
        proName = findViewById(R.id.name);
        qty =  findViewById(R.id.quantity);
        price = findViewById(R.id.price);
        picture = findViewById(R.id.picture);
        Intent in = getIntent();
        TextView edit = findViewById(R.id.customize);
        DAO db = new DAO(CustomizeProduct.this);
        boolean b;
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    //customize product
                    ArrayList<Product> products = db.getAllProducts();
                    Bundle extras = in.getExtras();
                    String id = extras.getString("ID");
                    for (int i=0; i < products.size(); i++){
                        if (products.get(i).getId().compareTo(id) == 0){
                            db.editProduct(id,proName.getText().toString()
                                    ,qty.getText().toString()
                                    ,price.getText().toString()
                                    ,picture.getText().toString());
                        }
                    }
                }catch (NumberFormatException nfe) {
                    Toast.makeText(CustomizeProduct.this, "Customize Product Fail: Wrong format", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //go to seller page
        Intent c = new Intent(this, SellerActivity.class);
        startActivity(c);
    }
}
