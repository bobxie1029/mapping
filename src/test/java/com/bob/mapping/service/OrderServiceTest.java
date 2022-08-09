package com.bob.mapping.service;

import com.bob.mapping.dto.OrderDto;
import com.bob.mapping.entities.OrderDetailEntity;
import com.bob.mapping.entities.OrderEntity;
import com.bob.mapping.entities.ProductEntity;
import com.bob.mapping.exception.NoSuchOrderExistsException;
import com.bob.mapping.helper.ExecuableNoSuchOrder;
import com.bob.mapping.repository.OrderDetailRepository;
import com.bob.mapping.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderService orderService;

    //create a mock object to manipulate the input and output of a method.
    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderDetailRepository orderDetailRepository;

    @Captor
    ArgumentCaptor<OrderEntity> orderEntityCaptor;


    @BeforeEach
    public void init(){

        //init the mock framework
        MockitoAnnotations.openMocks(this);
        //instantiate an object to be tested
        orderService = new OrderService(orderRepository,orderDetailRepository);

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

        OrderDto orderDtoResult = orderService.getOrder(1L);


        assertThat(orderDtoExpected).usingRecursiveComparison()
                .isEqualTo(orderDtoResult);

    }

    @Test
    public void returnExceptionIfOrderDoesNotExist() {

        Optional<OrderEntity> orderEntityOptional = Optional.empty();

        when(orderRepository.findById(1L)).thenReturn(orderEntityOptional);

        // () implement an interface with only one method.
        //cmd B to look what interface it is.
        Exception exception = assertThrows(NoSuchOrderExistsException.class, () -> {
            orderService.getOrder(1L);;
        });

        String expectedMessage = "1 doesn't exist in database";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void returnExceptionIfOrderDoesNotExist2() {

        Optional<OrderEntity> orderEntityOptional = Optional.empty();

        when(orderRepository.findById(1L)).thenReturn(orderEntityOptional);

        Executable executable = new ExecuableNoSuchOrder(orderService);

        Exception exception = assertThrows(NoSuchOrderExistsException.class, executable);

        String expectedMessage = "1 doesn't exist in database";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    //test 1: if order ID does not exist in the database. Threw NoSuchOrderExistsException.
    //test 2: Order ID has order details associated, threw Order Detail Exists Exception.
    //test 3: Order ID exists, no order details, call  orderDetailRepository.findByOrderEntityId(orderId) with proper parameters
    //and also return proper dto.

    @Test
    public void deleteFailedIfOrderDetailsExist() {

        OrderEntity orderEntity = OrderEntity
                .builder().orderEntityId(1L)
                .customerName("Customer")
                .signedDate("10/20/2000")
                .build();
        OrderDto orderDto = OrderDto
                .builder()
                .customerName("Customer")
                .signedDate("10/20/2000")
                .id(1L)
                .build();
        Optional<OrderEntity> orderEntityOptional = Optional.of(orderEntity);
        //simulate the behavior of method call for finsById using mock
        //in this case, it will return an object
        when(orderRepository.findById(1L)).thenReturn(orderEntityOptional);
//        ProductEntity productEntity = ProductEntity
//                .builder()
//                .productEntityId(10L)
//                .productName("product name")
//                .price(20.0)
//                .build();
//        OrderDetailEntity orderDetailEntity = OrderDetailEntity
//                .builder()
//                .orderEntityId(1L)
//                .productEntity(productEntity)
//                .build();
        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
//        orderDetailEntities.add(orderDetailEntity);

        //simulate the behavior of method call findByOrderId, in this case
        //it is an empty list.
        when(orderDetailRepository.findByOrderEntityId(1L)).thenReturn(orderDetailEntities);



        OrderDto actualResult = orderService.deleteOrders1(1L);
        //We want to make sure that delete get called with the right parameters
        //so we capture it and check it later
        verify(orderRepository).delete(orderEntityCaptor.capture());

        assertThat(orderEntityCaptor.getValue()).usingRecursiveComparison()
                .isEqualTo(orderEntity);
        assertThat(actualResult).usingRecursiveComparison()
                .isEqualTo(orderDto);

    }

}