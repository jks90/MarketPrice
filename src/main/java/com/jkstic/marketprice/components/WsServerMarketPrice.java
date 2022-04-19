package com.jkstic.marketprice.components;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.jkstic.marketprice.dto.MarketDto;
import com.jkstic.marketprice.services.MarketPricesService;
import com.jkstic.marketprice.services.impl.MarketPricesServiceImpl;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ServerEndpoint(value ="/marketPriceListen")
@Component
public class WsServerMarketPrice {
	
	
	private  MarketPricesService marketPricesService =(MarketPricesServiceImpl) MyApplicationContextAware.getApplicationContext().getBean("marketPricesService");

	@OnOpen
	public void onOpen(Session session) {
		log.info("Conectado correctamente");
	}

	@OnClose
	public void onClose(Session session) {
		log.info("Conexión cerrada");
	}
	
	@OnError
	public void onError(Session session, Throwable thr) {
		log.error(thr.getMessage());
	}

	@OnMessage
	public String onMsg(String text) throws Exception {
		log.info("server listening:" + text);

		List<MarketDto> list = readAll(text);
		for(MarketDto market : list) {

			MarketDto marketRefactorPrice = refactorPrice(market);
			log.info(marketRefactorPrice.toString());
			
			marketPricesService.saveMarketPrice(marketRefactorPrice.getName().trim(), marketRefactorPrice);

		}

		return text;
	}

	private MarketDto refactorPrice(MarketDto market) {
		market.setBid(market.getBid().add(modifyPrice(market.getBid(), new BigDecimal("0.1"), true)));
		market.setAsk(market.getAsk().add(modifyPrice(market.getAsk(), new BigDecimal("0.1"), false)));
		return market;
	}

	private BigDecimal modifyPrice(BigDecimal price, BigDecimal percentage, Boolean negative) {
		if (negative) {
			return percentage.negate().multiply(price).divide(new BigDecimal("100"));
		} else {
			return percentage.multiply(price).divide(new BigDecimal("100"));
		}
	}

	private List<MarketDto> readAll(String text) throws IOException {
		
		Reader reader = new StringReader(text);
		
		CSVParser parser = new CSVParserBuilder().withSeparator(',')
				.build();
		CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build();

		List<String[]> list = new ArrayList<>();
		list = csvReader.readAll();
		List<MarketDto> markets = new ArrayList<>();
		list.forEach(linea -> {
			
			String pattern = "yyyy-MM-dd HH:mmm:ss:SSS";
			//String pattern = "dd-MM-yyyy HH:mmm:ss:SSS";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			try {
				Date date = simpleDateFormat.parse(linea[4]);
				markets.add(MarketDto.builder().id(Long.valueOf(linea[0])).name(linea[1].trim())
						.bid(new BigDecimal(linea[2].trim())).ask(new BigDecimal(linea[3].trim())).timestamp(date)
						.build());
			} catch (ParseException e) {
				e.printStackTrace();
			}

		});

		reader.close();
		csvReader.close();
		return markets;
	}
}
