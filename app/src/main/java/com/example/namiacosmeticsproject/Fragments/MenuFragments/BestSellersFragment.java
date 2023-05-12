package com.example.namiacosmeticsproject.Fragments.MenuFragments;

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

import com.example.namiacosmeticsproject.Classes.ProductClass;
import com.example.namiacosmeticsproject.HomeAdapter.recyclerCardAdapter;
import com.example.namiacosmeticsproject.R;

import java.util.ArrayList;

public class BestSellersFragment extends Fragment {

    View view;
    TextView navTitle;
    Toolbar toolbar;

    ProgressBar progressBar;

    RecyclerView recyclerBestSellers;
    recyclerCardAdapter bestSellersFragmentAdapter;
    ArrayList<ProductClass> bestSellersFragmentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_new_products, container, false);

        setHasOptionsMenu(true);

        initViews();

        // Set the Toolbar as the ActionBar for the parent Activity
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        allProductsFragment();

        // Inflate the layout for this fragment
        return view;
    }

    private void initViews() {
        toolbar = view.findViewById(R.id.toolbar_best_sellers);

        progressBar = view.findViewById(R.id.best_sellers_progressbar);


        navTitle = view.findViewById(R.id.nav_title);
        navTitle.setText("Best Sellers");

        recyclerBestSellers = view.findViewById(R.id.best_sellers_recycler);
    }

    private void allProductsFragment() {
        recyclerBestSellers.setHasFixedSize(true);
        recyclerBestSellers.setLayoutManager(new GridLayoutManager(getContext() , 2));

        bestSellersFragmentList = new ArrayList<>();


        bestSellersFragmentAdapter = new recyclerCardAdapter(getContext() , bestSellersFragmentList);
        recyclerBestSellers.setAdapter(bestSellersFragmentAdapter);

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

        // Remove the current fragment
        fragmentManager.beginTransaction().remove(this).commit();

        // Remove the fragment from the back stack
        fragmentManager.popBackStack();
    }

}