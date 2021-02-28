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

    private EditText cake_name_EDT;
    private EditText short_description_EDT;
    private String cakeNameInput;
    private String descriptionInput;
    private String shortDescriptionInput;
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
        add_recipe_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack_recipe_add!=null){
                    if(checkEditText(v)){
                        if(checkRecipe(recipe)){
                            Log.d("pttt",recipe.toString());
                            recipe.setPastry_img("");
                            callBack_recipe_add.addRecipe(recipe);
                        }
                    }
                    else{
                        Toast.makeText(v.getContext(), "Please Fill All Fields!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean checkEditText(View view) {
        cakeNameInput = cake_name_EDT.getText().toString().trim();
        shortDescriptionInput = short_description_EDT.getText().toString().trim();
        descriptionInput = description_EDT.getText().toString().trim();
        if (cakeNameInput.length()==0 || shortDescriptionInput.length()==0 || descriptionInput.length()==0){
            Toast.makeText(view.getContext(), "Please Fill Name/Descriptions", Toast.LENGTH_SHORT).show();
            return false;
        }
        recipe.setPastry_name(cakeNameInput);
        recipe.setShort_description(shortDescriptionInput);
        recipe.setDescription(descriptionInput);
        return true;
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
