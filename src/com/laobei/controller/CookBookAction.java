package com.laobei.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laobei.entity.CookBookEneity;
import com.laobei.service.CookBookService;

@Controller
public class CookBookAction {
	@Resource
	private CookBookService cookBookService;
	
	@RequestMapping("/cookBook.do")
	public String cookBookList(CookBookEneity cookBookEneity) {
		cookBookService.addCookBook(cookBookEneity);
		
		return null;
		
	}
}
