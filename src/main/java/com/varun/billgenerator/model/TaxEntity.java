package com.varun.billgenerator.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tax_rule")
public class TaxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double sGST;

    private Double cGST;

    private Double serviceCharge;

    public TaxEntity(Double sGST, Double cGST, Double serviceCharge) {
        this.sGST = sGST;
        this.cGST = cGST;
        this.serviceCharge = serviceCharge;
    }

}
