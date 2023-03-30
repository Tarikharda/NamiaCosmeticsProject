package com.example.namiacosmeticsproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.namiacosmeticsproject.HomeAdapter.recyclerCardAdapter;
import com.example.namiacosmeticsproject.HomeAdapter.recyclerCardModel;
import com.example.namiacosmeticsproject.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    static final float END_SCALE = 0.7f;

    Toolbar toolbar;
    RecyclerView topSellsRecycler , NewProductsRecycler;
    recyclerCardAdapter cardAdapter , cardAdapterNewProducts;
    ArrayList<recyclerCardModel> topSellsProductsList, newProductsList , categoriesList , packsList;

    ImageView menuIcon;

    // drawer menu

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout body , cartIcon;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dashboard);

        initViews();

        imageSlider();

        topSellsRecycler();
        newProductsRecycler();
        categoriesRecycler();
        packsRecycler();

        drawerMenu();



    }

    private void imageSlider() {
        ImageSlider imgSlider = findViewById(R.id.image_slider);

        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.slider_img1 , ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.slider_img2 , ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.slider_img3 , ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.slider_img4 , ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.slider_img5 , ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.slider_img6 , ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.slider_img7 , ScaleTypes.FIT));

        imgSlider.setImageList(imageList , ScaleTypes.FIT);
    }

    @SuppressLint("UseSupportActionBar")
    private void initViews() {
        // toolbar

        toolbar = findViewById(R.id.toolbar);

        setActionBar(toolbar);

        // recyclers

        topSellsRecycler = findViewById(R.id.recycler_top_sells);
        NewProductsRecycler = findViewById(R.id.recycler_new_products);

        // menu

        body = findViewById(R.id.whole_body);
        menuIcon = findViewById(R.id.menu_icon);
        cartIcon = findViewById(R.id.cart_icon);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
    }

//    navigation menu functions

    private void drawerMenu() {
        // to interact with the navigation view

        navigationView.bringToFront();

        // to select items in the navigation view

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(v -> {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START);
            else drawerLayout.openDrawer(GravityCompat.START);
        });

        cartIcon.setOnClickListener(v -> {
            Intent intent =  new Intent(DashboardActivity.this , CartActivity.class);
            startActivity(intent);
        });

        animateMenu();
    }

    private void animateMenu() {
        // to change the color of the little part at right of the screen

        drawerLayout.setScrimColor(getResources().getColor(R.color.card_bg));

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // scale the view based on the current slide offset

                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;

                body.setScaleX(offsetScale);
                body.setScaleY(offsetScale);

                // translate the view , accounting for the scaled width

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = body.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;

                body.setTranslationX(xTranslation);

            }
        });
    }

    @Override
    public void onBackPressed() {
//        this for if we click in the back button when the menu is open than will be close

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // that means that we have some item to be clicked
        return true;
    }


//    recycler views functions

    private void topSellsRecycler() {
        // to load just the card that are viewed by the user not all
        topSellsRecycler.setHasFixedSize(true);

        // to set the orientation of it to linear layout horizontal
        topSellsRecycler.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));

        topSellsProductsList = new ArrayList<>();

        topSellsProductsList.add(new recyclerCardModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));
        topSellsProductsList.add(new recyclerCardModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));
        topSellsProductsList.add(new recyclerCardModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));
        topSellsProductsList.add(new recyclerCardModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));
        topSellsProductsList.add(new recyclerCardModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));

        // for the top sells products recycler view
        cardAdapter = new recyclerCardAdapter(topSellsProductsList);
        topSellsRecycler.setAdapter(cardAdapter);

    }

    private void newProductsRecycler() {
        NewProductsRecycler.setHasFixedSize(true);

        NewProductsRecycler.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));

        newProductsList = new ArrayList<>();

        newProductsList.add(new recyclerCardModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));
        newProductsList.add(new recyclerCardModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));
        newProductsList.add(new recyclerCardModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));
        newProductsList.add(new recyclerCardModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));
        newProductsList.add(new recyclerCardModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));

        // for the new products recycler view

        cardAdapterNewProducts = new recyclerCardAdapter(newProductsList);

        NewProductsRecycler.setAdapter(cardAdapterNewProducts);
    }

    private void categoriesRecycler() {
    }

    private void packsRecycler() {
    }


}