package com.varun.billgenerator.controller;

import com.varun.billgenerator.Response.ApiResponse;
import com.varun.billgenerator.constants.Messages;
import com.varun.billgenerator.exceptions.ResourceNotFoundException;
import com.varun.billgenerator.service.cart.ICartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}")
@RequiredArgsConstructor
public class CartItemController {

    private final ICartItemService cartItemService;


    @PostMapping("/additem/cart")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long productId,
                                                     @RequestParam Long cartId,
                                                     @RequestParam Long quantity){
        try{
            cartItemService.addItemToCart(productId,cartId,quantity);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(),null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/deleteitem/cart")
    public ResponseEntity<ApiResponse> removeItemFromCart(@RequestParam("productid") Long productId,
                                                          @RequestParam("cart") Long cartId){

        try{
            cartItemService.removeItemFromCart(productId,cartId);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(),null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/updatequantity/cart")
    public ResponseEntity<ApiResponse> updateProductQuantity(@RequestParam Long productId,
                                                             @RequestParam Long cartId,
                                                             @RequestParam Long quantity){
        try{
            cartItemService.updateQuantity(productId,cartId,quantity);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(),null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
