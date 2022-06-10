package com.example.pawsupapplication.data.adapter.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.model.service.ServiceAllCategoryModel;

import java.util.List;

public class ServiceAllCategoryAdapter extends RecyclerView.Adapter<ServiceAllCategoryAdapter.AllCategoryViewHolder>{

    Context context;
    List<ServiceAllCategoryModel> categoryList;

    public ServiceAllCategoryAdapter(Context context, List<ServiceAllCategoryModel> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public AllCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.service_all_category_row_item, parent, false);

        return new AllCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryViewHolder holder, int position) {

        holder.categoryImage.setImageResource(categoryList.get(position).getImageurl());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public  static class AllCategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImage;

        public AllCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.serviceCategoryImage);

        }
    }
}
