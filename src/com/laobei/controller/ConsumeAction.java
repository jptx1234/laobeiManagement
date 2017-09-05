package com.laobei.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.laobei.entity.ConsumeEntity;
import com.laobei.entity.CookBookEneity;
import com.laobei.entity.StockEntity;
import com.laobei.service.ConsumeService;
import com.laobei.service.CookBookService;
import com.laobei.service.DrinkService;
import com.laobei.service.StockService;

@Controller
@RequestMapping("/consume")
public class ConsumeAction {

	@Resource
	private ConsumeService consumeService;
	@Resource
	private CookBookService cookBookService;
	@Resource 
	private DrinkService drinkSerivce;
	@Resource
	private StockService stockService;
	
	@RequestMapping("/toAdd.do")
	public String toAdd(Model model) {
		
		
		
		
		return "/consume/add";
	}
	
	@RequestMapping("/listAll.do")
	public String listAll(Model model) {
		List<Map<String, Object>> list = consumeService.listAllConsume();
		model.addAttribute("list", list);
		
		return "/consume/list";
	}
	
	
	@RequestMapping(value="/listAllStockJSON.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String listAllStockJSON(HttpServletResponse response) {
		List<CookBookEneity> listAllCookBook = cookBookService.listAllCookBook(new CookBookEneity());
//		List<DrinkEntity> listAllDrink = drinkSerivce.listAllDrink(new DrinkEntity());
		StockEntity stockEntity = new StockEntity();
		stockEntity.setStockType("酒水");
		List<StockEntity> jiushuiStock = stockService.listAllStock(stockEntity);
		stockEntity.setStockType("食材");
		List<StockEntity> shicaiStock = stockService.listAllStock(stockEntity);
		stockEntity.setStockType("调料");
		List<StockEntity> tiaoliaoStock = stockService.listAllStock(stockEntity);
		stockEntity.setStockType("易耗品");
		List<StockEntity> yihaopinStock = stockService.listAllStock(stockEntity);
		stockEntity.setStockType("固定资产");
		List<StockEntity> gudingzichanStock = stockService.listAllStock(stockEntity);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("cp", listAllCookBook);
		jsonObject.put("js", jiushuiStock);
		jsonObject.put("sc", shicaiStock);
		jsonObject.put("tl", tiaoliaoStock);
		jsonObject.put("yhp", yihaopinStock);
		jsonObject.put("gdzc", gudingzichanStock);
		
		JSONObject gongziObject = new JSONObject();
		gongziObject.put("name", "工资");
		gongziObject.put("id", 1);
		JSONArray gongziArray = new JSONArray();
		gongziArray.add(gongziObject);
		jsonObject.put("gz", gongziArray);
		
		JSONObject guanliObject = new JSONObject();
		guanliObject.put("name", "管理费用");
		guanliObject.put("id", 1);
		JSONArray guanliArray = new JSONArray();
		guanliArray.add(guanliObject);
		jsonObject.put("glfy", guanliArray);
		
		JSONObject yiwaiObject = new JSONObject();
		yiwaiObject.put("name", "意外支出");
		yiwaiObject.put("id", 1);
		JSONArray yiwaiArray = new JSONArray();
		yiwaiArray.add(yiwaiObject);
		jsonObject.put("ywzc", yiwaiArray);
		
		return jsonObject.toJSONString();
	}
	
	@RequestMapping("/addConsume.do")
	public String addConsume(String cp, String js, String sc, String tl, String yhp, String gdzc, String gz, String glfy, String ywzc) {
		List<ConsumeEntity> consumeEntities = new ArrayList<>();
		
		List<ConsumeEntity> cookbookList = parseTotalString(cp, "菜品");
		consumeEntities.addAll(cookbookList);
		
		
		consumeEntities.addAll(parseTotalString(js, "酒水"));
		consumeEntities.addAll(parseTotalString(sc, "食材"));
		consumeEntities.addAll(parseTotalString(tl, "调料"));
		consumeEntities.addAll(parseTotalString(yhp, "易耗品"));
		consumeEntities.addAll(parseTotalString(gdzc, "固定资产"));
		consumeEntities.addAll(parseTotalString(gz, "工资"));
		consumeEntities.addAll(parseTotalString(glfy, "管理费用"));
		consumeEntities.addAll(parseTotalString(ywzc, "意外支出"));
		
		List<ConsumeEntity> cpConsumeList = new ArrayList<>();
		for (ConsumeEntity cookbookConsume : cookbookList) {
			CookBookEneity cookBookModel = new CookBookEneity();
			cookBookModel.setName(cookbookConsume.getName());
			List<CookBookEneity> cookBookEneities = cookBookService.listAllCookBook(cookBookModel);
			if (cookBookEneities != null && cookBookEneities.size() > 0) {
				CookBookEneity cookBookEneity = cookBookEneities.get(0);
				String primaryMaterial = cookBookEneity.getPrimaryMaterial();
				String auxiliaryMaterial = cookBookEneity.getAuxiliaryMaterial();
				String seasoning = cookBookEneity.getSeasoning();
				cpConsumeList.addAll(parseTotalString(primaryMaterial, "食材"));
				cpConsumeList.addAll(parseTotalString(auxiliaryMaterial, "食材"));
				cpConsumeList.addAll(parseTotalString(seasoning, "调料"));
			}
		}
		
		for(int i = 0; i < cpConsumeList.size(); i++) {
			for (int j = 0; j < consumeEntities.size(); j++) {
				ConsumeEntity cpCon = cpConsumeList.get(i);
				ConsumeEntity allCon = consumeEntities.get(j);
				if (cpCon.getStockType().equals(allCon.getStockType()) && cpCon.getName().equals(allCon.getName())) {
					allCon.setCount(cpCon.getCount() + allCon.getCount());
				}
			}
		}
		
		consumeService.insertAndUpdate(consumeEntities);
		
		return "redirect:/consume/listAll.do";
	}
	
	private List<ConsumeEntity> parseTotalString(String string, String stockType) {
		List<ConsumeEntity> result = new ArrayList<>();
		if (StringUtils.isEmpty(string)) {
			return result;
		}
		String[] entities = string.split(",");
		for (String consumeString : entities) {
			if (StringUtils.isNotEmpty(consumeString)) {
				String consumeStringWithoutBlank = consumeString.trim();
				String[] props = consumeStringWithoutBlank.split("*");
				if (props.length==2) {
					try {
						ConsumeEntity consumeEntity = new ConsumeEntity();
						consumeEntity.setName(props[0]);
						consumeEntity.setCount(Float.parseFloat(props[1]));
						consumeEntity.setStockType(stockType);
						consumeEntity.setCreateTime(new Date());
						result.add(consumeEntity);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if (props.length == 1) {
					try {
						ConsumeEntity consumeEntity = new ConsumeEntity();
						consumeEntity.setName(props[0]);
						consumeEntity.setCount(1F);
						consumeEntity.setStockType(stockType);
						consumeEntity.setCreateTime(new Date());
						result.add(consumeEntity);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		
		return result;
	}
}
