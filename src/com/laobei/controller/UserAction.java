package com.laobei.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.laobei.entity.UserLogin;
import com.laobei.service.UserService;

@Controller
@RequestMapping("/user")
public class UserAction {

	@Resource
	private UserService userService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String toLogin() {
		return "/login";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(String username, String password, HttpSession session, Model model) {
		UserLogin userLogin = userService.login(username, password);
		if (userLogin == null) {
			if (username != null) {
				model.addAttribute("msg", "账号或密码错误");
			}
			model.addAttribute("username", username);
			model.addAttribute("password", password);
			
			return "/login";
		}
		session.setAttribute("user", userLogin);
		
		
		return "redirect:/index.do";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/user/login.do";
	}
	
	@RequestMapping(value="/changePwd.do", produces = "application/json; charset=utf-8")
	public @ResponseBody String changePwd(String oldPwd, String newPwd, HttpSession session) {
		JSONObject result = new JSONObject();
		UserLogin user = (UserLogin) session.getAttribute("user");
		String username = user.getUsername();
		UserLogin userLogin = userService.login(username, oldPwd);
		if (userLogin == null) {
			result.put("status", 401);
			result.put("msg", "当前密码不正确");
		}else {
			userLogin.setPassword(newPwd);
			userService.updateOrAddUser(userLogin);
			logout(session);
			result.put("status", 200);
			result.put("msg", "密码修改成功");
		}
		
		return result.toJSONString();
	}
	
	
	@RequestMapping("/admin/listAll.do")
	public String listAll(Model model) {
		List<UserLogin> list = userService.listAll();
		model.addAttribute("list", list);
		
		return "/user/list";
	}
	
	@RequestMapping("/admin/updateOrAddUser.do")
	public String updateOrAddUser(UserLogin user) {
		userService.updateOrAddUser(user);
		
		return "redirect:/user/admin/listAll.do";
	}
	
}
