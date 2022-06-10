package com.example.pawsupapplication.data.adapter.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.model.product.ProductCategory;

import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.CategoryViewHolder> {
    Context context;
    List<ProductCategory> categoryList;

    public ProductCategoryAdapter(Context context, List<ProductCategory> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ProductCategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_category_row_item, parent, false);

        return new ProductCategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCategoryAdapter.CategoryViewHolder holder, int position) {

        holder.categoryImage.setImageResource(categoryList.get(position).getImageurl());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public  static class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImage;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.productCategoryImage);

        }
    }
}
