package com.itchengye.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.itchengye.domain.Category;
import com.itchengye.domain.Order;
import com.itchengye.domain.Product;
import com.itchengye.service.AdminService;
import com.itchengye.utils.CommonsUtils;

@Controller
@RequestMapping(value="admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	//管理员删除商品分类
	@RequestMapping(value="delCategory")
	public String delProduct(@RequestParam(value="cid") String cid) {
			
			//传递cid到service
			try {
				service.delCategory(cid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return "redirect:/admin/categoryList";
	}
	
	//管理员添加商品分类 cname 
	@RequestMapping(value="addCategory")
	public String addCategory(@RequestParam(value="cname") String cname) {
		
		Category category = new Category();
		category.setCid(CommonsUtils.getUUID());
		category.setCname(cname);
		try {
			service.addCategory(category);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/categoryList";
	}
	
	
	//管理员查看所有类别
	@RequestMapping(value="categoryList")
	public String categoryList(Model model, HttpServletResponse response) {
		//获得所有的商品的类别
		List<Category> categoryList = null;
		categoryList = service.findAllCategory();
		
		//放到request域
		model.addAttribute("categoryList", categoryList);
		//request.setAttribute("categoryList", categoryList);
		
		return "forward:/admin/category/list.jsp";
		//request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
	}

	
	//管理员删除商品
	@RequestMapping(value="delProduct")
	public String delProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="pid") String pid) {
			
			//传递pid到service
			try {
				service.delProductByPid(pid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return "redirect:/admin/productList";
			//response.sendRedirect(request.getContextPath()+"/admin?method=productList");
	}
	
	
	//管理员显示所有商品列表
	@RequestMapping(value="productList")
	public String productList(Model model, HttpServletResponse response) {
		//传递请求到service
		List<Product> productList = null;
		try {
			productList = service.findAllProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//获得所有的商品的类别
		List<Category> categoryList = null;
		categoryList = service.findAllCategory();
		
		//放到request域
		model.addAttribute("categoryList", categoryList);
		//request.setAttribute("categoryList", categoryList);
		
		//将productList存到request域中
		model.addAttribute("productList", productList);
		//request.setAttribute("productList", productList);
		//转发
		return "forward:/admin/product/list.jsp";
		//request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
	}
	
	//获得所有订单
	@RequestMapping(value="findAllOrders")
	public String findAllOrders(HttpServletRequest request, HttpServletResponse response) {
		List<Order> orderList = service.findAllOrders();
		
		request.setAttribute("orderList", orderList);
		return "forward:/admin/order/list.jsp";
		//request.getRequestDispatcher("/admin/order/list.jsp").forward(request, response);
	}
	
	
	
	@RequestMapping(value="findAllCategory",method=RequestMethod.POST)
	@ResponseBody
	public String findAllCategory() {
		//提供一个List<Category> 转成json字符串
		List<Category> categoryList = service.findAllCategory();
		
		Gson gson = new Gson();
		String json = gson.toJson(categoryList);
		
		//response.setContentType("text/html;charset=UTF-8");
		
		return json;
		//response.getWriter().write(json);
	}
	
}
