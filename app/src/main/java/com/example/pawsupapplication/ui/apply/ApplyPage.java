package com.example.pawsupapplication.ui.apply;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.MainActivity;
import com.example.pawsupapplication.R;
import com.example.pawsupapplication.ui.*;
import com.example.pawsupapplication.data.model.service.*;
import com.example.pawsupapplication.data.DAO;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class ApplyPage extends AppCompatActivity {
    protected EditText sername;
    protected EditText address;
    protected EditText description;
    protected EditText price;
    protected EditText email;
    protected EditText picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_page);
        sername =  findViewById(R.id.sername);
        address =  findViewById(R.id.serads);
        description =  findViewById(R.id.description);
        price = findViewById(R.id.price);
        email  = findViewById(R.id.email);
        picture = findViewById(R.id.picture);
        TextView Apply = findViewById(R.id.apply);
        DAO db = new DAO(ApplyPage.this);
        Apply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    //add new service
                    Map<String, ArrayList<String>> users = db.getUsers();
                    boolean b = false;
                    String uniqueID = UUID.randomUUID().toString();
                    for(String key: users.keySet()){
                        if (email.getText().toString().compareTo(key) == 0 && !db.getByEmail(key).getProvider()) {
                            Service ser = new ServiceImpl(db.getByEmail(key).getId(),
                                    sername.getText().toString(),
                                    description.getText().toString(),
                                    address.getText().toString(),
                                    price.getText().toString(),
                                    picture.getText().toString(),
                                    uniqueID);
                            db.addService(ser);
                            db.setFlagByEmail(key);
                            b = true;
                        }
                    }
                    if(b){
                        //return to previous page if successful
                        Toast.makeText(ApplyPage.this, "Apply Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ApplyPage.this, "Apply Fail: account not found/ not a customer", Toast.LENGTH_SHORT).show();
                    }
                }catch (NumberFormatException nfe) {
                    Toast.makeText(ApplyPage.this, "Apply Fail: Wrong format", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
