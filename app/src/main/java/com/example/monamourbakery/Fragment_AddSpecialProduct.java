package com.example.monamourbakery;
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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

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
    //delivery
    private RadioGroup special_delivery_RDG;
    private Button special_delivery_button;
    private EditText special_delivery_address_TXT;
    //price
    private MaterialTextView special_price_title_LBL;
    //more requests
    private EditText special_request_TXT;
    private Product special_product;

    private MaterialButton special_continue_BTN;


    private CallBack_Special callBack_special;
    public void setCallBack_special(CallBack_Special _callBack_special){
        this.callBack_special=_callBack_special;
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment__add_special_product, container, false);
        special_product=new Product();
        findviews(view);
        initViews(view);

        return view;
    }

    private void initViews(View view) {

        defineDefaultProduct(view);
        special_type_RDG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId){
                Log.d("chk", "id" + checkedId);

                if (checkedId == R.id.special_type_cake_RDG) {
                    //clearDRP();
                    //setSelected("");
                    Log.d("DDM", "Institute: cake");
                    setDRP(R.raw.cake_types,R.raw.cake_flavors,R.raw.cake_sizes);

                } else if (checkedId == R.id.special_type_pack_RDG) {
                    //clearDRP();
                    //setSelected("");
                    Log.d("DDM", "Institute: pack");
                    setDRP(R.raw.pack_types,R.raw.pack_flavors,R.raw.pack_sizes);

                }
            }
        });

        special_kosher_RDG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId){

                Log.d("chk", "id" + checkedId);
                if (checkedId == R.id.special_kosher_milk_RD) {
                    Log.d("DDM", "Institute: milk");
                } else if (checkedId == R.id.special_kosher_fur_RD) {
                    Log.d("DDM", "Institute: fur");
                }
            }
        });

        if (special_request_TXT.getText()!=null) {
            Log.d("chk", "בקשות מיוחדות " + special_request_TXT.getText());
        } else return;

        special_delivery_RDG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId){

                Log.d("chk", "id" + checkedId);

                if (checkedId == R.id.special_delivery_no_RDG) {
                    Log.d("DDM", "Institute: no delivery");
                    special_price_title_LBL.setText(special_price_title_LBL.getText());
                } else if (checkedId == R.id.special_delivery_yes_RDG) {
                    Log.d("DDM", "Institute: yes delivery");
                    special_price_title_LBL.setText(special_price_title_LBL.getText()+"300");
                    special_delivery_address_TXT.setVisibility(View.VISIBLE);
                }
            }
        });

        special_continue_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack_special!=null){
                    callBack_special.addSpecial();
                }
            }
        });

    }


 public void defineDefaultProduct(View view){
      //cake
     special_type_button=(RadioButton) view.findViewById(R.id.special_type_cake_RDG);
     special_type_button.performClick();
     setDRP(R.raw.cake_types,R.raw.cake_flavors,R.raw.cake_sizes);
     //check kosher
     special_kosher_button= (RadioButton) view.findViewById(R.id.special_kosher_milk_RD);
     special_kosher_button.performClick();
     //delivery
     special_delivery_button= (RadioButton) view.findViewById(R.id.special_delivery_no_RDG);
     special_delivery_button.performClick();

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
 }

    private void findviews(View view) {
        special_type_RDG=(RadioGroup)view.findViewById(R.id.special_type_RDG);
        special_type_DRP=view.findViewById(R.id.special_type_DRP);
        special_flavor_DRP=view.findViewById(R.id.special_flavor_DRP);
        //size
        special_size_DRP=view.findViewById(R.id.special_size_DRP);
        //kosher
        special_kosher_RDG=view.findViewById(R.id.special_kosher_RDG);
        //delivery
        special_delivery_RDG=view.findViewById(R.id.special_delivery_RDG);
        special_delivery_address_TXT=(EditText)view.findViewById(R.id.special_delivery_address_TXT);
        //price
        special_price_title_LBL=view.findViewById(R.id.special_price_title_LBL);
        //more requests
        special_request_TXT=view.findViewById(R.id.special_request_TXT);

        special_continue_BTN= view.findViewById(R.id.special_continue_BTN);

    }
}