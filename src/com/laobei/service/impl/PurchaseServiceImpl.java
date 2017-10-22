package com.laobei.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laobei.dao.service.PurchaseMapper;
import com.laobei.dao.service.StockMapper;
import com.laobei.entity.ConsumeEntity;
import com.laobei.entity.PurchaseEntity;
import com.laobei.entity.StockEntity;
import com.laobei.service.PurchaseService;
import com.laobei.utils.ExcelUtils;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {
	
	@Resource
	private PurchaseMapper purchaseMapper;
	@Resource
	private StockMapper stockMapper;

	@Override
	public void insertAndUpdate(List<PurchaseEntity> purchaseEntities) {
		StockEntity stockModel = new StockEntity();
		for (PurchaseEntity purchaseEntity : purchaseEntities) {
			purchaseMapper.insertPurchase(purchaseEntity);
			stockModel.setName(purchaseEntity.getName());
			stockModel.setStockType(purchaseEntity.getStockType());
			StockEntity stockEntity = stockMapper.getEntity(stockModel);
			Float stockCount = stockEntity.getTotalCount();
			Float consumeCount = purchaseEntity.getCount();
			if (stockCount != null && consumeCount != null) {
				stockEntity.setTotalCount(stockCount + consumeCount);
				stockEntity.setUnit(purchaseEntity.getUnit());
				stockMapper.updateStock(stockEntity);
			}
		}
		
	}

	@Override
	public List<Map<String, Object>> listAllPurchase(String beginDate, String endDate) {
		String beginTime = beginDate + " 00:00:00";
		String endTime = endDate + " 23:59:59";
		List<PurchaseEntity> list = purchaseMapper.listByRange(beginTime, endTime);
		List<Map<String, Object>> result = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDate = null;
		StringBuilder sb = new StringBuilder();
		for (PurchaseEntity purchase : list) {
			String dateString = sdf.format(purchase.getCreateTime());
			if (StringUtils.isEmpty(lastDate)) {
				lastDate = dateString;
			}
			if (lastDate.equals(dateString)) {
				sb.append(purchase.getName());
				sb.append(" * ");
				sb.append(purchase.getCount());
				sb.append(", ");
			}else {
				if (StringUtils.isNotEmpty(lastDate) && sb.length() != 0) {
					Map<String, Object> resultMap = new HashMap<>();
					resultMap.put("date", lastDate);
					String content = null;
					if (sb.length() > 60) {
						content = sb.substring(0, 60);
						content = content + "...";
					}else {
						content = sb.toString();
					}
					
					resultMap.put("content", content);
					
					result.add(resultMap);
					
					lastDate = null;
					sb.delete(0, sb.length());
				}
			}
		}
		
		if (StringUtils.isNotEmpty(lastDate) && sb.length() != 0) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("date", lastDate);
			String content = null;
			if (sb.length() > 60) {
				content = sb.substring(0, 60);
				content = content + "...";
			}else {
				content = sb.toString();
			}
			
			resultMap.put("content", content);
			
			result.add(resultMap);
			
		}
		
		
		return result;
	}

	@Override
	public List<PurchaseEntity> listByDate(String date) {
		String beginTime = date + " 00:00:00";
		String endTime = date + " 23:59:59";
		List<PurchaseEntity> purchaseList = purchaseMapper.listByRange(beginTime, endTime);
		
		return purchaseList;
	}

	@Override
	public Float getDaySum(String date) {
		String beginTime = date + " 00:00:00";
		String endTime = date + " 23:59:59";
		
		Float sum = purchaseMapper.getRangeSum(beginTime, endTime);
		
		return sum;
	}

	@Override
	public HSSFWorkbook exportPurchase(String beginDate, String endDate) {
		String beginTime = beginDate + " 00:00:00";
		String endTime = endDate + " 23:59:59";
		
		List<PurchaseEntity> list = purchaseMapper.listByRange(beginTime, endTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String, List<String>> datePurchaseMap = new TreeMap<>();
		
		StringBuilder sb = new StringBuilder();
		for (PurchaseEntity purchase : list) {
			String dateString = sdf.format(purchase.getCreateTime());
			List<String> purchaseListTheDay = datePurchaseMap.get(dateString);
			if (purchaseListTheDay == null) {
				purchaseListTheDay = new ArrayList<>();
				datePurchaseMap.put(dateString, purchaseListTheDay);
			}
			
			sb.delete(0, sb.length());
			sb.append(purchase.getName());
			sb.append(" * ");
			sb.append(purchase.getCount());
			sb.append(" * ");
			sb.append(purchase.getUnit());
			
			purchaseListTheDay.add(sb.toString());
		}
		
		List<List<String>> contentList = new ArrayList<>();
		List<String> row = new ArrayList<>();
		datePurchaseMap.forEach(new BiConsumer<String, List<String>>() {

			@Override
			public void accept(String t, List<String> u) {
				row.add(t);
				row.addAll(u);
				contentList.add(row);
			}
			
		});
		
		String[] excelHeader = {"日期", "内容"};
		
		return ExcelUtils.exportExcel("采购表", Arrays.asList(excelHeader), contentList);
	}

}
