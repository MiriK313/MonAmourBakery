package com.example.monamourbakery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Fragment_Cart extends Fragment_Base{
    public RecyclerView products_LST;
    private RadioGroup delivery_orders_GRP;
    private Button delivery_button_orders_RDB;
    private EditText delivery_address_orders_TXT;
    private DatePicker date_orders_calc;
    //price
    private MaterialTextView total_price_orders_LBL;
    private MaterialButton pay_orders_BTN;

    private Order currentOrder;

    public void setCurrentOrder(Order order){
        currentOrder=order;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_order, container, false);
        // special_product=new Product();

        findViews(view);
        initViews(view);

//        Log.d("pttt",  recipes.toArray().toString());

        //Adapter
        Product_Adapter product_adapter = new Product_Adapter(view.getContext(), currentOrder.getProducts());
        product_adapter.setClickListener(new Product_Adapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(view.getContext(), currentOrder.getProducts().get(position).getPastry_name(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onReadMoreClicked(View view, Product product) {
                openInfo(product,view);
            }
        });
        ;

        products_LST.setLayoutManager(new LinearLayoutManager(view.getContext()));
        products_LST.setAdapter(product_adapter);

        return view;
    }

    private void initViews(View view) {
        delivery_orders_GRP.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId){

                Log.d("DDM", "id" + checkedId);
                if (checkedId == R.id.delivery_yes_orders_RDB) {
                    delivery_address_orders_TXT.setVisibility(View.VISIBLE);
                    //TODO add the delivery order address
                    //TODO add delivery price to total price
                } else if (checkedId == R.id.delivery_no_orders_RDB) {
                    Log.d("DDM", "Institute: fur");
                }
            }
        });


        int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (date_orders_calc.getDayOfMonth()>currentDay &&  date_orders_calc.getMonth()>=currentMonth && date_orders_calc.getYear()>=currentYear)
        {
            Log.d("pttt","Selected Date: "+ date_orders_calc.getDayOfMonth()+"/"
                    + (date_orders_calc.getMonth() + 1)+"/"+date_orders_calc.getYear());
        }

        pay_orders_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DDM", "Institute: paid");
            }
        });

    }

    private void findViews(View view) {

        delivery_orders_GRP=view.findViewById(R.id.delivery_orders_GRP);
        delivery_address_orders_TXT=(EditText)view.findViewById(R.id.delivery_address_orders_TXT);
        products_LST=view.findViewById(R.id.products_LST);
        //price
        date_orders_calc=view.findViewById(R.id.date_orders_calc);
        total_price_orders_LBL=view.findViewById(R.id.total_price_orders_LBL);
        pay_orders_BTN= (MaterialButton) view.findViewById(R.id.pay_orders_BTN);

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