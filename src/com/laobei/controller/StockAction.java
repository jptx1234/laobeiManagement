package com.laobei.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laobei.entity.CookBookEneity;
import com.laobei.entity.StockEntity;
import com.laobei.service.StockService;

@Controller
public class StockAction {
	@Resource
	private StockService stockService;
	
	/**
	 * 添加库存
	 * @param cookBookEneity
	 * @return
	 */
	@RequestMapping("/addStock.do")
	public String cookBookList(StockEntity stockEntity) {
		stockService.insertStock(stockEntity);
		
		return "redirect:findAllStock.do";
		
	}
	/**
	 * 根据条件对库存进行查询
	 */
	@RequestMapping("/findAllStock.do")
	public String findAll(String name,Model model) {
		StockEntity stockEntity = new StockEntity();
		if(name != null) {
			stockEntity.setName(name);
			model.addAttribute("name", name);
		}
		List<CookBookEneity> allStock = stockService.listAllStock(stockEntity);
		model.addAttribute("list", allStock);
		return "stock/list";
	}
	/**
	 * 根据id对库存进行修改
	 */
	@RequestMapping("/editStock.do")
	public String editStock(StockEntity stockEntity) {
		stockService.updateStock(stockEntity);
		return "redirect:findAllStock.do";
	}
}
