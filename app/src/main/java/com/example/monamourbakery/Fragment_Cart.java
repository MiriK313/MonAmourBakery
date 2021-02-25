package com.example.monamourbakery;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    private DatePickerDialog datePickerDialog;
    private EditText date_EDT;
    //price
    private TextView total_price_orders_LBL;
    private MaterialButton pay_orders_BTN;
    //Buttons
    private MaterialButton cart_BTN_history;

    private CallBack_Order callBack_order;
    private Order currentOrder;

    public void setCallBack_order(CallBack_Order _callBack_order){
        this.callBack_order = _callBack_order;
    }
    public void setCurrentOrder(Order order){
        currentOrder=order;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_order, container, false);
        // special_product=new Product();
        //date_EDT.setInputType(InputType.TYPE_NULL);
        findViews(view);
        initViews(view);

//        Log.d("pttt",  recipes.toArray().toString());

        //Adapter
        Product_Adapter product_adapter = new Product_Adapter(view.getContext(), currentOrder.getProducts());
        product_adapter.setClickListener(new Product_Adapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(view.getContext(),
                        currentOrder.getProducts().get(position).getPastry_name(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReadMoreClicked(View view, Product product) {
                openInfo(product,view);
            }

            @Override
            public void onDeleteItemClicked(View view,Product product){
                int position = currentOrder.getProducts().indexOf(product);
                currentOrder.removeProduct(position);
                product_adapter.notifyItemRemoved(position);
                updatePrice();
            }
        });
        ;

        products_LST.setLayoutManager(new LinearLayoutManager(view.getContext()));
        products_LST.setAdapter(product_adapter);

        return view;
    }

    private void initViews(View view) {
        updatePrice();
        date_EDT.setInputType(InputType.TYPE_NULL);
        date_EDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                if(dayOfMonth>day &&  (monthOfYear + 1)>=month && year>=year){
                                    date_EDT.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                    Log.d("DDM", "Date: "+date_EDT.getText());
                                    currentOrder.setDate(date_EDT.getText().toString().trim());
                                }else{
                                    Toast.makeText(v.getContext(), "Please Choose a FUTURE DATE", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        pay_orders_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOrder(currentOrder)== true && checkGui()==true){
                    Log.d("DDM", "Institute: paid");
                    currentOrder.seteStatus(Status.WAITING);
                    if(callBack_order!=null){
                        callBack_order.addOrder(currentOrder);
                    }
                }
                else{
                    Toast.makeText(v.getContext(), "Please Fill All Order Details!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        cart_BTN_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack_order != null){
                    callBack_order.showOrdersHistory();
                }
            }
        });

    }

    private void updatePrice() {
        if(currentOrder.getProducts().size()>=0)
            total_price_orders_LBL.setText("לתשלום: "+currentOrder.getTotalPrice());
    }

    private boolean checkGui() {
        if( date_EDT.getText().toString().isEmpty() )
            return false;
        return true;
    }

    private boolean checkOrder(Order currentOrder) {
        if(currentOrder.getProducts().size()==0)
            return false;
        return true;
    }

    private void findViews(View view) {

        products_LST=view.findViewById(R.id.products_LST);
        //price
        date_EDT=(EditText) view.findViewById(R.id.date_EDT);
        total_price_orders_LBL=view.findViewById(R.id.total_price_orders_LBL);
        pay_orders_BTN= (MaterialButton) view.findViewById(R.id.pay_orders_BTN);
        cart_BTN_history = view.findViewById(R.id.cart_BTN_history);

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
