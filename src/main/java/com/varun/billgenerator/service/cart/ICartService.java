package com.varun.billgenerator.service.cart;

import com.varun.billgenerator.exceptions.ResourceNotFoundException;
import com.varun.billgenerator.model.Cart;

public interface ICartService {

    Cart getCart(Long cartId) throws ResourceNotFoundException;
    Double getTotalBill(Long cartId);
    void clearCart(Long cartId);
    Cart intializeCart();
}
