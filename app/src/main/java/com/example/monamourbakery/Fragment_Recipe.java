package com.example.monamourbakery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_Recipe extends Fragment_Base {

    public RecyclerView fragment_recipes_LST;
    private MaterialButton recipe_BTN_add;
    private CallBack_Recipe callBack_recipe;
    ArrayList<Recipe> recipes;

    public void setCallBack_recipe(CallBack_Recipe _callBack_recipe) {
        this.callBack_recipe = _callBack_recipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_recipe_list,container,false);
        findViews(view);
        initViews(view);
        recipes = RecipeMockDB.generateRecipes();
        findMoreFromFireBase(view);
        updateAdapter(view);
//        Log.d("pttt",  recipes.toArray().toString());


        return view;
    }

    private void findMoreFromFireBase(View view) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("Recipes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("DDM1",""+snapshot.getChildren().getClass().getName());
                for( DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Recipe recipe = dataSnapshot.getValue(Recipe.class);
                    recipes.add(recipe);
                    updateAdapter(view);
                    Log.d("DDM1",recipe.toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateAdapter(View view) {
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

            @Override
            public void onRemove(View view, Recipe recipe) {
                int position = recipes.indexOf(recipe);
                recipes.remove(position);
                recipe_adapter.notifyItemRemoved(position);

            }
        });


        fragment_recipes_LST.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fragment_recipes_LST.setAdapter(recipe_adapter);
    }

    private void initViews(View view) {
        if(Activity_Main_Manager.isManager){
            recipe_BTN_add.setVisibility(View.VISIBLE);
        }
        recipe_BTN_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack_recipe!=null){
                    callBack_recipe.addRecipeWindow();
                }
            }
        });
    }


    public void findViews(View view) {
        fragment_recipes_LST=view.findViewById(R.id.fragment_recipies_LST);
        recipe_BTN_add=view.findViewById(R.id.recipe_BTN_add);
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