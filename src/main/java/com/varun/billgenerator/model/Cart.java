package com.varun.billgenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Set<CartItem> cartItems=new HashSet<>();

    @OneToOne(mappedBy = "cart")
    private InvoiceGenerator invoice;

    public void addCartItem(CartItem item){
        this.cartItems.add(item);
        item.setCart(this);
        setTotalPrice();

    }

    public void removeCartItem(CartItem item){
        this.cartItems.remove(item);
        item.setCart(null);
        setTotalPrice();

    }

    public void setTotalPrice(){
        this.totalPrice=this.cartItems
                .stream()
                .map(item->item.getUnitPrice()*item.getQuantity())
                .reduce((c,d)->c+d)
                .orElse(0.0);
    }

    public Cart(Double totalPrice){
        this.totalPrice=totalPrice;
    }
}
