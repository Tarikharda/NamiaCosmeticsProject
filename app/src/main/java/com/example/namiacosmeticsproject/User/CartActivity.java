package com.example.namiacosmeticsproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.namiacosmeticsproject.Admin.PaymentActivity;
import com.example.namiacosmeticsproject.CartAdapter.CartProductAdapter;
import com.example.namiacosmeticsproject.CartAdapter.CartProductModel;
import com.example.namiacosmeticsproject.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    Toolbar cartToolbar;
    TextView checkoutBtn;

    RecyclerView recyclerCart;
    CartProductAdapter cartProductAdapter;
    ArrayList<CartProductModel> cartItems;

    @SuppressLint({"MissingInflatedId", "UseSupportActionBar"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_cart);

        initViews();

        setSupportActionBar(cartToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
        getSupportActionBar().setTitle("");


        cartRecycler();

        checkoutBtn.setOnClickListener(v -> {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CartActivity.this , PaymentActivity.class));
        });
    }

    private void cartRecycler() {
        recyclerCart.setHasFixedSize(true);
        recyclerCart.setLayoutManager(new LinearLayoutManager(getApplicationContext() , LinearLayoutManager.VERTICAL , false));

        cartItems = new ArrayList<>();

        cartItems.add(new CartProductModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));
        cartItems.add(new CartProductModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));
        cartItems.add(new CartProductModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));
        cartItems.add(new CartProductModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));
        cartItems.add(new CartProductModel(R.drawable.argan_oil_s , "argan oil bio" , "$ 100.00" , "Body"));

        // for the top sells products recycler view
        cartProductAdapter = new CartProductAdapter(cartItems);
        recyclerCart.setAdapter(cartProductAdapter);

    }

    private void initViews() {
        cartToolbar = findViewById(R.id.cart_toolbar);
        recyclerCart = findViewById(R.id.recycler_cart);
        checkoutBtn = findViewById(R.id.checkout_btn);
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