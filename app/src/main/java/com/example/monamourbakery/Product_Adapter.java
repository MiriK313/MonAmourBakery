package com.example.monamourbakery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class Product_Adapter extends RecyclerView.Adapter<com.example.monamourbakery.Product_Adapter.MyViewHolder> {

    private List<Product> products;
    private LayoutInflater mInflater;
    private com.example.monamourbakery.Product_Adapter.MyItemClickListener mClickListener;

    // data is passed into the constructor
    Product_Adapter(Context context, List<Product> _products) {
        this.mInflater = LayoutInflater.from(context);
        this.products = _products;
    }

    // inflates the row layout from xml when needed
    @Override
    public com.example.monamourbakery.Product_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_product, parent, false);
        return new com.example.monamourbakery.Product_Adapter.MyViewHolder(view);
    }



    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(com.example.monamourbakery.Product_Adapter.MyViewHolder holder, int position) {
        Log.d("pttt", "Position = " + position);
        Product product = products.get(position);
        holder.product_LBL_name.setText(product.getPastry_name());
        holder.product_LBL_price.setText("" + product.getKosher());
        holder.product_LBL_short_description.setText(product.getShort_description());
        Glide.with(mInflater.getContext())
                .load(product.getPastry_img())
                .centerCrop()
                .into(holder.product_IMG);

        if(!Activity_Main.isManager){
            holder.product_BTN_delete.setVisibility(View.INVISIBLE);
            if(Activity_Main.isCart)
                holder.product_BTN_delete.setVisibility(View.VISIBLE);
        }
        holder.product_BTN_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mClickListener!=null){
                    mClickListener.onDeleteItemClicked(v,product);
                }
            }
        });
        holder.product_BTN_readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onReadMoreClicked(v, product);
                }
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return products.size();
    }

    // convenience method for getting data at click position
    Product getItem(int id) {
        return products.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(com.example.monamourbakery.Product_Adapter.MyItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
        void onReadMoreClicked(View view, Product product);
        void onDeleteItemClicked(View view,Product product);
    }



    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView product_LBL_name;
        TextView product_LBL_price;
        TextView product_LBL_short_description;
        ShapeableImageView product_IMG;
        MaterialButton product_BTN_readMore;
        MaterialButton product_BTN_delete;

        MyViewHolder(View itemView) {
            super(itemView);
            product_LBL_name = itemView.findViewById(R.id.product_LBL_name);
            product_LBL_price = itemView.findViewById(R.id.product_LBL_price);
            product_LBL_short_description = itemView.findViewById(R.id.product_LBL_short_description);
            product_IMG = itemView.findViewById(R.id.product_IMG);
            product_BTN_readMore = itemView.findViewById(R.id.product_BTN_readMore);
            product_BTN_delete = itemView.findViewById(R.id.product_BTN_delete);

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