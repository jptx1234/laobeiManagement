package com.laobei.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.laobei.service.StockService;

@Controller
public class StockAction {
	@Resource
	private StockService stockService;
	
	
}
