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
import com.laobei.entity.CookBookEneity;
import com.laobei.entity.PurchaseEntity;
import com.laobei.entity.StockEntity;
import com.laobei.service.CookBookService;
import com.laobei.service.DrinkService;
import com.laobei.service.PurchaseService;
import com.laobei.service.StockService;

@Controller
@RequestMapping("/purchase")
public class PurchaseAction {

	@Resource
	private PurchaseService purchaseService;
	@Resource
	private CookBookService cookBookService;
	@Resource 
	private DrinkService drinkSerivce;
	@Resource
	private StockService stockService;
	
	@RequestMapping("/toAdd.do")
	public String toAdd(Model model) {
		
		
		
		
		return "/purchase/add";
	}
	
	@RequestMapping("/listAll.do")
	public String listAll(Model model) {
		List<Map<String, Object>> list = purchaseService.listAllPurchase();
		model.addAttribute("list", list);
		
		return "/purchase/list";
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
	
	@RequestMapping("/addPurchase.do")
	public String addPurchase(String js, String sc, String tl, String yhp, String gdzc, String gz, String glfy, String ywzc) {
		List<PurchaseEntity> purchaseEntities = new ArrayList<>();
		
		purchaseEntities.addAll(parseTotalString(js, "酒水"));
		purchaseEntities.addAll(parseTotalString(sc, "食材"));
		purchaseEntities.addAll(parseTotalString(tl, "调料"));
		purchaseEntities.addAll(parseTotalString(yhp, "易耗品"));
		purchaseEntities.addAll(parseTotalString(gdzc, "固定资产"));
		purchaseEntities.addAll(parseTotalString(gz, "工资"));
		purchaseEntities.addAll(parseTotalString(glfy, "管理费用"));
		purchaseEntities.addAll(parseTotalString(ywzc, "意外支出"));
		
		
		purchaseService.insertAndUpdate(purchaseEntities);
		
		return "redirect:/purchase/listAll.do";
	}
	
	private List<PurchaseEntity> parseTotalString(String string, String stockType) {
		List<PurchaseEntity> result = new ArrayList<>();
		if (StringUtils.isEmpty(string)) {
			return result;
		}
		String[] entities = string.split(",");
		for (String consumeString : entities) {
			if (StringUtils.isNotEmpty(consumeString)) {
				String purchaseStringWithoutBlank = consumeString.trim();
				String[] props = purchaseStringWithoutBlank.split("*");
				if (props.length==2) {
					try {
						PurchaseEntity purchaseEntity = new PurchaseEntity();
						purchaseEntity.setName(props[0]);
						purchaseEntity.setCount(Float.parseFloat(props[1]));
						purchaseEntity.setStockType(stockType);
						purchaseEntity.setCreateTime(new Date());
						result.add(purchaseEntity);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if (props.length == 1) {
					try {
						PurchaseEntity purchaseEntity = new PurchaseEntity();
						purchaseEntity.setName(props[0]);
						purchaseEntity.setCount(1F);
						purchaseEntity.setStockType(stockType);
						purchaseEntity.setCreateTime(new Date());
						result.add(purchaseEntity);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		
		return result;
	}
}
