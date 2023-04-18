package com.example.namiacosmeticsproject.CartAdapter;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namiacosmeticsproject.Classes.ProductClass;
import com.example.namiacosmeticsproject.Data.LocalDataBase;
import com.example.namiacosmeticsproject.R;
import com.example.namiacosmeticsproject.User.CartActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.CartViewHolder> {
    Context context;
    TextView totalprice;
    List<ProductClass> cartProductsList;
    LocalDataBase db;

    public CartProductAdapter(Context context, ArrayList<ProductClass> cartProductsList, TextView totalprice) {
        this.context = context;
        this.cartProductsList = cartProductsList;
        db = new LocalDataBase(context);
        this.totalprice =totalprice;
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @SuppressLint({"RestrictedApi", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ProductClass itemCart = cartProductsList.get(position);

        Picasso.get().load(itemCart.getProductImgUrl()).into(holder.cartProductImg);
        holder.cartProductId.setText(itemCart.getProductId());
        holder.cartProductTitle.setText(itemCart.getProductName());
        holder.cartProductPrice.setText(itemCart.getProductPrice() + "");
        holder.cartProductCategory.setText(itemCart.getProductCategory());
        holder.cartProductCounter.setText(itemCart.getProductCount()+"");

        holder.cartProductBin.setOnClickListener(v -> {
            int id = Integer.parseInt(cartProductsList.get(holder.getAdapterPosition()).getProductId());
//            LocalDataBase db = new LocalDataBase(context);
            db.deleteProduct(id);
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, CartActivity.class);
            context.startActivity(intent);
            (Objects.requireNonNull(getActivity(context))).finish();
        });
        holder.cartProductPlus.setOnClickListener(v -> {
            int id = Integer.parseInt(cartProductsList.get(holder.getAdapterPosition()).getProductId());
//            LocalDataBase db = new LocalDataBase(context);
            int count = db.plusProductCounter(id);
            Toast.makeText(context, "Plus", Toast.LENGTH_SHORT).show();
            if (count > 0) {
                holder.cartProductCounter.setText(count + "");
                totalprice.setText(db.sumPrices()+"");

            }
        });
        holder.cartProductMinus.setOnClickListener(v -> {
            int id = Integer.parseInt(cartProductsList.get(holder.getAdapterPosition()).getProductId());
//            LocalDataBase db = new LocalDataBase(context);
            int count = db.minusProductCounter(id);
            Toast.makeText(context, "Minus", Toast.LENGTH_SHORT).show();
            if (count > 0) {
                holder.cartProductCounter.setText(count + "");
                totalprice.setText(db.sumPrices()+"");
            }else{
                db.deleteProduct(id);
                Intent intent = new Intent(context, CartActivity.class);
                context.startActivity(intent);
                (Objects.requireNonNull(getActivity(context))).finish();
            }
        });
    }
    // the onclick of an item of the recycler view


    @Override
    public int getItemCount() {
        return cartProductsList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        CardView cart_item;
        ImageView cartProductImg, cartProductBin, cartProductPlus, cartProductMinus;
        TextView cartProductTitle, cartProductPrice, cartProductCategory, cartProductCounter, cartProductId;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            cartProductId = itemView.findViewById(R.id.cartProId);
            cartProductImg = itemView.findViewById(R.id.cart_item_img);
            cartProductBin = itemView.findViewById(R.id.cart_bin);
            cartProductPlus = itemView.findViewById(R.id.cart_plus_img);
            cartProductMinus = itemView.findViewById(R.id.cart_minus_img);
            cartProductTitle = itemView.findViewById(R.id.cart_item_txt);
            cartProductPrice = itemView.findViewById(R.id.cart_item_price);
            cartProductCategory = itemView.findViewById(R.id.cart_item_category);
            cartProductCounter = itemView.findViewById(R.id.cart_num_product_txt);
            cart_item = itemView.findViewById(R.id.cart_product);

        }
    }
}