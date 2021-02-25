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

public class Order_Adapter extends RecyclerView.Adapter<com.example.monamourbakery.Order_Adapter.MyViewHolder> {

    private List<Order> orders;
    private LayoutInflater mInflater;
    private com.example.monamourbakery.Order_Adapter.MyItemClickListener mClickListener;

    // data is passed into the constructor
    Order_Adapter(Context context, List<Order> _orders) {
        this.mInflater = LayoutInflater.from(context);
        this.orders = _orders;
    }

    // inflates the row layout from xml when needed
    @Override
    public com.example.monamourbakery.Order_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_order_user, parent, false);
        return new com.example.monamourbakery.Order_Adapter.MyViewHolder(view);
    }



    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(com.example.monamourbakery.Order_Adapter.MyViewHolder holder, int position) {
        Log.d("pttt", "Position = " + position);
        Order order = orders.get(position);
        holder.history_TXT_order_number.setText(""+order.getOrderID());
        holder.history_TXT_order_price.setText("" + order.getTotalPrice());
        holder.history_TXT_order_status.setText(order.geteStatus().name());


        holder.history_TXT_readMore.setOnClickListener(new View.OnClickListener() {
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
    public void setClickListener(com.example.monamourbakery.Order_Adapter.MyItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
        void onReadMoreClicked(View view, Order order);
    }



    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView history_TXT_order_number;
        TextView history_TXT_order_price;
        TextView history_TXT_order_status;
        TextView history_TXT_order_date;
        MaterialButton history_TXT_readMore;


        MyViewHolder(View itemView) {
            super(itemView);
            history_TXT_order_number = itemView.findViewById(R.id.history_TXT_order_number);
            history_TXT_order_price = itemView.findViewById(R.id.history_TXT_order_price);
            history_TXT_order_status = itemView.findViewById(R.id.history_TXT_order_status);
            history_TXT_order_date = itemView.findViewById(R.id.history_TXT_order_date);
            history_TXT_readMore = itemView.findViewById(R.id.history_TXT_readMore);

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