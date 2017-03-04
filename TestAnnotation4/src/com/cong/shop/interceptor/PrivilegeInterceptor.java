package com.cong.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.cong.shop.admin.vo.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * @author Administrator
 * ��̨Ȩ��У��������
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {
	//ִ������
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//�ж�session���Ƿ񱣴��˺�̨����Ϣ
		AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest()
				.getSession().getAttribute("existAdmin");
		if(existAdminUser == null){
			ActionSupport actionSupport=(ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("û�е�¼û��Ȩ�޷���");
			return "loginFail";
		}else {
			return actionInvocation.invoke();//����
		}
	}

}
