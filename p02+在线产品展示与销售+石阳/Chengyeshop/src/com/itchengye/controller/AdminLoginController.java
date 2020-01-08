package com.itchengye.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itchengye.domain.Admin;
import com.itchengye.service.AdminService;
import com.itchengye.service.UserService;

@Controller
public class AdminLoginController {
	
	@Autowired
	private AdminService service;
	
	@RequestMapping(value="adminLogin",method=RequestMethod.POST)
	public String adminLogin(Model model,HttpServletRequest request,HttpServletResponse response,
							@RequestParam(value="adminname") String adminname,
							@RequestParam(value="password") String password) {
		
		Admin admin =null;
		try {
			admin = service.login(adminname,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(admin!=null){
			//*******************************
			return "redirect:admin/home.jsp";
			//response.sendRedirect(request.getContextPath()+"/admin/home.jsp");
		}else{
			model.addAttribute("loginError", "闲人免进");
			//request.setAttribute("loginError", "闲人免进");
			return "forward:/admin/index.jsp";
			//request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
		}
		
	}

}
