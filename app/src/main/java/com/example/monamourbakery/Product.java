package com.example.monamourbakery;

public class Product extends Pastry {

    private double price;
    private Size size;
    private Type type;

    public Product (){

    }

    public Product (String name, Kosher kosher, String short_description,double price,Size size, Type type, String pastry_img){
        super(name,short_description,kosher,pastry_img);
        this.price=price;
        this.size=size;
        this.type=type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +super.toString()+
                "price=" + price +
                ", size=" + size +
                ", type=" + type +
                '}'+"\n";
    }
}
