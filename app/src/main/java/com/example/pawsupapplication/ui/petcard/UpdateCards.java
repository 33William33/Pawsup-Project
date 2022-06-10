package com.example.pawsupapplication.ui.petcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.data.adapter.DeleteAdapter;

import java.util.ArrayList;

/**
 * This class is responsible for selecting what pets in the database you want to update.
 *
 * @author Annas Rahuma
 */

public class UpdateCards extends AppCompatActivity {

    String ID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cards);

        ID = getIntent().getStringExtra("userEmail");
        DAO dbh = new DAO(UpdateCards.this);
        ArrayList<String> petNames = dbh.getPetsName(ID);

        // Toast.makeText(this, arr3.toString(), Toast.LENGTH_LONG).show();
        //ArrayList<String> arr2 =  getIntent().getStringArrayListExtra("map1");
        DeleteAdapter a = new DeleteAdapter(this, petNames.toArray());
        ListView cardList = (ListView)findViewById(R.id.deleteLister);
        cardList.setAdapter(a);
    }
    public void viewDeleteCards(View v){

        Intent i = new Intent(this, DeleteCards.class);
        i.putExtra("userEmail", ID);
        System.out.println("1");
        startActivity(i);
    }
/*
    public void deletePetCardButton(View v){
        Button b = (Button)v;
        String name = b.getText().toString();
        try {

            DAO dbHelp = new DAO(UpdateCards.this);
            boolean report = dbHelp.deletePet(name, ID);
            Toast.makeText(this, "Pet has been deleted", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this, "An error has occurred with Deletion", Toast.LENGTH_LONG).show();
        }

    }
*/
    public void viewCardDeleteChange(View v){
        Button b = (Button)v;
        String name = b.getText().toString();
        Intent i = new Intent(this, cardDeleteChange.class);
        i.putExtra("userEmail", ID);
        i.putExtra("petName", name);
        startActivity(i);
    }
}