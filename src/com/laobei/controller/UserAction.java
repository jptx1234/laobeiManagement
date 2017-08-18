package com.laobei.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laobei.entity.UserLogin;
import com.laobei.service.UserService;

@Controller
@RequestMapping("/user")
public class UserAction {

	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do")
	public String login(String username, String password, HttpSession session, Model model) {
		UserLogin userLogin = userService.login(username, password);
		if (userLogin == null) {
			if (username != null) {
				model.addAttribute("msg", "账号或密码错误");
			}
			
			return "/login";
		}
		session.setAttribute("user", userLogin);
		
		
		return "redirect:/index.do";
	}
	
}
