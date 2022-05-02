package com.zensar.stockapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.service.StockService;

/*import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;*/

@RestController
//@Controller
//@CrossOrigin("http://localhost:9090")
@RequestMapping(value="/stocks")
//produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//@ApiIgnore
public class StockController {

	@Autowired

	private StockService stockService;

	/*
	 * public StockController() { stocks.add(new Stock(1L, "RIL", "bse", 2610));
	 * stocks.add(new Stock(2L, "Zensar", "bse", 342)); stocks.add(new Stock(3L,
	 * "Jio", "bse", 2210)); }
	 */

	/*
	 * @RequestMapping(value="/test",method=RequestMethod.GET) public void test() {
	 * System.out.println("I am inside test method"); }
	 */
	
	/*@RequestMapping(value="/name/{name}", method=RequestMethod.GET)
	public List<StockDto> getStockByName(@PathVariable("name") String name){
		
		return stockService.getStockByItsName(name);
	}*/
	
	/*@RequestMapping(value="/name/{name}/price/{price}", method=RequestMethod.GET)
	public List<StockDto> getStockByNameAndPrice(@PathVariable("name") String name, @PathVariable("price") double price){
		
		return stockService.getStockByItsNameAndPrice(name,price);
	}*/
	

	// @GetMapping("/stocks")
	// @ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	//@ApiOperation("Getting all Stocks info")
	public List<StockDto> getAllStocks(@RequestParam(value="pageNumber" ,defaultValue="0",required=false) int pageNumber, @RequestParam(value="pagesize" ,defaultValue="5",required=false) int pagesize) {
		return stockService.getAllStocks(pageNumber,pagesize);
	}

	/*
	 * @GetMapping("/{stockId}")
	 * 
	 * @RequestMapping(value="/{stockId}",method=RequestMethod.GET) public Stock
	 * getStock(@PathVariable("stockId") long id) {
	 * 
	 * for(Stock stock:stocks) { if(stock.getStockId()== id) { return stock; } }
	 * return null; }
	 */

	//@GetMapping("/{stockId}")
	@RequestMapping(value = "/{stockId}", method = RequestMethod.GET)
	//@ApiOperation("Getting stock based on StockId")
	//@ApiResponse(message = "Got stockId", code=200)
	public StockDto getStock( @PathVariable("stockId") long id) {
		//@ApiParam("stockId has to be greater than 0")
		return stockService.getStock(id);
	}

	/*
	 * @GetMapping("/stocks/stockId") public Stock
	 * getStockByRequestParam(@RequestParam(required=false,value="id",defaultValue=
	 * "2") long id) {
	 * 
	 * for(Stock stock:stocks) { if(stock.getStockId()== id) { return stock; } }
	 * return null; }
	 */

//	@PostMapping("/stocks")
	@RequestMapping(method = RequestMethod.POST)
	//@ApiOperation("Creating a Stock")
	public ResponseEntity<StockDto> createStock(@RequestBody StockDto stock, @RequestHeader("auth-token") String token) {
		
		StockDto createStock=stockService.createStock(stock, token);
			
			/*if(createStock==null) {
				return new ResponseEntity<Stock>(HttpStatus.BAD_REQUEST);
			}*/
			return new ResponseEntity<StockDto>(HttpStatus.CREATED);
	}

	@DeleteMapping("/{stockId}")
	// @RequestMapping(value="/stocks",method=RequestMethod.DELETE)
	//@ApiOperation("Delete  Stock based on stockId")
	public String deleteStock(@PathVariable("stockId") long stockId) {
		return stockService.deleteStock(stockId);
	}

	@PutMapping("/{stockId}")
//	@RequestMapping(value="/stocks/{stockId}",method=RequestMethod.GET)
	//@ApiOperation("updating Stock")
	public StockDto updateStock(@PathVariable int stockId, @RequestBody StockDto stock) {

		return stockService.updateStock(stockId, stock);
	}

	// Delete all stocks
	@DeleteMapping
	//@ApiOperation("Deleting all Stocks")
	public ResponseEntity<String> deleteAllStocks() {

		stockService.deleteAllStocks();

		return new ResponseEntity<String>("All stocks deleted successfullyy", HttpStatus.OK);
	}

}