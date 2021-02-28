package com.example.monamourbakery;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.button.MaterialButton;

public class Fragment_AddNewProduct extends Fragment_Base {

    private MaterialButton new_product_catalog_BTN;
    private MaterialButton new_product_creation_BTN;
    private CallBack_Add callBack_add;

    public void setCallBack_add(CallBack_Add _callBack_add){this.callBack_add = _callBack_add;}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment__add_new_product,container,false);
        findViews(view);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        new_product_catalog_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack_add!=null){
                    callBack_add.addFromCatalog();
                }
            }
        });
        new_product_creation_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack_add!=null){
                    callBack_add.addCustomize();
                }
            }
        });
    }

    private void findViews(View view) {
        new_product_catalog_BTN=(MaterialButton)view.findViewById(R.id.new_product_catalog_BTN);
        new_product_creation_BTN=(MaterialButton)view.findViewById(R.id.new_product_creation_BTN);
    }
}