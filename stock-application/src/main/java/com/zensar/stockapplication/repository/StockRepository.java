package com.zensar.stockapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zensar.stockapplication.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{
	
	//@Query("FROM MyStock s where s.name=?1")
	@Query(value="select * from stock where name=:name",nativeQuery = true)
	List<Stock> findStockByItsName(@Param("name")String name);
	
	//@Query("FROM MyStock s where s.name=?1 and s.price=?2")
	@Query(value="select * from stock where name=:name and price=:price", nativeQuery = true)
	List<Stock> findStockByItsNameAndPrice(@Param("name")String name, @Param("price")double price);

}
