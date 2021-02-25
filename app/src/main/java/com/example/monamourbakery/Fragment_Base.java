package com.example.monamourbakery;

import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Base extends Fragment {
    private String selected;

    protected Product product = new Product();

    protected List<String> readTextFile(int resourceFile) {
        List<String> data = new ArrayList<>();
        String string = "";
        InputStream is = this.getResources().openRawResource(resourceFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            while (true) {
                try {
                    if ((string = reader.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!string.isEmpty()) {
                    data.add(string.replace('\n', '\0')); }
            }
            is.close();
        } finally {
            return data;
        }
    }

    protected void setFitDropDown( AutoCompleteTextView dropDown,int  resourceFile){
        dropDown.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.dropdown_menu_list_item, readTextFile(resourceFile)));
        dropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setSelected(parent.getItemAtPosition(position).toString().trim());
                Log.d("DDM", "Institute: " + selected);
               if( resourceFile == R.raw.cake_sizes){
                    if( selected.equals("קוטר 16")){
                        product.setSize(Size.S);
                        Log.d("DDM", "Institute: S, "+product.getSize());
                        product.setPrice(260);
                    }
                    else if( selected.equals("קוטר 18")  ){
                        product.setSize(Size.M);
                        Log.d("DDM", "Institute: M, "+product.getSize());
                        product.setPrice(320);
                    }
                    else if( selected.equals("קוטר 20")  ){
                        product.setSize(Size.L);
                        Log.d("DDM", "Institute: L, "+product.getSize());
                        product.setPrice(380);
                    }
                }
                else if (resourceFile == R.raw.pack_sizes ) {
                    if( selected.equals("6 יחידות")){
                        product.setSize(Size.S);
                        Log.d("DDM", "Institute: S, "+product.getSize());
                        product.setPrice(160);
                    }
                    else if( selected.equals("8 יחידות") ){
                        product.setSize(Size.M);
                        Log.d("DDM", "Institute: M, "+product.getSize());
                        product.setPrice(220);
                    }
                    else if( selected.equals("12 יחידות") ){
                        product.setSize(Size.L);
                        Log.d("DDM", "Institute: L, "+product.getSize());
                        product.setPrice(280);
                    }
                }
                else if( resourceFile==R.raw.cake_types ||resourceFile==R.raw.pack_types ){
                    product.setPastry_name(selected);
                    Log.d("DDM", "Institute: Name "+product.getPastry_name());
                }
                else if(resourceFile==R.raw.cake_flavors ||resourceFile==R.raw.pack_flavors){
                    product.setShort_description(product.getShort_description()+", "+selected);
                    Log.d("DDM", "Institute: flavors "+product.getShort_description());
                }
            }
        });
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
