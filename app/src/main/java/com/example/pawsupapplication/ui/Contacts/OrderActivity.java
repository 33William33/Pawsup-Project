package com.example.pawsupapplication.ui.Contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.MainActivity;
import com.example.pawsupapplication.data.adapter.OrderAdapter;
import com.example.pawsupapplication.data.model.User;
import com.example.pawsupapplication.R;

import java.util.ArrayList;

/**
 * This class is responsible for displaying all the recent orders along with its details for
 * a selected service.
 *
 * @author Shu Sun
 */

public class OrderActivity extends AppCompatActivity {
    public static User use;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        ArrayList<String> arr =  getIntent().getStringArrayListExtra("map");
        OrderAdapter a = new OrderAdapter(this, arr.toArray());
        ListView OrderList = (ListView)findViewById(R.id.orderLister);
        OrderList.setAdapter(a);

        OrderList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
                Intent x = new Intent(OrderActivity.this, ContactsActivity.class);
                use = MainActivity.reviewMap.get(i).getUser();
                startActivity(x);
            }
        });
    }

    public void back(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
