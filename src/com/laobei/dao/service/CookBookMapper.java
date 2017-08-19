package com.laobei.dao.service;

import java.util.List;

import com.laobei.entity.CookBookEneity;

public interface CookBookMapper {
	void insertCookingBook(CookBookEneity cookBookEneity);
	CookBookEneity findCookingBook(String name);
	List<CookBookEneity> listAllCookBook(CookBookEneity cookBookEneity);
}
