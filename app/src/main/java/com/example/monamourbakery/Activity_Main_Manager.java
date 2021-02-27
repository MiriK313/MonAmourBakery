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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class Activity_Main_Manager extends AppCompatActivity {

    private Gson gson = new Gson();
    private FirebaseAuth mAuth;
    private DatabaseReference db;
    private User user;
    public static boolean isManager = false;
    private FragmentManager fragment_manager;
    private Fragment_Base fragment;
    private Fragment_Product fragment_product;
    private Fragment_AddSpecialProduct fragment_addSpecialProduct;
    private Fragment_ManagerOrders fragment_managerOrders;
    private Fragment_Recipe fragment_recipe;
    private Fragment_AddNewRecipe fragment_addNewRecipe;
    private BottomNavigationView bottomNav;

    private CallBack_Recipe_Add callBack_recipe_add = new CallBack_Recipe_Add() {
        @Override
        public void addRecipe(Recipe recipe) {
            Toast.makeText(Activity_Main_Manager.this, "Recipe has been Added to DB!", Toast.LENGTH_SHORT).show();
            db = FirebaseDatabase.getInstance().getReference("Recipes");
            db.child(""+recipe.getPastry_name()).setValue(recipe);
            fragment_recipe = new Fragment_Recipe();
            fragment_recipe.setCallBack_recipe(callBack_recipe);
            fragment = fragment_recipe;
            loadFragment(fragment_recipe);

        }
    };
    private CallBack_Recipe callBack_recipe = new CallBack_Recipe() {
        @Override
        public void addRecipeWindow() {
            fragment_addNewRecipe = new Fragment_AddNewRecipe();
            fragment_addNewRecipe.setCallBack_recipe_add(callBack_recipe_add);
            loadFragment(fragment_addNewRecipe);
        }
    };

    private CallBack_Product callBack_product = new CallBack_Product() {
        @Override
        public void addProductWindow() {
            fragment_addSpecialProduct = new Fragment_AddSpecialProduct();
            fragment_addSpecialProduct.setCallBack_special(callBack_special);
            loadFragment(fragment_addSpecialProduct);
        }
    };

    private CallBack_Special callBack_special = new CallBack_Special() {
        @Override
        public void addSpecial(Product product) {
            Toast.makeText(Activity_Main_Manager.this, "Product has been Added to DB!", Toast.LENGTH_SHORT).show();
            db = FirebaseDatabase.getInstance().getReference("Products");
            db.child(""+product.getPastry_name()).setValue(product);
//            fragment_product = new Fragment_Product();
//            fragment_product.setCallBack_product(callBack_product);
//            fragment = fragment_product;
//            loadFragment(fragment_product);
            Intent intent = new Intent(Activity_Main_Manager.this, Activity_UploadPhoto.class);
            intent.putExtra("currentProduct",product.getPastry_name());
            startActivity(intent);
            finish();
        }
    };






        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity__main__manager);
            isManager = true;
            user = gson.fromJson(getIntent().getStringExtra("CurrentUser"), User.class);
            findViews();
            initViews();
        }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.products:
                        fragment_product = new Fragment_Product();
                        fragment_product.setCallBack_product(callBack_product);
                        loadFragment(fragment_product);
                        return true;
                    case R.id.recipes:
                        Log.d("pttt", "mess");
                        fragment = new Fragment_Recipe();
                        loadFragment(fragment);
                        return true;
                    case R.id.cart:
                        Log.d("pttt", "cart");
                        fragment_managerOrders = new Fragment_ManagerOrders();
                        loadFragment(fragment_managerOrders);
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

        private void initViews() {
            loadFragment(fragment);
            bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        }

        private void findViews() {
            fragment_manager = getFragmentManager();
            fragment_recipe = new Fragment_Recipe();
            fragment_recipe.setCallBack_recipe(callBack_recipe);
            fragment = fragment_recipe;
            bottomNav = (BottomNavigationView) findViewById(R.id.main_BAR_bottomNav);
        }


}