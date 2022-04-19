package com.jkstic.marketprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MarketPriceApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(MarketPriceApplication.class, args);
	}
}
