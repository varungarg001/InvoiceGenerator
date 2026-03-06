package com.varun.billgenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;
    private Set<CartItemDto> items;
    private Double totalPrice;
}
