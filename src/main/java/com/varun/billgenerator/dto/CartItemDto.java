package com.varun.billgenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private Long id;
    private Double unitPrice;
    private Long quantity;
    private ProductDto product;
}
