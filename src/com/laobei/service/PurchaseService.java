package com.laobei.service;

import java.util.List;
import java.util.Map;

import com.laobei.entity.ConsumeEntity;
import com.laobei.entity.PurchaseEntity;

public interface PurchaseService {

	void insertAndUpdate(List<PurchaseEntity> purchaseEntities);

	List<Map<String, Object>> listAllPurchase();

	List<PurchaseEntity> listByDate(String date);

	Float getDaySum(String date);

}
