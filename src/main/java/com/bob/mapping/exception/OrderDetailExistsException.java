package com.bob.mapping.exception;

public class OrderDetailExistsException extends RuntimeException{
    private String message;
    public OrderDetailExistsException(){}
    public OrderDetailExistsException(String msg){
        super(msg);
        this.message = msg;
    }
}
