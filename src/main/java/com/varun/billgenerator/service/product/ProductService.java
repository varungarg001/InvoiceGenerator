package com.varun.billgenerator.service.product;

import com.varun.billgenerator.exceptions.ResourceNotFoundException;
import com.varun.billgenerator.model.Category;
import com.varun.billgenerator.model.Product;
import com.varun.billgenerator.repository.CategoryRepo;
import com.varun.billgenerator.repository.ProductRepo;
import com.varun.billgenerator.requests.AddProductRequest;
import com.varun.billgenerator.requests.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepo productRepo;

    private final CategoryRepo categoryRepo;

    @Override
    public Product addProduct(AddProductRequest product) {
        Category category1 = Optional.ofNullable(categoryRepo.findByName(product.getCategory().getName())).orElseGet(() -> {
            Category category = new Category(product.getCategory().getName());
            return categoryRepo.save(category);
        });

        product.setCategory(category1);

        return productRepo.save(createProduct(product,category1));
    }

    @Override
    public Product createProduct(AddProductRequest request, Category category){
        return new Product(
                request.getProductName()
                ,request.getPrice()
                ,request.getDescription()
                ,category);
    }

    @Override
    public void deleteProductById(Long id) throws ResourceNotFoundException {
        Product product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        productRepo.delete(product);
    }

    @Override
    public Product updateProduct(UpdateProductRequest updatedProduct, Long id) throws ResourceNotFoundException {
        Product product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        product.setProductName(updatedProduct.getProductName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        Category category=categoryRepo.findByName(updatedProduct.getCategory().getName());
        product.setCategory(category);
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) throws ResourceNotFoundException {
        return productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource Not Found"));
    }

    @Override
    public Product getProductByName(String name) throws ResourceNotFoundException {
        return productRepo.
                findByProductName(name)
                .orElseThrow(()->new ResourceNotFoundException("Resource Not Found"));
    }

    @Override
    public List<Product> getAllProductByCategoryName(String categoryName) {
       return productRepo
               .findAll()
               .stream()
               .filter(product -> product.getCategory().getName().equals(categoryName))
               .toList();
    }
}
