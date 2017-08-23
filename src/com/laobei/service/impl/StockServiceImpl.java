package com.laobei.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laobei.dao.service.StockMapper;
import com.laobei.entity.CookBookEneity;
import com.laobei.entity.StockEntity;
import com.laobei.service.StockService;
@Service
@Transactional
public class StockServiceImpl implements StockService{
	
	private static Logger logger =  LoggerFactory.getLogger(StockServiceImpl.class);
	
	@Resource
	private StockMapper stockMapper;
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
	/**
	 * 对库存进行查询所有
	 */
	@Override
	public List<CookBookEneity> listAllStock(StockEntity stockEntity) {
		try {
			List<CookBookEneity> listAllStock = stockMapper.listAllStock(stockEntity);
			return listAllStock;
		} catch (Exception e) {
			logger.error("listAllStock error");
			try {
				throw new Exception("", e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void updateStock(StockEntity stockEntity) {
		
	}

}
