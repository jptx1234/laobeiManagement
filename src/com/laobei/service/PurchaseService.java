package com.laobei.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.laobei.entity.PurchaseEntity;

public interface PurchaseService {

	void insertAndUpdate(List<PurchaseEntity> purchaseEntities);

	List<Map<String, Object>> listAllPurchase(String beginDate, String endDate);

	List<PurchaseEntity> listByDate(String date);

	Float getDaySum(String date);

	HSSFWorkbook exportPurchase(String beginDate, String endDate);

}
