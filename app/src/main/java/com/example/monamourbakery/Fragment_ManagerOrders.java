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

public class Fragment_ManagerOrders extends Fragment_Base{

    private ArrayList<Order> orders = new ArrayList<>();
    private Order_Adapter_Manager order_adapter_manager;
    public RecyclerView fragment_LST_orders;




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("DDM","Inside LastOrders\n");

        View view = inflater.inflate(R.layout.fragment_order_list_manager, container, false);
        findViews(view);
        generateOrders(view);
        initViews(view);
        updateAdapter(view);


        return view;
    }

    private void updateAdapter(View view) {
        //Adapter
        order_adapter_manager = new Order_Adapter_Manager(view.getContext(), orders);
        order_adapter_manager.setClickListener(new Order_Adapter_Manager.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                orders.get(position).changeStatus();
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Orders");
                db.child(""+orders.get(position).getOrderID()).setValue(orders.get(position));
                updateAdapter(view);
                Toast.makeText(view.getContext(),
                        "Status changed to :"+orders.get(position).geteStatus().name()
                        ,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onReadMoreClicked(View view, Order order) {
                openInfo(order,view);
            }

        });


        fragment_LST_orders.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fragment_LST_orders.setAdapter(order_adapter_manager);
    }

    private void initViews(View view) {

    }

    private void findViews(View view) {
        fragment_LST_orders = view.findViewById(R.id.fragment_LST_orders);


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
                    if(!checkIfExist(order))
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

    private boolean checkIfExist(Order order) {
        for (int i = 0; i < orders.size(); i++) {
            if(order.getOrderID() == orders.get(i).getOrderID() ){
                return true;
            }
        }
        return false;
    }
}
