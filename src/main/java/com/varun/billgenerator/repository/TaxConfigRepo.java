package com.varun.billgenerator.repository;

import com.varun.billgenerator.model.TaxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxConfigRepo extends JpaRepository<TaxEntity,Long> {
}
