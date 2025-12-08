package com.varun.billgenerator.repository;

import com.varun.billgenerator.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    Category findByName(String name);
    Boolean existsByName(String name);
}
