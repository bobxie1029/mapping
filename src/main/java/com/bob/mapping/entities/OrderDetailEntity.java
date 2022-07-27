package com.bob.mapping.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailEntity {
    @Id
    Long id;
    Long orderEntityId;
//    Long productEntityId;
    int quantity;

    @ManyToOne
    @JoinColumn(name="product_entity_id", nullable=false)
    ProductEntity productEntity;

}
