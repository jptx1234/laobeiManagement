package com.laobei.controller;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laobei.entity.CookBookEneity;
import com.laobei.entity.StockEntity;
import com.laobei.service.CookBookService;
import com.laobei.service.StockService;
import com.laobei.utils.CommonUtils;
import com.laobei.utils.Constants;

@Controller
@RequestMapping("/cookBook")
public class CookBookAction {
	@Resource
	private CookBookService cookBookService;
	@Resource
	private StockService stockService;

	/**
	 * 导出菜谱表
	 */
	@RequestMapping(value = "/exportCookBook.do")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Model model,
			CookBookEneity cookBookEneity) throws Exception {
		List<CookBookEneity> list = cookBookService.listAllCookBook(cookBookEneity, 0, 0);
		HSSFWorkbook wb = cookBookService.exportCookBook(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=caipu.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	/**
	 * 添加菜谱
	 * 
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
	public String deleteCookBooks(Long ids[], Model model) {
		cookBookService.deleteCookBook(ids);
		return "redirect:/cookBook/findAllCookBook.do";
	}

	/**
	 * 根据条件对菜谱进行查询
	 */
	@RequestMapping("/findAllCookBook.do")
	public String findAll(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "currPage", required = false) String currPageString,
			@RequestParam(value = "pageSize", required = false) String pageSizeString, Model model) {

		int currPage = CommonUtils.parseInt(currPageString, 1);
		int pageSize = CommonUtils.parseInt(pageSizeString, Constants.PAGE_SIZE);

		CookBookEneity cookBookEneity = new CookBookEneity();
		if (name != null) {
			cookBookEneity.setName(name);
			model.addAttribute("name", name);
		}
		List<CookBookEneity> allCookBook = cookBookService.listAllCookBook(cookBookEneity, currPage, pageSize);
		int totalCount = cookBookService.totalCount(cookBookEneity);

		model.addAttribute("list", allCookBook);
		model.addAttribute("currPage", currPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", (totalCount + pageSize - 1) / pageSize);
		return "cookBook/list";
	}

	/**
	 * 去增加菜谱页面
	 */
	@RequestMapping("/toAdd.do")
	public String toAdd(Model model) {
		StockEntity exampleStock = new StockEntity();
		exampleStock.setStockType("食材");
		List<StockEntity> shicaiStock = stockService.listAllStock(exampleStock, 0, 0);
		exampleStock.setStockType("调料");
		List<StockEntity> tiaoliaoStock = stockService.listAllStock(exampleStock, 0, 0);

		model.addAttribute("shicai", shicaiStock);
		model.addAttribute("tiaoliao", tiaoliaoStock);

		return "cookBook/add";
	}
}
