package com.laobei.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.laobei.dao.service.DrinkMapper;
import com.laobei.entity.CookBookEneity;
import com.laobei.entity.DrinkEntity;
import com.laobei.service.DrinkService;

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
	public List<DrinkEntity> listAllDrink(DrinkEntity drinkEntity) {
		return drinkMapper.listAllDrink(drinkEntity);
	}

	@Override
	public HSSFWorkbook exportDrink(List<DrinkEntity> list) {
		String[] excelHeader = { "酒水名", "备注", "价格"};    
        HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("酒水单表");
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
    
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);    
            sheet.autoSizeColumn(i);    
        }    
    
        for (int i = 0; i < list.size(); i++) {    
            row = sheet.createRow(i + 1);    
            DrinkEntity drinkEntity = list.get(i);
            row.createCell(0).setCellValue(drinkEntity.getDrinkName());
            row.createCell(1).setCellValue(drinkEntity.getDrinkComment());
            row.createCell(2).setCellValue(drinkEntity.getDrinkPrice());
        }    
        return wb;    
    }    
	
	
}
