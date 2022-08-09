package com.bob.mapping.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDto
{
    Long id;
    int quantity;
    OrderDto orderDto;
    ProductDto productDto;
}
