package com.example.pawsupapplication.ui.services;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.example.pawsupapplication.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawsupapplication.data.adapter.service.ServiceAllCategoryAdapter;
import com.example.pawsupapplication.data.model.service.ServiceAllCategoryModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.pawsupapplication.R.drawable.*;

public class ServiceAllCategory extends AppCompatActivity {
    RecyclerView AllCategoryRecycler;
    ServiceAllCategoryAdapter allCategoryAdapter;
    List<ServiceAllCategoryModel> allCategoryModelList;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_all_category);

        AllCategoryRecycler = findViewById(R.id.all_category_service);
        back = findViewById(R.id.back_service);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(ServiceAllCategory.this, ServiceActivity.class);
                startActivity(back);
                finish();
            }
        });


        // adding data to model
        allCategoryModelList = new ArrayList<>();
        allCategoryModelList.add(new ServiceAllCategoryModel(1, R.drawable.ic_fish));
        allCategoryModelList.add(new ServiceAllCategoryModel(2, R.drawable.ic_fish));
        allCategoryModelList.add(new ServiceAllCategoryModel(3, R.drawable.ic_fish));
        allCategoryModelList.add(new ServiceAllCategoryModel(4, R.drawable.ic_fish));
        allCategoryModelList.add(new ServiceAllCategoryModel(5, R.drawable.ic_fish));
        allCategoryModelList.add(new ServiceAllCategoryModel(6, R.drawable.ic_fish));
        allCategoryModelList.add(new ServiceAllCategoryModel(7, R.drawable.ic_fish));
        allCategoryModelList.add(new ServiceAllCategoryModel(8, R.drawable.ic_fish));
        allCategoryModelList.add(new ServiceAllCategoryModel(8, R.drawable.ic_fish));


        setCategoryRecycler(allCategoryModelList);

    }

    private void setCategoryRecycler(List<ServiceAllCategoryModel> allcategoryModelList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        AllCategoryRecycler.setLayoutManager(layoutManager);
        AllCategoryRecycler.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(16), true));
        AllCategoryRecycler.setItemAnimator(new DefaultItemAnimator());
        allCategoryAdapter = new ServiceAllCategoryAdapter(this,allcategoryModelList);
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
