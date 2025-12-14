package com.varun.billgenerator.requests;

import com.varun.billgenerator.model.Category;
import lombok.Data;

@Data
public class AddProductRequest {

    private Long id;
    private String productName;
    private Double price;
    private String description;
    private Category category;

}
