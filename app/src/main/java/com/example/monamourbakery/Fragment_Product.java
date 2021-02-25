package com.example.monamourbakery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment_Product extends Fragment_Base {
    public RecyclerView fragment_products_LST;
    private CallBack_Catalog callBack_catalog;

    public void setCallBack_catalog(CallBack_Catalog _callBack_catalog) {
        this.callBack_catalog = _callBack_catalog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_product_list,container,false);
        findviews(view);
        ArrayList<Product> products = ProductMockDB.generateProducts();
//        Log.d("pttt",  recipes.toArray().toString());

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
                product_adapter.notifyItemRemoved(position);
            }
        });


        fragment_products_LST.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fragment_products_LST.setAdapter(product_adapter);

        return view;
    }

    public void findviews(View view) {
        this.fragment_products_LST=view.findViewById(R.id.fragment_products_LST);

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