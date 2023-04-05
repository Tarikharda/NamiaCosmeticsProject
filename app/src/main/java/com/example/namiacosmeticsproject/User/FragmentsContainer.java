package com.example.namiacosmeticsproject.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;
import com.example.namiacosmeticsproject.R;

public class FragmentsContainer extends AppCompatActivity {

    Toolbar toolbar;

    FragmentTransaction fragmentTransaction;
    TextView navTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_container);

        toolbar = findViewById(R.id.toolbar_fragments);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
        getSupportActionBar().setTitle("");


        navTitle = findViewById(R.id.nav_title);

//        fragmentSearch.setOnClickListener(v -> {
//            navTitle.setText("All Products");
//            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            AllProductsFragment fragment1 = new AllProductsFragment();
//            fragmentTransaction.replace(R.id.container , fragment1);
//            fragmentTransaction.commit();
//        });

    }

//    public void fragmentCreator(String fragmentTitle, Fragment fragment) {
//        navTitle.setText(fragmentTitle);
//        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container , fragment);
//        fragmentTransaction.commit();
//    }

    public FragmentsContainer(String fragmentTitle, Fragment fragment) {
        navTitle.setText(fragmentTitle);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container , fragment);
        fragmentTransaction.commit();
    }

}