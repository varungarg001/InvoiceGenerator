package com.varun.billgenerator.service.category;

import com.varun.billgenerator.exceptions.AlreadyExitsException;
import com.varun.billgenerator.exceptions.ResourceNotFoundException;
import com.varun.billgenerator.model.Category;

import java.util.List;

public interface ICategoryService {

    Category getCategoryById(Long id) throws ResourceNotFoundException;
    Category getCategoryByName(String name) throws ResourceNotFoundException;
    List<Category> getAllCategory();
    Category addCategory(Category category) throws AlreadyExitsException;
    Category updateCategory(Category category,Long id) throws ResourceNotFoundException;
    Boolean deleteCategory(Long id);
}
