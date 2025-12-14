package com.varun.billgenerator.service.cart;

import com.varun.billgenerator.exceptions.ResourceNotFoundException;
import com.varun.billgenerator.model.CartItem;

public interface ICartItemService {

    void addItemToCart(Long productId,Long cartId,Long quantity) throws ResourceNotFoundException;
    void removeItemFromCart(Long productId,Long cartId) throws ResourceNotFoundException;
    void updateQuantity(Long productId,Long cartId,Long quantity) throws ResourceNotFoundException;
    CartItem getCartItem(Long  productId, Long cartId) throws ResourceNotFoundException;
}
