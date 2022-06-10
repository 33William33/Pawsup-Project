package com.example.pawsupapplication.data.adapter.service;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawsupapplication.data.model.service.Service;
import com.example.pawsupapplication.R;
import com.example.pawsupapplication.ui.services.ServiceDetails;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * This class is the adapter for the service. It handles the functionalities that will be required
 * to get information from service, and creating the structures for service viewing.
 *
 * @author Lingfeng Yang
 */
public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    Context context;
    List<Service> serviceList;


    public ServiceAdapter(Context context, List<Service> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.service_row_item, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ServiceViewHolder holder, int position) {
        Picasso.with(context).load(serviceList.get(position).getServicePicture()).placeholder(R.drawable.ic_launcher_background).into(holder.servImage);
        holder.servName.setText(serviceList.get(position).getServiceName());
        holder.servDesc.setText(serviceList.get(position).getServiceDesc());
        holder.servAddr.setText(serviceList.get(position).getServiceAddress());
        holder.servPrice.setText(serviceList.get(position).getServicePrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ServiceDetails.class);
/*
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(holder.prodImage, "image");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pairs);
               */ context.startActivity(i/*, activityOptions.toBundle()*/);
            }
        });


    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static final class ServiceViewHolder extends RecyclerView.ViewHolder{

        ImageView servImage;
        TextView servName, servDesc, servAddr, servPrice;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            servImage = itemView.findViewById(R.id.serv_image);
            servName = itemView.findViewById(R.id.serv_name);
            servDesc = itemView.findViewById(R.id.serv_desc);
            servAddr = itemView.findViewById(R.id.serv_address);
            servPrice = itemView.findViewById(R.id.serv_price);
        }
    }

}
