package com.example.namiacosmeticsproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.namiacosmeticsproject.R;

public class WishlistFragment extends Fragment {

    TextView navTitle;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        setHasOptionsMenu(true);

        navTitle = view.findViewById(R.id.nav_title);

        navTitle.setText("Wishlist");

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragments_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}