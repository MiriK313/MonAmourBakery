package com.example.monamourbakery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;


import java.util.List;

public class Recipe_Adapter extends RecyclerView.Adapter<Recipe_Adapter.MyViewHolder> {

    private List<Recipe> recipes;
    private LayoutInflater mInflater;
    private MyItemClickListener mClickListener;

    // data is passed into the constructor
    Recipe_Adapter(Context context, List<Recipe> _recipes) {
        this.mInflater = LayoutInflater.from(context);
        this.recipes = _recipes;
    }

    // inflates the row layout from xml when needed
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_recipe, parent, false);
        return new MyViewHolder(view);
    }



    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("pttt", "Position = " + position);
        Recipe recipe = recipes.get(position);
        holder.recipe_LBL_name.setText(recipe.getPastry_name());
        holder.recipe_LBL_kosher.setText("" + recipe.getKosher());
        holder.recipe_LBL_short_description.setText(recipe.getShort_description());
        Glide.with(mInflater.getContext())
                .load(recipe.getPastry_img())
                .centerCrop()
                .into(holder.recipe_IMG);

        if(!Activity_Main.isManager){
            holder.recipe_BTN_delete.setVisibility(View.INVISIBLE);
        }

        holder.recipe_BTN_readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onReadMoreClicked(v, recipe);
                }
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return recipes.size();
    }

    // convenience method for getting data at click position
    Recipe getItem(int id) {
        return recipes.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(MyItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
        void onReadMoreClicked(View view, Recipe recipe);
    }



    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends ViewHolder {

        TextView recipe_LBL_name;
        TextView recipe_LBL_kosher;
        TextView recipe_LBL_short_description;
        ShapeableImageView recipe_IMG;
        MaterialButton recipe_BTN_readMore;
        MaterialButton recipe_BTN_delete;

        MyViewHolder(View itemView) {
            super(itemView);
            recipe_LBL_name = itemView.findViewById(R.id.recipe_LBL_name);
            recipe_LBL_kosher = itemView.findViewById(R.id.recipe_LBL_kosher);
            recipe_LBL_short_description = itemView.findViewById(R.id.recipe_LBL_short_discription);
            recipe_IMG = itemView.findViewById(R.id.recipe_IMG);
            recipe_BTN_readMore = itemView.findViewById(R.id.recipe_BTN_readMore);
            recipe_BTN_delete = itemView.findViewById(R.id.recipe_BTN_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) {
                        mClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
        }
    }

}