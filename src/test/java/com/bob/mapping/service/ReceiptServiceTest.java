package com.bob.mapping.service;

import com.bob.mapping.repository.OrderDetailRepository;
import com.bob.mapping.repository.OrderRepository;
import com.bob.mapping.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptServiceTest {
    private ReceiptService receiptService;

    //create a mock object to manipulate the input and output of a method.
    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderDetailRepository orderDetailRepository;

    @BeforeEach
    public void init(){

        //init the mock framework
        MockitoAnnotations.initMocks(this);
        //instantiate an object to be tested
        receiptService = new ReceiptService(orderRepository, orderDetailRepository);

    }

    @Test
    public void test() {

    }
}
