package com.itchengye.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itchengye.domain.Category;
import com.itchengye.domain.Product;
import com.itchengye.service.AdminService;
import com.itchengye.utils.CommonsUtils;

@Controller
public class AdminAddProductController {
	
	@Autowired
	private AdminService service;
	
	@RequestMapping(value="adminAddProduct")
	public String adminAddProduct(HttpServletRequest request,HttpServletResponse response) {
		//目的：收集表单数据 封装一个product实体将上传图片存到服务器上
				Product product = new Product();
				HashMap<String,Object> map = new HashMap<String,Object>();
				
				//文件上传！！！！！！
				try {
					//1.创建磁盘文件项工厂
					DiskFileItemFactory factory = new DiskFileItemFactory();
					//2.创建文件上传核心对象
					ServletFileUpload upload = new ServletFileUpload(factory);
					//3.解析request获得文件项对象集合
					List<FileItem> parseRequest = upload.parseRequest(request);
					for(FileItem item : parseRequest) {
						//判断是否是普通表单项
						boolean formFile = item.isFormField();
						if(formFile) {
							//普通表单项获得表单数据封装到product实体中
							String fieldName = item.getFieldName();
							String fieldValue = item.getString("UTF-8");
							
							map.put(fieldName, fieldValue);
							
						}else {
							//文件上传项 获得文件名称，获得文件内容
							String fileName = item.getName();
							String path = request.getServletContext().getRealPath("upload");
							InputStream in = item.getInputStream();
							OutputStream out = new FileOutputStream(path+"/"+fileName);
							IOUtils.copy(in, out);
							in.close();
							out.close();
							item.delete();
							map.put("pimage", "upload/"+fileName);
						}
						
					}
					BeanUtils.populate(product, map);
					//手动封其他数据
					product.setPid(CommonsUtils.getUUID());
					product.setPdate(new Date());
					product.setPflag(0);
					Category category = new Category();
					category.setCid(map.get("cid").toString());
					product.setCategory(category);
					
					service.saveProduct(product);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "forward:/admin/productList";
	}


}
