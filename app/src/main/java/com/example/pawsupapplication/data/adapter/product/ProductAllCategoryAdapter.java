package com.example.pawsupapplication.data.adapter.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.model.product.ProductAllCategoryModel;

import java.util.List;

public class ProductAllCategoryAdapter extends RecyclerView.Adapter<ProductAllCategoryAdapter.AllCategoryViewHolder>{
    Context context;
    List<ProductAllCategoryModel> categoryList;

    public ProductAllCategoryAdapter(Context context, List<ProductAllCategoryModel> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ProductAllCategoryAdapter.AllCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_all_category_row_item, parent, false);

        return new ProductAllCategoryAdapter.AllCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAllCategoryAdapter.AllCategoryViewHolder holder, int position) {

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

            categoryImage = itemView.findViewById(R.id.productCategoryImage);

        }
    }
}
