package com.example.monamourbakery;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class Fragment_Base_Manager extends Fragment_Base {

    protected Recipe recipe=new Recipe();

    protected void setFitDropDownRecipe(AutoCompleteTextView dropDown, int  resourceFile){
        dropDown.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.dropdown_menu_list_item, readTextFile(resourceFile)));
        dropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setSelected(parent.getItemAtPosition(position).toString().trim());
                Log.d("DDM", "Institute: " + selected);
                String ingredient="",size="", amount="";

                if( resourceFile == R.raw.ingredians){
                    ingredient=selected;
                }
                else if (resourceFile == R.raw.sizes ) {
                    size=selected;
                }
                else if(resourceFile == R.raw.amounts ){
                    amount=selected;
                }
                if(ingredient!=null && size!=null &&amount!=null){
                    recipe.addIngredient(amount+" "+size,ingredient);
                }
                else
                    Toast.makeText(view.getContext(), "Please Fill All Fields!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}