package com.example.pawsupapplication.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pawsupapplication.R;
import com.squareup.picasso.Picasso;

/**
 * This class is an extension of the adapter class that allows for the input of images alongside
 * text rather than the basic adapter only having one parameter, this one has two.
 * This is an extension of a class from https://abhiandroid.com/ui/listview.
 *
 * @author Annas Rahuma
 */

public class HistoryAdapter extends BaseAdapter {
    Context context;
    Object historyInfo[];
    Object historyPic[];
    LayoutInflater inflter;

    public HistoryAdapter(Context context, Object[] historyInfo, Object[] historyPic) {
        this.context = context;
        this.historyInfo = historyInfo;
        this.historyPic = historyPic;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return historyInfo.length;
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
        view = inflter.inflate(R.layout.cards_display, null);
        TextView petInfo = (TextView)view.findViewById(R.id.petText);
        ImageView petPic = (ImageView)view.findViewById(R.id.petImage);
        String url = (String)historyPic[i];
        Picasso.with(context).load(url).placeholder(R.drawable.ic_launcher_background)
                .resize(100, 100).into(petPic);
        petInfo.setText((String)historyInfo[i]);
        return view;
    }

}
