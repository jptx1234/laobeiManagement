package com.laobei.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laobei.entity.CookBookEneity;
import com.laobei.service.CookBookService;

@Controller
@RequestMapping("/cookBook")
public class CookBookAction {
	@Resource
	private CookBookService cookBookService;
	
	/**
	 * 添加菜谱
	 * @param cookBookEneity
	 * @return
	 */
	@RequestMapping("/addCookBook.do")
	public String cookBookList(CookBookEneity cookBookEneity) {
		cookBookService.addCookBook(cookBookEneity);
		
		return "redirect:findAllCookBook.do";
		
	}
	/**
	 * 删除菜谱
	 */
	@RequestMapping("/deletesCookBook.do")
	public String deleteCookBooks(Long ids[],Model model) {
		cookBookService.deleteCookBook(ids);
		return "redirect:/cookBook/findAllCookBook.do";
	}
	
	/**
	 * 根据条件对菜谱进行查询
	 */
	@RequestMapping("/findAllCookBook.do")
	public String findAll(String name,Model model) {
		CookBookEneity cookBookEneity = new CookBookEneity();
		if(name != null) {
			cookBookEneity.setName(name);
			model.addAttribute("name", name);
		}
		List<CookBookEneity> allCookBook = cookBookService.listAllCookBook(cookBookEneity);
		model.addAttribute("list", allCookBook);
		return "cookBook/list";
	}
	
	/**
	 * 去增加菜谱页面
	 */
	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "cookBook/add";
	}
}
