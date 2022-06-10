package com.example.pawsupapplication.ui.Contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;

import com.example.pawsupapplication.MainActivity;
import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.model.Order;

/**
 * This class is responsible for displaying the contact information such as the email and the
 * phone number for a user who has recently ordered a service.
 *
 * @author Shu Sun
 */

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);

        View t = findViewById(R.id.displayName);
        ((TextView) t).setText(OrderActivity.use.getName());

        View t2 = findViewById(R.id.emailAddress);
        ((TextView) t2).setText(OrderActivity.use.getEmail());

        View t3 = findViewById(R.id.emailAddress);
        ((TextView) t3).setText(OrderActivity.use.getPhone());
    }

    public void back1(View v) {
        Intent i = new Intent(this, OrderActivity.class);

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
                    + "\n";

            orderArray.add(review);
        }

        i.putExtra("map", orderArray);

        startActivity(i);
    }
}
