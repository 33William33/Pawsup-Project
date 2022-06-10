package com.example.pawsupapplication.ui.seller;
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
import com.example.pawsupapplication.user.SellerActivity;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

/**
 * Class responsible for removing service from shop for a seller/admin, and contains paths to all functionality.
 * @author Yunfei Wang
 * @version 1.0
 * @since Nov 15th 2021
 */

public class RemoveShop extends AppCompatActivity {
    protected EditText address;
    protected EditText description;
    protected EditText price;
    protected EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, SellerActivity.class));
    }
}

