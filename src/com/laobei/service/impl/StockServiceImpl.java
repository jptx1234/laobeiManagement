package com.laobei.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.laobei.dao.service.StockMapper;
import com.laobei.entity.StockEntity;
import com.laobei.service.StockService;
import com.laobei.utils.ExcelUtils;

@Service
public class StockServiceImpl implements StockService {

	private static Logger logger =  LoggerFactory.getLogger(StockServiceImpl.class);
	
	@Resource
	private StockMapper stockMapper;
	/**
	 * 对库存进行查询所有 
	 */
	@Override
	public List<StockEntity> listAllStock(StockEntity stockEntity, int currPage, int pageSize) {
		int start = (currPage - 1) * pageSize;
		try {
			return stockMapper.listAllStock(stockEntity, start, pageSize);
		} catch (Exception e) {
			logger.error("listStock error");
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
		StockEntity stockEntity = new StockEntity();
		stockEntity.setId(id);
		return stockMapper.getEntity(stockEntity);
	}

	@Override
	public void updateStock(StockEntity stockEntity) {
		stockMapper.updateStock(stockEntity);
	}

	@Override
	public HSSFWorkbook exportStock(List<StockEntity> list) {
		String[] excelHeader = { "名称", "数量", "单位", "单价" };
		
		List<String> titleList = new ArrayList<>();
		titleList.addAll(Arrays.asList(excelHeader));
		
		List<List<String>> contentList = new ArrayList<>();
		for (StockEntity stockEntity : list) {
			List<String> row = new ArrayList<>();
			contentList.add(row);
			row.add(stockEntity.getName());
			row.add(String.valueOf(stockEntity.getTotalCount()));
			row.add(stockEntity.getUnit());
			row.add(String.valueOf(stockEntity.getUnitPrice()));
		}
		
		String stockType = list.size() > 0 ? list.get(0).getStockType() : "";

		HSSFWorkbook wb = ExcelUtils.exportExcel(stockType+"库存表", titleList, contentList);
		return wb;
	}
	@Override
	public int totalCount(StockEntity stockEntity) {
		return stockMapper.count(stockEntity);
	}

}
