package com.example.pawsupapplication.ui.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.ui.purchase.Checkout;
import com.example.pawsupapplication.ui.purchase.addCart;
import com.squareup.picasso.Picasso;

/**
 * This class creates the activity for product details
 *
 * @author Lingfeng Yang
 */
public class ProductDetails extends AppCompatActivity {
    ImageView img, back;
    TextView proName, proPrice, proDesc, proQty, proRating;

    String name, price, desc, qty, rating, ID, image, productID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        Intent i = getIntent();
        ID = getIntent().getStringExtra("userEmail");

        name = i.getStringExtra("name");
        image = i.getStringExtra("image");
        price = i.getStringExtra("price");
        desc = i.getStringExtra("desc");
        qty = i.getStringExtra("quantity");
        rating = i.getStringExtra("rating");
        productID = i.getStringExtra("productID");

        proName = findViewById(R.id.productName);
        proDesc = findViewById(R.id.prodDesc);
        proPrice = findViewById(R.id.prodPrice);
        img = findViewById(R.id.big_image_product);
        back = findViewById(R.id.back_product2);
        proQty = findViewById(R.id.qty);
        proRating = findViewById(R.id.rating);
        View cart = findViewById(R.id.cart);
        View addToCart = findViewById(R.id.button_product);

        proName.setText(name);
        proPrice.setText(price);
        proDesc.setText(desc);
        proQty.setText(qty);
        proRating.setText(rating);

        Picasso.with(this).load(image).placeholder(R.drawable.ic_launcher_background).into(img);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductDetails.this, ProductsActivity.class);
                startActivity(i);
                finish();

            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductDetails.this, Checkout.class);
                i.putExtra("userEmail", ID);

                startActivity(i);

            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductDetails.this, addCart.class);
                i.putExtra("userEmail", ID);
                i.putExtra("itemID", productID);
                i.putExtra("amount", "1");

                startActivity(i);

            }
        });

    }
}
