<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理界面</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form action="${pageContext.request.contextPath}/admin_login.action" id="loginForm" method="post" target="_parent">
	<h3>管理员登录</h3>
	<s:actionerror cssStyle="color:red;list-style:none;font-size:16px;font-weight:bold; text-align:center;"/>
	<label for="userName"><span>用户名：</span><input id="userName" name="username" type="text" /></label>
	<label for="passWord"><span>密码：</span><input id="passWord" name="password" type="password" /></label>
	<label><input type="submit" class="bt" value="确定" />
	</label>
</form>
</body>
</html>

