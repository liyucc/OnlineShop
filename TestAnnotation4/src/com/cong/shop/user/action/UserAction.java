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
	
	//ʹ��ģ���������ղ���
	private User user = new User();
	//ע��userService 
	@Resource
	private UserService userService;
	//���Խ���checkcode��֤��
	private String checkcode;
	//���Խ���ȷ��������к�̨У��
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

	//��ת��ע��ҳ��
	public String registPage(){
		return "registPage";
	}
	//ʹ��Ajax�첽��֤�û���
	public String findUserByUsername() throws IOException{
		User existUser = userService.findByUsername(user.getUsername());
		System.out.println(user.getUsername());
		//���response������ҳ�����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		if(existUser != null){
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
		}else {
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
	}
	//�û���ע�᷽��
	public String regist(){
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode1");
		if(!checkcode1.equalsIgnoreCase(checkcode)){
			this.addActionError("��֤����������");
			return "checkcode";
		}
		userService.save(user);
		this.addActionMessage("ע��ɹ���ȥ���伤����˺�");
		return "msg";
	}
	//�û�����
	public String active(){
		User exist = userService.findByCode(user.getCode());
		if(exist == null){
			this.addActionMessage("����ʧ�ܣ��������Ѿ����۸ģ�");
		}else {
			exist.setState(1); //��ʾδ�����û�
			exist.setCode(null); //��ռ�����
			userService.updateState(exist);
			this.addActionMessage("����ɹ������¼��");
		}
		return "msg";
	}
	
	//��ת����¼ҳ���Action
	public String loginPage(){
		return "loginPage";
	}
	
	//�û���¼
	public String login(){
		User existUser = userService.login(user.getUsername(), user.getPassword());
		if(existUser != null && existUser.getState() == 1){
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}else {
			this.addActionError("��¼ʧ�ܣ������û���������˺�δ����");
			return "login";
		}
	}
	//�û��˳�
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}
	
}
