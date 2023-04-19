package com.example.namiacosmeticsproject.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.namiacosmeticsproject.Admin.AddressChecker;
import com.example.namiacosmeticsproject.Classes.ProductClass;
import com.example.namiacosmeticsproject.Data.LocalDataBase;
import com.example.namiacosmeticsproject.Data.ProductsService;
import com.example.namiacosmeticsproject.R;

import java.util.ArrayList;

public class ProductDetails extends AppCompatActivity {

    TextView detailProductTitle, detailProductCategory, detailProductPrice, detailProductDescription;
    ImageView heartImg;
    RelativeLayout favoriteBtn;
    boolean isFavorite = false;
    int id;
    Button btn_addToCart;
    Button btn_buy;
    ProductsService productsService = new ProductsService(ProductDetails.this);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        initViews();

        Bundle data = getIntent().getExtras();
        id = Integer.parseInt(data.getString("id"));

        btn_buy = findViewById(R.id.btn_buy);

        productsService.getProductsById(id, new ProductsService.ProductsInfo() {
            @Override
            public void getProductsArray(ArrayList<ProductClass> productsArrayList) {
                ProductClass recyclerCardModel = productsArrayList.get(0);
                setInfoProducts(recyclerCardModel);
                btn_addToCart.setOnClickListener(v -> {
                    LocalDataBase db = new LocalDataBase(ProductDetails.this);
                    db.insertProduct(new ProductClass(
                            recyclerCardModel.getProductId(),
                            recyclerCardModel.getProductName(),
                            recyclerCardModel.getProductDes(),
                            recyclerCardModel.getProductPrice(),
                            recyclerCardModel.getProductImgUrl(),
                            recyclerCardModel.getProductCategory(),
                            1
                    ));
                    Toast.makeText(ProductDetails.this, db.countProduct() + "", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(ProductDetails.this, "Error !!!", Toast.LENGTH_SHORT).show();
            }
        });

        favoriteManager();

        btn_buy.setOnClickListener(v -> {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProductDetails.this, AddressChecker.class);
            intent.putExtra("id", id);
            intent.putExtra("title", detailProductTitle.getText().toString());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        });

    }

    @SuppressLint("SetTextI18n")
    void setInfoProducts(ProductClass recyclerCardModel) {
        if (recyclerCardModel != null) {
            detailProductTitle.setText(recyclerCardModel.getProductName());
            detailProductPrice.setText(recyclerCardModel.getProductPrice() + "");
            detailProductCategory.setText(recyclerCardModel.getProductCategory());
            detailProductDescription.setText(recyclerCardModel.getProductDes());
            imageSlider(recyclerCardModel);
        }
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
        btn_addToCart = findViewById(R.id.btn_addToCart);
    }

    private void imageSlider(ProductClass recyclerCardModel) {
        ImageSlider sliderProductImg = findViewById(R.id.slider_product_img);
        ArrayList<SlideModel> imageListProduct = new ArrayList<>();

        imageListProduct.add(new SlideModel(recyclerCardModel.getProductImgUrl(), ScaleTypes.FIT));


        sliderProductImg.setImageList(imageListProduct, ScaleTypes.FIT);
    }
}
