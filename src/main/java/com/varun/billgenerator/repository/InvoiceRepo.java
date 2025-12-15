package com.varun.billgenerator.repository;

import com.varun.billgenerator.model.InvoiceGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepo extends JpaRepository<InvoiceGenerator,Long> {
}
