package com.bob.mapping.service;

import com.bob.mapping.dto.OrderDto;
import com.bob.mapping.entities.OrderDetailEntity;
import com.bob.mapping.entities.OrderEntity;
import com.bob.mapping.exception.OrderDetailExistsException;
import com.bob.mapping.exception.NoSuchOrderExistsException;
import com.bob.mapping.repository.OrderDetailRepository;
import com.bob.mapping.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class OrderService {
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDto getOrder(long orderId) {
        OrderDto orderDto = new OrderDto();
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);

        if (orderEntityOptional.isPresent()) {
            log.info("The order with this id " + orderId + " found");
            OrderEntity orderEntity = orderEntityOptional.get();
            orderDto.setId(orderEntity.getOrderEntityId());
            orderDto.setCustomerName(orderEntity.getCustomerName());
            orderDto.setSignedDate(orderEntity.getSignedDate());
        } else {
            log.info("The order with this id " + orderId + " doesn't exist in database");
            throw new NoSuchOrderExistsException(orderId + " doesn't exist in database");
        }
        return orderDto;
    }

    public OrderDto deleteOrders(long orderId) {
        OrderDto orderDto = new OrderDto();
//        OrderDetailDto orderDetailDto = new OrderDetailDto();
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);
        List<OrderDetailEntity> orderDetailEntityOptional = orderDetailRepository.findByOrderEntityId(orderId);
        //list for loop or check the size
        if (orderEntityOptional.isPresent()) {
            for (OrderDetailEntity entity : orderDetailEntityOptional) {
                orderDetailRepository.delete(entity);
//            orderDetailDto.setId(entity.getId());
//            orderDetailDto.setOrderDto(orderDetailDto.getOrderDto());
//            orderDetailDto.setProductDto(orderDetailDto.getProductDto());
//            orderDetailDto.setQuantity(entity.getQuantity());

            }
            log.info("The order with this id " + orderId + " found");
            OrderEntity orderEntity = orderEntityOptional.get();
            orderRepository.delete(orderEntity);
            orderDto.setId(orderEntity.getOrderEntityId());
            orderDto.setCustomerName(orderEntity.getCustomerName());
            orderDto.setSignedDate(orderEntity.getSignedDate());

        } else {
            log.info("The order with this id " + orderId + " doesn't exist in database");
            throw new NoSuchOrderExistsException(orderId + " doesn't exist in database");
        }
        return orderDto;
    }

    public OrderDto deleteOrders1(long orderId) {
        OrderDto orderDto = new OrderDto();
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);
        if (orderEntityOptional.isPresent()) {
            List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findByOrderEntityId(orderId);
            if (orderDetailEntities.size() == 0) {
                log.info("The order with this id " + orderId + " found");
                OrderEntity orderEntity = orderEntityOptional.get();
                orderRepository.delete(orderEntity);
                orderDto.setId(orderEntity.getOrderEntityId());
                orderDto.setCustomerName(orderEntity.getCustomerName());
                orderDto.setSignedDate(orderEntity.getSignedDate());
            } else {
                log.info("Order Id" + orderId + " has order details associated with it");
                throw new OrderDetailExistsException(orderId + " has order details associated with it");
            }
        } else {
            log.info("The order with this id " + orderId + " doesn't exist in database");
            throw new NoSuchOrderExistsException(orderId + " doesn't exist in database");
        }
        return orderDto;
    }
}
//        if(orderEntityOptional.isPresent()){
//            if(orderDetailEntityOptional.contains(orderId)){
//                log.info("The order with this id " + orderId + " found");
//                OrderDetailEntity orderDetailEntity = orderDetailEntityOptional.get(orderId);
//                orderDetailRepository.delete(orderDetailEntity);
//                orderDetailDto.setId(orderDetailEntity.getId());
//                orderDetailDto.setOrderDto(orderDetailDto.getOrderDto());
//                orderDetailDto.setProductDto(orderDetailDto.getProductDto());
//                orderDetailDto.setQuantity(orderDetailEntity.getQuantity());
//            }
//            else{
//                log.info("The order detail with this id " + orderId + " doesn't exist in database");
//                throw new NoSuchOrderExistsException(orderId + " doesn't exist in database");
//            }
//        log.info("The order with this id " + orderId + " found");
//        OrderEntity orderEntity = orderEntityOptional.get();
//        orderRepository.delete(orderEntity);
//        orderDto.setId(orderEntity.getOrderEntityId());
//        orderDto.setCustomerName(orderEntity.getCustomerName());
//        orderDto.setSignedDate(orderEntity.getSignedDate());
//convert orderEntity to an object of OrderDto.
//        }
//        }
//        else{
//            log.info("The order with this id " + orderId + " doesn't exist in database");
//            throw new NoSuchOrderExistsException(orderId + " doesn't exist in database");
//        }
//        return orderDto;
//    }