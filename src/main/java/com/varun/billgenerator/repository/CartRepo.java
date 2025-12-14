package com.varun.billgenerator.repository;

import com.varun.billgenerator.model.Cart;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepositoryImplementation<Cart,Long> {
}
