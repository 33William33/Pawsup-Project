package com.example.pawsupapplication.ui.petcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.squareup.picasso.Picasso;

/**
 * This class is responsible for deleting or updating the specified pet chosen from the previous
 * activity
 *
 * @author Annas Rahuma
 */

public class cardDeleteChange extends AppCompatActivity {

    String ID = null;
    String petName = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_delete_change);
        ID = getIntent().getStringExtra("userEmail");
        petName = getIntent().getStringExtra("petName");
        TextView pet = (TextView) findViewById(R.id.namePet);
        pet.setText(petName);
    }

    public void changePetCard(View v){

        EditText t1 = findViewById(R.id.changeInput1);
        EditText t2 = findViewById(R.id.changeInput2);
        EditText t3 = findViewById(R.id.changeInput3);
        EditText t4 = findViewById(R.id.changeInput4);


        String[] s = inputParse(t1.getText().toString(), t2.getText().toString(),
                t3.getText().toString());

        String nS = s[0];
        String weight = s[1];
        String information = s[2];
        String picture = t4.getText().toString();


        try {


            DAO db = new DAO(cardDeleteChange.this);
            db.updatePet(petName, ID, nS, weight, information, picture);
            Toast.makeText(this, "Pet card updated", Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Toast.makeText(this, "An error has occurred with updating", Toast.LENGTH_LONG).show();
        }


    }
    public void deletePetCardButton(View v){

        try {

            DAO db = new DAO(cardDeleteChange.this);
            db.deletePet(petName, ID);
            Toast.makeText(this, "Pet has been deleted", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this, "An error has occurred with Deletion", Toast.LENGTH_LONG).show();
        }

    }

    public String[] inputParse(String ns, String weight, String information){
        String[] parsed;
        String s1;
        String s2;
        String s3;



        if(ns.toLowerCase().equals("y")  || ns.toLowerCase().equals("yes")){
            s1 = "Yes";
        }
        else if(ns.toLowerCase().equals("n") || ns.toLowerCase().equals("no")){
            s1 = "No";
        }
        else{
            s1 = "N/A";
        }

        if(weight.length() == 0 || weight.length() > 20) {
            s2 = "N/A";
        }
        else{
            s2 = weight;
        }
        if(information.length() == 0 || information.length() > 20) {
            s3 = "N/A";
        }
        else{
            s3 = information;
        }


        parsed = new String[]{s1, s2, s3};

        return parsed;
    }
}