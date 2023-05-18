package com.example.namiacosmeticsproject.HomeAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namiacosmeticsproject.Classes.Categories;
import com.example.namiacosmeticsproject.R;

import java.util.ArrayList;

public class recyclerCategoriesAdapter extends RecyclerView.Adapter<recyclerCategoriesAdapter.rc_category_ViewHolder> {

    Context context;
    ArrayList<Categories> categoryList;

    public recyclerCategoriesAdapter(Context context, ArrayList<Categories> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public rc_category_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item , parent , false);
        return new rc_category_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rc_category_ViewHolder holder, int position) {
        Categories category = categoryList.get(position);

        holder.categoryTitle.setText(category.getCategoryTitle());

//        Picasso.get().load(category.getCategoryImgUrl()).into(holder.categoryImg);
        holder.categoryImg.setImageResource(Integer.parseInt(category.getCategoryImgUrl()));

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class rc_category_ViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImg;
        TextView categoryTitle;

        public rc_category_ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImg = itemView.findViewById(R.id.category_img);
            categoryTitle = itemView.findViewById(R.id.category_title);
        }
    }
}
