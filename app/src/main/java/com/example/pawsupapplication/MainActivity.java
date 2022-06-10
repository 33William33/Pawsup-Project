package com.example.pawsupapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.data.model.Order;
import com.example.pawsupapplication.data.model.User;
import com.example.pawsupapplication.ui.Contacts.OrderActivity;
import com.example.pawsupapplication.ui.apply.ApplyPage;
import com.example.pawsupapplication.ui.login.LoginActivity;
import com.example.pawsupapplication.ui.products.ChangePrice;
import com.example.pawsupapplication.ui.products.ProductsActivity;
import com.example.pawsupapplication.ui.services.ServiceActivity;
import com.example.pawsupapplication.ui.ratingReview.RecentServices;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * Class responsible for the main interface of the app, and contains paths to all functionality.
 * @author Dream Team
 * @version 1.0
 * @since Nov 2nd 2021
 */

public class MainActivity extends AppCompatActivity {
    public static Map<Integer, Order> reviewMap= new HashMap<Integer, Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void goToLogin(View v) {
        Intent startIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(startIntent);
    }

    public void goToProducts(View v) {
        Intent startIntent = new Intent(getApplicationContext(), ProductsActivity.class);
        startActivity(startIntent);
    }
  
    public void goToServices(View v) {
        Intent startIntent = new Intent(getApplicationContext(), ServiceActivity.class);
        startActivity(startIntent);
    }

    public void goToReview(View v) {
        Intent startIntent = new Intent(getApplicationContext(), RecentServices.class);
        startActivity(startIntent);
    }

    public void goToPriceChange(View v) {
        Intent startIntent = new Intent(getApplicationContext(), ChangePrice.class);
        startActivity(startIntent);
    }

    public void applyProvider(View v) {
        Intent startIntent = new Intent(getApplicationContext(), ApplyPage.class);
        startActivity(startIntent);
    }

    public void createOrder(View v){
        User u1 = new User("Shu Sun", "shu.s@gmail.com", "416-000-0001");
        User u2 = new User("Annas Rahuma", "Annas.r@gmail.com", "416-000-0002");
        User u3 = new User("Danyal Rana", "Danyal.r@gmail.com", "416-000-0003");
        User u4 = new User("Mike Yang", "Mike.y@gmail.com", "416-000-0004");
        User u5 = new User("Wader Zheng", "Wader.z@gmail.com", "416-000-0005");

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String day = formatter.format(date);

        Order o1 = new Order(u1, 1, "Labrador Retriever", day, "06/11/2021 2:00:00", "08/11/2021 2:00:00");
        Order o2 = new Order(u2, 2, "Pomeranian", day, "07/11/2021 2:00:00", "09/11/2021 4:00:00");
        Order o3 = new Order(u3, 3, "German Shepherd", day, "09/11/2021 2:00:00", "10/11/2021 12:00:00");
        Order o4 = new Order(u4, 4,"Golden Retriever", day, "10/11/2021 2:00:00", "13/11/2021 2:00:00");
        Order o5 = new Order(u5, 5,"Poodle", "01/11/2021 3:12:26", "03/11/2021 5:00:00", "04/11/2021 1:00:00");

        try {
            MainActivity.reviewMap.put(0, o1);
            MainActivity.reviewMap.put(1, o2);
            MainActivity.reviewMap.put(2, o3);
            MainActivity.reviewMap.put(3, o4);
            MainActivity.reviewMap.put(4, o5);
        }
        catch(Exception e){
            Toast.makeText(this, "An error has occurred with last Review", Toast.LENGTH_LONG).show();
        }
    }

    public void displayOrder(View v){
        Intent i = new Intent(this, OrderActivity.class);

        createOrder(v);
        Iterator<Order> it = MainActivity.reviewMap.values().iterator();
        ArrayList<String> orderArray = new ArrayList<String>();

        while(it.hasNext()){
            Order o = it.next();
            String review = "\n" + o.getOrder()
                    + "\n" + o.getUser().getName()
                    + "\n" + o.getPet()
                    + "\n" + o.getDate()
                    + "\n" + o.getStart()
                    + "\n" + o.getEnd()
                    + "\n";;

            orderArray.add(review);
        }

        i.putExtra("map", orderArray);
        startActivity(i);
    }
}
