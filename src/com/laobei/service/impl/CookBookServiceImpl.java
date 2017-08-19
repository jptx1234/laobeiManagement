package com.laobei.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laobei.dao.service.CookBookMapper;
import com.laobei.entity.CookBookEneity;
import com.laobei.service.CookBookService;
@Service
@Transactional
public class CookBookServiceImpl implements CookBookService{
	@Resource
	private CookBookMapper cookBookMapper;
	/**
	 * 增加菜谱
	 */
	@Override
	public void addCookBook(CookBookEneity cookBookEneity) {
		cookBookMapper.insertCookingBook(cookBookEneity);
	}
	/**
	 * 根据菜名查找菜谱
	 */
	@Override
	public CookBookEneity findCookBook(String name) {
		if(StringUtils.isBlank(name)) {
			return null;
		}
		return cookBookMapper.findCookingBook(name);
	}

}
