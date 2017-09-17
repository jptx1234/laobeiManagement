package com.laobei.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.laobei.entity.DrinkEntity;

public interface DrinkService {

	void addDrink(DrinkEntity drinkEntity);

	void deleteDrink(Long[] ids);

	List<DrinkEntity> listAllDrink(DrinkEntity drinkEntity);

	HSSFWorkbook exportDrink(List<DrinkEntity> list);

}
