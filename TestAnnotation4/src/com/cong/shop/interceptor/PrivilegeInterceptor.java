package com.cong.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.cong.shop.admin.vo.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * @author Administrator
 * 后台权限校验拦截器
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {
	//执行拦截
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//判断session中是否保存了后台的信息
		AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest()
				.getSession().getAttribute("existAdmin");
		if(existAdminUser == null){
			ActionSupport actionSupport=(ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("没有登录没有权限访问");
			return "loginFail";
		}else {
			return actionInvocation.invoke();//放行
		}
	}

}
