package com.laobei.dao.service;

import java.util.List;

import com.laobei.entity.ConsumeEntity;

public interface ConsumeMapper {

	void insertConsume(ConsumeEntity consumeEntity);

	List<ConsumeEntity> listAllConsume();

}
