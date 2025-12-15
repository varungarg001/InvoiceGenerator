package com.varun.billgenerator.service.tax;

import com.varun.billgenerator.exceptions.ResourceNotFoundException;
import com.varun.billgenerator.model.TaxEntity;
import com.varun.billgenerator.repository.TaxConfigRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxConfigService {

    private final TaxConfigRepo taxRepo;

    @Cacheable("taxConfig")
    public TaxEntity getTaxEntity() {
        if(taxRepo.findById(1L).isEmpty()) {
            TaxEntity taxEntity = new TaxEntity(5.0, 5.0, 10.0);
            taxRepo.save(taxEntity);
        }
        return taxRepo.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Value Not found"));
    }
}
