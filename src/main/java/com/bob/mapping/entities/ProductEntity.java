package com.bob.mapping.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    @Id
    Long productEntityId;
    String productName;
    Double price;
}
