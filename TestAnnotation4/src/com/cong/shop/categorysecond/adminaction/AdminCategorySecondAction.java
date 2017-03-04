package com.cong.shop.categorysecond.adminaction;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.category.service.CategoryService;
import com.cong.shop.category.vo.Category;
import com.cong.shop.categorysecond.service.CategorySecondService;
import com.cong.shop.categorysecond.vo.CategorySecond;
import com.cong.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台二级分类的Action
 *
 */
@Controller("adminCategorySecondAction")
@Scope("prototype")
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	
	//模型驱动接收参数
	private CategorySecond categorySecond = new CategorySecond();
	//注入二级分类的service
	@Resource
	private CategorySecondService categorySecondService;
	//注入一级分类service
	@Resource
	private CategoryService categoryService;
	//接收前台传过来的页数
	private Integer page;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public CategorySecond getModel() {
		return categorySecond;
	}
	
	//查询所有的二级分类
	public String findAll(){
		//带分页查询所有的二级分类
		PageBean<CategorySecond> pageBean = categorySecondService.findAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//二级分类添加跳转
	public String addPage(){
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPage";
	}
	//保存二级分类
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	//删除二级分类
	public String delete(){
		//如果级联删除，配置cascade
	 categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
	 categorySecondService.delete(categorySecond);
	 return "deleteSuccess";
	}
	
	//修改二级分类页面跳转
	public String edit(){
		//如果级联删除，配置cascade
	 categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
	 //查询所有一级分类
	 List<Category> cList = categoryService.findAll();
	 ActionContext.getContext().getValueStack().set("cList", cList);
	 return "editPage";
	}
	//修改二级分类
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
