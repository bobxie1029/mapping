package com.bob.mapping.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    Long orderEntityId;
    String customerName;
    String signedDate;

}
