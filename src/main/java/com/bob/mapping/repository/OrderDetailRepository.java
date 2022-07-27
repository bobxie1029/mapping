package com.bob.mapping.repository;


import com.bob.mapping.entities.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Long> {
    List<OrderDetailEntity> findByOrderEntityId(Long  orderEntityId);
}
