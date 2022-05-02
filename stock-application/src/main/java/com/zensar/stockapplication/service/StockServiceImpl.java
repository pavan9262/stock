package com.zensar.stockapplication.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;

	//private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private ModelMapper modelMapper;

	public List<StockDto> getStockByItsName(String name) {

		List<Stock> findStockByName = stockRepository.findStockByItsName(name);

		List<StockDto> stocks = new ArrayList<StockDto>();

		for (Stock st : findStockByName) {
			stocks.add(modelMapper.map(st, StockDto.class));
		}

		return stocks;
	}

	public List<StockDto> getStockByItsNameAndPrice(String name, double price) {

		List<Stock> findStockByNameAndPrice = stockRepository.findStockByItsNameAndPrice(name,price);

		List<StockDto> stocks = new ArrayList<StockDto>();

		for (Stock st : findStockByNameAndPrice) {
			stocks.add(modelMapper.map(st, StockDto.class));
		}

		return stocks;
	}

	/*
	 * static List<Stock> stocks = new ArrayList<Stock>();
	 * 
	 * static { stocks.add(new Stock(1L, "RIL", "bse", 2610)); stocks.add(new
	 * Stock(2L, "Zensar", "bse", 342)); stocks.add(new Stock(3L, "Jio", "bse",
	 * 2210)); }
	 */

	@Override
	public List<StockDto> getAllStocks(int pageNumber, int pageSize) {
		Page<Stock> pageStocks = stockRepository
				.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("stockId").descending()));
		List<Stock> content = pageStocks.getContent();

		List<StockDto> stockResponses = new ArrayList<>();

		for (Stock stock : content) {
			StockDto mapToResponse = modelMapper.map(stock, StockDto.class);
			stockResponses.add(mapToResponse);
		}
		return stockResponses;
	}

	@Override
	public StockDto getStock(long id) {

		Stock stock = stockRepository.findById(id).get();

		return modelMapper.map(stock, StockDto.class);

		/*
		 * StockResponse stockResponse = new StockResponse();
		 * stockResponse.setStockId(stock.getStockId());
		 * stockResponse.setName(stock.getName());
		 * stockResponse.setPrice(stock.getPrice());
		 * stockResponse.setMarketName(stock.getMarketName());
		 * 
		 * return stockResponse;
		 */
		/*
		 * Optional<Stock> stock1= stocks.stream().filter(stock ->
		 * stock.getStockId()==id).findAny();
		 * 
		 * if(stock1.isPresent()){ return stock1.get(); }else { return
		 * stock1.orElseGet(()->{return new Stock();}); }
		 */
	}

	@Override
	public StockDto createStock(StockDto stockRequest, String token) {

		// Stock newStock = mapToStock(stock);

		Stock newStock = modelMapper.map(stockRequest, Stock.class);

		// return stockRepository.save(stock);

		if (token.equals("NP66472")) {
			Stock save = stockRepository.save(newStock);
			return modelMapper.map(save, StockDto.class);

		} else {
			return null;
		}

	}

	@Override
	public String deleteStock(long stockId) {

		stockRepository.deleteById(stockId);

		return " Stock deleted with stock id " + stockId;

		/*
		 * for(Stock stock:stocks) { if(stock.getStockId()== stockId) {
		 * stocks.remove(stock); return " Stock deleted with stock id "+stockId; } }
		 * return "Sorry,stock id is not available";
		 */
	}

	@Override
	public StockDto updateStock(int stockId, StockDto stockDto) {

		// Stock maptoStock=mapToStock(stock);

		StockDto stockResponse = getStock(stockId);

		//Stock stock2 = mapToStock(stockResponse);
		
		Stock stock2= modelMapper.map(stockDto, Stock.class);
		
		if (stock2 != null) {

			stock2.setPrice(stockDto.getPrice());
			stock2.setMarketName(stockDto.getMarketName());
			stock2.setName(stockDto.getName());
			stock2.setStockId(stockId);
			Stock stock3 = stockRepository.save(stock2);

			return modelMapper.map(stock3,StockDto.class);
		}
		return null;

		/*
		 * availableStock.setMarketName(stock.getMarketName());
		 * availableStock.setName(stock.getName());
		 * availableStock.setPrice(stock.getPrice());
		 * 
		 * return availableStock;
		 */
	}

	@Override
	public String deleteAllStocks() {
		stockRepository.deleteAll();
		return "All stocks deleted Successfully";
	}

	/*private Stock mapToStock(StockDto stockRequest) {

		Stock newStock = new Stock();

		newStock.setMarketName(stockRequest.getMarketName());
		newStock.setName(stockRequest.getName());
		newStock.setPrice(stockRequest.getPrice());

		return newStock;

	}*/


	/*private StockDto mapToResponse(Stock stock) {

		StockDto stockResponse = new StockDto();

		stockResponse.setStockId(stock.getStockId());

		stockResponse.setPrice(stock.getPrice());

		stockResponse.setName(stock.getName());

		stockResponse.setMarketName(stock.getMarketName());

		return stockResponse;

	}*/

}
