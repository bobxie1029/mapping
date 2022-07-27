package com.bob.mapping.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    Long id;
    String customerName;
    String signedDate;
}
