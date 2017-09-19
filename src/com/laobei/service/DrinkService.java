package com.laobei.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.laobei.entity.DrinkEntity;

public interface DrinkService {

	void addDrink(DrinkEntity drinkEntity);

	void deleteDrink(Long[] ids);

	//如果不分页，把pageSize设为0即可
	List<DrinkEntity> listAllDrink(DrinkEntity drinkEntity, int currPage, int pageSize);

	HSSFWorkbook exportDrink(List<DrinkEntity> list);

	int totalCount(DrinkEntity drinkEntity);

}
