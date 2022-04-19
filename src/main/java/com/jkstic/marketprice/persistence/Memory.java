package com.jkstic.marketprice.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jkstic.marketprice.dto.MarketDto;

public class Memory {
	
	
	private static final Map<String, MarketDto> priceStream = new HashMap<>();
	
	public static Map<String, MarketDto> getAllPriceStream(){
		return priceStream;
	}
	
	public static MarketDto getPriceStream(String name){
		return priceStream.get(name);
	}
	
	public static MarketDto setPriceStream(String name,MarketDto market ){
		return priceStream.put(name,market);
	}
	
	public static List<String> getMarketsInStream( ){
		return new ArrayList<>(priceStream.keySet());
	}
}
