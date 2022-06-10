package com.example.pawsupapplication.user;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.data.adapter.HistoryAdapter;
import com.example.pawsupapplication.data.adapter.PetCardAdapter;
import com.example.pawsupapplication.data.model.History;
import com.example.pawsupapplication.ui.petcard.PetCards;

import java.util.ArrayList;

/**
 * Class responsible for the display and function of the user interface for transaction history.
 * @author Wader
 * @version 1.0
 * @since Nov 4th 2021
 */

public class HistoryActivity extends AppCompatActivity {
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_of_transaction);
        userEmail = getIntent().getStringExtra("userEmail");

        // Note: As of right now, the data is hardcoded, this will change when product/services is full functional.

        // Create some Mock Data for the user (Until service is completed)
        //History person1 = new History(2, "200", "Cat Food", "Nov 4th 2021", "https://s7d2.scene7.com/is/image/PetSmart/5178105");
        //History person2 = new History(1, "150", "Cleaning", "Nov 4th 2021", "https://i0.wp.com/maidsbytrade.com/wp-content/uploads/2016/02/How-to-Clean-Up-After-Cats.jpg?fit=450%2C350&ssl=1");
        //Mock Data ends

        DAO database = new DAO(HistoryActivity.this);

        // Add mock data to database
        //database.addHistory(person1, "fake@email.com");
       // database.addHistory(person2, "fake@email2.com");
        // End

        ArrayList<String> arrInfo = database.getHistoryInfo(userEmail);
        ArrayList<String> arrPic = database.getHistoryPic(userEmail);
        HistoryAdapter a = new HistoryAdapter(this, arrInfo.toArray(), arrPic.toArray());
        ListView transaction = (ListView)findViewById(R.id.TransactionsLog);
        transaction.setAdapter(a);
    }
}
