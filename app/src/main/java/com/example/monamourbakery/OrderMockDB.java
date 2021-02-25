package com.example.monamourbakery;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderMockDB {

    public static ArrayList<Order> generateOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("Orders").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for( DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Order order = dataSnapshot.getValue(Order.class);
                    orders.add(order);
                    Log.d("DDM1",order.toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return orders;
    }

}
