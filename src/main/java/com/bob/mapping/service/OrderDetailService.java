package com.bob.mapping.service;

import com.bob.mapping.dto.OrderDetailDto;
import com.bob.mapping.entities.OrderDetailEntity;
import com.bob.mapping.repository.OrderDetailRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Log4j2
public class OrderDetailService {
    private OrderDetailRepository orderDetailRepository;
    public OrderDetailDto deleteOrderDetails(long id){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        Optional<OrderDetailEntity> orderDetailDtoOptional = orderDetailRepository.findById(id);
        if(orderDetailDtoOptional.isPresent()){
            log.info("The product with this id " + id + " found");
            OrderDetailEntity orderDetailEntity = orderDetailDtoOptional.get();
            orderDetailRepository.delete(orderDetailEntity);
            orderDetailDto.setId(orderDetailEntity.getId());
            orderDetailDto.setProductDto(orderDetailDto.getProductDto());
            orderDetailDto.setOrderDto(orderDetailDto.getOrderDto());
            orderDetailDto.setQuantity(orderDetailEntity.getQuantity());
        }
        return orderDetailDto;
    }
}
