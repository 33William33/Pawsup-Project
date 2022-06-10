package com.example.pawsupapplication.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pawsupapplication.R;

/**
 * This class is an extension of the adapter class that allows for the input of
 * details related to the orders.
 * This is an extension of a class from https://abhiandroid.com/ui/listview.
 *
 * @author Shu Sun
 */

public class OrderAdapter extends BaseAdapter {
    Context context;
    Object OrderInfo[];
    LayoutInflater inflater;

    public OrderAdapter(Context applicationContext, Object[] oInfo) {
        this.context = applicationContext;
        this.OrderInfo = oInfo;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return OrderInfo.length;
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
        view = inflater.inflate(R.layout.order_display, null);
        TextView oInfo = (TextView)view.findViewById(R.id.orderText);
        TextView text = (TextView)view.findViewById(R.id.textView2);
        String s = "\nOrder Number:"
                + "\nCustomer Name:"
                + "\nPet:"
                + "\nOrder Date:"
                + "\nService Start:"
                + "\nService End:"
                + "\n";
        oInfo.setText((String)OrderInfo[i]);
        text.setText(s);

        return view;
    }
}
