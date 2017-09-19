package com.laobei.dao.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.laobei.entity.DrinkEntity;

public interface DrinkMapper {
	//对酒水单进行添加
	void insertDrink(DrinkEntity drinkEntity);
	//根据条件对酒水单进行查询
	List<DrinkEntity> listAllDrink(@Param("entity")DrinkEntity drinkEntity, @Param("start")int start, @Param("pageSize")int pageSize);
	//根据选中的id删除对应的酒水单
	void deleteDrink(Long[] ids);
	//查询此条件对应的酒水单的数量
	int count(DrinkEntity drinkEntity);
}
