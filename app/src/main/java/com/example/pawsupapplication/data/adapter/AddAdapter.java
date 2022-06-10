package com.example.pawsupapplication.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.pawsupapplication.R;

/**
 * This class is an extension of the adapter class that allows displaying of current pets before the
 * processes of booking a service.
 * This is an extension of a class from https://abhiandroid.com/ui/listview.
 *
 * @author Annas Rahuma
 */

public class AddAdapter extends BaseAdapter {
    Context context;
    Object PetCardInfo[];
    LayoutInflater inflter;

    public AddAdapter(Context applicationContext, Object[] PetCardInfo) {
        this.context = applicationContext;
        this.PetCardInfo = PetCardInfo;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return PetCardInfo.length;
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
        view = inflter.inflate(R.layout.add_display, null);
        Button delete = (Button)view.findViewById(R.id.adder);
        delete.setText((String)PetCardInfo[i]);

        return view;
    }
}