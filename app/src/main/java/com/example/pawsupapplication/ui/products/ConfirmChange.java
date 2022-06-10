package com.example.pawsupapplication.ui.products;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pawsupapplication.R;

/**
 * A general Class that displays a confirm message for changes.
 * @author Wader
 * @version 1.1
 * @since Oct 22st 2021
 */

public class ConfirmChange extends AppCompatActivity {

    // Displays UI for confirming a change.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_change);
    }

    // If confirm, show appropriate message.
    public void confirm(View v) {
        Toast.makeText(this, "Changes are saved!", Toast.LENGTH_LONG).show();
        finish();
    }

    // If cancel, show appropriate message.
    public void cancel(View v) {
        Toast.makeText(this, "Changes are canceled.", Toast.LENGTH_LONG).show();
        finish();
    }
}
