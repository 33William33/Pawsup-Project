package com.example.pawsupapplication.data.adapter.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.model.service.Service;
import com.example.pawsupapplication.data.model.service.ServiceImpl;
import com.example.pawsupapplication.ui.services.ServiceDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServiceRecentlyViewAdapter extends RecyclerView.Adapter<ServiceRecentlyViewAdapter.RecentlyViewedViewHolder>{
    Context context;
    List<Service> recentlyViewedList;
    String ID = null;

    public ServiceRecentlyViewAdapter(Context context, List<Service> recentlyViewedList, String ID) {
        this.context = context;
        this.recentlyViewedList = recentlyViewedList;
        this.ID = ID;
    }

    @NonNull
    @Override
    public RecentlyViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_recently_viewed_items, parent, false);

        return new RecentlyViewedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyViewedViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        ImageView tempImg = new ImageView(context);

        Picasso.with(context).load(recentlyViewedList.get(position).getServicePicture()).placeholder(R.drawable.ic_launcher_background).into(tempImg);

        holder.name.setText(recentlyViewedList.get(position).getServiceName());
        holder.description.setText(recentlyViewedList.get(position).getServiceDesc());
        holder.price.setText(recentlyViewedList.get(position).getServicePrice());
        holder.address.setText(recentlyViewedList.get(position).getServiceAddress());
        holder.bg.setBackground(tempImg.getDrawable());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(context, ServiceDetails.class);
                i.putExtra("name", recentlyViewedList.get(position).getServiceName());
                i.putExtra("image", recentlyViewedList.get(position).getServicePicture());
                i.putExtra("price",recentlyViewedList.get(position).getServicePrice());
                i.putExtra("desc",recentlyViewedList.get(position).getServiceDesc());
                i.putExtra("qty",recentlyViewedList.get(position).getServiceAddress());
                i.putExtra("userId", recentlyViewedList.get(position).getUserId());
                i.putExtra("serviceId", recentlyViewedList.get(position).getId());
                i.putExtra("userEmail", ID);

                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return recentlyViewedList.size();
    }

    public  static class RecentlyViewedViewHolder extends RecyclerView.ViewHolder{

        TextView name, description, price, address;
        ConstraintLayout bg;

        public RecentlyViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.service_name);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            address = itemView.findViewById(R.id.address);
            bg = itemView.findViewById(R.id.recently_layout);

        }
    }
}
