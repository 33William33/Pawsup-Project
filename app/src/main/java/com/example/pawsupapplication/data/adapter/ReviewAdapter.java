package com.example.pawsupapplication.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pawsupapplication.R;

/**
 * This class is an adapter object for the reviews.
 * It contains all functions necessary for retrieving information from Reviews
 * including the ratings and review body, and creating structures for viewing
 * reviews.
 *
 * @author Annas Rahuma and Shu Sun
 */
public class ReviewAdapter extends BaseAdapter {

    Context context;
    Object ReviewInfo[];
    Object ReviewRatings[];
    LayoutInflater inflater;

    public ReviewAdapter(Context applicationContext, Object[] rInfo, Object[] rRatings) {
        this.context = applicationContext;
        this.ReviewInfo = rInfo;
        this.ReviewRatings = rRatings;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return ReviewInfo.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.review_display, null);
        Float rating = (Float)ReviewRatings[i];
        TextView rInfo = (TextView)view.findViewById(R.id.reviewText);

        ImageView star1 = (ImageView)view.findViewById(R.id.star1);
        ImageView star2 = (ImageView)view.findViewById(R.id.star2);
        ImageView star3 = (ImageView)view.findViewById(R.id.star3);
        ImageView star4 = (ImageView)view.findViewById(R.id.star4);
        ImageView star5 = (ImageView)view.findViewById(R.id.star5);

        if (rating.floatValue() >= 1.0){
            star1.setImageResource(R.drawable.imageedit_1_4802603235);
            if (rating.floatValue() >= 2.0){
                star2.setImageResource(R.drawable.imageedit_1_4802603235);
                if (rating.floatValue() >= 3.0){
                    star3.setImageResource(R.drawable.imageedit_1_4802603235);
                    if (rating.floatValue() >= 4.0){
                        star4.setImageResource(R.drawable.imageedit_1_4802603235);
                        if (rating.floatValue() >= 5.0){
                            star5.setImageResource(R.drawable.imageedit_1_4802603235);
                        }
                    }
                }
            }
        }
        rInfo.setText((String)ReviewInfo[i]);
        return view;
    }
}
