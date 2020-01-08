package com.itchengye.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.itchengye.dao.UserDao;
import com.itchengye.domain.User;

@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	public boolean regist(User user) {

		int row=0;
		try {
			row = dao.regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row>0?true:false;
	}

	//激活
	public void active(String activeCode) {
		
		try {
			dao.active(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//校验用户名是否存在
	public boolean checkUsername(String username) {
		
		Long isExist = 0L;
		try {
			isExist = dao.checkUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist>0?true:false;
	}

	public User login(String username, String password) throws SQLException {
		
		return dao.login(username,password);
	}

}
