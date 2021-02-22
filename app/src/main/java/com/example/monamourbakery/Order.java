package com.example.monamourbakery;

import java.util.ArrayList;
import java.util.Date;

public class Order {

    private static final Double DELIVERY_PRICE=30.0;

    private int orderID;
    private ArrayList<Product> products = new ArrayList<>();
    private Status eStatus;
    private Boolean shipping = false;
    private String date;
    private Double totalPrice;
    private String userEmail;

    public Order(){}

    public Order(int orderID, ArrayList<Product> products, Status eStatus, Boolean shipping, String date, Double totalPrice,String userEmail) {
        this.orderID = orderID;
        this.products = products;
        this.eStatus = eStatus;
        this.shipping = shipping;
        this.date = date;
        this.userEmail = userEmail;
        this.totalPrice = totalPrice;
    }

    public static Double getDeliveryPrice() {
        return DELIVERY_PRICE;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public Status geteStatus() {
        return eStatus;
    }

    public void seteStatus(Status eStatus) {
        this.eStatus = eStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Boolean getShipping() {
        return shipping;
    }

    public void setShipping(Boolean shipping) {
        this.shipping = shipping;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String  date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        for (int i = 0; i < products.size() ; i++) {
            this.totalPrice += products.get(i).getPrice();
        }
        if(shipping == true){
            this.totalPrice += 30;
        }
    }





    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", products=" + products.size() +
                ", eStatus=" + eStatus +
                ", shipping='" + shipping + '\'' +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
