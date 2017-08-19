package com.laobei.dao.service;

import java.util.List;

import com.laobei.entity.CookBookEneity;

public interface CookBookMapper {
	//对菜谱进行添加
	void insertCookingBook(CookBookEneity cookBookEneity);
	//根据条件对菜谱进行查询
	List<CookBookEneity> listAllCookBook(CookBookEneity cookBookEneity);
	//根据选中的id删除对应的菜谱
	void deleteCookBook(Long[] ids);
}
