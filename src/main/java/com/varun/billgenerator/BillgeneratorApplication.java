package com.varun.billgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class BillgeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillgeneratorApplication.class, args);
	}


}
