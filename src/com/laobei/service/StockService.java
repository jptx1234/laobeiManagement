package com.laobei.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.laobei.entity.StockEntity;

public interface StockService {
	//根据条件对库存进行查询
	List<StockEntity> listAllStock(StockEntity stockEntity);
	//对库存进行添加
	void insertStock(StockEntity stockEntity);
	
	void deleteStock(Long[] ids);

	StockEntity getEntity(Long id);
	//对库存进行修改
	void updateStock(StockEntity stockEntity);

	HSSFWorkbook exportStock(List<StockEntity> list);
	
}
