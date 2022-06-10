package com.example.pawsupapplication.ui.purchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.data.adapter.HistoryAdapter;
import com.example.pawsupapplication.data.adapter.PetCardAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class responsible for displaying a summary of information collected throughout the checkout process.
 * @author Dream Team
 * @version 1.0
 * @since Nov 19th 2021
 */

public class PurchaseSummary extends AppCompatActivity {
    private String userEmail;
    private String cardNum;
    private String cardPass;
    private String cardSpec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_service_summary);
        userEmail = getIntent().getStringExtra("userEmail");
        if(getIntent().getIntExtra("payMethod", 0) > 1) {
            cardNum = getIntent().getStringExtra("cardNum");
            cardPass = getIntent().getStringExtra("cardPass");
            cardSpec = getIntent().getStringExtra("cvv");
        }
        DAO database = new DAO(PurchaseSummary.this);
        // Order summary
        Map<String, Integer> items = new HashMap<>();
        items = database.getPurchases(userEmail);
        Iterator it = items.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ArrayList<String> item;
            item = database.getPurchasedItems(pair.getKey().toString());
            if(!item.isEmpty()) {
                ArrayList<String> arrInfo = new ArrayList<>();
                ArrayList<String> arrPic = new ArrayList<>();
                String info = "Provider: " + item.get(0) + "\nService: " + item.get(1) +
                        "\nPrice $: " + item.get(4) + "\nLocation: " + item.get(3) + "\nDescription: " + item.get(2);
                arrInfo.add(info);
                arrPic.add(item.get(6));
                HistoryAdapter a = new HistoryAdapter(this, arrInfo.toArray(), arrPic.toArray());
                ListView transaction = (ListView) findViewById(R.id.services_to_buy);
                transaction.setAdapter(a);
            }else if (!database.getPurchasedProduct(pair.getKey().toString()).isEmpty()){
                item = database.getPurchasedProduct(pair.getKey().toString());
                ArrayList<String> arrInfo = new ArrayList<>();
                ArrayList<String> arrPic = new ArrayList<>();
                String info = "Product: " + item.get(0) +
                        "\nPrice $: " + item.get(2) + "\nQuantity: " + item.get(1) + "\nRating: " + item.get(3);
                arrInfo.add(info);
                arrPic.add(item.get(4));
                HistoryAdapter a = new HistoryAdapter(this, arrInfo.toArray(), arrPic.toArray());
                ListView transaction = (ListView) findViewById(R.id.services_to_buy);
                transaction.setAdapter(a);
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        // Personal information
        ArrayList<String> arr3 = database.getPetsInfo(userEmail);
        ArrayList<String> arr4 = database.getPetsPic(userEmail);
        PetCardAdapter a = new PetCardAdapter(this, arr3.toArray(), arr4.toArray());
        ListView cardList = (ListView)findViewById(R.id.your_infos);
        cardList.setAdapter(a);
        // Card information
        ArrayList<String> arrInfo2 = new ArrayList<>();
        ArrayList<String> arrPic2 = new ArrayList<>();
        String cardInfo = "Card Number: " + cardNum + "\nPassword: ********** " +
                "\nCVV: XXX ";
        String cardImage = "https://img.favpng.com/22/14/21/payment-brand-debit-card-credit-card-service-png-favpng-k297G3vM10UzSw9m1wLcB4pL6.jpg";
        arrInfo2.add(cardInfo);
        arrPic2.add(cardImage);
    }

    public void toPlaceOrder(View view) {
        Intent startIntent = new Intent(getApplicationContext(), PlaceOrder.class);
        startIntent.putExtra("userEmail", userEmail);
    }

    public void backToPreviousPage(View view) {
        finish();
    }

}
