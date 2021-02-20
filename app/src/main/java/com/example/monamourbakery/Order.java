package com.example.monamourbakery;

import java.util.ArrayList;
import java.util.Date;

public class Order {

    private static final Double DELIVERY_PRICE=30.0;

    private int orderID;
    private ArrayList<Product> products = new ArrayList<>();
    private Status eStatus;
    private String address;
    private Date date;
    private Double totalPrice;

    public Order(){}

    public Order(int orderID, ArrayList<Product> products, Status eStatus, String address, Date date, Double totalPrice) {
        this.orderID = orderID;
        this.products = products;
        this.eStatus = eStatus;
        this.address = address;
        this.date = date;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        for (int i = 0; i < products.size() ; i++) {
            this.totalPrice += products.get(i).getPrice();
        }
        if(address!=null){
            this.totalPrice += 30;
        }
    }





    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", products=" + products.size() +
                ", eStatus=" + eStatus +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
