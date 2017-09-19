package com.laobei.controller;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laobei.entity.DrinkEntity;
import com.laobei.service.DrinkService;
import com.laobei.utils.CommonUtils;
import com.laobei.utils.Constants;

@Controller
@RequestMapping("/drink")
public class DrinkAction {
	@Resource
	private DrinkService drinkService;
	
	/**
	 * 导出酒水单
	 */
	  @RequestMapping(value = "/exportDrink.do")    
	    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
	    		Model model,DrinkEntity drinkEntity)     
	    throws Exception {    
	        List<DrinkEntity> list = drinkService.listAllDrink(drinkEntity, 0, 0);
	        HSSFWorkbook wb = drinkService.exportDrink(list);    
	        response.setContentType("application/vnd.ms-excel");    
	        response.setHeader("Content-disposition", "attachment;filename=jiushuidan.xls");    
	        OutputStream ouputStream = response.getOutputStream();    
	        wb.write(ouputStream);    
	        ouputStream.flush();    
	        ouputStream.close();    
	   }    
	
	/**
	 * 添加酒水单
	 * @param cookBookEneity
	 * @return
	 */
	@RequestMapping("/addDrink.do")
	public String addDrink(DrinkEntity drinkEntity) {
		drinkService.addDrink(drinkEntity);
		
		return "redirect:findAllDrink.do";
		
	}
	/**
	 * 删除酒水单
	 */
	@RequestMapping("/deletesDrink.do")
	public String deleteCookBooks(Long ids[],Model model) {
		drinkService.deleteDrink(ids);
		return "redirect:findAllDrink.do";
	}
	
	/**
	 * 根据条件对酒水单进行查询
	 */
	@RequestMapping("/findAllDrink.do")
	public String findAll(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "currPage", required = false) String currPageString,
			@RequestParam(value = "pageSize", required = false) String pageSizeString, Model model) {
		
		int currPage = CommonUtils.parseInt(currPageString, 1);
		int pageSize = CommonUtils.parseInt(pageSizeString, Constants.PAGE_SIZE);
		
		DrinkEntity drinkEntity = new DrinkEntity();
		if(StringUtils.isNotBlank(name)) {
			drinkEntity.setDrinkName(name);
			model.addAttribute("name", name);
		}
		List<DrinkEntity> allDrink = drinkService.listAllDrink(drinkEntity, currPage, pageSize);
		int totalCount = drinkService.totalCount(drinkEntity);
		
		model.addAttribute("list", allDrink);
		model.addAttribute("currPage", currPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", (totalCount + pageSize - 1) / pageSize);
		return "drink/list";
	}
	
	/**
	 * 去增加酒水单页面
	 */
	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "drink/add";
	}
}
