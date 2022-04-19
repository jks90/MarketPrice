package com.jkstic.marketprice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.jkstic.marketprice.services.MarketPricesService;
import com.jkstic.marketprice.services.impl.MarketPricesServiceImpl;

@Configuration
@EnableWebSocket
public class MarketPriceWsConfig {
	
	@Bean
    public ServerEndpointExporter serverEndpoint() {
        return new ServerEndpointExporter();
    }

	@Bean
    public MarketPricesService marketPricesService() {
        return new MarketPricesServiceImpl();
    }
}
