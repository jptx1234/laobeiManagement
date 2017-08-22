package com.laobei.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laobei.dao.service.CookBookMapper;
import com.laobei.entity.CookBookEneity;
import com.laobei.service.CookBookService;
@Service
@Transactional
public class CookBookServiceImpl implements CookBookService{
	@Resource
	private CookBookMapper cookBookMapper;
	/**
	 * 增加菜谱
	 */
	@Override
	public void addCookBook(CookBookEneity cookBookEneity) {
		cookBookMapper.insertCookingBook(cookBookEneity);
	}
	/**
	 * 根据条件查找菜谱
	 */
	@Override
	public List<CookBookEneity> listAllCookBook(CookBookEneity cookBookEneity) {
		return cookBookMapper.listAllCookBook(cookBookEneity);
	}
	/**
	 * 对菜谱进行删除
	 */
	@Override
	public void deleteCookBook(Long[] ids) {
		cookBookMapper.deleteCookBook(ids);
	}
	/**
	 * 导出excel表格
	 */
	@Override
	public HSSFWorkbook exportCookBook(List<CookBookEneity> list) {
		String[] excelHeader = { "菜名", "主材", "辅材","调料","制作方法","制作时间","价格"};    
		        HSSFWorkbook wb = new HSSFWorkbook();    
		        HSSFSheet sheet = wb.createSheet("菜谱表");
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
		            CookBookEneity cookBookEneity = list.get(i);
		            row.createCell(0).setCellValue(cookBookEneity.getName());    
		            row.createCell(1).setCellValue(cookBookEneity.getPrimaryMaterial());    
		            row.createCell(2).setCellValue(cookBookEneity.getAuxiliaryMaterial()); 
		            row.createCell(3).setCellValue(cookBookEneity.getSeasoning());
		            row.createCell(4).setCellValue(cookBookEneity.getCookingMethod());
		            row.createCell(5).setCellValue(cookBookEneity.getCookingTime());
		            row.createCell(6).setCellValue(cookBookEneity.getPrice());
		        }    
		        return wb;    
		    }    
	
}
