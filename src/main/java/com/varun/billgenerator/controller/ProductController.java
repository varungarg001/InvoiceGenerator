package com.varun.billgenerator.controller;

import com.varun.billgenerator.Response.ApiResponse;
import com.varun.billgenerator.constants.Messages;
import com.varun.billgenerator.exceptions.ResourceNotFoundException;
import com.varun.billgenerator.model.Product;
import com.varun.billgenerator.requests.AddProductRequest;
import com.varun.billgenerator.requests.UpdateProductRequest;
import com.varun.billgenerator.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping("/getAll/product")
    public ResponseEntity<ApiResponse> getAllProduct(){
        try {
            List<Product> allProduct = productService.getAllProduct();
            if(allProduct.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse(Messages.NO_PRODUCT.getValue(),null));
            }
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(), allProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(Messages.INTERNAL_ERROR.getValue(), null));
        }
    }

    @GetMapping("/product")
    public ResponseEntity<ApiResponse> getProductById(@RequestParam Long id)  {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(), product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/name/product")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam("productname") String name){
        try {
            Product product = productService.getProductByName(name);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(), product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/category/product")
    public ResponseEntity<ApiResponse> getProductByCategory(@RequestParam("category") String categoryName){
        try {
            List<Product> allProductByCategoryName = productService.getAllProductByCategoryName(categoryName);
            if(allProductByCategoryName.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(), null));
            }
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(),allProductByCategoryName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(Messages.INTERNAL_ERROR.getValue(), null));
        }
    }

    @PostMapping("/add/product")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest request){
        try {
            Product product = productService.addProduct(request);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(),product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(Messages.INTERNAL_ERROR.getValue(), null));
        }
    }

    @PutMapping("/update/product")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest request,
                                                     @RequestParam Long id){

        try {
            Product product = productService.updateProduct(request, id);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(), product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/delete/product")
    public ResponseEntity<ApiResponse> deleteProduct(@RequestParam Long id){
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(), null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
