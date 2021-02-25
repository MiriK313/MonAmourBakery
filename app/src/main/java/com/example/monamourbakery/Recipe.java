package com.example.monamourbakery;

import java.util.HashMap;

public class Recipe extends Pastry {

    private HashMap<String, String> ingredients = new HashMap<String, String>();
    private String description;

    public Recipe() {
    }

    public Recipe(String name, HashMap<String, String> ingredients, String short_description, String description, Kosher kosher,String pastry_img) {
        super(name,short_description,kosher,pastry_img);
        this.ingredients = ingredients;
        this.description = description;
    }

    public String toString_Ingredients() {
        String st = "";
        for (String key: ingredients.keySet()){
            //System.out.println(key + " " + ingredients.get(key));
            st = st + key + " " + ingredients.get(key)+"\n";
        }
        return st;
    }

    public void setIngredients(HashMap<String, String> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(String quantity, String ingredient){
        this.ingredients.put(quantity,ingredient);
    }

    public void setIngredient(String quantity, String ingredient){
        this.ingredients.put(quantity,ingredient);
    }
    public HashMap<String, String> getIngredients(){
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Recipe{" + super.getPastry_name()+
                "ingredients=" + toString_Ingredients() +
                ", description='" + description + '\'' +
                '}';
    }
}
