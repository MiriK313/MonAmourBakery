package com.example.monamourbakery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment_Recipe extends Fragment_Base {

    public RecyclerView fragment_recipies_LST;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_recipe_list,container,false);
        findviews(view);
        ArrayList<Recipe> recipes = RecipeMockDB.generateRecipes();
//        Log.d("pttt",  recipes.toArray().toString());

        //Adapter
        Recipe_Adapter recipe_adapter = new Recipe_Adapter(view.getContext(), recipes);
        recipe_adapter.setClickListener(new Recipe_Adapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(view.getContext(), recipes.get(position).getPastry_name(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReadMoreClicked(View view, Recipe recipe) {
                openInfo(recipe,view);
            }
        });
        ;

        fragment_recipies_LST.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fragment_recipies_LST.setAdapter(recipe_adapter);

        return view;
    }

    public void findviews(View view) {
        this.fragment_recipies_LST=view.findViewById(R.id.fragment_recipies_LST);

    }

    private void openInfo(Recipe recipe,View view) {
        // https://stackoverflow.com/a/50309163/7147289
        new AlertDialog.Builder(view.getContext())
                .setTitle(recipe.getPastry_name())
                 .setMessage(recipe.getKosher().toString()+"\n\n "+ "מצרכים: "+"\n"+ recipe.toString_Ingredients()+" \n"+"אופן הכנה:"+ recipe.getDescription())
                .setPositiveButton("סגור", null)
                .show();
    }

}
