package com.itchengye.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itchengye.service.UserService;

@Controller
public class CheckUsernameController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="checkUsername",method=RequestMethod.POST)
	
	public void register(HttpServletResponse response,
							@RequestParam(value="username") String username) {
		boolean isExist = service.checkUsername(username);
		String json = "{\"isExist\":"+isExist+"}";
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
