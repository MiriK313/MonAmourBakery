package com.example.monamourbakery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Fragment_AddNewRecipe extends Fragment_Base_Manager{

    public static final int MIN_STRING_LENGTH=5;
    private EditText cake_name_EDT;
    private EditText short_description_EDT;
    private AutoCompleteTextView ingredient_DRP;
    private AutoCompleteTextView sizes_DRP;
    private AutoCompleteTextView amounts_DRP;
    private MaterialButton add_new_ingredient_BTN;
    private RadioGroup kosher_RDG;
    private EditText description_EDT;
    private MaterialButton add_recipe_BTN;

    private CallBack_Recipe_Add callBack_recipe_add ;
    public void setCallBack_recipe_add(CallBack_Recipe_Add _callBack_recipe_add){
        this.callBack_recipe_add=_callBack_recipe_add;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        findViews(view);
        initViews(view);

        return view;
    }

    private void initViews(View view) {

        if(cake_name_EDT.getText().toString()!=null && cake_name_EDT.getText().toString().length()>MIN_STRING_LENGTH){
            recipe.setPastry_name(cake_name_EDT.getText().toString().trim());
        }
        if(short_description_EDT.getText().toString()!=null && short_description_EDT.getText().toString().length()>MIN_STRING_LENGTH){
            recipe.setShort_description(short_description_EDT.getText().toString().trim());
        }

        kosher_RDG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId){

                Log.d("DDM", "id" + checkedId);
                if (checkedId == R.id.kosher_milk_RD) {
                    Log.d("DDM", "Institute: milk");
                    recipe.setKosher(Kosher.MILK);
                } else if (checkedId == R.id.kosher_fur_RD) {
                    Log.d("DDM", "Institute: fur");
                    recipe.setKosher(Kosher.FUR);
                }
            }
        });

        setDRP(R.raw.ingredians, R.raw.sizes, R.raw.amounts);
        add_new_ingredient_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDRP();
                Toast.makeText(v.getContext(), "Please Fill All Fields!!", Toast.LENGTH_SHORT).show();

            }
        });

        if(description_EDT.getText().toString()!=null && description_EDT.getText().toString().length()>MIN_STRING_LENGTH){
            recipe.setDescription(description_EDT.getText().toString().trim());
        }

        add_recipe_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack_recipe_add!=null){
                    if(checkRecipe(recipe) == true){
                        Log.d("pttt",recipe.toString());
                        recipe.setPastry_img("");
                        callBack_recipe_add.addRecipe(recipe);
                    }
                    else{
                        Toast.makeText(v.getContext(), "Please Fill All Fields!!", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

    }

    private boolean checkRecipe(Recipe recipe) {
        if (recipe.getPastry_name() == null)
            return false;
        if(recipe.getShort_description() == null)
            return false;
        if(recipe.getDescription() == null)
            return false;
        if (recipe.getIngredients().size()==0)
            return false;
        if(recipe.getKosher() == Kosher.NONE)
            return false;
        return true;
    }

    public void setDRP(int typeResourceFile,int flavorResourceFile,int sizeResourceFile){

        setFitDropDownRecipe(ingredient_DRP,typeResourceFile);
        setFitDropDownRecipe(sizes_DRP,flavorResourceFile);
        setFitDropDownRecipe(amounts_DRP,sizeResourceFile);

    }

    public void clearDRP(){
        ingredient_DRP.setText("");
        sizes_DRP.setText("");
        amounts_DRP.setText("");
        ingredient_DRP.clearListSelection();
        sizes_DRP.clearListSelection();
        amounts_DRP.clearListSelection();
    }

    private void findViews(View view) {
        cake_name_EDT=view.findViewById(R.id.cake_name_EDT);
        short_description_EDT=view.findViewById(R.id.short_description_EDT);
        ingredient_DRP=view.findViewById(R.id.ingredient_DRP);
        sizes_DRP=view.findViewById(R.id.sizes_DRP);
        amounts_DRP=view.findViewById(R.id.amounts_DRP);
        add_new_ingredient_BTN=(MaterialButton)view.findViewById(R.id.add_new_ingredient_BTN);
        kosher_RDG=(RadioGroup)view.findViewById(R.id.kosher_RDG);
        description_EDT=view.findViewById(R.id.description_EDT);
        add_recipe_BTN=(MaterialButton)view.findViewById(R.id.add_recipe_BTN);
    }
}
