package com.cong.shop.category.adminaction;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.category.service.CategoryService;
import com.cong.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台一级分类
 *
 */
@Controller("adminCategoryAction")
@Scope("prototype")
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	
	//模型驱动接收数据
	private Category category=new Category();
	//注入一级分类service
	@Resource
	private CategoryService categoryService;

	@Override
	public Category getModel() {
		return category;
	}
	
	//后台查询所有一级分类
	public String findAll(){
		List<Category> cList = categoryService.findAll();
		//将集合数据显示到页面
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	//后台添加一级分类
	public String save(){
		//调用service保存service
		categoryService.save(category);
		return "saveSuccess";
	}
	//后台删除一级分类
	public String delete(){
		//调用service删除一级分类
		category =categoryService.findByCid(category.getCid());
		//删除
		categoryService.delete(category);
		return "deleteSuccess";
	}
	//后台修改一级分类页面跳转
	public String edit(){
		//根据cid查询数据并显示的编辑页面
		category=categoryService.findByCid(category.getCid());
		return "editPage";
	}
	//后台修改一级分类
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
	
}
