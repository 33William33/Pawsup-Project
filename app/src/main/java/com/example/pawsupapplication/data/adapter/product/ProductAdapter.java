package com.example.pawsupapplication.data.adapter.product;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawsupapplication.data.adapter.service.ServiceAdapter;
import com.example.pawsupapplication.data.model.product.Product;
import com.example.pawsupapplication.data.model.service.Service;
import com.example.pawsupapplication.ui.products.ProductDetails;
import com.example.pawsupapplication.R;
import com.example.pawsupapplication.ui.services.ServiceDetails;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * This class is the adapter for the product. It handles the functionalities that will be required
 * to get information from product, and creating the structures for product viewing.
 *
 * @author Lingfeng Yang
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<Product> productList;


    public ProductAdapter(Context context, List<Product> serviceList) {
        this.context = context;
        this.productList = serviceList;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_row_item, parent, false);
        return new ProductAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.ProductViewHolder holder, int position) {

        Picasso.with(context).load(productList.get(position).getProductPicture()).placeholder(R.drawable.ic_launcher_background).into(holder.prodImage);
        holder.prodName.setText(productList.get(position).getProductName());
        holder.prodQty.setText(productList.get(position).getProductQty());
        holder.prodPrice.setText(productList.get(position).getProductPrice());
        holder.prodRating.setText(productList.get(position).getProductRating());

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
        return productList.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView prodImage;
        TextView prodName, prodQty, prodPrice, prodRating;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            prodImage = itemView.findViewById(R.id.prod_image);
            prodName = itemView.findViewById(R.id.prod_name);
            prodQty = itemView.findViewById(R.id.prod_qty);
            prodPrice = itemView.findViewById(R.id.prod_price);
            prodRating = itemView.findViewById(R.id.prod_rating);
        }
    }

}
