package com.example.pawsupapplication.ui.products;


import static com.example.pawsupapplication.R.drawable.food1;
import static com.example.pawsupapplication.R.drawable.food2;
import static com.example.pawsupapplication.R.drawable.ic_home_fish;
import static com.example.pawsupapplication.R.drawable.treat1;
import static com.example.pawsupapplication.R.drawable.treat2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.data.adapter.product.ProductCategoryAdapter;
import com.example.pawsupapplication.data.adapter.product.ProductRecentlyViewAdapter;
import com.example.pawsupapplication.data.model.product.Product;
import com.example.pawsupapplication.data.model.product.ProductCategory;
import com.example.pawsupapplication.data.model.service.Service;
import com.example.pawsupapplication.data.model.service.ServiceImpl;
import com.example.pawsupapplication.ui.purchase.Checkout;
import com.example.pawsupapplication.ui.services.ServiceActivity;


import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for viewing all products in the database.
 * This class also contains the recycler that holds the products for viewing
 *
 * @author Lingfeng Yang
 */

public class ProductsActivity extends AppCompatActivity {

    RecyclerView categoryRecyclerView, recentlyViewedRecycler;

    ProductCategoryAdapter categoryAdapter;
    List<ProductCategory> categoryList;

    ProductRecentlyViewAdapter recentlyViewedAdapter;
    List<Product> recentlyViewedList;

    String ID = null;

    TextView allCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);
        ID = getIntent().getStringExtra("userEmail");

        categoryRecyclerView = findViewById(R.id.categoryRecycler_product);
        recentlyViewedRecycler = findViewById(R.id.recently_item_product);
        allCategory = findViewById(R.id.allProductCategoryImage);
        View cart = findViewById(R.id.cart);

        DAO database = new DAO(ProductsActivity.this);

        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProductsActivity.this, ProductAllCategory.class);
                startActivity(i);
            }
        });

        // adding data to model
        categoryList = new ArrayList<>();
        categoryList.add(new ProductCategory(1, ic_home_fish));
        categoryList.add(new ProductCategory(2, ic_home_fish));
        categoryList.add(new ProductCategory(3, ic_home_fish));
        categoryList.add(new ProductCategory(4, ic_home_fish));
        categoryList.add(new ProductCategory(5, ic_home_fish));
        categoryList.add(new ProductCategory(6, ic_home_fish));
        categoryList.add(new ProductCategory(7, ic_home_fish));
        categoryList.add(new ProductCategory(8, ic_home_fish));

        // adding data to model
        Product pro1 = new Product("Product 1", "20kg", "20.99", "4.5", "https://images.costco-static.com/ImageDelivery/imageService?profileId=12026539&itemId=29506-894&recipeName=680", "ProductId1");
        database.addProduct(pro1);
        Product pro2 = new Product("Product 2", "25kg", "30.99", "3.5", "https://s3.amazonaws.com/pv-web-01t/wordpress/wp-content/uploads/sites/3/2019/06/14115148/1000207-300x300.jpg", "ProductId2");
        database.addProduct(pro2);
        Product pro3 = new Product("Product 3", "15kg", "25.99", "4.0", "https://m.media-amazon.com/images/I/81a8tFtMGyL._AC_SX425_.jpg", "ProductId3");
        database.addProduct(pro3);
        Product pro4 = new Product("Product 4", "5kg", "5.99", "4.5", "https://canadiantire.scene7.com/is/image/CanadianTire/1426276__1?defaultImage=image_na_EN?defaultImage=image_na_EN&fmt=jpg&wid=573&hei=499", "ProductId4");
        database.addProduct(pro4);
        Product pro5 = new Product("Product 5", "10kg", "10.99", "3.0", "https://cdn.shopify.com/s/files/1/1920/8961/products/php25sup_1000x1000.jpg?v=1561658818", "ProductId5");
        database.addProduct(pro4);


        recentlyViewedList = database.getAllProducts();

        setCategoryRecycler(categoryList);
        setRecentlyViewedRecycler(recentlyViewedList, ID);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductsActivity.this, Checkout.class);
                i.putExtra("userEmail", ID);

                startActivity(i);

            }
        });

    }


    private void setCategoryRecycler(List<ProductCategory> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new ProductCategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    private void setRecentlyViewedRecycler(List<Product> recentlyViewedDataList, String ID) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new ProductRecentlyViewAdapter(this,recentlyViewedDataList, ID);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
    }
}
