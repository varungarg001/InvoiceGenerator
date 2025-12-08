package com.varun.billgenerator.service.category;

import com.varun.billgenerator.exceptions.AlreadyExitsException;
import com.varun.billgenerator.exceptions.ResourceNotFoundException;
import com.varun.billgenerator.model.Category;
import com.varun.billgenerator.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepo categoryRepo;

    @Override
    public Category getCategoryById(Long id) throws ResourceNotFoundException {
        return categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource Not Found"));
    }

    @Override
    public Category getCategoryByName(String name) throws ResourceNotFoundException {
        return categoryRepo
                .findAll()
                .stream()
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElseThrow(()->new ResourceNotFoundException("Resource Not Found"));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Category addCategory(Category category) throws AlreadyExitsException {
        return Optional.of(category).filter(c->!categoryRepo.existsByName(c.getName())).map(c->categoryRepo.save(c)).orElseThrow(()->new AlreadyExitsException("Resource already present"));
    }

    @Override
    public Category updateCategory(Category category, Long id) throws ResourceNotFoundException {
        Category category1 = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        category1.setName(category.getName());
        return categoryRepo.save(category1);
    }

    @Override
    public Boolean deleteCategory(Long id) {
        return categoryRepo.findAll().removeIf(c->c.getId().equals(id));
    }
}
