package com.example.pawsupapplication.ui.petcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.data.model.PetCard;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class AddCard extends AppCompatActivity {


    String ID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        ID = getIntent().getStringExtra("userEmail");
        Intent i = getIntent();
        ImageView petPic = (ImageView) findViewById(R.id.imageView2);
        petPic.setImageResource(R.drawable.pawsup_logo);
    }

    /**
     * Takes the user to the activity that allows them to preview all petcards they've
     * added to the database
     *
     * @param v, the button to be clicked to change activities
     */

    public void viewPetCards(View v){

        Intent i = new Intent(this, PetCards.class);
        i.putExtra("userEmail", ID);
        System.out.println("1");
        startActivity(i);
    }

    public void viewUpdateCards(View v){

        Intent i = new Intent(this, UpdateCards.class);
        i.putExtra("userEmail", ID);
        System.out.println("1");
        startActivity(i);
    }



    public void createPetCard(View v){

        EditText t1 = findViewById(R.id.textInput1);
        EditText t2 = findViewById(R.id.textInput2);
        EditText t3 = findViewById(R.id.textInput3);
        EditText t4 = findViewById(R.id.textInput4);
        EditText t5 = findViewById(R.id.textInput5);
        EditText t6 = findViewById(R.id.textInput6);
        EditText t7 = findViewById(R.id.textInput7);

        String[] s = inputParse(t1.getText().toString(), t2.getText().toString(),
                t3.getText().toString(), t4.getText().toString(), t5.getText().toString(),
                t6.getText().toString(), t7.getText().toString());

        PetCard card = new PetCard(s[0], s[1], s[2], s[3], s[4], s[5], s[6]);
        String url = s[0];
        try {
            ImageView petPic = (ImageView) findViewById(R.id.imageView2);
            Picasso.with(this).load(url).placeholder(R.drawable.pawsup_logo)
                    .resize(100, 100).into(petPic);
            //cardMap.put(id, card);
            DAO db = new DAO(AddCard.this);
            db.addPetCard(card, ID);
            Toast.makeText(this, "Pet card created", Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Toast.makeText(this, "An error has occurred with last pet card", Toast.LENGTH_LONG).show();
        }

    }

    /**
     *A temporary function that parses input to the temporary database. THis function will be
     * removed by sprint 2
     *
     * @param i1-i7,inputs user has put in the textboxes for petcard.
     */

    public String[] inputParse(String i1, String i2, String i3, String i4, String i5, String i6,
                               String i7){
        String[] parsed;
        String s1;
        String s2;
        String s3;
        String s4;
        String s5;
        String s6;

        if(i1.length() == 0 || i1.length() > 20) {
            s1 = "N/A";
        }
        else{
            s1 = i1;
        }

        if(i2.toLowerCase().equals( "m") ||  i2.toLowerCase().equals( "male")){
            s2 = "Male";
        }
        else if(i2.toLowerCase().equals("f") || i2.toLowerCase().equals("female")){
            s2 = "Female";
        }
        else{
            s2 = "N/A";
        }

        if(i3.toLowerCase().equals("y")  || i3.toLowerCase().equals("yes")){
            s3 = "Yes";
        }
        else if(i3.toLowerCase().equals("n") || i3.toLowerCase().equals("no")){
            s3 = "No";
        }
        else{
            s3 = "N/A";
        }

        if(i4.length() == 0 || i4.length() > 20) {
            s4 = "N/A";
        }
        else{
            s4 = i4;
        }
        if(i5.length() == 0 || i5.length() > 20) {
            s5 = "N/A";
        }
        else{
            s5 = i5;
        }
        if(i6.length() == 0 || i6.length() > 60) {
            s6 = "N/A";
        }
        else{
            s6 = i6;
        }

        parsed = new String[]{i7, s1, s2, s3, s4, s5, s6};

        return parsed;
    }

}