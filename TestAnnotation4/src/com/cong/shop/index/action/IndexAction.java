package com.cong.shop.index.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.category.service.CategoryService;
import com.cong.shop.category.vo.Category;
import com.cong.shop.product.service.ProductService;
import com.cong.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport{

	//注入一级分类的service
	@Resource
	private CategoryService categoryService;
	//注入商品的service
	@Resource 
	private ProductService productService;
	
	public String execute() throws Exception {
		//查询所有的一级分类
		List<Category> cList = categoryService.findAll();
		//将值存入的session中，因为每个页面都用到
		ActionContext.getContext().getSession().put("cList", cList);
		//查询热门商品
		List<Product> hList=productService.findHot();
		ActionContext.getContext().getValueStack().set("hList", hList);
		//查询最新商品
		List<Product> nList = productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
}
