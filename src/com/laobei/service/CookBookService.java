package com.laobei.service;

import com.laobei.entity.CookBookEneity;

public interface CookBookService {
	
	void addCookBook(CookBookEneity cookBookEneity);
	
	CookBookEneity findCookBook(String name);
}
