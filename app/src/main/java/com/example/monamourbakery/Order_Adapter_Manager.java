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

public class Order_Adapter_Manager extends RecyclerView.Adapter<com.example.monamourbakery.Order_Adapter_Manager.MyViewHolder> {

    private List<Order> orders;
    private LayoutInflater mInflater;
    private com.example.monamourbakery.Order_Adapter_Manager.MyItemClickListener mClickListener;

    // data is passed into the constructor
    Order_Adapter_Manager(Context context, List<Order> _orders) {
        this.mInflater = LayoutInflater.from(context);
        this.orders = _orders;
    }

    // inflates the row layout from xml when needed
    @Override
    public com.example.monamourbakery.Order_Adapter_Manager.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_order_manager, parent, false);
        return new com.example.monamourbakery.Order_Adapter_Manager.MyViewHolder(view);
    }



    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(com.example.monamourbakery.Order_Adapter_Manager.MyViewHolder holder, int position) {
        Log.d("pttt", "Position = " + position);
        Order order = orders.get(position);
        holder.manager_TXT_order_number.setText(""+order.getOrderID());
        holder.manager_TXT_order_price.setText("" + order.getTotalPrice());
        holder.manager_TXT_order_userMail.setText(order.getUserEmail());
        holder.manager_TXT_order_status.setText(order.geteStatus().name());
        holder.manager_TXT_order_date.setText(order.getDate());


        holder.manager_TXT_readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onReadMoreClicked(v,orders.get(position));
                }
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return orders.size();
    }

    // convenience method for getting data at click position
    Order getItem(int id) {
        return orders.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(com.example.monamourbakery.Order_Adapter_Manager.MyItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
        void onReadMoreClicked(View view, Order order);
    }



    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView manager_TXT_order_number;
        TextView manager_TXT_order_price;
        TextView manager_TXT_order_userMail;
        TextView manager_TXT_order_status;
        TextView manager_TXT_order_date;
        MaterialButton manager_TXT_readMore;


        MyViewHolder(View itemView) {
            super(itemView);
            manager_TXT_order_number = itemView.findViewById(R.id.manager_TXT_order_number);
            manager_TXT_order_price = itemView.findViewById(R.id.manager_TXT_order_price);
            manager_TXT_order_userMail = itemView.findViewById(R.id.manager_TXT_order_userMail);
            manager_TXT_order_status = itemView.findViewById(R.id.manager_TXT_order_status);
            manager_TXT_order_date = itemView.findViewById(R.id.manager_TXT_order_date);
            manager_TXT_readMore = itemView.findViewById(R.id.manager_TXT_readMore);

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