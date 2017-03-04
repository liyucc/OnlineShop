package com.cong.shop.user.adminaction;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.user.service.UserService;
import com.cong.shop.user.vo.User;
import com.cong.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller("adminUserAction")
@Scope("prototype")
public class AdminUserAction extends ActionSupport implements ModelDriven<User>{
	//模型驱动接收参数
	private User user = new User();
	public User getModel() {
		return user;
	}
	//注入userservice 
	@Resource
	private UserService userService;
	//接收前台传来的页数参数
	private Integer page;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//分页查询用户
	public String findAll(){
		
		PageBean<User> pageBean = userService.findAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//删除用户
	public String delete(){
		user = userService.findByUid(user.getUid());
		userService.delete(user);
		return "deleteSuccess";
	}
	//修改用户页面跳转
	public String edit(){
		//根据uid查询用户信息
		user = userService.findByUid(user.getUid());
		return "editPage";
	}
	//修改用户
	public String update(){
		userService.update(user);
		return "updateSuccess";
	}
 }
