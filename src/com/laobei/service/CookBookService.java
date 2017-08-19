package com.laobei.service;

import java.util.List;

import com.laobei.entity.CookBookEneity;

public interface CookBookService {
	
	void addCookBook(CookBookEneity cookBookEneity);
	
	List<CookBookEneity> listAllCookBook(CookBookEneity cookBookEneity);
	
	void deleteCookBook(Long[] ids);
}
