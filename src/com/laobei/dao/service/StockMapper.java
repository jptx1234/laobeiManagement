package com.laobei.dao.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.laobei.entity.StockEntity;

public interface StockMapper {
		//对库存进行添加
		void insertStock(StockEntity stockEntity);
		//根据条件对库存进行查询
		List<StockEntity> listAllStock(@Param("entity")StockEntity stockEntity, @Param("start")int start, @Param("pageSize")int pageSize);
		//对库存进行修改
		void updateStock(StockEntity stockEntity);
		void deleteStock(Long[] ids);
		StockEntity getEntity(StockEntity stockEntity);
		//计算符合条件的总数
		int count(StockEntity stockEntity);
}
