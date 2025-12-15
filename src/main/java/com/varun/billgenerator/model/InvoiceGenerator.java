package com.varun.billgenerator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class InvoiceGenerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double subTotal;

    private Double totalTax;

    private Double totalAmount;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public InvoiceGenerator(Double subTotal, Double totalTax, Double totalAmount) {
        this.subTotal = subTotal;
        this.totalTax = totalTax;
        this.totalAmount = totalAmount;
    }

}
