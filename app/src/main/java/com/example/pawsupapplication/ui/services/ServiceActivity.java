package com.example.pawsupapplication.ui.services;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.adapter.service.ServiceCategoryAdapter;
import com.example.pawsupapplication.data.adapter.service.ServiceRecentlyViewAdapter;
import com.example.pawsupapplication.data.model.product.Product;
import com.example.pawsupapplication.data.model.service.Service;
import com.example.pawsupapplication.data.model.service.ServiceCategory;
import com.example.pawsupapplication.data.model.service.ServiceImpl;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.ui.purchase.Checkout;

import static com.example.pawsupapplication.R.drawable.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for viewing all services in the database.
 * This class also contains the recycler that holds the services for viewing
 *
 * @author Lingfeng Yang
 */

public class ServiceActivity extends AppCompatActivity {

    RecyclerView categoryRecyclerView, recentlyViewedRecycler;

    ServiceCategoryAdapter categoryAdapter;
    List<ServiceCategory> categoryList;

    ServiceRecentlyViewAdapter recentlyViewedAdapter;
    List<Service> recentlyViewedList;

    TextView allCategory;

    String ID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        ID = getIntent().getStringExtra("userEmail");

        categoryRecyclerView = findViewById(R.id.categoryRecycler_service);
        recentlyViewedRecycler = findViewById(R.id.recently_item_service);
        allCategory = findViewById(R.id.allServiceCategoryImage);
        View cart = findViewById(R.id.cart);

        DAO database = new DAO(ServiceActivity.this);

        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ServiceActivity.this, ServiceAllCategory.class);
                startActivity(i);
            }
        });

        // adding data to model
        categoryList = new ArrayList<>();
        categoryList.add(new ServiceCategory(1, ic_home_fish));
        categoryList.add(new ServiceCategory(2, ic_home_fish));
        categoryList.add(new ServiceCategory(3, ic_home_fish));
        categoryList.add(new ServiceCategory(4, ic_home_fish));
        categoryList.add(new ServiceCategory(5, ic_home_fish));
        categoryList.add(new ServiceCategory(6, ic_home_fish));
        categoryList.add(new ServiceCategory(7, ic_home_fish));
        categoryList.add(new ServiceCategory(8, ic_home_fish));

        // adding data to model
        Service ser1 = new ServiceImpl("user1", "Service 1", "Service 1 description", "Service 1 Address", "20.99", "https://images.costco-static.com/ImageDelivery/imageService?profileId=12026539&itemId=29506-894&recipeName=680", "serviceId1");
        database.addService(ser1);
        Service ser2 = new ServiceImpl("user1", "Service 2", "Service 2 description", "Service 2 Address", "40.99", "https://s3.amazonaws.com/pv-web-01t/wordpress/wp-content/uploads/sites/3/2019/06/14115148/1000207-300x300.jpg", "serviceId2");
        database.addService(ser2);
        Service ser3 = new ServiceImpl("user2", "Service 3", "Service 3 description", "Service 3 Address", "60.99", "https://m.media-amazon.com/images/I/81a8tFtMGyL._AC_SX425_.jpg", "serviceId3");
        database.addService(ser3);
        Service ser4 = new ServiceImpl("user2", "Service 4", "Service 4 description", "Service 4 Address", "10.99", "https://canadiantire.scene7.com/is/image/CanadianTire/1426276__1?defaultImage=image_na_EN?defaultImage=image_na_EN&fmt=jpg&wid=573&hei=499", "serviceId4");
        database.addService(ser4);
        Service ser5 = new ServiceImpl("user3", "Service 5", "Service 5 description", "Service 5 Address", "20.99", "https://cdn.shopify.com/s/files/1/1920/8961/products/php25sup_1000x1000.jpg?v=1561658818", "serviceId5");
        database.addService(ser5);

        recentlyViewedList = database.getAllService();


        setCategoryRecycler(categoryList);
        setRecentlyViewedRecycler(recentlyViewedList, ID);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ServiceActivity.this, Checkout.class);
                i.putExtra("userEmail", ID);

                startActivity(i);

            }
        });

    }


    public void goToCart(View v) {
        //Intent startIntent = new Intent(getApplicationContext(), );
        //startActivity(startIntent);
    }

    private void setCategoryRecycler(List<ServiceCategory> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new ServiceCategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    private void setRecentlyViewedRecycler(List<Service> recentlyViewedDataList, String ID) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new ServiceRecentlyViewAdapter(this,recentlyViewedDataList, ID);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
    }
}
