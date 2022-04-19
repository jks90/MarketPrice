package com.jkstic.marketprice.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarketDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private BigDecimal bid;
	private BigDecimal ask;
	private Date timestamp;

}
