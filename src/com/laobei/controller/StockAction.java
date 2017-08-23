package com.laobei.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laobei.entity.StockEntity;
import com.laobei.service.StockService;

@Controller
@RequestMapping("/stock")
public class StockAction {

	@Resource
	private StockService stockService;

	/**
	 * 列出库存
	 */
	@RequestMapping("/findAllStock.do")
	public String findAll(@RequestParam(required = true) String stockType, String name, Model model) {
		StockEntity stockEntity = new StockEntity();
		if (stockType != null) {
			stockEntity.setStockType(stockType);
			model.addAttribute("stockType", stockType);
		}
		if (name != null) {
			stockEntity.setName(name);
			model.addAttribute("name", name);
		}
		List<StockEntity> allStock = stockService.listAllStock(stockEntity);
		model.addAttribute("list", allStock);

		return "stock/list";
	}

	/**
	 * 新增库存
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/addStock.do")
	public String addStock(StockEntity stockEntity) throws UnsupportedEncodingException {
		if (stockEntity.getUpdateTime() == null) {
			stockEntity.setUpdateTime(new Date());
		}
		stockService.insertStock(stockEntity);

		return "redirect:findAllStock.do?stockType=" + URLEncoder.encode(stockEntity.getStockType(), "UTF-8");
	}

	/**
	 * 修改库存
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/updateStock.do")
	public String updateStock(StockEntity stockEntity) throws UnsupportedEncodingException {
		stockService.updateStock(stockEntity);

		return "redirect:findAllStock.do?stockType=" + URLEncoder.encode(stockEntity.getStockType(), "UTF-8");
	}

	/**
	 * 删除库存
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/deleteStock.do")
	public String deleteStock(Long[] ids, @RequestParam(required = true) String stockType) throws UnsupportedEncodingException {
		stockService.deleteStock(ids);

		return "redirect:findAll.do?stockType=" + URLEncoder.encode(stockType, "UTF-8");
	}

	/**
	 * 查询单个，去修改页面
	 */
	@RequestMapping("/getEntity.do")
	public String getEntity(@RequestParam(required = true) Long id, Model model) {
		StockEntity stockEntity = stockService.getEntity(id);
		model.addAttribute("entity", stockEntity);

		return "stock/edit";
	}

	/**
	 * 去增加页面
	 */
	@RequestMapping("/toAdd.do")
	public String toAdd(@RequestParam(required = true) String stockType, Model model) {
		model.addAttribute("stockType", stockType);

		return "stock/edit";
	}

	/**
	 * 导出库存表
	 */
	@RequestMapping(value = "/exportStock.do")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Model model,
			StockEntity stockEntity) throws Exception {
		List<StockEntity> list = stockService.listAllStock(stockEntity);
		HSSFWorkbook wb = stockService.exportStock(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=kucun.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

}
