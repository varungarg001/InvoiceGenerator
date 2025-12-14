package com.varun.billgenerator.repository;

import com.varun.billgenerator.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long> {

    void deleteAllByCartId(Long cartId);
//    void deleteAllByCartId(Long id);
}
