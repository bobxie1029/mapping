package com.bob.mapping.service;

import com.bob.mapping.dto.OrderDto;
import com.bob.mapping.entities.OrderEntity;
import com.bob.mapping.exception.NoSuchOrderExistsException;
import com.bob.mapping.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
@Service
@Log4j2
public class OrderService {
    private OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    public OrderDto getOrders(long orderId){
        OrderDto orderDto = new OrderDto();
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);

        if(orderEntityOptional.isPresent()){
            log.info("The order with this id " + orderId + " found");
            OrderEntity orderEntity = orderEntityOptional.get();
            orderDto.setId(orderEntity.getOrderEntityId());
            orderDto.setCustomerName(orderEntity.getCustomerName());
            orderDto.setSignedDate(orderEntity.getSignedDate());
        }
        else{
            log.info("The order with this id " + orderId + " doesn't exist in database");
            throw new NoSuchOrderExistsException( orderId + " doesn't exist in database");
        }
        return orderDto;
    }
    public OrderDto createOrder(@RequestBody OrderDto body){
        OrderEntity entity = new OrderEntity();
        entity.setOrderEntityId(body.getId());
        entity.setCustomerName(body.getCustomerName());
        entity.setSignedDate(body.getSignedDate());
        orderRepository.save(entity);

        OrderDto orderDto = new OrderDto();
        orderDto.setId(entity.getOrderEntityId());
        orderDto.setCustomerName(entity.getCustomerName());
        return orderDto;
    }
}
