package com.itchengye.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itchengye.dao.ProductDao;
import com.itchengye.dao.UserDao;
import com.itchengye.domain.Category;
import com.itchengye.domain.Order;
import com.itchengye.domain.OrderItem;
import com.itchengye.domain.PageBean;
import com.itchengye.domain.Product;
import com.itchengye.utils.DataSourceUtils;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao dao;

	//获得热门商品
	public List<Product> findHotProductList() {
		
		List<Product> hotProductList = null;
		try {
			hotProductList = dao.findHotProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hotProductList;
	}
	
	//获得最新商品
	public List<Product> findNewProductList() {
		List<Product> newProductList = null;
		try {
			newProductList = dao.findNewProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newProductList;
	}

	//准备分类数据
	public List<Category> findAllCategory() {
		List<Category> categoryList = null;
		try {
			categoryList = dao.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	public PageBean findProductListByCid(String cid, int currentPage, int currentCount) {
		
		
		//封装一个PageBean 返回web层
				PageBean<Product> pageBean = new PageBean<Product>();
				
				//1、封装当前页
				pageBean.setCurrentPage(currentPage);
				//2、封装每页显示的条数
				pageBean.setCurrentCount(currentCount);
				//3、封装总条数
				int totalCount = 0;
				try {
					totalCount = dao.getCount(cid);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				pageBean.setTotalCount(totalCount);
				//4、封装总页数
				int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
				pageBean.setTotalPage(totalPage);
				
				//5、当前页显示的数据
				// select * from product where cid=? limit ?,?
				// 当前页与起始索引index的关系
				int index = (currentPage-1)*currentCount;
				List<Product> list = null;
				try {
					list = dao.findProductByPage(cid,index,currentCount);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				pageBean.setList(list);
				
				
				return pageBean;
			}

	public Product findProductByPid(String pid) {
		Product product = null;
		try {
			product = dao.findProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	
	//提交订单，将订单数据和订单项数据存到数据库中
	public void submitOrder(Order order) {
		
		
		try {
			//1.开启事务
			DataSourceUtils.startTransaction();
			//2.调用dao存order表
			dao.addOrders(order);
			//3.调用dao存orderItem表
			dao.addOrderItem(order);
			
			
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void updateOrderAddr(Order order) {

		try {
			dao.updateOrderAddr(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateOrderState(String oid) {
		try {
			dao.updateOrderState(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//获得指定用户的订单集合
	public List<Order> findAllOrders(String uid) {
		List<Order> orderList = null;
		try {
			orderList = dao.findAllOrders(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return orderList;
	}

	
	public List<Map<String, Object>> findAllOrderItemByOid(String oid) {
		List<Map<String, Object>> mapList = null;
		try {
			mapList = dao.findAllOrderItemByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return mapList;
	}

}
