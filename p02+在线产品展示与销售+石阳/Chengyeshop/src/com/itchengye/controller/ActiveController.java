package com.itchengye.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itchengye.service.UserService;

@Controller
public class ActiveController {
	
	@Autowired
	private UserService service;
	@RequestMapping(value="active",method=RequestMethod.GET)
	public String active(HttpServletRequest request,HttpServletResponse response,
						@RequestParam(value="activeCode") String activeCode) {
		
		service.active(activeCode);
		
		return "redirect:login.jsp";
		//response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}
}
