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

import com.laobei.dao.service.ConsumeMapper;
import com.laobei.dao.service.StockMapper;
import com.laobei.entity.ConsumeEntity;
import com.laobei.entity.StockEntity;
import com.laobei.service.ConsumeService;
import com.laobei.utils.ExcelUtils;

@Service
@Transactional
public class ConsumeServiceImpl implements ConsumeService {
	
	@Resource
	private ConsumeMapper consumeMapper;
	@Resource
	private StockMapper stockMapper;

	@Override
	public void insertAndUpdate(List<ConsumeEntity> consumeEntities) {
		StockEntity stockModel = new StockEntity();
		for (ConsumeEntity consumeEntity : consumeEntities) {
			consumeMapper.insertConsume(consumeEntity);
			stockModel.setName(consumeEntity.getName());
			stockModel.setStockType(consumeEntity.getStockType());
			StockEntity stockEntity = stockMapper.getEntity(stockModel);
			if (stockEntity != null) {
				Float stockCount = stockEntity.getTotalCount();
				Float consumeCount = consumeEntity.getCount();
				if (stockCount != null && consumeCount != null) {
					stockEntity.setTotalCount(stockCount - consumeCount);
					stockMapper.updateStock(stockEntity);
				}
			}
		}
		
	}

	@Override
	public List<Map<String, Object>> listAllConsume(String beginDateStr, String endDateStr) {
		String beginTime = beginDateStr + " 00:00:00";
		String endTime = endDateStr + " 23:59:59";
		
		List<ConsumeEntity> list = consumeMapper.listByRange(beginTime, endTime);
		List<Map<String, Object>> result = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDate = null;
		StringBuilder sb = new StringBuilder();
		for (ConsumeEntity consume : list) {
			String dateString = sdf.format(consume.getCreateTime());
			if (StringUtils.isEmpty(lastDate)) {
				lastDate = dateString;
			}
			if (lastDate.equals(dateString)) {
				sb.append(consume.getName());
				sb.append(" * ");
				sb.append(consume.getCount());
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
		String content = null;
		if (sb.length() > 60) {
			content = sb.substring(0, 60);
			content = content + "...";
		}else {
			content = sb.toString();
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (StringUtils.isNotEmpty(lastDate) && sb.length() != 0) {
			resultMap.put("date", lastDate);
			resultMap.put("content", content);
			
			result.add(resultMap);
		}
		
		return result;
	}

	@Override
	public List<ConsumeEntity> listByDate(String date) {
		String beginTime = date + " 00:00:00";
		String endTime = date + " 23:59:59";
		List<ConsumeEntity> consumeList = consumeMapper.listByRange(beginTime, endTime);
		
		return consumeList;
	}

	@Override
	public Float getDaySum(String date) {
		String beginTime = date + " 00:00:00";
		String endTime = date + " 23:59:59";
		
		Float sum = consumeMapper.getRangeSum(beginTime, endTime);
		
		return sum;
	}

	@Override
	public int totalCount() {
		return consumeMapper.count();
	}

	@Override
	public HSSFWorkbook exportConsume(String beginDate, String endDate) {
		String beginTime = beginDate + " 00:00:00";
		String endTime = endDate + " 23:59:59";
		
		List<ConsumeEntity> list = consumeMapper.listByRange(beginTime, endTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String, List<String>> dateConsumeMap = new TreeMap<>();
		
		StringBuilder sb = new StringBuilder();
		for (ConsumeEntity consume : list) {
			String dateString = sdf.format(consume.getCreateTime());
			List<String> consumeListTheDay = dateConsumeMap.get(dateString);
			if (consumeListTheDay == null) {
				consumeListTheDay = new ArrayList<>();
				dateConsumeMap.put(dateString, consumeListTheDay);
			}
			
			sb.delete(0, sb.length());
			sb.append(consume.getName());
			sb.append(" * ");
			sb.append(consume.getCount());
			sb.append(" * ");
			sb.append(consume.getUnit());
			
			consumeListTheDay.add(sb.toString());
		}
		
		List<List<String>> contentList = new ArrayList<>();
		List<String> row = new ArrayList<>();
		dateConsumeMap.forEach(new BiConsumer<String, List<String>>() {

			@Override
			public void accept(String t, List<String> u) {
				row.add(t);
				row.addAll(u);
				contentList.add(row);
			}
			
		});
		
		String[] excelHeader = {"日期", "内容"};
		
		return ExcelUtils.exportExcel("消耗表", Arrays.asList(excelHeader), contentList);
	}

}
