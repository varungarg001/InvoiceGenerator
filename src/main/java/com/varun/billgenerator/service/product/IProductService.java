package com.varun.billgenerator.service.product;

import com.varun.billgenerator.exceptions.ResourceNotFoundException;
import com.varun.billgenerator.model.Category;
import com.varun.billgenerator.model.Product;
import com.varun.billgenerator.requests.AddProductRequest;
import com.varun.billgenerator.requests.UpdateProductRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest product);
    void deleteProductById(Long id) throws ResourceNotFoundException;

    Product updateProduct(UpdateProductRequest updatedProduct, Long id) throws ResourceNotFoundException;

    List<Product> getAllProduct();
    Product getProductById(Long id) throws ResourceNotFoundException;
    Product getProductByName(String name) throws ResourceNotFoundException;
    List<Product> getAllProductByCategoryName(String categoryName);
    Product createProduct(AddProductRequest request, Category category);

}
