package com.example.namiacosmeticsproject.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.namiacosmeticsproject.R;

import java.util.ArrayList;

public class ProductDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        imageSlider();
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