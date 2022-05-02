package com.zensar.stockapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel("This is Stock Model")
@Entity(name = "MyStock")
@Table(name = "Stock")

/*
 * @NamedQueries(value = {
 * 
 * @NamedQuery(name = "Stock.findStockByItsNameAndPrice", query =
 * "FROM MyStock s where s.name=?1 and where s.price=?2") }) //@NamedQuery(name
 * = "Stock.findStockByItsName", query = "FROM MyStock s where s.name=?1")
 * 
 * @NamedNativeQuery(name = "Stock.findStockByItsName", query =
 * "select * from stock where name=?1", resultClass=Stock.class)
 */
public class Stock {

	//@ApiModelProperty("StockId is of Integer type")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "Id")
	private long stockId;
	private String name;
	private String marketName;
	private double price;
}
