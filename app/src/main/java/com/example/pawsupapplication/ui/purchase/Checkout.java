package com.example.pawsupapplication.ui.purchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.data.adapter.HistoryAdapter;
import com.example.pawsupapplication.ui.products.ProductsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class responsible for the shopping cart interface for a logged in user, and contains paths to the checkout process.
 * @author Dream Team
 * @version 1.0
 * @since Nov 19th 2021
 */

public class Checkout extends AppCompatActivity {

    private String userEmail;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_service_cart);
        userEmail = getIntent().getStringExtra("userEmail");
        DAO database = new DAO(Checkout.this);
        Map<String, Integer> items = new HashMap<>();
        items = database.getPurchases(userEmail);
        total = iterateMap(items, database);
        TextView price = (TextView)findViewById(R.id.total_price);
        price.setText(String.valueOf(total));

    }

    public double iterateMap(Map mp, DAO dao) {
        double sum = 0.00;
        ArrayList<String> arrInfo = new ArrayList<>();
        ArrayList<String> arrPic = new ArrayList<>();
        if(mp.isEmpty()) {
            return sum;
        }
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ArrayList<String> item;
            item = dao.getPurchasedItems(pair.getKey().toString());
            if(!item.isEmpty()) {
                try {
                    sum += (Double.parseDouble(item.get(4)) * Integer.parseInt(pair.getValue().toString()));
                } catch (Exception e) {
                    System.out.println("Exception: " + e);
                }
                //ArrayList<String> arrInfo = new ArrayList<>();
                //ArrayList<String> arrPic = new ArrayList<>();
                String info = "Provider: " + item.get(0) + "\nService: " + item.get(1) +
                        "\nPrice $: " + item.get(4) + "\nLocation: " + item.get(3) + "\nDescription: " + item.get(2);
                arrInfo.add(info);
                arrPic.add(item.get(6));
                //HistoryAdapter a = new HistoryAdapter(this, arrInfo.toArray(), arrPic.toArray());
                //ListView transaction = (ListView) findViewById(R.id.services_to_buy);
                //transaction.setAdapter(a);
            }else if (!dao.getPurchasedProduct(pair.getKey().toString()).isEmpty()){
                item = dao.getPurchasedProduct(pair.getKey().toString());
                try {
                    sum += (Double.parseDouble(item.get(2)) * Integer.parseInt(pair.getValue().toString()));
                } catch (Exception e) {
                    System.out.println("Exception: " + e);
                }
                //ArrayList<String> arrInfo = new ArrayList<>();
                //ArrayList<String> arrPic = new ArrayList<>();
                String info = "Product: " + item.get(0) +
                        "\nPrice $: " + item.get(2) + "\nQuantity: " + item.get(1) + "\nRating: " + item.get(3);
                arrInfo.add(info);
                arrPic.add(item.get(4));
                //HistoryAdapter a = new HistoryAdapter(this, arrInfo.toArray(), arrPic.toArray());
                //ListView transaction = (ListView) findViewById(R.id.services_to_buy);
                //transaction.setAdapter(a);
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        HistoryAdapter a = new HistoryAdapter(this, arrInfo.toArray(), arrPic.toArray());
        ListView transaction = (ListView) findViewById(R.id.services_to_buy);
        transaction.setAdapter(a);
        return (Math.round(sum*100.0)/100.0);
    }

    public void toDetails(View view) {
        Intent startIntent = new Intent(getApplicationContext(), PurchaseDetail.class);
        startIntent.putExtra("userEmail", userEmail);
        startActivity(startIntent);
    }

    public void backToPreviousPage(View view) {
        finish();
    }

}
