package com.example.namiacosmeticsproject.Fragments.MenuFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.namiacosmeticsproject.Classes.ProductClass;
import com.example.namiacosmeticsproject.Data.ProductsService;
import com.example.namiacosmeticsproject.HomeAdapter.recyclerCardAdapter;
import com.example.namiacosmeticsproject.R;
import com.example.namiacosmeticsproject.User.DashboardActivity;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class AllProductsFragment extends Fragment {

    View view;
    TextView navTitle;
    Toolbar toolbar;

    ProgressBar progressBar;

    RecyclerView recyclerAllProducts;
    recyclerCardAdapter allProductsFragmentAdapter;
    ArrayList<ProductClass> allProductsFragmentList;

    ProductsService productsService = new ProductsService(AllProductsFragment.this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_all_products, container, false);

        setHasOptionsMenu(true);

        initViews();

        // Set the Toolbar as the ActionBar for the parent Activity
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        allProductsFragment();

        return view;
    }

    private void initViews() {
        toolbar = view.findViewById(R.id.toolbar_all_products);

        progressBar = view.findViewById(R.id.all_products_progressbar);

        navTitle = view.findViewById(R.id.nav_title);
        navTitle.setText("All Products");

        recyclerAllProducts = view.findViewById(R.id.all_products_recycler);
    }

    private void allProductsFragment() {
        recyclerAllProducts.setHasFixedSize(true);
        recyclerAllProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));

        allProductsFragmentList = new ArrayList<>();

        productsService.getProducts(new ProductsService.ProductsInfo() {

            @Override
            public void getProductsArray(ArrayList<ProductClass> productsArrayList) {
                allProductsFragmentList = productsArrayList;
                allProductsFragmentAdapter = new recyclerCardAdapter(getActivity().getApplicationContext(), allProductsFragmentList);
                recyclerAllProducts.setAdapter(allProductsFragmentAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), "Error Loading Products !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragments_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onBackPressed() {
        // Handle back button event here
        // For example, pop the current fragment from the back stack
        // Get the FragmentManager
        FragmentManager fragmentManager = getParentFragmentManager();

        // Reove the current fragment
        fragmentManager.beginTransaction().remove(this).commit();

        // Remove the fragment from the back stack
        fragmentManager.popBackStack();
    }


}