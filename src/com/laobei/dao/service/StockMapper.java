package com.laobei.dao.service;

import java.util.List;

import com.laobei.entity.CookBookEneity;
import com.laobei.entity.StockEntity;

public interface StockMapper {
		//对库存进行添加
		void insertStock(StockEntity stockEntity);
		//根据条件对库存进行查询
		List<StockEntity> listAllStock(StockEntity stockEntity);
		//对库存进行修改
		void updateStock(StockEntity stockEntity);
		void deleteStock(Long[] ids);
		StockEntity getEntity(StockEntity stockEntity);
}
