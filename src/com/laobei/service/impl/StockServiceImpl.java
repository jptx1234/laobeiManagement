package com.laobei.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.laobei.dao.service.StockMapper;
import com.laobei.entity.StockEntity;
import com.laobei.service.StockService;

@Service
public class StockServiceImpl implements StockService {

	private static Logger logger =  LoggerFactory.getLogger(StockServiceImpl.class);
	
	@Resource
	private StockMapper stockMapper;
	/**
	 * 对库存进行查询所有 
	 */
	@Override
	public List<StockEntity> listAllStock(StockEntity stockEntity) {
		try {
			return stockMapper.listAllStock(stockEntity);
		} catch (Exception e) {
			logger.error("insertStock error");
			try {
				throw new Exception("", e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 对库存进行新增
	 */
	@Override
	public void insertStock(StockEntity stockEntity) {
		try {
			stockMapper.insertStock(stockEntity);
		} catch (Exception e) {
			logger.error("insertStock error");
			try {
				throw new Exception("", e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void deleteStock(Long[] ids) {
		stockMapper.deleteStock(ids);
	}

	@Override
	public StockEntity getEntity(Long id) {
		return stockMapper.getEntity(id);
	}

	@Override
	public void updateStock(StockEntity stockEntity) {
		stockMapper.updateStock(stockEntity);
	}

	@Override
	public HSSFWorkbook exportStock(List<StockEntity> list) {
		String[] excelHeader = { "名称", "数量", "单位", "单价" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("库存表");
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
			StockEntity stockEntity = list.get(i);
			row.createCell(0).setCellValue(stockEntity.getName());
			row.createCell(1).setCellValue(stockEntity.getTotalCount());
			row.createCell(2).setCellValue(stockEntity.getUnit());
			row.createCell(3).setCellValue(stockEntity.getUnitPrice());
		}
		return wb;
	}

}
