package com.cong.shop.user.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.user.service.UserService;
import com.cong.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	//使用模型驱动接收参数
	private User user = new User();
	//注入userService 
	@Resource
	private UserService userService;
	//属性接收checkcode验证码
	private String checkcode;
	//属性接收确认密码进行后台校验
	private String repassword;
	
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public User getModel() {
		return user;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//跳转到注册页面
	public String registPage(){
		return "registPage";
	}
	//使用Ajax异步验证用户名
	public String findUserByUsername() throws IOException{
		User existUser = userService.findByUsername(user.getUsername());
		System.out.println(user.getUsername());
		//获得response对象，向页面输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		if(existUser != null){
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}else {
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}
	//用户的注册方法
	public String regist(){
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode1");
		if(!checkcode1.equalsIgnoreCase(checkcode)){
			this.addActionError("验证码输入有误！");
			return "checkcode";
		}
		userService.save(user);
		this.addActionMessage("注册成功请去邮箱激活该账号");
		return "msg";
	}
	//用户激活
	public String active(){
		User exist = userService.findByCode(user.getCode());
		if(exist == null){
			this.addActionMessage("激活失败，激活码已经被篡改！");
		}else {
			exist.setState(1); //显示未激活用户
			exist.setCode(null); //清空激活码
			userService.updateState(exist);
			this.addActionMessage("激活成功，请登录！");
		}
		return "msg";
	}
	
	//跳转到登录页面的Action
	public String loginPage(){
		return "loginPage";
	}
	
	//用户登录
	public String login(){
		User existUser = userService.login(user.getUsername(), user.getPassword());
		if(existUser != null && existUser.getState() == 1){
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}else {
			this.addActionError("登录失败，密码用户名有误或账号未激活");
			return "login";
		}
	}
	//用户退出
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}
	
}
