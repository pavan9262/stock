package com.zensar.stockapplication.service;

import java.util.List;

import com.zensar.stockapplication.dto.StockDto;

public interface StockService {
	
	
	 List<StockDto> getAllStocks(int pageNumber,int pagesize);
	 
	 StockDto getStock(long id);
	 
	 StockDto createStock(StockDto stock, String token);
	 
	 String deleteStock(long stockId); 
	 
	 StockDto updateStock(int stockId, StockDto stock);
	 
	 String deleteAllStocks();

	//List<StockDto> getStockByItsName(String name);

	//List<StockDto> getStockByItsNameAndPrice(String name, double price);
			
}
