package com.jkstic.marketprice.services;

import java.util.List;

import com.jkstic.marketprice.dto.MarketDto;

public interface MarketPricesService {
	
	public List<String> getAllNamesMarkets();
	public MarketDto getPrice(String market);
	
	public void saveMarketPrice(String nameMarket,MarketDto market);

}
