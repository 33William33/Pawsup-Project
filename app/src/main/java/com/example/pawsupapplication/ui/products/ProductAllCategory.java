package com.example.pawsupapplication.ui.products;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.adapter.product.ProductAllCategoryAdapter;
import com.example.pawsupapplication.data.model.product.ProductAllCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class ProductAllCategory extends AppCompatActivity {
    RecyclerView AllCategoryRecycler;
    ProductAllCategoryAdapter allCategoryAdapter;
    List<ProductAllCategoryModel> allCategoryModelList;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_all_category);

        AllCategoryRecycler = findViewById(R.id.all_category_product);
        back = findViewById(R.id.back_product);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(ProductAllCategory.this, ProductsActivity.class);
                startActivity(back);
                finish();
            }
        });


        // adding data to model
        allCategoryModelList = new ArrayList<>();
        allCategoryModelList.add(new ProductAllCategoryModel(1, R.drawable.ic_fish));
        allCategoryModelList.add(new ProductAllCategoryModel(2, R.drawable.ic_fish));
        allCategoryModelList.add(new ProductAllCategoryModel(3, R.drawable.ic_fish));
        allCategoryModelList.add(new ProductAllCategoryModel(4, R.drawable.ic_fish));
        allCategoryModelList.add(new ProductAllCategoryModel(5, R.drawable.ic_fish));
        allCategoryModelList.add(new ProductAllCategoryModel(6, R.drawable.ic_fish));
        allCategoryModelList.add(new ProductAllCategoryModel(7, R.drawable.ic_fish));
        allCategoryModelList.add(new ProductAllCategoryModel(8, R.drawable.ic_fish));
        allCategoryModelList.add(new ProductAllCategoryModel(8, R.drawable.ic_fish));


        setCategoryRecycler(allCategoryModelList);

    }

    private void setCategoryRecycler(List<ProductAllCategoryModel> allcategoryModelList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        AllCategoryRecycler.setLayoutManager(layoutManager);
        AllCategoryRecycler.addItemDecoration(new ProductAllCategory.GridSpacingItemDecoration(4, dpToPx(16), true));
        AllCategoryRecycler.setItemAnimator(new DefaultItemAnimator());
        allCategoryAdapter = new ProductAllCategoryAdapter(this,allcategoryModelList);
        AllCategoryRecycler.setAdapter(allCategoryAdapter);
    }

    // now we need some item decoration class for manage spacing

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
