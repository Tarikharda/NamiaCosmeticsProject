package com.example.namiacosmeticsproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.namiacosmeticsproject.Admin.LoginActivity;
import com.example.namiacosmeticsproject.Classes.ProductClass;
import com.example.namiacosmeticsproject.Data.LocalDataBase;
import com.example.namiacosmeticsproject.Data.ProductsService;
import com.example.namiacosmeticsproject.Data.SessionManager;
import com.example.namiacosmeticsproject.Fragments.MenuFragments.AllProductsFragment;
import com.example.namiacosmeticsproject.Fragments.MenuFragments.BestSellersFragment;
import com.example.namiacosmeticsproject.Fragments.MenuFragments.WishlistFragment;
import com.example.namiacosmeticsproject.HomeAdapter.recyclerCardAdapter;
import com.example.namiacosmeticsproject.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    Toolbar toolbar;
    RecyclerView topSellsRecycler, NewProductsRecycler;
    recyclerCardAdapter cardAdapter, cardAdapterNewProducts;
    ArrayList<ProductClass> topSellsProductsList;
    ArrayList<ProductClass> newProductsList;

    ImageView menuIcon, headerMenuImg, userProfileMenu;
    TextView headerMenuTitle, navTitle, textcart, userTitleMenu;

    FragmentTransaction fragmentTransaction;

    // drawer menu

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout body, cartIcon;
    FragmentManager fragmentManager;

    ProductsService productsService = new ProductsService(DashboardActivity.this);
    LocalDataBase db = new LocalDataBase(this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dashboard);

        initViews();
        textcart.setText(db.allproductscounter() + "");
        imageSlider();

        topSellsRecycler();
        newProductsRecycler();
        categoriesRecycler();
        packsRecycler();

        drawerMenu();
        userProfileMenu = findViewById(R.id.img_menu);
        userTitleMenu = findViewById(R.id.txt_menu);
        SessionManager sessionManager = new SessionManager(this);
//        if (sessionManager.isLoggedIn()) {
//            HashMap<String, String> userInfo = sessionManager.getUserDetails();
//            String userName = userInfo.get("userName");
//            String userImgUrl = userInfo.get("userImgUrl");
//            userTitleMenu.setText(userName);
//            Picasso.get().load(userImgUrl).into(userProfileMenu);
//        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        textcart.setText(db.allproductscounter() + "");
    }

    private void imageSlider() {
        ImageSlider imgSlider = findViewById(R.id.image_slider);

        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.slider_img1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.slider_img2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.slider_img3, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.slider_img4, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.slider_img5, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.slider_img6, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.slider_img7, ScaleTypes.FIT));

        imgSlider.setImageList(imageList, ScaleTypes.FIT);
    }

    @SuppressLint("UseSupportActionBar")
    private void initViews() {
        // toolbar

        toolbar = findViewById(R.id.toolbar_signup);

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

        // handle the clicks on the header menu

        View headerView = navigationView.getHeaderView(0);
        headerMenuImg = headerView.findViewById(R.id.img_menu);
        headerMenuTitle = headerView.findViewById(R.id.txt_menu);

        navTitle = findViewById(R.id.nav_title);
        textcart = findViewById(R.id.txt_cart);


    }

//    navigation menu functions

    private void drawerMenu() {
        // to interact with the navigation view

        navigationView.bringToFront();

        // to select items in the navigation view

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        // to let the icons inside the drawer layout but it disabled the style of the click on the items
//        navigationView.setItemIconTintList(null);

        menuIcon.setOnClickListener(v -> {
            if (drawerLayout.isDrawerVisible(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START);
            else drawerLayout.openDrawer(GravityCompat.START);
        });

        cartIcon.setOnClickListener(v -> {
            int count = Integer.parseInt(textcart.getText().toString());
            if (count > 0) {
                Intent intent = new Intent(DashboardActivity.this, CartActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Your Cart Is Empty You Should To Select Product!", Toast.LENGTH_LONG).show();
            }

        });

        animateMenu();

        // handle the clicks on the header menu

        headerMenuTitle.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
        });

        headerMenuImg.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
        });

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
        fragmentManager = getSupportFragmentManager();
        Fragment curentf1 = fragmentManager.findFragmentById(R.id.fragment_container);
        if (curentf1 != null) {
            fragmentManager.beginTransaction().remove(curentf1).commit();

            // Remove the fragment from the back stack
            fragmentManager.popBackStack();
            return;
        }

//        this for if we click in the back button when the menu is open than will be close
        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }


    public void fragmentCreator(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // that means that we have some item to be clicked

        switch (item.getItemId()) {
            case R.id.nav_home:
                fragmentManager = getSupportFragmentManager();
                Fragment curentf1 = fragmentManager.findFragmentById(R.id.fragment_container);
                if (curentf1 != null) {
                    fragmentManager.beginTransaction().remove(curentf1).commit();
                    fragmentManager.popBackStack();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else
                    drawerLayout.closeDrawer(GravityCompat.START);

//                startActivity(new Intent(DashboardActivity.this, DashboardActivity.class));
//                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_all_products:
                fragmentCreator(new AllProductsFragment());
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_best_sellers:
                fragmentCreator(new BestSellersFragment());
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_wishlist:
                fragmentCreator(new WishlistFragment());
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_shipping_infos:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_about:
//                startActivity(new Intent(this, AboutUsActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_contact:
                startActivity(new Intent(this, ContactUsActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_exit:
                finish();

        }
        return true;
    }


    //    recycler views functions

    private void topSellsRecycler() {
        // to load just the card that are viewed by the user not all
        topSellsRecycler.setHasFixedSize(true);

        // to set the orientation of it to linear layout horizontal
        topSellsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        topSellsProductsList = new ArrayList<>();
        productsService.getProducts(new ProductsService.ProductsInfo() {

            @Override
            public void getProductsArray(ArrayList<ProductClass> productsArrayList) {
                topSellsProductsList = productsArrayList;

                // for the top sells products recycler view
                cardAdapter = new recyclerCardAdapter(getApplicationContext(), topSellsProductsList);
                topSellsRecycler.setAdapter(cardAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(DashboardActivity.this, "Error Loading Products !!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void newProductsRecycler() {
        NewProductsRecycler.setHasFixedSize(true);

        NewProductsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        newProductsList = new ArrayList<>();
        productsService.getProducts(new ProductsService.ProductsInfo() {

            @Override
            public void getProductsArray(ArrayList<ProductClass> productsArrayList) {
                newProductsList = productsArrayList;

                // for the top sells products recycler view
                cardAdapter = new recyclerCardAdapter(getApplicationContext(), newProductsList);
                topSellsRecycler.setAdapter(cardAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(DashboardActivity.this, "Error Loading Products !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void categoriesRecycler() {
    }

    private void packsRecycler() {
    }


}