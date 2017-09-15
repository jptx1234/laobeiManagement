package com.laobei.dao.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.laobei.entity.PurchaseEntity;

public interface PurchaseMapper {

	void insertPurchase(PurchaseEntity purchaseEntity);

	List<PurchaseEntity> listAllPurchase();

	List<PurchaseEntity> listByRange(@Param("beginTime")String beginTime, @Param("endTime")String endTime);

	Float getRangeSum(@Param("beginTime")String beginTime, @Param("endTime")String endTime);

}
