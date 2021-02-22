package com.example.monamourbakery;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fragment_AddSpecialProduct extends Fragment_Base {

    //type of
    private RadioGroup special_type_RDG;
    private RadioButton special_type_button;
    private AutoCompleteTextView special_type_DRP;
    private AutoCompleteTextView special_flavor_DRP;
    //size
    private AutoCompleteTextView special_size_DRP;
    //kosher
    private RadioGroup special_kosher_RDG;
    private RadioButton special_kosher_button;

    //price
    private MaterialTextView special_price_title_LBL;

    private MaterialButton special_continue_BTN;
    private FirebaseAuth mAuth =FirebaseAuth.getInstance();

    private CallBack_Special callBack_special;
    public void setCallBack_special(CallBack_Special _callBack_special){
        this.callBack_special=_callBack_special;
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment__add_special_product, container, false);
        findViews(view);
        initViews(view);

        return view;
    }

    private void initViews(View view) {

//        defineDefaultProduct(view);
        special_type_RDG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId){
                Log.d("DDM", "id" + checkedId);

                if (checkedId == R.id.special_type_cake_RDG) {
                    clearDRP();
                    //setSelected("");
                    Log.d("DDM", "Institute: cake");
                    setDRP(R.raw.cake_types,R.raw.cake_flavors,R.raw.cake_sizes);
                    product.setType(Type.CAKE);

                } else if (checkedId == R.id.special_type_pack_RDG) {
                    clearDRP();
                    //setSelected("");
                    Log.d("DDM", "Institute: pack");
                    setDRP(R.raw.pack_types,R.raw.pack_flavors,R.raw.pack_sizes);
                    product.setType(Type.PACK);

                }

            }
        });

        special_kosher_RDG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId){

                Log.d("DDM", "id" + checkedId);
                if (checkedId == R.id.special_kosher_milk_RD) {
                    Log.d("DDM", "Institute: milk");
                    product.setKosher(Kosher.MILK);
                } else if (checkedId == R.id.special_kosher_fur_RD) {
                    Log.d("DDM", "Institute: fur");
                    product.setKosher(Kosher.FUR);
                }
            }
        });


        special_continue_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack_special!=null){
                    if(checkProduct(product) == true){
                        Log.d("pttt",product.toString());
                        product.setPastry_img("");
                        callBack_special.addSpecial(product);
                    }
                    else{
                        Toast.makeText(v.getContext(), "Please Fill All Fields!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    private boolean checkProduct(Product product) {
        if (product.getPastry_name() == null)
            return false;
        if(product.getShort_description() == null)
            return false;
        if(product.getSize() == Size.NONE || product.getSize() == Size.NONE || product.getKosher() == Kosher.NONE)
            return false;
        return true;
    }


    public void defineDefaultProduct(View view){
      //cake
     special_type_button=(RadioButton) view.findViewById(R.id.special_type_cake_RDG);
     special_type_button.performClick();
     setDRP(R.raw.cake_types,R.raw.cake_flavors,R.raw.cake_sizes);
     //check kosher
     special_kosher_button= (RadioButton) view.findViewById(R.id.special_kosher_milk_RD);
     special_kosher_button.performClick();


 }
 public void setDRP(int typeResourceFile,int flavorResourceFile,int sizeResourceFile){

        setFitDropDown(special_type_DRP,typeResourceFile);
        setFitDropDown(special_flavor_DRP,flavorResourceFile);
        setFitDropDown(special_size_DRP,sizeResourceFile);

 }

 public void clearDRP(){
     special_type_DRP.clearListSelection();
     special_flavor_DRP.clearListSelection();
     special_size_DRP.clearListSelection();
     special_type_DRP.setText("");
     special_flavor_DRP.setText("");
     special_size_DRP.setText("");
 }

    private void findViews(View view) {
        //cake
        special_type_button=(RadioButton) view.findViewById(R.id.special_type_cake_RDG);
        special_type_RDG=(RadioGroup)view.findViewById(R.id.special_type_RDG);
        special_type_DRP=view.findViewById(R.id.special_type_DRP);
        special_flavor_DRP=view.findViewById(R.id.special_flavor_DRP);
        //size
        special_size_DRP=view.findViewById(R.id.special_size_DRP);
        //kosher
        special_kosher_RDG=view.findViewById(R.id.special_kosher_RDG);
        //check kosher
        special_kosher_button= (RadioButton) view.findViewById(R.id.special_kosher_milk_RD);
        //price
        special_price_title_LBL=view.findViewById(R.id.special_price_title_LBL);

        special_continue_BTN= view.findViewById(R.id.special_continue_BTN);

    }
}