package com.laobei.entity;

import java.io.Serializable;

/**
 * 
 * @author ll
 *
 */
public class DrinkEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String drinkName;
	
	private String drinkComment;
	
	private Float drinkPrice = 0.0f;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDrinkName() {
		return drinkName;
	}

	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}

	public String getDrinkComment() {
		return drinkComment;
	}

	public void setDrinkComment(String drinkComment) {
		this.drinkComment = drinkComment;
	}

	public Float getDrinkPrice() {
		return drinkPrice;
	}

	public void setDrinkPrice(Float drinkPrice) {
		this.drinkPrice = drinkPrice;
	}

	
	
	
	
}
