package com.example.usedtransactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.usedtransactionservice"})
public class UsedTransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsedTransactionServiceApplication.class, args);
	}

}
