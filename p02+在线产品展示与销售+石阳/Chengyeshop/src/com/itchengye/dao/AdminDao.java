package com.itchengye.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Component;

import com.itchengye.domain.Admin;
import com.itchengye.domain.Category;
import com.itchengye.domain.Order;
import com.itchengye.domain.Product;
import com.itchengye.utils.DataSourceUtils;

@Component
public class AdminDao {

	
	public List<Category> findAllCategory() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category";
		List<Category> query = runner.query(sql, new BeanListHandler<Category>(Category.class));
		return query;
	}

	public void saveProduct(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		runner.update(sql, product.getPid(),product.getPname(),product.getMarket_price(),
				product.getShop_price(),product.getPimage(),null,
				product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCategory().getCid());
	}

	public List<Order> findAllOrders() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from orders";
		List<Order> query = runner.query(sql, new BeanListHandler<Order>(Order.class));
		return query;
	}

	public List<Product> findAllProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product";
		List<Product> query = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return query;
	}

	public void delProductByPid(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from product where pid=?";
		runner.update(sql, pid);
	}

	public Admin login(String adminname, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from admin where adminname=? and password=?";
		return runner.query(sql, new BeanHandler<Admin>(Admin.class), adminname,password);
	}

	public void addCategory(Category category) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values(?,?)";
		runner.update(sql, category.getCid(),category.getCname());
		
	}

	public void delCategory(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from category where cid=?";
		runner.update(sql, cid);
	}

}
