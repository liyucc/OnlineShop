package com.cong.shop.category.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.category.service.CategoryService;
import com.cong.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends ActionSupport implements ModelDriven<Category> {
	//ʵ��ģ������
	private Category category = new Category();
	//ע��categoryservice
	@Resource
	private CategoryService categoryService;
	public Category getModel() {
		return null;
	}
	
	
}
