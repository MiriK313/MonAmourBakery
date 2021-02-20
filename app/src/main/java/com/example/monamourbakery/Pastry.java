package com.example.monamourbakery;

public class Pastry {

    private String pastry_name;
    private String short_description;
    private Kosher kosher;
    private String pastry_img;

    public Pastry(){
    }

    public Pastry(String pastry_name, String short_description, Kosher kosher, String pastry_img){
        this.pastry_name=pastry_name;
        this.short_description=short_description;
        this.kosher=kosher;
        this.pastry_img=pastry_img;

    }

    public String getPastry_name() {
        return pastry_name;
    }

    public void setPastry_name(String pastry_name) {
        this.pastry_name = pastry_name;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public Kosher getKosher() {
        return kosher;
    }

    public void setKosher(Kosher kosher) {
        this.kosher = kosher;
    }

    public String getPastry_img() {
        return pastry_img;
    }

    public void setPastry_img(String pastry_img) {
        this.pastry_img = pastry_img;
    }

    @Override
    public String toString() {
        return "" +
                "pastry_name='" + pastry_name + '\'' +
                ", short_description='" + short_description + '\'' +
                ", kosher=" + kosher +
                ", pastry_img='" + pastry_img + '\'' ;
    }
}
