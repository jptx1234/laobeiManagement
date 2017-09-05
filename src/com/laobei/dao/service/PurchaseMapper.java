package com.laobei.dao.service;

import java.util.List;

import com.laobei.entity.ConsumeEntity;
import com.laobei.entity.PurchaseEntity;

public interface PurchaseMapper {

	void insertPurchase(PurchaseEntity purchaseEntity);

	List<PurchaseEntity> listAllPurchase();

}
