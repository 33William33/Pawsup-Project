package com.example.pawsupapplication.ui.seller;
import android.content.Intent;
import android.graphics.Picture;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.MainActivity;
import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.model.product.Product;
import com.example.pawsupapplication.ui.*;
import com.example.pawsupapplication.data.model.service.*;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.ui.apply.ApplyPage;
import com.example.pawsupapplication.user.SellerActivity;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * Class responsible for adding service to shop for a seller/admin, and contains paths to all functionality.
 * @author Yunfei Wang
 * @version 1.0
 * @since Nov 15th 2021
 */

public class AddShop extends AppCompatActivity {
    protected EditText proName;
    protected EditText qty;
    protected EditText price;
    protected EditText picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shop);
        proName = findViewById(R.id.name);
        qty =  findViewById(R.id.quantity);
        price = findViewById(R.id.price);
        picture = findViewById(R.id.picture);
        TextView Add = findViewById(R.id.add);
        DAO db = new DAO(AddShop.this);
        Add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    //add new product
                    String uniqueID = UUID.randomUUID().toString();
                    Product pro = new Product(proName.getText().toString(),
                            qty.getText().toString(),
                            price.getText().toString(), "2.5",
                            picture.getText().toString(), uniqueID);
                    db.addProduct(pro);
                }catch (NumberFormatException nfe) {
                    Toast.makeText(AddShop.this, "Add Product Fail: Wrong format", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
