package com.varun.billgenerator.service.cart;

import com.varun.billgenerator.dto.CartDto;
import com.varun.billgenerator.model.Cart;
import com.varun.billgenerator.repository.CartItemRepo;
import com.varun.billgenerator.repository.CartRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private final CartRepo cartRepo;

    private final CartItemRepo cartItemRepo;

    private final ModelMapper modelMapper;


    @Override
    public Cart getCart(Long cartId){

        return cartRepo.findById(cartId).orElse(null);
    }

    @Override
    public Double getTotalBill(Long cartId) {

        Cart cart = getCart(cartId);
        return cart.getTotalPrice();
    }

    @Override
    @Transactional
    public void clearCart(Long cartId) {

        Cart cart = getCart(cartId);
        cartItemRepo.deleteAllByCartId(cart.getId());
        cart.getCartItems().clear();
        cartRepo.deleteById(cartId);

    }

    @Override
    public Cart intializeCart() {
        Cart cart=new Cart(0.0);
        cartRepo.save(cart);
        return cart;
    }

    @Override
    public CartDto convertToCartDto(Cart cart){
        return modelMapper.map(cart,CartDto.class);
    }
}
