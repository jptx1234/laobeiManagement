package com.laobei.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author ll
 *
 */
public class ConsumeEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Float count;
	
	private String stockType;
	
	private Date createTime;
	

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

	public Float getCount() {
		return count;
	}

	public void setCount(Float count) {
		this.count = count;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
