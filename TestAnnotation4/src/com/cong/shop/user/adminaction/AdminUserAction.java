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
	//ģ���������ղ���
	private User user = new User();
	public User getModel() {
		return user;
	}
	//ע��userservice 
	@Resource
	private UserService userService;
	//����ǰ̨������ҳ������
	private Integer page;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//��ҳ��ѯ�û�
	public String findAll(){
		
		PageBean<User> pageBean = userService.findAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//ɾ���û�
	public String delete(){
		user = userService.findByUid(user.getUid());
		userService.delete(user);
		return "deleteSuccess";
	}
	//�޸��û�ҳ����ת
	public String edit(){
		//����uid��ѯ�û���Ϣ
		user = userService.findByUid(user.getUid());
		return "editPage";
	}
	//�޸��û�
	public String update(){
		userService.update(user);
		return "updateSuccess";
	}
 }
