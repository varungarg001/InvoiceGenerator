package com.varun.billgenerator.controller;

import com.varun.billgenerator.Response.ApiResponse;
import com.varun.billgenerator.constants.Messages;
import com.varun.billgenerator.model.Cart;
import com.varun.billgenerator.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/getcart/cart")
    public ResponseEntity<ApiResponse> getCart(@RequestParam("cartid") Long cartId) {
        try {
            Cart cart = cartService.getCart(cartId);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(),  cart));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(Messages.INTERNAL_ERROR.getValue(), null));
        }
    }

    @DeleteMapping("/deletecart/cart")
    public ResponseEntity<ApiResponse> clearCart(@RequestParam("cartid") Long cartId) {
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(),  null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(Messages.INTERNAL_ERROR.getValue(), null));
        }
    }

    @GetMapping("/total/cart")
    public ResponseEntity<ApiResponse> getTotalBill(@RequestParam("cartid") Long cartId) {
        try {
            Double totalBill = cartService.getTotalBill(cartId);
            return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(),  totalBill));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(Messages.INTERNAL_ERROR.getValue(), null));
        }
    }
}
