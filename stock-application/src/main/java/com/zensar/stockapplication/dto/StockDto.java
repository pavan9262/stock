package com.zensar.stockapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel("This is Stock Model")
public class StockDto {

	private long stockId;
	private String name;
	private String marketName;
	private double price;
}
