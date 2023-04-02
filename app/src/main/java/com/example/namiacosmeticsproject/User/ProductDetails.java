package com.example.namiacosmeticsproject.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.namiacosmeticsproject.R;

import java.util.ArrayList;

public class ProductDetails extends AppCompatActivity {

    TextView detailProductTitle , detailProductCategory , detailProductPrice , detailProductDescription;
    ImageView heartImg;
    RelativeLayout favoriteBtn;
    boolean isFavorite = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        imageSlider();
        initViews();

        // to set the data that comes from the product that we clicked

        Bundle data = getIntent().getExtras();

        if (data != null) {
            detailProductPrice.setText(data.getString("price"));
//            Toast.makeText(this, data.getInt("position"), Toast.LENGTH_SHORT).show();
        }

        favoriteManager();

    }

    private void favoriteManager() {
        favoriteBtn = findViewById(R.id.favorite_btn);
        heartImg = findViewById(R.id.heart_img);

        favoriteBtn.setOnClickListener(v -> {

            if (!isFavorite) {
                heartImg.setImageResource(R.drawable.love);
                Toast.makeText(this, "Add to Favorite List ❤", Toast.LENGTH_SHORT).show();
                isFavorite = true;
            } else {
                heartImg.setImageResource(R.drawable.heart);
                Toast.makeText(this, "Removed from Favorite List ❤", Toast.LENGTH_SHORT).show();
                isFavorite = false;
            }

        });
    }

    private void initViews() {
        detailProductTitle = findViewById(R.id.details_product_name);
        detailProductCategory = findViewById(R.id.details_product_category);
        detailProductPrice = findViewById(R.id.details_product_price);
        detailProductDescription = findViewById(R.id.details_product_description);
    }

    private void imageSlider() {
        ImageSlider sliderProductImg = findViewById(R.id.slider_product_img);

        ArrayList<SlideModel> imageListProduct = new ArrayList<>();

        imageListProduct.add(new SlideModel(R.drawable.slider_img1 , ScaleTypes.FIT));
        imageListProduct.add(new SlideModel(R.drawable.slider_img2 , ScaleTypes.FIT));
        imageListProduct.add(new SlideModel(R.drawable.slider_img7 , ScaleTypes.FIT));

        sliderProductImg.setImageList(imageListProduct , ScaleTypes.FIT);
    }

}