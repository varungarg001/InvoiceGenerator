package com.varun.billgenerator.controller;

import com.varun.billgenerator.Response.ApiResponse;
import com.varun.billgenerator.constants.Messages;
import com.varun.billgenerator.exceptions.AlreadyExitsException;
import com.varun.billgenerator.exceptions.ResourceNotFoundException;
import com.varun.billgenerator.model.Category;
import com.varun.billgenerator.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/all/category")
    public ResponseEntity<ApiResponse> getAllCategories(){
        try {
            List<Category> categories = categoryService.getAllCategory();
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(), categories));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(Messages.INTERNAL_ERROR.getValue(), null));
        }
    }

    @GetMapping("/id/category")
    public ResponseEntity<ApiResponse> getCategoryById(@RequestParam("category_id") Long id){
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(), category));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/name/category")
    public ResponseEntity<ApiResponse> getCategoryByName(@RequestParam("category_name") String name ){
        try {
            Category categoryByName = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(), categoryByName));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }

    }

    @PostMapping("/add/category")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
        try {
            Category addedCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(),addedCategory));
        } catch (AlreadyExitsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/update/category")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody Category category,@RequestParam Long id){
        try {
            Category updatedCategory = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(), updatedCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/delete/category")
    public ResponseEntity<ApiResponse> deleteCategory(@RequestParam Long id){
        Boolean deletedCategory = categoryService.deleteCategory(id);
        if(deletedCategory){
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(), null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(Messages.NO_CONTENT.getValue(),null));
    }
}
