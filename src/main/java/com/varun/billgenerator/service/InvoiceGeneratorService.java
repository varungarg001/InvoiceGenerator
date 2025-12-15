package com.varun.billgenerator.service;

import com.varun.billgenerator.model.Cart;
import com.varun.billgenerator.model.InvoiceGenerator;
import com.varun.billgenerator.model.TaxEntity;
import com.varun.billgenerator.repository.InvoiceRepo;
import com.varun.billgenerator.service.cart.ICartService;
import com.varun.billgenerator.service.tax.TaxConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceGeneratorService {

    private final TaxConfigService tax;

    private final InvoiceRepo invoiceRepo;

    private final ICartService cartService;

    public TaxEntity getTaxConfig() {
        return tax.getTaxEntity();
    }

    public InvoiceGenerator getInvoice(Long cartId) {
        Cart cart = cartService.getCart(cartId);
        TaxEntity taxConfig =getTaxConfig();
        Double cGST= taxConfig.getCGST();
        Double sGST= taxConfig.getSGST();
        Double serviceCharge= taxConfig.getServiceCharge();
        Double subTotal=cart.getTotalPrice();

        Double totalTax=((subTotal*cGST)+(subTotal*sGST)+(subTotal*serviceCharge))/100.0;

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(subTotal, totalTax, (subTotal + totalTax));
        return invoiceRepo.save(invoiceGenerator);
    }
}
