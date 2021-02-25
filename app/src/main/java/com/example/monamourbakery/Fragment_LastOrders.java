package com.example.monamourbakery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_LastOrders extends Fragment_Base{

    private ArrayList<Order> orders = new ArrayList<>();
    private Order_Adapter order_adapter;
    private User user;
    public RecyclerView fragment_LST_history;
    private MaterialButton history_BTN_current;
    private CallBack_History callBack_history;


    public void setUser(User _user){
        this.user = _user;
    }
    public void setCallBack_history(CallBack_History _callBack_history) {
        this.callBack_history = _callBack_history;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("DDM","Inside LastOrders\n");

        View view = inflater.inflate(R.layout.fragment_last_orders, container, false);
        findViews(view);
        generateOrders(view);
        initViews(view);
        updateAdapter(view);


        return view;
    }

    private void updateAdapter(View view) {
        //Adapter
        order_adapter = new Order_Adapter(view.getContext(), orders);
        order_adapter.setClickListener(new Order_Adapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(view.getContext(),
                        orders.get(position).getOrderID(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReadMoreClicked(View view, Order order) {
                openInfo(order,view);
            }

        });


        fragment_LST_history.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fragment_LST_history.setAdapter(order_adapter);
    }

    private void initViews(View view) {
        history_BTN_current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack_history!=null){
                    callBack_history.showCurrentOrder();
                }
            }
        });

    }

    private void findViews(View view) {
        fragment_LST_history = view.findViewById(R.id.fragment_LST_history);
        history_BTN_current = view.findViewById(R.id.history_BTN_current);


    }

    private void openInfo(Order order,View view) {
        // https://stackoverflow.com/a/50309163/7147289
        new AlertDialog.Builder(view.getContext())
                .setTitle(""+order.getOrderID())
                .setMessage(order.printProducts())
                .setPositiveButton("סגור", null)
                .show();
    }

    private  void generateOrders(View view) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("DDM1",""+snapshot.getChildren().getClass().getName());
                for( DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Order order = dataSnapshot.getValue(Order.class);
                    if(user.getEmail().equals(order.getUserEmail()))
                        orders.add(order);
                    updateAdapter(view);
                    Log.d("DDM1",order.toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
