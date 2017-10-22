package com.laobei.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.laobei.entity.ConsumeEntity;
import com.laobei.entity.CookBookEneity;
import com.laobei.entity.PurchaseEntity;
import com.laobei.entity.StockEntity;
import com.laobei.service.ConsumeService;
import com.laobei.service.CookBookService;
import com.laobei.service.DrinkService;
import com.laobei.service.PurchaseService;
import com.laobei.service.StockService;
import com.laobei.utils.CommonUtils;

@Controller
@RequestMapping("/purchase")
public class PurchaseAction {
	
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

	@Resource
	private PurchaseService purchaseService;
	@Resource
	private CookBookService cookBookService;
	@Resource 
	private DrinkService drinkSerivce;
	@Resource
	private StockService stockService;
	@Resource
	private ConsumeService consumeService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat yearSdf = new SimpleDateFormat("yyyy");
	SimpleDateFormat monthSdf = new SimpleDateFormat("MM");
	SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	Map<String, String> typeStringMap = new HashMap<>();
	
	public PurchaseAction() {
		typeStringMap.put("菜品", "cp");
		typeStringMap.put("酒水", "js");
		typeStringMap.put("食材", "sc");
		typeStringMap.put("调料" , "tl");
		typeStringMap.put("易耗品", "yhp");
		typeStringMap.put("固定资产", "gdzc");
		typeStringMap.put("工资", "gz");
		typeStringMap.put("管理费用", "glfy");
		typeStringMap.put("意外支出", "ywzc");
	}
	
	@RequestMapping("/toAdd.do")
	public String toAdd(Model model) {
		
		
		return "/purchase/add";
	}
	
	@RequestMapping(value="/getYesterday.do", produces="application/json; charset=utf-8")
	public @ResponseBody String getYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		String dateString = sdf.format(date);
		List<ConsumeEntity> cunsumeYesterday = consumeService.listByDate(dateString);
		
		JSONObject result = new JSONObject();
		for (ConsumeEntity consumeEntity : cunsumeYesterday) {
			String type = typeStringMap.get(consumeEntity.getStockType());
			if (type != null) {
				JSONArray thisType = (JSONArray) result.get(type);
				if (thisType == null) {
					thisType = new JSONArray();
					result.put(type, thisType);
				}
				
				thisType.add(consumeEntity);
			}
		}
		
		return result.toJSONString();
	}
	
	@RequestMapping("/listAll.do")
	public String listAll(@RequestParam(value = "beginDate", required = false) String beginDate,
			@RequestParam(value = "endDate", required = false) String endDate, Model model) {
		

		String[] dates = CommonUtils.dealDateRange(beginDate, endDate);
		beginDate = dates[0];
		endDate = dates[1];
		
		List<Map<String, Object>> list = purchaseService.listAllPurchase(beginDate, endDate);
		model.addAttribute("list", list);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		
		return "/purchase/list";
	}
	
	
	@RequestMapping(value="/listAllStockJSON.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String listAllStockJSON() {
		List<CookBookEneity> listAllCookBook = cookBookService.listAllCookBook(new CookBookEneity(), 0, 0);
//		List<DrinkEntity> listAllDrink = drinkSerivce.listAllDrink(new DrinkEntity());
		StockEntity stockEntity = new StockEntity();
		stockEntity.setStockType("酒水");
		List<StockEntity> jiushuiStock = stockService.listAllStock(stockEntity, 0, 0);
		stockEntity.setStockType("食材");
		List<StockEntity> shicaiStock = stockService.listAllStock(stockEntity, 0, 0);
		stockEntity.setStockType("调料");
		List<StockEntity> tiaoliaoStock = stockService.listAllStock(stockEntity, 0, 0);
		stockEntity.setStockType("易耗品");
		List<StockEntity> yihaopinStock = stockService.listAllStock(stockEntity, 0, 0);
		stockEntity.setStockType("固定资产");
		List<StockEntity> gudingzichanStock = stockService.listAllStock(stockEntity, 0, 0);
		
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
	public String addPurchase(@RequestParam(value = "img", required = false) MultipartFile[] files, String js, String sc, String tl, String yhp, String gdzc, String gz, String glfy, String ywzc, HttpSession session) {
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
		
		Date date = new Date();
		String year = yearSdf.format(date);
		String month = monthSdf.format(date);
		String parentPath = session.getServletContext().getRealPath("/") + "/upload/" + year + "/" + month + "/";
		File parentDir = new File(parentPath);
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		
		if (files != null && parentDir.isDirectory()) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				if (file.isEmpty()) {
					continue;
				}
				String ext = FilenameUtils.getExtension(file.getOriginalFilename());
				String fileName = parentPath + yyyyMMdd.format(date) + "_" + UUID.randomUUID().toString().replaceAll("-", "")+"."+ext;
				try {
					file.transferTo(new File(fileName));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		
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
				String[] props = purchaseStringWithoutBlank.split("\\*");
				if (props.length==4) {
					try {
						PurchaseEntity purchaseEntity = new PurchaseEntity();
						purchaseEntity.setName(props[0]);
						purchaseEntity.setCount(Float.parseFloat(props[1]));
						purchaseEntity.setUnit(props[2]);
						purchaseEntity.setUnitPrice(Float.parseFloat(props[3]));
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
	
	@RequestMapping("/generatePurchaseList.do")
	public String generateConsumeList(String date, Model model, HttpSession session) {
		List<PurchaseEntity> listByDate = purchaseService.listByDate(date);
		Map<String, Map<String, Float>> resultMap = new HashMap<>();
		for (PurchaseEntity purchaseEntity : listByDate) {
			String stockType = purchaseEntity.getStockType();
			Map<String, Float> nameAndCountMap = resultMap.get(stockType);
			if (nameAndCountMap == null) {
				nameAndCountMap = new HashMap<>();
				resultMap.put(stockType, nameAndCountMap);
			}
			String consumeName = purchaseEntity.getName();
			String key = consumeName + "（单位：" + purchaseEntity.getUnit() + "）";
			Float nowCount = nameAndCountMap.get(key);
			if (nowCount == null) {
				nowCount = 0.0F;
			}
			nowCount += purchaseEntity.getCount();
			nameAndCountMap.put(key, nowCount);
		}
		
		if (StringUtils.isNotEmpty(date)) {
			String[] dateStrings = date.split("-");
			String year = dateStrings[0];
			String month = dateStrings[1];
			String parentPath = session.getServletContext().getRealPath("/") + "/upload/" + year + "/" + month + "/";
			final String yyyyMMddString = date.replaceAll("-", "");
			File parentPathFile = new File(parentPath);
			if (parentPathFile.exists() && parentPathFile.isDirectory()) {
				File[] listFiles = parentPathFile.listFiles(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						if (name.startsWith(yyyyMMddString)) {
							return true;
						}
						return false;
					}
				});
				
				List<String> files = new ArrayList<>();
				for (File file : listFiles) {
					files.add(file.getName());
				}
				
				model.addAttribute("imgPath", year + "/" + month);
				model.addAttribute("imgFiles", files);
			}
		}
		
		model.addAttribute("date", date);
		model.addAttribute("result", resultMap);
		return "/purchase/generatePurchaseList";
	}
	
	@RequestMapping("/exportPurchase.do")
	public void exportPurchase(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model,
			@RequestParam(value = "beginDate", required = false) String beginDate,
			@RequestParam(value = "endDate", required = false) String endDate) throws Exception {
		
		String[] dates = CommonUtils.dealDateRange(beginDate, endDate);
		beginDate = dates[0];
		endDate = dates[1];
		
		String imgBasePath = session.getServletContext().getRealPath("/") + "/upload/";
		HSSFWorkbook wb = purchaseService.exportPurchase(beginDate, endDate, imgBasePath);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=caigou_"+beginDate+"_"+endDate+".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
}
