package com.example.namiacosmeticsproject.HomeAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namiacosmeticsproject.R;
import com.example.namiacosmeticsproject.User.ProductDetails;
import com.squareup.picasso.Picasso;
import com.example.namiacosmeticsproject.Classes.ProductClass;
import java.util.ArrayList;

public class recyclerCardAdapter extends RecyclerView.Adapter<recyclerCardAdapter.rc_card_ViewHolder> {

    Context context;
    ArrayList<ProductClass> ProductsList;

    public recyclerCardAdapter(Context context , ArrayList<ProductClass> ProductsList) {
        this.context = context;
        this.ProductsList = ProductsList;
    }

    @NonNull
    @Override
    public rc_card_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent , false);
        rc_card_ViewHolder rc_card = new rc_card_ViewHolder(view);
        return rc_card;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull rc_card_ViewHolder holder, int position) {
        ProductClass cardInfos = ProductsList.get(position);

        holder.productId.setText(cardInfos.getProductId());
        holder.productName.setText(cardInfos.getProductName());
        holder.productPrice.setText(cardInfos.getProductPrice()+"");
        holder.productCategory.setText(cardInfos.getProductCategory());
        Picasso.get().load(cardInfos.getProductImgUrl()).into(holder.productImage);

        // the onclick of an item of the recycler view

        holder.productCard.setOnClickListener(v -> {
            Intent intent = new Intent(context , ProductDetails.class);
            intent.putExtra("id" , ProductsList.get(holder.getAdapterPosition()).getProductId());
            intent.putExtra("position" , holder.getAdapterPosition());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ProductsList.size();
    }

    public static class rc_card_ViewHolder extends RecyclerView.ViewHolder {

        CardView productCard;
        ImageView productImage;
        TextView productName, productPrice , productCategory,productId;

        public rc_card_ViewHolder(@NonNull View itemView) {
            super(itemView);
            productId=itemView.findViewById(R.id.recyclerProId);
            productName = itemView.findViewById(R.id.product_txt);
            productPrice = itemView.findViewById(R.id.product_price);
            productCategory = itemView.findViewById(R.id.product_category);
            productImage = itemView.findViewById(R.id.product_img);
            productCard = itemView.findViewById(R.id.product_card);
        }
    }
}
