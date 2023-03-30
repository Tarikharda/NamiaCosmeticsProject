package com.example.namiacosmeticsproject.CartAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namiacosmeticsproject.R;

import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.CartViewHolder> {

    List<CartProductModel> cartProductsList;

    public CartProductAdapter(List<CartProductModel> cartProductsList) {
        this.cartProductsList = cartProductsList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item , parent , false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartProductModel itemCart = cartProductsList.get(position);

        holder.cartProductImg.setImageResource(itemCart.getCartProductImg());
        holder.cartProductTitle.setText(itemCart.getCartProductTitle());
        holder.cartProductPrice.setText(itemCart.getCartProductPrice());
        holder.cartProductCategory.setText(itemCart.getCartProductCategory());

    }

    @Override
    public int getItemCount() {
        return cartProductsList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView cartProductImg , cartProductBin , cartProductPlus , cartProductMinus;
        TextView cartProductTitle , cartProductPrice , cartProductCategory , cartProductCounter;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            cartProductImg = itemView.findViewById(R.id.cart_item_img);
            cartProductBin = itemView.findViewById(R.id.cart_bin);
            cartProductPlus = itemView.findViewById(R.id.cart_plus_img);
            cartProductMinus = itemView.findViewById(R.id.cart_minus_img);
            cartProductTitle = itemView.findViewById(R.id.cart_item_txt);
            cartProductPrice = itemView.findViewById(R.id.cart_item_price);
            cartProductCategory = itemView.findViewById(R.id.cart_item_category);
            cartProductCounter = itemView.findViewById(R.id.cart_num_product_txt);
        }
    }
}
