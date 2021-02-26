package com.example.monamourbakery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_Product extends Fragment_Base {
    public RecyclerView fragment_products_LST;
    private MaterialButton product_BTN_add;
    private ArrayList<Product> products;
    private CallBack_Catalog callBack_catalog;
    private CallBack_Product callBack_product;

    public void setCallBack_catalog(CallBack_Catalog _callBack_catalog) {
        this.callBack_catalog = _callBack_catalog;
    }

    public void setCallBack_product(CallBack_Product _callBack_product){
        this.callBack_product = _callBack_product;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_product_list,container,false);
        findViews(view);
        initViews(view);
        products = ProductMockDB.generateProducts();
        findMoreFromFireBase(view);
        updateAdapter(view);


        return view;
    }

    private void initViews(View view) {
        if(Activity_Main_Manager.isManager){
            product_BTN_add.setVisibility(View.VISIBLE);
        }
        product_BTN_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack_product!=null)
                    callBack_product.addProductWindow();
            }
        });
    }

    private void findMoreFromFireBase(View view) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("DDM1",""+snapshot.getChildren().getClass().getName());
                for( DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    if(!checkIfExist(product))
                        products.add(product);
                    updateAdapter(view);
                    Log.d("DDM1",product.toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateAdapter(View view) {
        //Adapter
        Product_Adapter product_adapter = new Product_Adapter(view.getContext(), products);
        product_adapter.setClickListener(new Product_Adapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(view.getContext(), products.get(position).getPastry_name(), Toast.LENGTH_SHORT).show();
                if(callBack_catalog!=null){
                    product = products.get(position);
                    Log.d("pttt",product.toString());
                    callBack_catalog.addItem(product);
                }
            }
            @Override
            public void onReadMoreClicked(View view, Product product) {
                openInfo(product,view);
            }

            @Override
            public void onDeleteItemClicked(View view,Product product){
                int position = products.indexOf(product);
                products.remove(position);
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Products");
                db.child(""+product.getPastry_name()).removeValue();
                product_adapter.notifyItemRemoved(position);
            }
        });
        fragment_products_LST.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fragment_products_LST.setAdapter(product_adapter);
    }

    private boolean checkIfExist(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if(product.getPastry_name().equals(products.get(i).getPastry_name()) ){
                return true;
            }
        }
        return false;
    }

    public void findViews(View view) {
        fragment_products_LST=view.findViewById(R.id.fragment_products_LST);
        product_BTN_add = view.findViewById(R.id.product_BTN_add);

    }

    private void openInfo(Product product,View view) {
        // https://stackoverflow.com/a/50309163/7147289
        new AlertDialog.Builder(view.getContext())
                .setTitle(product.getPastry_name())
                .setMessage("עלות"+product.getPrice()+"\n\n "+ "גודל"+"\n"+ product.getSize()+" \n"+"סוג: "+ product.getType())
                .setPositiveButton("סגור", null)
                .show();
    }
}