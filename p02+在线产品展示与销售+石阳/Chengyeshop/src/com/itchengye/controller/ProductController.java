package com.itchengye.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.itchengye.domain.Cart;
import com.itchengye.domain.CartItem;
import com.itchengye.domain.Category;
import com.itchengye.domain.Order;
import com.itchengye.domain.OrderItem;
import com.itchengye.domain.PageBean;
import com.itchengye.domain.Product;
import com.itchengye.domain.User;
import com.itchengye.service.ProductService;
import com.itchengye.utils.CommonsUtils;

@Controller
@RequestMapping(value="product")
public class ProductController {
	@Autowired
	private ProductService service;
	
	//获得我的订单
	@RequestMapping(value="myOrders")
	public String myOrders(HttpServletRequest request, HttpServletResponse response,
				Model model,
				HttpSession session) {
		//判断用户是否已登录
		User user = (User) session.getAttribute("user");
		if(user==null) {
			return "redirect:/login.jsp";
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
			//return;
			}
		
		//查询该用户的所有的订单信息(单表查询orders表)
		//集合中的每一个Order对象的数据是不完整的 缺少List<OrderItem> orderItems数据
		List<Order> orderList = service.findAllOrders(user.getUid());
		//循环所有的订单 为每个订单填充订单项集合信息
		if(orderList!=null){
			for(Order order : orderList){
				//获得每一个订单的oid
				String oid = order.getOid();
				//查询该订单的所有的订单项---mapList封装的是多个订单项和该订单项中的商品的信息
				List<Map<String, Object>> mapList = service.findAllOrderItemByOid(oid);
				//将mapList转换成List<OrderItem> orderItems 
				for(Map<String,Object> map : mapList){
					
					try {
						//从map中取出count subtotal 封装到OrderItem中
						OrderItem item = new OrderItem();
						BeanUtils.populate(item, map);
						//从map中取出pimage pname shop_price 封装到Product中
						Product product = new Product();
						BeanUtils.populate(product, map);
						//将product封装到OrderItem
						item.setProduct(product);
						//将orderitem封装到order中的orderItemList中
						order.getOrderItems().add(item);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		//orderList封装完整了
		session.setAttribute("orderList",orderList);
		//request.setAttribute("orderList", orderList);
		return "redirect:/order_list.jsp";
		//request.getRequestDispatcher("/order_list.jsp").forward(request, response);
	}	
	
	//确认订单：更新收货人信息
	@RequestMapping(value="confirmOrder",method=RequestMethod.POST)
	public String confirmOrder(HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value="address") String address,
							@RequestParam(value="name") String name,
							@RequestParam(value="telephone") String telephone,
							@RequestParam(value="oid") String oid,
							HttpSession session) {
		
		//更新收货人信息
		Order order = new Order();
		order.setOid(oid);
		order.setAddress(address);
		order.setName(name);
		order.setTelephone(telephone);
		service.updateOrderAddr(order);
		
		//修改订单状态
		service.updateOrderState(oid);
			
			return "forward:myOrders";
			//response.sendRedirect(url);
	}
	
	//提交订单
	@RequestMapping(value="submitOrder")
	public String submitOrder(HttpServletRequest request, HttpServletResponse response,
								HttpSession session) {
		
		//判断用户是否已登录
		User user = (User) session.getAttribute("user");
		if(user==null) {
			return "redirect:../login.jsp";
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
			//return;
		}
		
		//目地：封装好一个order对象传递给service
			Order order = new Order();
			
			String oid = CommonsUtils.getUUID();
			order.setOid(oid);
			order.setOrdertime(new Date());
			//订单金额--通过购物车取
			Cart cart = (Cart) session.getAttribute("cart");
			double total = cart.getTotal();
			order.setTotal(total);
			
			order.setState(0);
			
			order.setAddress(null);
			
			order.setName(null);
			
			order.setTelephone(null);
			
			order.setUser(user);
			
			//封装订单项集合
			//获得购物车中购物项的集合map
			Map<String, CartItem> cartItems = cart.getCartItems();
			for(Map.Entry<String, CartItem> entry : cartItems.entrySet()) {
				//取出每一个购物项
				CartItem cartItem = entry.getValue();
				//创建新的订单项
				OrderItem orderItem = new OrderItem();
				orderItem.setItemid(CommonsUtils.getUUID());
				orderItem.setCount(cartItem.getBuyNum());
				orderItem.setSubtotal(cartItem.getSubtotal());
				orderItem.setProduct(cartItem.getProduct());
				orderItem.setOrder(order);
				//将该订单项封装到订单项集合中
				order.getOrderItems().add(orderItem);
			}
			
			
			service.submitOrder(order);
			
			session.setAttribute("order", order);
			
			//添加订单成功同时清空购物车
			session.removeAttribute("cart");
			
			return "redirect:../order_info.jsp";
			//response.sendRedirect(request.getContextPath()+"/order_info.jsp");
			
	}
	//清空购物车
	@RequestMapping(value="clearCart")
	public String clearCart(HttpServletRequest request, HttpServletResponse response,
								HttpSession session) {
		session.removeAttribute("cart");
		
		return "redirect:../cart.jsp";
		//response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
	
	//购物车删除一个商品
	@RequestMapping(value="delProFromCart")
	public String delProFromCart(HttpServletRequest request, HttpServletResponse response,
								@RequestParam(value="pid") String pid,
								HttpSession session) {
			//删除session中的购物车中的购物项集合中的item
			Cart cart = (Cart) session.getAttribute("cart");
			if(cart!=null) {
				Map<String, CartItem> cartItems = cart.getCartItems();
				//需要修改总价
				cart.setTotal(cart.getTotal()-cartItems.get(pid).getSubtotal());
				
				cartItems.remove(pid);
				cart.setCartItems(cartItems);
				
			}
			session.setAttribute("cart", cart);
			
			return "redirect:../cart.jsp";
			//response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
	
	
	//将商品添加到购物车
	@RequestMapping(value="addProductToCart")
	public String addProductToCart(HttpServletRequest request, HttpServletResponse response,
								@RequestParam(value="pid") String pid,
								@RequestParam(value="buyNum") Integer buyNum,
								HttpSession session) {
		//获得product对象
		Product product = service.findProductByPid(pid);
		//计算小计
		double subtotal = product.getShop_price()*buyNum;
		//封装CartItem
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setBuyNum(buyNum);
		item.setSubtotal(subtotal);

		//获得购物车---判断是否在session中已经存在购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null){
			cart = new Cart();
		}

		//将购物项放到车中---key是pid
		//先判断购物车中是否已将包含此购物项了 ----- 判断key是否已经存在
		//如果购物车中已经存在该商品----将现在买的数量与原有的数量进行相加操作
		Map<String, CartItem> cartItems = cart.getCartItems();

		double newsubtotal = 0.0;

		if(cartItems.containsKey(pid)){
			//取出原有商品的数量
			CartItem cartItem = cartItems.get(pid);
			int oldBuyNum = cartItem.getBuyNum();
			oldBuyNum+=buyNum;
			cartItem.setBuyNum(oldBuyNum);
			cart.setCartItems(cartItems);
			//修改小计
			//原来该商品的小计
			double oldsubtotal = cartItem.getSubtotal();
			//新买的商品的小计
			newsubtotal = buyNum*product.getShop_price();
			cartItem.setSubtotal(oldsubtotal+newsubtotal);

		}else{
			//如果车中没有该商品
			cart.getCartItems().put(product.getPid(), item);
			newsubtotal = buyNum*product.getShop_price();
		}

		//计算总计
		double total = cart.getTotal()+newsubtotal;
		cart.setTotal(total);


		//将车再次访问session
		session.setAttribute("cart", cart);

		//直接跳转到购物车页面
		return "redirect:../cart.jsp";
		//response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
	
	//显示商品的类别的的功能
		@RequestMapping(value="categoryList",method=RequestMethod.POST)
		@ResponseBody
		public String categoryList() {
			
			//准备分类数据
			List<Category> categoryList = service.findAllCategory();
			
			Gson gson = new Gson();
			String json = gson.toJson(categoryList);
			
			//response.setContentType("text/html;charset=UTF-8");
			//response.setCharacterEncoding(arg0);
			return json;
			//response.getWriter().write(json);
			
		}
		
		//显示首页的功能
		//显示商品的类别的的功能
		@RequestMapping(value="index")
		public String index(Model model, HttpServletResponse response,
									HttpSession session) {
			
			
			//准备热门商品---List<Product>
			List<Product> hotProductList = service.findHotProductList();

			//准备最新商品---List<Product>
			List<Product> newProductList = service.findNewProductList();

			//准备分类数据
			//List<Category> categoryList = service.findAllCategory();

			//request.setAttribute("categoryList", categoryList);
//			request.setAttribute("hotProductList", hotProductList);
//			request.setAttribute("newProductList", newProductList);
			model.addAttribute("hotProductList", hotProductList);
			model.addAttribute("newProductList", newProductList);

			return "index";
			//request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		//显示商品的详细信息功能
		@RequestMapping(value="productInfo")
		public String productInfo(Model model,HttpServletRequest request,HttpServletResponse response,
				@RequestParam(value="currentPage") String currentPage,
				@RequestParam(value="cid") String cid,
				@RequestParam(value="pid") String pid) {
			

			Product product = service.findProductByPid(pid);

			model.addAttribute("currentPage", currentPage);
			model.addAttribute("product", product);
			model.addAttribute("cid", cid);
 		
			//获得客户端携带cookie---获得名字是pids的cookie
			String pids = pid;
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for(Cookie cookie : cookies){
					if("pids".equals(cookie.getName())){
						pids = cookie.getValue();
						
						//将pids拆成一个数组
						String[] split = pids.split("-");//{3,1,2}
						List<String> asList = Arrays.asList(split);//[3,1,2]
						LinkedList<String> list = new LinkedList<String>(asList);//[3,1,2]
						//判断集合中是否存在当前pid
						if(list.contains(pid)){
							//包含当前查看商品的pid
							list.remove(pid);
							list.addFirst(pid);
						}else{
							//不包含当前查看商品的pid 直接将该pid放到头上
							list.addFirst(pid);
						}
						//将[3,1,2]转成3-1-2字符串
						StringBuffer sb = new StringBuffer();
						for(int i=0;i<list.size()&&i<7;i++){
							sb.append(list.get(i));
							sb.append("-");//3-1-2-
						}
						//去掉3-1-2-后的-
						pids = sb.substring(0, sb.length()-1);
					}
				}
			}


			Cookie cookie_pids = new Cookie("pids",pids);
			response.addCookie(cookie_pids);

			return "product_info";
			//request.getRequestDispatcher("/product_info.jsp").forward(request, response);

		}
		
		
		//首页根据商品的类别获得商品的列表
		
	
		@RequestMapping(value="productListByCid")
		public String productListByCid(Model model, HttpServletResponse response,HttpServletRequest request,
									@RequestParam(value="cid") String cid){
			int currentPage = 1;
			int currentCount = 12;
			PageBean pageBean = service.findProductListByCid(cid,currentPage,currentCount);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("cid", cid);
			return "product_list";
			//return "redirect:/product_list.jsp";
			//return "forward:../product_list.jsp";
		}
		
		
		//根据商品的类别获得商品的列表
		@RequestMapping(value="productList")
		public String productList(Model model, HttpServletResponse response,HttpServletRequest request,
									@RequestParam(value="cid") String cid,
									@RequestParam(value="currentPage") String currentPageStr
									) {
			if(currentPageStr==null) currentPageStr="1";
			int currentPage = Integer.parseInt(currentPageStr);
			int currentCount = 12;

			PageBean pageBean = service.findProductListByCid(cid,currentPage,currentCount);
			
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("cid", cid);
			//request.setAttribute("pageBean", pageBean);
			//request.setAttribute("cid", cid);

			//定义一个记录历史商品信息的集合
			List<Product> historyProductList = new ArrayList<Product>();

			//获得客户端携带名字叫pids的cookie
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for(Cookie cookie:cookies){
					if("pids".equals(cookie.getName())){
						String pids = cookie.getValue();//3-2-1
						String[] split = pids.split("-");
						for(String pid : split){
							Product pro = service.findProductByPid(pid);
							historyProductList.add(pro);
						}
					}
				}
			}

			//将历史记录的集合放到域中
			model.addAttribute("historyProductList", historyProductList);
			//request.setAttribute("historyProductList", historyProductList);
			
			return "product_list";
			//request.getRequestDispatcher("/product_list.jsp").forward(request, response);


		}
		
}		

