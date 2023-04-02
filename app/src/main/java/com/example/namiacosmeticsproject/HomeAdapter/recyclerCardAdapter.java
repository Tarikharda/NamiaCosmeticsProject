package com.example.namiacosmeticsproject.HomeAdapter;

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

import java.util.ArrayList;

public class recyclerCardAdapter extends RecyclerView.Adapter<recyclerCardAdapter.rc_card_ViewHolder> {

    Context context;
    ArrayList<recyclerCardModel> topSellsProductsList;

    public recyclerCardAdapter(Context context , ArrayList<recyclerCardModel> topSellsProductsList) {
        this.context = context;
        this.topSellsProductsList = topSellsProductsList;
    }

    @NonNull
    @Override
    public recyclerCardAdapter.rc_card_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent , false);
        rc_card_ViewHolder rc_card = new rc_card_ViewHolder(view);
        return rc_card;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerCardAdapter.rc_card_ViewHolder holder, int position) {
        recyclerCardModel cardInfos = topSellsProductsList.get(position);

        holder.productImage.setImageResource(cardInfos.getProductImg());
        holder.productTxt.setText(cardInfos.getProductTitle());
        holder.productPrice.setText(cardInfos.getProductPrice());
        holder.productCategory.setText(cardInfos.getProductCategory());

        // the onclick of an item of the recycler view

        holder.productCard.setOnClickListener(v -> {
            Intent intent = new Intent(context , ProductDetails.class);
            intent.putExtra("price" , topSellsProductsList.get(holder.getAdapterPosition()).getProductPrice());
            intent.putExtra("position" , holder.getAdapterPosition());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return topSellsProductsList.size();
    }

    public static class rc_card_ViewHolder extends RecyclerView.ViewHolder {

        CardView productCard;
        ImageView productImage;
        TextView productTxt , productPrice , productCategory;

        public rc_card_ViewHolder(@NonNull View itemView) {
            super(itemView);

            productCard = itemView.findViewById(R.id.product_card);
            productImage = itemView.findViewById(R.id.product_img);
            productTxt = itemView.findViewById(R.id.product_txt);
            productPrice = itemView.findViewById(R.id.product_price);
            productCategory = itemView.findViewById(R.id.product_category);
        }
    }
}
