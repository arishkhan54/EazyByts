package com.internship.stocktrading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StockTradingSimulationApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockTradingSimulationApplication.class, args);
	}

}
