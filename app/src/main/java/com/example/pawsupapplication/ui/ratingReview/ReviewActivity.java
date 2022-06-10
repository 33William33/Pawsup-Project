package com.example.pawsupapplication.ui.ratingReview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.model.Review;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

/**
 * This class is responsible for allowing users
 * to enter their review and add them to the
 * database.
 *
 * @author Annas Rahuma and Shu Sun
 */

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addreview_activity);
    }

    public void createReview(View v){
        EditText t1 = findViewById(R.id.TitleBox);
        EditText t2 = findViewById(R.id.AddReviewBox);

        RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.ratingBar); // initiate a rating bar
        float numberOfStars = simpleRatingBar.getRating();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String day = formatter.format(date);

        String id = UUID.randomUUID().toString();
        String[] s = new String[]{t1.getText().toString(), t2.getText().toString()};

        Review rev = new Review(numberOfStars, day, s[0], s[1]);

        try {
            RecentServices.reviewMap.put(id, rev);
            Toast.makeText(this, "Review created", Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Toast.makeText(this, "An error has occurred with last Review", Toast.LENGTH_LONG).show();
        }
    }

    public void viewReviews(View v){
        createReview(v);
        Intent i = new Intent(this, DisplayReviews.class);

        Iterator<Review> it = RecentServices.reviewMap.values().iterator();
        Iterator<Review> itRatings = RecentServices.reviewMap.values().iterator();
        ArrayList<String> reviewArray = new ArrayList<String>();
        ArrayList<Float> reviewRatings = new ArrayList<Float>();

        while(it.hasNext()){
            Review r = it.next();
            String review = "Title:" + r.getTitle()
                    + "\nDetails:" + r.getReview() +
                    "\nDate: " + r.getDate() ;

            reviewArray.add(review);
        }

        while(itRatings.hasNext()){
            Review r = itRatings.next();
            Float rate = r.getRating();

            reviewRatings.add(rate);
        }

        i.putExtra("map", reviewArray);
        i.putExtra("map1", reviewRatings);
        startActivity(i);
    }
}