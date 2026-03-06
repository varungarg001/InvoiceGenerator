package com.varun.billgenerator.dto;

import com.varun.billgenerator.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String productName;
    private Double price;
    private String description;
    private Category category;
}
