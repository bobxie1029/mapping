package com.bob.mapping.dto;

import java.util.List;

public class Receipt {
    Long orderId;
    double totalPrice;
    String customerName;
//    String dateSigned;
//    Long id;
//    Long productId;
//    int quantity;
//    String productName;
//    double price;
    List<Item> items;

    public Receipt() {

    }
//    public Receipt(Long orderId, int price, String customerName, String dateSigned, Long id, Long productId, int quantity, String productName, List<Item> items){
//        this.orderId = orderId;
//        this.totalPrice = price;
//        this.customerName = customerName;
//        this.dateSigned = dateSigned;
//        this.id = id;
//        this.productId = productId;
//        this.quantity = quantity;
//        this.productName = productName;
//        this.items = items;
//    }

    public Receipt(Long orderId, int price, String customerName, List<Item> items) {
        this.orderId = orderId;
        this.totalPrice = price;
        this.customerName = customerName;
        this.items = items;
    }


    public Long getOrderId() {
        return orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

//    public String getDateSigned() {
//        return dateSigned;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public Long getProductId() {
//        return productId;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public double getPrice() {
//        return price;
//    }

    public List<Item> getItems() {
        return items;
    }


    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

//    public void setDateSigned(String dateSigned) {
//        this.dateSigned = dateSigned;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}





