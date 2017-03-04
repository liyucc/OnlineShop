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

	//ģ������
	private AdminUser adminUser = new AdminUser();
	//ע��Service
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
		//����service��ɵ�¼
		AdminUser existAdmin = adminService.login(adminUser);
		if(existAdmin == null){
			this.addActionError("�û������������");
			return "loginFail";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("existAdmin", existAdmin);
			return "loginsuccess";
		}
	}
	
	//�û��˳�
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}
}
