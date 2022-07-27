package com.bob.mapping.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    Long id;
    String productName;
    Double price;
}
