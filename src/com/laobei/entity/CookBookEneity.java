package com.laobei.entity;

import java.io.Serializable;

/**
 * 
 * @author ll
 *
 */
public class CookBookEneity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String primaryMaterial;
	
	private String auxiliaryMaterial;
	
	private String seasoning;
	
	private String cookingMethod;
	
	private int cookingTime = 0;
	
	private Float price = 0.0f;

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
	
	
	public String getPrimaryMaterial() {
		return primaryMaterial;
	}

	public void setPrimaryMaterial(String primaryMaterial) {
		this.primaryMaterial = primaryMaterial;
	}

	public String getAuxiliaryMaterial() {
		return auxiliaryMaterial;
	}

	public void setAuxiliaryMaterial(String auxiliaryMaterial) {
		this.auxiliaryMaterial = auxiliaryMaterial;
	}

	public String getSeasoning() {
		return seasoning;
	}

	public void setSeasoning(String seasoning) {
		this.seasoning = seasoning;
	}

	public String getCookingMethod() {
		return cookingMethod;
	}

	public void setCookingMethod(String cookingMethod) {
		this.cookingMethod = cookingMethod;
	}

	public int getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	
}
