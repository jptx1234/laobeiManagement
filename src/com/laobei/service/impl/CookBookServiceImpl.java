package com.laobei.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laobei.dao.service.CookBookMapper;
import com.laobei.entity.CookBookEneity;
import com.laobei.service.CookBookService;
import com.laobei.utils.ExcelUtils;


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
		List<String> titleList = new ArrayList<>();
		titleList.addAll(Arrays.asList(excelHeader));
		
		List<List<String>> contentList = new ArrayList<>();
		for (CookBookEneity cookBookEneity : list) {
			List<String> row = new ArrayList<>();
			contentList.add(row);
			row.add(cookBookEneity.getName());
			row.add(cookBookEneity.getPrimaryMaterial());
			row.add(cookBookEneity.getAuxiliaryMaterial());
			row.add(cookBookEneity.getSeasoning());
			row.add(cookBookEneity.getCookingMethod());
			row.add(String.valueOf(cookBookEneity.getCookingTime()));
			row.add(String.valueOf(cookBookEneity.getPrice()));
		}
		
		HSSFWorkbook workbook = ExcelUtils.exportExcel("菜谱表", titleList, contentList);
		return workbook;
	}    
	
}
