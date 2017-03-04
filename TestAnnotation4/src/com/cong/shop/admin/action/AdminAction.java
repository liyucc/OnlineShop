package com.cong.shop.admin.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.admin.service.AdminService;
import com.cong.shop.admin.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends ActionSupport implements ModelDriven<AdminUser>{

	//模型驱动
	private AdminUser adminUser = new AdminUser();
	//注入Service
	@Resource
	private AdminService adminService;
	@Override
	public AdminUser getModel() {
		return adminUser;
	}
	
	public String welcome(){
		return "welcome";
	}
	
	public String login(){
		//调用service完成登录
		AdminUser existAdmin = adminService.login(adminUser);
		if(existAdmin == null){
			this.addActionError("用户名或密码错误");
			return "loginFail";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("existAdmin", existAdmin);
			return "loginsuccess";
		}
	}
	
	//用户退出
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}
}
