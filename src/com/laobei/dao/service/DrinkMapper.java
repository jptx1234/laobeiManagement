package com.laobei.dao.service;

import java.util.List;

import com.laobei.entity.CookBookEneity;
import com.laobei.entity.DrinkEntity;

public interface DrinkMapper {
	//对酒水单进行添加
	void insertDrink(DrinkEntity drinkEntity);
	//根据条件对酒水单进行查询
	List<CookBookEneity> listAllDrink(DrinkEntity drinkEntity);
	//根据选中的id删除对应的酒水单
	void deleteDrink(Long[] ids);
}
