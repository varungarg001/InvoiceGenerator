package com.varun.billgenerator.controller;

import com.varun.billgenerator.Response.ApiResponse;
import com.varun.billgenerator.constants.Messages;
import com.varun.billgenerator.model.Cart;
import com.varun.billgenerator.model.InvoiceGenerator;
import com.varun.billgenerator.service.InvoiceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceGeneratorService invoice;

    @GetMapping("/gettotalbill/invoice")
    public ResponseEntity<ApiResponse> getTotalBill(@RequestParam Long cartId){
        InvoiceGenerator invoices = invoice.getInvoice(cartId);
        return ResponseEntity.ok(new ApiResponse(Messages.SUCCESS_MESSAGE.getValue(), invoices));
    }

}
