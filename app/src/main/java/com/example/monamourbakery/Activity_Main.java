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

    private FragmentManager fragment_manager;
    private Fragment_Base fragment;
    private Fragment_AddNewProduct fragment_addNewProduct;

    private BottomNavigationView bottomNav;

    private CallBack_Add callBack_add = new CallBack_Add() {
        @Override
        public void addCustomize() {
            Log.d("pttt","In Customize");
            fragment = new Fragment_AddSpecialProduct();
            loadFragment(fragment);
        }

        @Override
        public void addFromCatalog() {
            Log.d("pttt","In Catalog");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("pttt","isManager = "+getIntent().getBooleanExtra("isManager",false));
        fragment_manager = getFragmentManager();

        fragment_addNewProduct = new Fragment_AddNewProduct();
        fragment_addNewProduct.setCallBack_add(callBack_add);

        fragment = new Fragment_Recipe();
        loadFragment(fragment);

        //navigation Listener
        bottomNav = (BottomNavigationView) findViewById(R.id.main_BAR_bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                case R.id.messages:
                    Log.d("pttt","mess");
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