package com.laobei.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.laobei.entity.CookBookEneity;

public interface CookBookService {
	
	void addCookBook(CookBookEneity cookBookEneity);
	
	List<CookBookEneity> listAllCookBook(CookBookEneity cookBookEneity);
	
	void deleteCookBook(Long[] ids);
	
	HSSFWorkbook exportCookBook(List<CookBookEneity> list);
}
