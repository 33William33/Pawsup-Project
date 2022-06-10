package com.example.pawsupapplication.ui.petcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;

/**
 * This class is responsible for deleting petcards
 *
 * @author Annas Rahuma
 */

public class DeleteCards extends AppCompatActivity {

    String ID = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_cards);
        ID = getIntent().getStringExtra("userEmail");
    }

    public void deletePetCard(View v) {
    try {
        EditText t = findViewById(R.id.deletePetName);
        DAO db = new DAO(DeleteCards.this);
        boolean report = db.deletePet(t.getText().toString(), ID);

    }catch(Exception e){
        Toast.makeText(this, "An error has occurred with Deletion", Toast.LENGTH_LONG).show();
    }

    }
}