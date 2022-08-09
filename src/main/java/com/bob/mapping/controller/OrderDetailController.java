package com.bob.mapping.controller;

import com.bob.mapping.dto.OrderDetailDto;
import com.bob.mapping.service.OrderDetailService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class OrderDetailController {
    private OrderDetailService orderDetailService;
    public OrderDetailController(OrderDetailService orderDetailService){
        this.orderDetailService = orderDetailService;
    }
    @DeleteMapping("/deleteOrderDetail/{id}")
    public OrderDetailDto deleteOrderDetail(@PathVariable("id") int id){
        return orderDetailService.deleteOrderDetails(id);
    }
}
