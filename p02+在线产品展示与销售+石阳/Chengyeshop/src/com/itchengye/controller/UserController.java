package com.itchengye.controller;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itchengye.domain.User;
import com.itchengye.service.UserService;

@Controller
@RequestMapping(value="user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	//用户注销
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletResponse response,
						HttpSession session) {
		
		session.removeAttribute("user");
		//cookie中存的值删掉
		Cookie cookie_username = new Cookie("cookie_username","");
		cookie_username.setMaxAge(10*60);
		Cookie cookie_password = new Cookie("cookie_password","");
		cookie_password.setMaxAge(0);

		response.addCookie(cookie_username);
		response.addCookie(cookie_password);

		return "redirect:/login.jsp";
		//response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)	
	public String login(HttpServletRequest request,HttpServletResponse response,
					@RequestParam(value="checkCode") String checkCode_client,
					@RequestParam(value="username") String username,
					@RequestParam(value="password") String password,
						HttpSession session) {
		//2.获得生成图片的文字的验证码
		String checkCode_session = (String) session.getAttribute("checkcode_session");
		//对比页面生成的图片和文字验证码是否一致
				if(!checkCode_session.equals(checkCode_client)) {
					request.setAttribute("loginInfo", "您的验证码不正确");
					return "login";
					//request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
				
				
				
				//将用户名和密码传递给service层
				User user = null;
				try {
					user = service.login(username,password);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				//判断用户是否登录成功 user是否是null
				if(user!=null){
					//将user对象存到session中
					session.setAttribute("user", user);

					//重定向到首页
					return "forward:../default.jsp";
					//response.sendRedirect(request.getContextPath()+"/index.jsp");
				}else{
					request.setAttribute("loginInfo", "用户名或密码错误");
					return "login";
					//request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
	}
}
