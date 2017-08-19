package com.laobei.service.impl;

import java.util.List;

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
	 * 根据条件查找菜谱
	 */
	@Override
	public List<CookBookEneity> listAllCookBook(CookBookEneity cookBookEneity) {
		return cookBookMapper.listAllCookBook(cookBookEneity);
	}
	/**
	 * 对菜谱进行删除
	 */
	@Override
	public void deleteCookBook(Long[] ids) {
		cookBookMapper.deleteCookBook(ids);
	}

}
