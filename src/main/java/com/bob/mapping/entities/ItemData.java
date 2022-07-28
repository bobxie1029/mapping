package com.bob.mapping.entities;


import lombok.*;


public interface ItemData {

    Long getProductEntityId();
    String getProductName();
    double getPrice();
    int getQuantity();
}
