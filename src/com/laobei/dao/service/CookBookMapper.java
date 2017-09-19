package com.laobei.dao.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.laobei.entity.CookBookEneity;

public interface CookBookMapper {
	//对菜谱进行添加
	void insertCookingBook(CookBookEneity cookBookEneity);
	//根据条件对菜谱进行查询
	List<CookBookEneity> listAllCookBook(@Param("entity")CookBookEneity cookBookEneity, @Param("start")int start, @Param("size")int pageSize);
	//根据选中的id删除对应的菜谱
	void deleteCookBook(Long[] ids);
	//查询有多少条记录
	int count(CookBookEneity cookBookEneity);
}
