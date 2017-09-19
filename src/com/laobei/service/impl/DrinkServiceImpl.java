package com.laobei.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.laobei.dao.service.DrinkMapper;
import com.laobei.entity.DrinkEntity;
import com.laobei.service.DrinkService;
import com.laobei.utils.ExcelUtils;

@Service
public class DrinkServiceImpl implements DrinkService {

	@Resource
	private DrinkMapper drinkMapper;

	@Override
	public void addDrink(DrinkEntity drinkEntity) {
		drinkMapper.insertDrink(drinkEntity);
	}

	@Override
	public void deleteDrink(Long[] ids) {
		drinkMapper.deleteDrink(ids);
	}

	@Override
	public List<DrinkEntity> listAllDrink(DrinkEntity drinkEntity, int currPage, int pageSize) {
		int start = (currPage - 1) * pageSize;
		
		return drinkMapper.listAllDrink(drinkEntity, start, pageSize);
	}

	@Override
	public HSSFWorkbook exportDrink(List<DrinkEntity> list) {
		String[] excelHeader = { "酒水名", "备注", "价格"};   
		
		List<String> titleList = new ArrayList<>();
		titleList.addAll(Arrays.asList(excelHeader));
		
		List<List<String>> contentList = new ArrayList<>();
		for (DrinkEntity drinkEntity : list) {
			List<String> row = new ArrayList<>();
			contentList.add(row);
			row.add(drinkEntity.getDrinkName());
			row.add(drinkEntity.getDrinkComment());
			row.add(String.valueOf(drinkEntity.getDrinkPrice()));
		}
		
		HSSFWorkbook wb = ExcelUtils.exportExcel("酒水单表", titleList, contentList);
        return wb;    
    }

	@Override
	public int totalCount(DrinkEntity drinkEntity) {
		return drinkMapper.count(drinkEntity);
	}    
	
	
}
