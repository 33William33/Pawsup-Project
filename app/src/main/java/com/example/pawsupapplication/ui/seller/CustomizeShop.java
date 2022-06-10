package com.example.pawsupapplication.ui.seller;
import android.content.Intent;
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
import com.example.pawsupapplication.ui.services.ServiceActivity;
import com.example.pawsupapplication.ui.services.ServiceAllCategory;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * Class responsible for editing service in shop for a seller/admin, and contains paths to all functionality.
 * @author Yunfei Wang
 * @version 1.0
 * @since Nov 15th 2021
 */

public class CustomizeShop extends AppCompatActivity {
    protected EditText id;
    protected String product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customize_shop);
        id = findViewById(R.id.edit);
        TextView text = findViewById(R.id.textView);
        TextView edit = findViewById(R.id.button1);
        DAO db = new DAO(CustomizeShop.this);
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    //add products and id to be seen
                    ArrayList<Product> products = db.getAllProducts();
                    for (int i=0; i < products.size(); i++){
                        text.setText(text.getText() + "\nName：" + products.get(i).getProductName() + " ID：" + products.get(i).getId());
                    }
                    for (int i=0; i < products.size(); i++){
                        if (products.get(i).getId().compareTo(id.getText().toString()) == 0){
                            //go to customize page if successful
                            Intent c = new Intent(CustomizeShop.this, CustomizeProduct.class);
                            c.putExtra("ID", id.getText().toString());
                            startActivity(c);
                        }
                    }
                    Toast.makeText(CustomizeShop.this, "Customize Fail: Product not found", Toast.LENGTH_SHORT).show();
                }catch (NumberFormatException nfe) {
                    Toast.makeText(CustomizeShop.this, "Customize Fail: Wrong format", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

