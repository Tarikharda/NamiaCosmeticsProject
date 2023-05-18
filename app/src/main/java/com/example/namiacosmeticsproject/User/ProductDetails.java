package com.example.namiacosmeticsproject.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.namiacosmeticsproject.Admin.AddressChecker;
import com.example.namiacosmeticsproject.Classes.ProductClass;
import com.example.namiacosmeticsproject.Data.LocalDataBase;
import com.example.namiacosmeticsproject.Data.ProductsService;
import com.example.namiacosmeticsproject.Data.SessionManager;
import com.example.namiacosmeticsproject.R;
import com.example.namiacosmeticsproject.Classes.favoriteProducts;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductDetails extends AppCompatActivity {

    TextView detailProductTitle, detailProductCategory, detailProductPrice, detailProductDescription, dt_txt_cart;
    ImageView heartImg, dt_img_cart;
    RelativeLayout favoriteBtn;
    int productId;
    Button btn_addToCart;
    Button btn_buy;
    ProductsService productsService = new ProductsService(ProductDetails.this);
    LocalDataBase db = new LocalDataBase(this);

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        SessionManager sessionManager = new SessionManager(this);

        initViews();

        Bundle data = getIntent().getExtras();
        productId = Integer.parseInt(data.getString("id"));


        productsService.getProductsById(productId, new ProductsService.ProductsInfo() {
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

        if (isFavorite()) {
            heartImg.setImageResource(R.drawable.love);
        }

        favoriteBtn.setOnClickListener(v -> {
            if (!isFavorite()) {

                heartImg.setImageResource(R.drawable.love);
                HashMap<String, String> userInfo = sessionManager.getUserDetails();
                Object userId = userInfo.get("id");
                assert userId != null;
                int usrId = Integer.parseInt(userId.toString());
                db.rememberFavorite(true, usrId,productId);
                Toast.makeText(this, "Add to Favorite List ❤", Toast.LENGTH_SHORT).show();


            } else {
                heartImg.setImageResource(R.drawable.heart);
                if (db.removeFavorite(productId)) {
                    Toast.makeText(this, "Removed from Favorite List ❤", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Can't Removed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dt_img_cart.setOnClickListener(v -> {
            int count = Integer.parseInt(dt_txt_cart.getText().toString());
            if (count > 0) {
                Intent intent = new Intent(ProductDetails.this, CartActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Your Cart Is Empty You Should To Select Product!", Toast.LENGTH_LONG).show();
            }
        });


        btn_buy.setOnClickListener(v -> {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProductDetails.this, AddressChecker.class);
            intent.putExtra("id", productId);
            intent.putExtra("title", detailProductTitle.getText().toString());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        });

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        dt_txt_cart.setText(db.allproductscounter() + "");
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

    public boolean isFavorite() {
        for (favoriteProducts item : db.getIsFavArray()) {
            if (item.getProductId() == productId) {
                return true;
            }
        }
        return false;
    }

    private void initViews() {
        detailProductTitle = findViewById(R.id.details_product_name);
        detailProductCategory = findViewById(R.id.details_product_category);
        detailProductPrice = findViewById(R.id.details_product_price);
        detailProductDescription = findViewById(R.id.details_product_description);
        btn_addToCart = findViewById(R.id.btn_addToCart);
        dt_img_cart = findViewById(R.id.dt_img_cart);
        dt_txt_cart = findViewById(R.id.dt_txt_cart);

        favoriteBtn = findViewById(R.id.favorite_btn);
        heartImg = findViewById(R.id.heart_img);

        btn_buy = findViewById(R.id.btn_buy);

    }

    private void imageSlider(ProductClass recyclerCardModel) {
        ImageSlider sliderProductImg = findViewById(R.id.slider_product_img);
        ArrayList<SlideModel> imageListProduct = new ArrayList<>();

        imageListProduct.add(new SlideModel(recyclerCardModel.getProductImgUrl(), ScaleTypes.FIT));
        sliderProductImg.setImageList(imageListProduct, ScaleTypes.FIT);
    }
}
