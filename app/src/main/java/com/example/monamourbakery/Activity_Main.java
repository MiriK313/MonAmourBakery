package com.example.monamourbakery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

public class Activity_Main extends AppCompatActivity {

    private Gson gson = new Gson();
    private FirebaseAuth mAuth;
    private DatabaseReference db;
    private User user;
    private Order order;
    private FragmentManager fragment_manager;
    private Fragment_Base fragment;
    private Fragment_AddNewProduct fragment_addNewProduct;
    private Fragment_AddSpecialProduct fragment_addSpecialProduct;
    private Fragment_Product fragment_product;
    private Fragment_Cart fragment_cart;

    private BottomNavigationView bottomNav;



    private CallBack_Add callBack_add = new CallBack_Add() {
        @Override
        public void addCustomize() {
            Log.d("pttt","In Customize");
            fragment_addSpecialProduct = new Fragment_AddSpecialProduct();
            fragment_addSpecialProduct.setCallBack_special(callBack_special);
            fragment = fragment_addSpecialProduct;
            loadFragment(fragment);
        }

        @Override
        public void addFromCatalog() {
            Log.d("pttt","In Catalog");
            fragment_product = new Fragment_Product();
            fragment_product.setCallBack_catalog(callBack_catalog);
            fragment = fragment_product;
            loadFragment(fragment);
        }
    };

    private CallBack_Catalog callBack_catalog = new CallBack_Catalog() {
        @Override
        public void addItem(Product product) {
            Log.d("pttt","Catalog --- added an item to order");
            order.addProduct(product);
            Log.d("pttt","Order - "+order.toString());
        }
    };

    private CallBack_Special callBack_special = new CallBack_Special() {
        @Override
        public void addSpecial(Product product) {
            Log.d("pttt","Special --- added a special item to order");
            order.addProduct(product);
            Log.d("pttt","Order - "+order.toString());

        }
    };

    private CallBack_Order callBack_order = new CallBack_Order() {
        @Override
        public void addOrder(Order currentOrder) {
            Toast.makeText(Activity_Main.this, "Order is Waiting to be Accepted", Toast.LENGTH_SHORT).show();
            user.addOrder(currentOrder.getOrderID());
            db = FirebaseDatabase.getInstance().getReference("myUsers");
            db.child(user.getUserId()).setValue(user);
            db = FirebaseDatabase.getInstance().getReference("Orders");
            db.child(user.getUserId()).child(""+currentOrder.getOrderID()).setValue(currentOrder);
            createOrder();
            fragment_addNewProduct = new Fragment_AddNewProduct();
            fragment_addNewProduct.setCallBack_add(callBack_add);
            fragment = fragment_addNewProduct;
            loadFragment(fragment);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("pttt","isManager = "+getIntent().getBooleanExtra("isManager",false));
        user = gson.fromJson(getIntent().getStringExtra("CurrentUser"),User.class);
        findViews();
        initViews();
        createOrder();
    }

    private void createOrder() {
        this.order = new Order();
        this.order.setOrderID(createID());
        this.order.setUserEmail(user.getEmail());
    }

    private int createID() {
       int id = (int)((Math.random()*1000)+1);;
        ArrayList<Order> allOrders = new ArrayList<>();
        db = FirebaseDatabase.getInstance().getReference();
        db.child("Orders").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for( DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Order order = dataSnapshot.getValue(Order.class);
                    allOrders.add(order);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if(allOrders.size()==0)
            return id;
        for (int i = 0; i < allOrders.size(); i++) {
            if(id == allOrders.get(i).getOrderID())
                id = (int)((Math.random()*1000)+1);
        }
        return id;
    }

    private void initViews() {
        loadFragment(fragment);
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void findViews() {
        fragment_manager = getFragmentManager();

        fragment_addNewProduct = new Fragment_AddNewProduct();
        fragment_addNewProduct.setCallBack_add(callBack_add);

        fragment = new Fragment_Recipe();
        bottomNav = (BottomNavigationView) findViewById(R.id.main_BAR_bottomNav);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
        Log.d("pttt","signed out");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.add:
                    fragment = fragment_addNewProduct;
                    loadFragment(fragment);
                    return true;
                case R.id.recipes:
                    Log.d("pttt","mess");
                    fragment = new Fragment_Recipe();
                    loadFragment(fragment);
                    return true;
                case R.id.cart:
                    Log.d("pttt","cart");
                    fragment_cart = new Fragment_Cart();
                    fragment_cart.setCurrentOrder(order);
                    fragment_cart.setCallBack_order(callBack_order);
                    fragment = fragment_cart;
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment_Base fragment) {
        // load fragment
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fragment_manager.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.main_LAY_frame, fragment);
        fragmentTransaction.commit(); // save the changes

    }



}