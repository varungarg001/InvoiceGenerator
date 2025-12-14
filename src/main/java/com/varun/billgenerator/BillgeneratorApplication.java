package com.varun.billgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class BillgeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillgeneratorApplication.class, args);
	}
//
//	@Bean
//	public PlatformTransactionManager add(){
//		return new MongoTransactionManager(dbfactory);
//	}

}
