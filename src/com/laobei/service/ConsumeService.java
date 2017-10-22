package com.laobei.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.laobei.entity.ConsumeEntity;

public interface ConsumeService {

	void insertAndUpdate(List<ConsumeEntity> consumeEntities);

	List<Map<String, Object>> listAllConsume(String beginDateStr, String endDateStr);

	List<ConsumeEntity> listByDate(String date);

	Float getDaySum(String date);

	int totalCount();

	HSSFWorkbook exportConsume(String beginDate, String endDate);

}
