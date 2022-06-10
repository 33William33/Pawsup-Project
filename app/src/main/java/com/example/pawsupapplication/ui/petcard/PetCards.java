package com.example.pawsupapplication.ui.petcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.data.adapter.PetCardAdapter;

import java.util.ArrayList;

/**
 * This class is responsible for viewing all petcards has saved in the database.
 *
 * @author Annas Rahuma
 */

public class PetCards extends AppCompatActivity {

    String ID = null;
    public static ArrayList<String> arr3 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_cards);
        ID = getIntent().getStringExtra("userEmail");
       // ArrayList<String> arr =  getIntent().getStringArrayListExtra("map");
        DAO dbh = new DAO(PetCards.this);

        arr3 = dbh.getPetsInfo(ID);
        ArrayList<String> arr4 = dbh.getPetsPic(ID);
       // Toast.makeText(this, arr3.toString(), Toast.LENGTH_LONG).show();
        //ArrayList<String> arr2 =  getIntent().getStringArrayListExtra("map1");
        PetCardAdapter a = new PetCardAdapter(this, arr3.toArray(), arr4.toArray());
        ListView cardList = (ListView)findViewById(R.id.petLister);
        cardList.setAdapter(a);




    }
}