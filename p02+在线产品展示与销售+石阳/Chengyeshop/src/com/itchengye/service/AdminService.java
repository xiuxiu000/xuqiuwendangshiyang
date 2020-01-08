package com.itchengye.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itchengye.dao.AdminDao;
import com.itchengye.dao.UserDao;
import com.itchengye.domain.Admin;
import com.itchengye.domain.Category;
import com.itchengye.domain.Order;
import com.itchengye.domain.Product;

@Service
public class AdminService {

	@Autowired
	private AdminDao dao;
	
	public List<Category> findAllCategory() {
		try {
			return dao.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveProduct(Product product) throws SQLException {
		dao.saveProduct(product);
		
	}

	public List<Order> findAllOrders() {
		List<Order> orderList = null;
		try {
			orderList = dao.findAllOrders();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	public List<Product> findAllProduct() throws SQLException {
		//因为没有复杂业务，直接传递请求到dao层
		return dao.findAllProduct();
	}

	public void delProductByPid(String pid) throws SQLException {
		dao.delProductByPid(pid);
	}

	public Admin login(String adminname, String password) throws SQLException {
		return dao.login(adminname,password);
	}

	public void addCategory(Category category) throws SQLException {
		dao.addCategory(category);
		
	}

	public void delCategory(String cid) throws SQLException {
		dao.delCategory(cid);
		
	}

}
