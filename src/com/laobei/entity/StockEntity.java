package com.laobei.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author ll
 *	库存类
 */
public class StockEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String unit;        //单位
	
	private Float unitPrice;    //单价
	
	private Float totalCount;
	
	private Date updateTime;
	
	private String stockType;
	
	
	public Float getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Float totalCount) {
		this.totalCount = totalCount;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
