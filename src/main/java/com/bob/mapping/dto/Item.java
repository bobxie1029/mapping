package com.bob.mapping.dto;

public class Item {
    Long productId;
    String productName;
    double productPrice;
    int quantity;

    public Item(){

    }
    public Item(Long productId, String productName, double productPrice, int quantity){
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
    public long getProductId(){
        return productId;
    }
    public String getProductName(){
        return productName;
    }
    public double getProductPrice(){
        return productPrice;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setProductId(Long productId){
        this.productId = productId;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public void setProductPrice(double productPrice){
        this.productPrice = productPrice;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

}
