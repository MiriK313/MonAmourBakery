package com.example.monamourbakery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_Main extends AppCompatActivity {

    private Order order;
    private FragmentManager fragment_manager;
    private Fragment_Base fragment;
    private Fragment_AddNewProduct fragment_addNewProduct;
    private Fragment_AddSpecialProduct fragment_addSpecialProduct;
    private Fragment_Product fragment_product;

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
        public void addItem() {
            Log.d("pttt","Catalog --- added an item to order");
        }
    };

    private CallBack_Special callBack_special = new CallBack_Special() {
        @Override
        public void addSpecial() {
            Log.d("pttt","Special --- added a special item to order");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("pttt","isManager = "+getIntent().getBooleanExtra("isManager",false));
        findViews();
        initViews();
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