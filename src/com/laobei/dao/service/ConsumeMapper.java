package com.laobei.dao.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.laobei.entity.ConsumeEntity;

public interface ConsumeMapper {

	void insertConsume(ConsumeEntity consumeEntity);

	List<ConsumeEntity> listAllConsume(@Param("start")int start, @Param("pageSize")int pageSize);

	List<ConsumeEntity> listByRange(@Param("beginTime")String beginTime, @Param("endTime")String endTime);

	Float getRangeSum(@Param("beginTime")String beginTime, @Param("endTime")String endTime);

	int count();

}
