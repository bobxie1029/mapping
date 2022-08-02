package com.bob.mapping.repository;


import com.bob.mapping.entities.ItemData;
import com.bob.mapping.entities.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Long> {
    List<OrderDetailEntity> findByOrderEntityId(Long orderEntityId);

    //write query directly
    //with parameter :orderId
    //this match to the declaration of line 20
    //Since this is not a table,we can not declare the return as list of an entity, we have declar ItemData as interface
    //which have have a few method.

    //it comes back with a result set of a select (list of rows).  We read each row via the interface ItemData

    @Query (value="SELECT ode.quantity, pe.product_name as productName, pe.product_entity_id as productEntityId, pe.price FROM order_detail_entity  ode INNER JOIN product_entity pe ON ode.product_entity_id = pe.product_entity_id WHERE ode.order_entity_id = :orderId", nativeQuery = true)
    List<ItemData> getItems (@Param("orderId") Long orderId);

}
