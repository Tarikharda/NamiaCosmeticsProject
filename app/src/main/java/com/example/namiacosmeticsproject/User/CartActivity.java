package com.example.namiacosmeticsproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.namiacosmeticsproject.Admin.AddressChecker;
import com.example.namiacosmeticsproject.CartAdapter.CartProductAdapter;
import com.example.namiacosmeticsproject.Classes.ProductClass;
import com.example.namiacosmeticsproject.Data.LocalDataBase;
import com.example.namiacosmeticsproject.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {


    Toolbar cartToolbar;
    TextView totalPrice;
    RecyclerView recyclerCart;
    CartProductAdapter cartProductAdapter;
    ArrayList<ProductClass> cartItems;
    ImageView deleteProduct, plusProductCounter, minusProductCounter;
    MaterialRippleLayout  checkout;
    LocalDataBase db = new LocalDataBase(this);

    @SuppressLint({"MissingInflatedId", "UseSupportActionBar", "SetTextI18n"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_cart);

        cartToolbar = findViewById(R.id.cart_toolbar);
        deleteProduct = findViewById(R.id.cart_bin);
        plusProductCounter = findViewById(R.id.cart_plus_img);
        minusProductCounter = findViewById(R.id.cart_minus_img);
        checkout = findViewById(R.id.checkout);

        totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText(db.sumPrices()+"");
        setSupportActionBar(cartToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_black);
        getSupportActionBar().setTitle("");


        initViews();

        cartRecycler();

        checkout.setOnClickListener(V->{
            float price=Float.parseFloat(totalPrice.getText().toString());
            if(price>0){
                Intent intent = new Intent(CartActivity.this, AddressChecker.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Your Cant Pay For Nothing You Should To Select Product!", Toast.LENGTH_LONG).show();
            }

        });



    }

    private void cartRecycler() {
        recyclerCart.setHasFixedSize(true);
        recyclerCart.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        cartItems = new ArrayList<>();
        LocalDataBase localDataBase = new LocalDataBase(this);
        cartItems = localDataBase.getAllProducts();
        cartProductAdapter = new CartProductAdapter(this, cartItems, totalPrice);
        recyclerCart.setAdapter(cartProductAdapter);

//
    }

    private void initViews() {
        recyclerCart = findViewById(R.id.recycler_cart);
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