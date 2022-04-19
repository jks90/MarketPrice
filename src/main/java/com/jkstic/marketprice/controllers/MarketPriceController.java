package com.jkstic.marketprice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jkstic.marketprice.dto.MarketDto;
import com.jkstic.marketprice.services.MarketPricesService;

@RestController
@RequestMapping("/api")
public class MarketPriceController {
	
	@Autowired
	private MarketPricesService marketPricesService;
	
	@GetMapping(value = "/markets/names")
	public ResponseEntity<?> getAllMarkets() {
		try {
			List<String> result = marketPricesService.getAllNamesMarkets();
			return new ResponseEntity<List<String> >(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/lastprice")
	public ResponseEntity<?> getLastPrice(@RequestParam String market) {
		try {
			MarketDto result = marketPricesService.getPrice(market);
			return new ResponseEntity<MarketDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
