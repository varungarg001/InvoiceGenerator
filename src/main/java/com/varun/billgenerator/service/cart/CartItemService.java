package com.varun.billgenerator.service.cart;

import com.varun.billgenerator.exceptions.ResourceNotFoundException;
import com.varun.billgenerator.model.Cart;
import com.varun.billgenerator.model.CartItem;
import com.varun.billgenerator.model.Product;
import com.varun.billgenerator.repository.CartItemRepo;
import com.varun.billgenerator.repository.CartRepo;
import com.varun.billgenerator.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartItemRepo cartItemRepo;
    private final CartRepo cartRepo;
    private final IProductService productService;
    private final ICartService cartService;

    @Override
    public void addItemToCart(Long productId, Long cartId, Long quantity) throws ResourceNotFoundException {

        // 1. get the cart
        // 2. get the product
        // 3. check if the product already in the cart
        // 4. if yes then increase the quantity with the requested quantity
        // 5. if no, then initiate new cartItem entry.

        Product productById = productService.getProductById(productId);
        Cart cart = cartService.getCart(cartId);

        if(cart==null){
            cart=cartService.intializeCart();
        }

        CartItem cartItem = cart.getCartItems().stream().filter(item -> item.getProduct().getId().equals(productById.getId())).findFirst().orElse(new CartItem());
        if(cartItem.getId()==null){
            cartItem.setProduct(productById);
            cartItem.setCart(cart);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(productById.getPrice());
        }else{
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
        }

        cart.addCartItem(cartItem);
        cart.setTotalPrice();
        cartItemRepo.save(cartItem);
        cartRepo.save(cart);
    }

    @Override
    public void removeItemFromCart(Long productId, Long cartId) throws ResourceNotFoundException {
        Cart cart = cartService.getCart(cartId);
        CartItem cartItem = getCartItem(productId,cartId);
        cart.removeCartItem(cartItem);
        cartRepo.save(cart);
    }

    @Override
    public void updateQuantity(Long productId, Long cartId, Long quantity) throws ResourceNotFoundException {

        Cart cart = cartService.getCart(cartId);
        CartItem cartItem = getCartItem(productId, cartId);
        cartItem.setQuantity(quantity);
        cart.setTotalPrice();
        cartItemRepo.save(cartItem);
        cartRepo.save(cart);

    }

    @Override
    public CartItem getCartItem(Long productId, Long cartId) throws ResourceNotFoundException {
        Cart cart = cartService.getCart(cartId);
        return cart
                .getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}
