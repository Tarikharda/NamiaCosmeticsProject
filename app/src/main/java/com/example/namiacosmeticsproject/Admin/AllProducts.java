package com.example.namiacosmeticsproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.namiacosmeticsproject.Classes.ProductClass;
import com.example.namiacosmeticsproject.Data.ProductsService;
import com.example.namiacosmeticsproject.HomeAdapter.recyclerCardAdapter;
import com.example.namiacosmeticsproject.R;

import java.util.ArrayList;

public class AllProducts extends AppCompatActivity {
    TextView navTitle;
    Toolbar toolbar;

    ProgressBar progressBar;

    RecyclerView recyclerAllProducts;
    recyclerCardAdapter allProductsFragmentAdapter;
    ArrayList<ProductClass> allProductsList;

    ProductsService productsService = new ProductsService(AllProducts.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        initViews();
        // Set the Toolbar as the ActionBar for the parent Activity
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
        getSupportActionBar().setTitle("");

        allProducts();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar_all_products);

        progressBar = findViewById(R.id.all_products_progressbar);

        navTitle =findViewById(R.id.nav_title);
        navTitle.setText("All Products");

        recyclerAllProducts = findViewById(R.id.all_products_recycler);
    }

    private void allProducts() {
        recyclerAllProducts.setHasFixedSize(true);
        recyclerAllProducts.setLayoutManager(new GridLayoutManager(this, 2));

        allProductsList = new ArrayList<>();

        productsService.getProducts(new ProductsService.ProductsInfo() {

            @Override
            public void getProductsArray(ArrayList<ProductClass> productsArrayList) {
                allProductsList = productsArrayList;
                if(!allProductsList.isEmpty()){
                   progressBar.setVisibility(View.GONE);
                }
                allProductsFragmentAdapter = new recyclerCardAdapter(AllProducts.this, allProductsList);

                recyclerAllProducts.setAdapter(allProductsFragmentAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(AllProducts.this, "Error Loading Products !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}