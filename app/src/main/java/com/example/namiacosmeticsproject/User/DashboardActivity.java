package com.example.namiacosmeticsproject.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.namiacosmeticsproject.Admin.AllProducts;
import com.example.namiacosmeticsproject.Admin.LoginActivity;
import com.example.namiacosmeticsproject.Admin.newProducts;
import com.example.namiacosmeticsproject.Classes.Categories;
import com.example.namiacosmeticsproject.Classes.ProductClass;
import com.example.namiacosmeticsproject.Data.LocalDataBase;
import com.example.namiacosmeticsproject.Data.ProductsService;
import com.example.namiacosmeticsproject.Data.SessionManager;
import com.example.namiacosmeticsproject.HomeAdapter.recyclerCardAdapter;
import com.example.namiacosmeticsproject.Admin.AllProducts;
import com.example.namiacosmeticsproject.HomeAdapter.recyclerCategoriesAdapter;
import com.example.namiacosmeticsproject.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    Toolbar toolbar;
    RecyclerView topSellersRecycler, NewProductsRecycler, recyclerAllProducts,CategoryRecycler;
    recyclerCardAdapter cardAdapter, cardAdapterNewProducts, allProductsFragmentAdapter;
    recyclerCategoriesAdapter recyclerCategoryAdapter;
    ArrayList<ProductClass> topSellsProductsList, newProductsList, allProductsList;
    ArrayList<Categories> categoryList;
    ImageView menuIcon, headerMenuImg, userProfileMenu, img_search;
    TextView headerMenuTitle, navTitle, textcart, userTitleMenu, newProducts, allProducts;
    EditText ed_search;

    FragmentTransaction fragmentTransaction;

    // drawer menu

    ProgressBar progressBar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout body, cartIcon;
    FragmentManager fragmentManager;
    RelativeLayout relativeProgress;
    ProductsService productsService = new ProductsService(DashboardActivity.this);
    LocalDataBase db = new LocalDataBase(this);

    int userId;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dashboard);

        initViews();
        textcart.setText(db.allproductscounter() + "");
        imageSlider();

        topSellersRecycler();
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
        newProducts.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, AllProducts.class);
            startActivity(intent);
        });

//        ed_search.setOnEditorActionListener((v, actionId, event) -> {
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                String searchData =ed_search.getText().toString();
//                //showResults(searchData); //passing string to search in your database to your method
//                if(searchData.equals("Tarik")){
//                    Toast.makeText(DashboardActivity.this, "Yes", Toast.LENGTH_SHORT).show();
//                   return true;
//
//                }
//            }
//            return false;
//        });
        allProducts();
    }

    @SuppressLint("SetTextI18n")
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

        topSellersRecycler = findViewById(R.id.recycler_top_sells);
        NewProductsRecycler = findViewById(R.id.recycler_new_products);
        recyclerAllProducts = findViewById(R.id.rc_all_products);
        CategoryRecycler = findViewById(R.id.recycler_category);
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

        newProducts = findViewById(R.id.newProducts);
        relativeProgress = findViewById(R.id.relativeProgress);

        //for search bar
        img_search = findViewById(R.id.img_search);
        ed_search = findViewById(R.id.ed_search);


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
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        headerMenuImg.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
            drawerLayout.closeDrawer(GravityCompat.START);
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
                Intent intent = new Intent(DashboardActivity.this, AllProducts.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_best_sellers:
                Intent intent1 = new Intent(DashboardActivity.this, com.example.namiacosmeticsproject.Admin.newProducts.class);
                startActivity(intent1);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_wishlist:
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

    private void topSellersRecycler() {
        // to load just the card that are viewed by the user not all
        topSellersRecycler.setHasFixedSize(true);

        // to set the orientation of it to linear layout horizontal
        topSellersRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        topSellsProductsList = new ArrayList<>();
        productsService.getProducts(new ProductsService.ProductsInfo() {

            @Override
            public void getProductsArray(ArrayList<ProductClass> productsArrayList) {
                topSellsProductsList = productsArrayList;
                if (!topSellsProductsList.isEmpty()) {
                    relativeProgress.setVisibility(View.GONE);
                }
                // for the top sells products recycler view
                cardAdapter = new recyclerCardAdapter(DashboardActivity.this, topSellsProductsList);
                topSellersRecycler.setAdapter(cardAdapter);
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
                if (!newProductsList.isEmpty()) {
                    relativeProgress.setVisibility(View.GONE);
                }
                // for the top sells products recycler view
                cardAdapter = new recyclerCardAdapter(DashboardActivity.this, newProductsList);
                NewProductsRecycler.setAdapter(cardAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(DashboardActivity.this, "Error Loading Products !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void allProducts() {
        recyclerAllProducts.setHasFixedSize(true);
        recyclerAllProducts.setLayoutManager(new GridLayoutManager(this, 2));

        allProductsList = new ArrayList<>();

        productsService.getProducts(new ProductsService.ProductsInfo() {

            @Override
            public void getProductsArray(ArrayList<ProductClass> productsArrayList) {
                allProductsList = productsArrayList;
                allProductsFragmentAdapter = new recyclerCardAdapter(DashboardActivity.this, allProductsList);

                recyclerAllProducts.setAdapter(allProductsFragmentAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(DashboardActivity.this, "Error Loading Products !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void categoriesRecycler() {
        CategoryRecycler.setHasFixedSize(true);
        CategoryRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext() , RecyclerView.HORIZONTAL , false));

        categoryList = new ArrayList<>();
        categoryList.add(new Categories(String.valueOf(R.drawable.face) , "Face"));
        categoryList.add(new Categories(String.valueOf(R.drawable.hair) , "Hair"));
        categoryList.add(new Categories(String.valueOf(R.drawable.body) , "Body"));
        categoryList.add(new Categories(String.valueOf(R.drawable.atmosphere) , "Atmosphere"));

        recyclerCategoryAdapter= new recyclerCategoriesAdapter(getApplicationContext() , categoryList);
        CategoryRecycler.setAdapter(recyclerCategoryAdapter);
    }
    private void packsRecycler() {
    }


}
