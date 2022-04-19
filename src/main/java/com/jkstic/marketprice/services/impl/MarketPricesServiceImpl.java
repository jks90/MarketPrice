package com.jkstic.marketprice.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jkstic.marketprice.dto.MarketDto;
import com.jkstic.marketprice.persistence.Memory;
import com.jkstic.marketprice.services.MarketPricesService;

@Service
public class MarketPricesServiceImpl implements MarketPricesService {

	@Override
	public MarketDto getPrice(String market) {
		return Memory.getPriceStream(market);
	}

	@Override
	public List<String> getAllNamesMarkets() {
		return Memory.getMarketsInStream();
	}

	@Override
	public void saveMarketPrice(String nameMarket, MarketDto market) {
		Memory.setPriceStream(nameMarket, market);
	}

}
