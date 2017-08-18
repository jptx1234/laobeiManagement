package com.laobei.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonAction {

	
	@RequestMapping("/index.do")
	public String toIndex(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/user/login.do";
		}
		
		return "index";
	}
}
