package com.laobei.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laobei.dao.service.ConsumeMapper;
import com.laobei.dao.service.StockMapper;
import com.laobei.entity.ConsumeEntity;
import com.laobei.entity.StockEntity;
import com.laobei.service.ConsumeService;

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
			Float stockCount = stockEntity.getTotalCount();
			Float consumeCount = consumeEntity.getCount();
			if (stockCount != null && consumeCount != null) {
				stockEntity.setTotalCount(stockCount - consumeCount);
				stockMapper.updateStock(stockEntity);
			}
		}
		
	}

	@Override
	public List<Map<String, Object>> listAllConsume() {
		List<ConsumeEntity> list = consumeMapper.listAllConsume();
		List<Map<String, Object>> result = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDate = null;
		StringBuilder sb = new StringBuilder();
		for (ConsumeEntity consume : list) {
			String dateString = sdf.format(consume.getCreateTime());
			if (StringUtils.isEmpty(lastDate)) {
				lastDate = dateString;
			}else if (lastDate.equals(dateString)) {
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
		
		return result;
	}

}
