package com.bob.mapping.controller;

import com.bob.mapping.dto.ErrorResponse;
import com.bob.mapping.dto.OrderDto;
import com.bob.mapping.exception.OrderDetailExistsException;
import com.bob.mapping.exception.NoSuchOrderExistsException;
import com.bob.mapping.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/listOrders")
    public List<OrderDto> listAllOrder(){
        return orderService.getOrders();
    }
    @GetMapping("/order/{id}")
    public OrderDto getOrder(@PathVariable("id") int id) {
        return orderService.getOrder(id);
    }

    @ExceptionHandler(value
            = NoSuchOrderExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse
    handleCustomerAlreadyExistsException(
            NoSuchOrderExistsException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage());
    }

    @DeleteMapping("/deleteOrder/{id}")
    public OrderDto deleteOrder(@PathVariable("id") int id) {
        return orderService.deleteOrders(id);
    }

    @DeleteMapping("/deleteOrder1/{id}")
    public OrderDto deleteOrder1(@PathVariable("id") int id){
        return orderService.deleteOrders1(id);
    }

    @ExceptionHandler(value = OrderDetailExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleOrdersAlreadyExistsException
            (OrderDetailExistsException ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
    }
    @PostMapping("/order")
    public OrderDto postOrder(@RequestBody OrderDto body){
        log.info("Received order name " + body.getCustomerName() + " with date " +body.getSignedDate());
        OrderDto orderDto = orderService.createOrder(body);
        log.info("assigned id" + orderDto.getId());
        return orderDto;
    }
}
