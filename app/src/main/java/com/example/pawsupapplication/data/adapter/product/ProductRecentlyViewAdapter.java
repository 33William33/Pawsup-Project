package com.example.pawsupapplication.data.adapter.product;

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
import com.example.pawsupapplication.data.model.product.Product;
import com.example.pawsupapplication.ui.products.ProductDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductRecentlyViewAdapter extends RecyclerView.Adapter<ProductRecentlyViewAdapter.RecentlyViewedViewHolder>{
    Context context;
    List<Product> recentlyViewedList;
    String ID = null;

    public ProductRecentlyViewAdapter(Context context, List<Product> recentlyViewedList, String ID) {
        this.context = context;
        this.recentlyViewedList = recentlyViewedList;
        this.ID = ID;
    }

    @NonNull
    @Override
    public ProductRecentlyViewAdapter.RecentlyViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_recently_viewed_items, parent, false);

        return new ProductRecentlyViewAdapter.RecentlyViewedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecentlyViewAdapter.RecentlyViewedViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        ImageView tempImg = new ImageView(context);

        Picasso.with(context).load(recentlyViewedList.get(position).getProductPicture()).placeholder(R.drawable.ic_launcher_background).into(tempImg);

        holder.name.setText(recentlyViewedList.get(position).getProductName());
        holder.quantity.setText(recentlyViewedList.get(position).getProductQty());
        holder.price.setText(recentlyViewedList.get(position).getProductPrice());
        holder.rating.setText(recentlyViewedList.get(position).getProductRating());
        holder.bg.setBackground(tempImg.getDrawable());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(context, ProductDetails.class);
                i.putExtra("name", recentlyViewedList.get(position).getProductName());
                i.putExtra("image", recentlyViewedList.get(position).getProductPicture());
                i.putExtra("price",recentlyViewedList.get(position).getProductPrice());
                i.putExtra("qty",recentlyViewedList.get(position).getProductQty());
                i.putExtra("rating",recentlyViewedList.get(position).getProductRating());
                i.putExtra("productID", recentlyViewedList.get(position).getId());
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

        TextView name, quantity, price, rating;
        ConstraintLayout bg;

        public RecentlyViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            quantity = itemView.findViewById(R.id.qty);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);
            bg = itemView.findViewById(R.id.recently_layout);

        }
    }
}
