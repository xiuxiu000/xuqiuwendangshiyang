package com.itchengye.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
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
import com.itchengye.utils.CommonsUtils;
import com.itchengye.utils.MailUtils;

@Controller
public class RegisterController {

	@Autowired
	private UserService service;
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String register(HttpServletRequest request,HttpServletResponse response,
							@RequestParam(value="username") String username,
							@RequestParam(value="password") String password,
							@RequestParam(value="email") String email,
							@RequestParam(value="uname") String name,
							@RequestParam(value="sex") String sex,
							@RequestParam(value="birthday") String birthday,
							HttpSession session) {
		
		User user = new User();
		
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setName(name);
		user.setSex(sex);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(birthday);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		user.setBirthday(date);
		
		//private String uid;
		user.setUid(CommonsUtils.getUUID());
		//private String telephone;
		user.setTelephone(null);
		//private int state;//是否激活
		user.setState(0);
		//private String code;//激活码
		String activeCode = CommonsUtils.getUUID();
		user.setCode(activeCode);
		
		
		boolean isRegisterSuccess = service.regist(user);
		
		//是否注册成功
		if(isRegisterSuccess){
			//发送激活邮件
			String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
					+ "<a href='http://localhost:8080/ChengyeShop/active?activeCode="+activeCode+"'>"
							+ "http://localhost:8080/ChengyeShop/active?activeCode="+activeCode+"</a>";
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			
			//跳转到注册成功页面
			return "redirect:registerSuccess.jsp";
			//response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
		}else{
			//跳转到失败的提示页面
			return "redirect:registerFail.jsp";
			//response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
		
		
		
	}
	
}
