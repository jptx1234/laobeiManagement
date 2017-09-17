package com.laobei.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laobei.entity.ConsumeEntity;
import com.laobei.entity.PurchaseEntity;
import com.laobei.service.ConsumeService;
import com.laobei.service.CookBookService;
import com.laobei.service.PurchaseService;
import com.laobei.service.StockService;

@Controller
@RequestMapping("/chart")
public class ChartAction {

	@Resource
	private StockService stockService;
	@Resource
	private ConsumeService consumeService;
	@Resource
	private PurchaseService purchaseService;
	@Resource
	private CookBookService cookBookService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	@RequestMapping("/consumeAndPurchase.do")
	public String consumeAndPurchase(@RequestParam(value="date", required=false)String date, Model model) {
		date = validateDate(date);
		
		List<ConsumeEntity> consumeList = consumeService.listByDate(date);
		List<PurchaseEntity> purchaseList = purchaseService.listByDate(date);
		
		List<Map<String, Object>> list = getConsumePurchaseUnion(consumeList, purchaseList);
		
		String[] typeList = {"食材", "酒水", "调料", "易耗品", "固定资产"};
		
		model.addAttribute("date", date);
		model.addAttribute("list", list);
		model.addAttribute("typeList", typeList);
		
		return "/chart/list";
	}
	
	private List<Map<String, Object>> getConsumePurchaseUnion(List<ConsumeEntity> consumeList, List<PurchaseEntity> purchaseList) {
		List<PurchaseEntity> tmpPurchaseList = new ArrayList<>(purchaseList);
		List<Map<String, Object>> result = new ArrayList<>();
		//把消耗全部放进去
		for(ConsumeEntity consumeEntity : consumeList) {
			Map<String, Object> resultMap = new HashMap<>();
			if (StringUtils.isNotEmpty(consumeEntity.getStockType()) && StringUtils.isNotEmpty(consumeEntity.getName())) {
				resultMap.put("con", consumeEntity);
				result.add(resultMap);
			}
		}
		//把消耗与采购对应起来
		for(int i=0; i < tmpPurchaseList.size(); i++) {
			PurchaseEntity purchaseEntity = tmpPurchaseList.get(i);
			if (StringUtils.isEmpty(purchaseEntity.getStockType()) || StringUtils.isEmpty(purchaseEntity.getName())) {
				continue;
			}
			for (Map<String, Object> map : result) {
				ConsumeEntity consumeEntity = (ConsumeEntity) map.get("con");
				if (consumeEntity.getStockType().equals(purchaseEntity.getStockType()) && consumeEntity.getName().equals(purchaseEntity.getName())) {
					map.put("pur", purchaseEntity);
					tmpPurchaseList.remove(i);
					i--;
				}
			}
		}
		//把剩余的采购放进去
		for (PurchaseEntity purchaseEntity : tmpPurchaseList) {
			Map<String, Object> resultMap = new HashMap<>();
			if (StringUtils.isNotEmpty(purchaseEntity.getStockType()) && StringUtils.isNotEmpty(purchaseEntity.getName())) {
				resultMap.put("pur", purchaseEntity);
				result.add(resultMap);
			}
		}
		
		return result;
	}
	
	
	@RequestMapping("/operatingChart.do")
	private String operatingChart(@RequestParam(value = "date", required=false)String date, Model model) {
		date = validateDate(date);
		List<ConsumeEntity> consumeList = consumeService.listByDate(date);
		
		List<PurchaseEntity> purchaseList = purchaseService.listByDate(date);
		
		Map<String, Float> purchasePriceMap = new HashMap<>();
		for (PurchaseEntity purchaseEntity : purchaseList) {
			purchasePriceMap.put(purchaseEntity.getStockType() + "," + purchaseEntity.getName(), purchaseEntity.getUnitPrice());
		}
		
		List<ConsumeEntity> result = new ArrayList<>();
//		CookBookEneity cookBookExample = new CookBookEneity();
//		String numReg="(\\d+(\\.?\\d+)?)"; 
//		Pattern pattern = Pattern.compile(numReg); 
		for (ConsumeEntity consumeEntity : consumeList) {
			if (!"菜品".equals(consumeEntity.getStockType())) {
				continue;
			}
			result.add(consumeEntity);
			/*Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("type", "cp");
			resultMap.put("name", consumeEntity.getName());
			resultMap.put("count", consumeEntity.getCount());*/
			
			/*cookBookExample.setName(consumeEntity.getName());
			List<CookBookEneity> cookbookList = cookBookService.listAllCookBook(cookBookExample);
			if (cookbookList == null || cookbookList.size() == 0) {
				continue;
			}
			CookBookEneity cookBook = cookbookList.get(0);
			String primaryMaterial = cookBook.getPrimaryMaterial();
			String auxiliaryMaterial = cookBook.getAuxiliaryMaterial();
			String seasoning = cookBook.getSeasoning();
			Float cost = 0F;
			if (StringUtils.isNotEmpty(primaryMaterial)) {
				String[] mas = primaryMaterial.split(",");
				for (String string : mas) {
					if (StringUtils.isNotEmpty(string)) {
						Matcher matcher = pattern.matcher(string);
						if (matcher.find()) {
							
						}
					}
				}
			}*/
		}
		
		Float consumeSum = consumeService.getDaySum(date);
		Float purchaseSum = purchaseService.getDaySum(date);
		
		model.addAttribute("date", date);
		model.addAttribute("result", result);
		model.addAttribute("consumeSum", consumeSum == null ? 0 : consumeSum);
		model.addAttribute("purchaseSum", purchaseSum == null ? 0 : purchaseSum);
		
		return "/chart/operatingChart";
	}
	
	
	private String validateDate(String date) {
		try {
			sdf.parse(date);
		} catch (Exception e) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
			date = sdf.format(calendar.getTime());
		}
		
		return date;
	}
	
	
}
