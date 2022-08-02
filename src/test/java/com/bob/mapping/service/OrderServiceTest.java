package com.bob.mapping.service;

import com.bob.mapping.dto.OrderDto;
import com.bob.mapping.entities.OrderEntity;
import com.bob.mapping.repository.OrderDetailRepository;
import com.bob.mapping.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderService orderService;

    //create a mock object to manipulate the input and output of a method.
    @Mock
    OrderRepository orderRepository;


    @BeforeEach
    public void init(){

        //init the mock framework
        MockitoAnnotations.openMocks(this);
        //instantiate an object to be tested
        orderService = new OrderService(orderRepository);

    }

    @Test
    public void returnAOderDtoIfOrderPresent() {
        OrderEntity orderEntity = OrderEntity
                .builder().orderEntityId(1L)
                .customerName("Customer")
                .signedDate("10/20/2000")
                .build();
        Optional<OrderEntity> orderEntityOptional = Optional.of(orderEntity);
        //mock. Didn't really run findById. Didn't go to database.
        //we just assume that when orderRepository.findById(1L) is executed,
        //it will return orderEntityOptional
        when(orderRepository.findById(1L)).thenReturn(orderEntityOptional);
        OrderDto orderDtoExpected = OrderDto
                .builder()
                .id(1L)
                .customerName("Customer")
                .signedDate("10/20/2000").build();

        OrderDto orderDtoResult = orderService.getOrders(1L);
        assertThat(orderDtoExpected).usingRecursiveComparison()
                .isEqualTo(orderDtoResult);

    }

    @Test
    public void returnExceptionIfOrderDoesNotExist() {


    }

}