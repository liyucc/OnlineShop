<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理界面</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/js.js"></script>
</head>

<body id="index">
<h1>商城后台管理系统</h1>
<ul id="globalNav">
	<li><a>管理用户</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/adminUser_findAll.action?page=1" target="frameBord">&gt;&gt;&gt;&nbsp;所有用户</a></li>
		</ul>
	</li>
	<li><a>管理一级分类</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/adminCategory_findAll.action" target="frameBord">&gt;&gt;&gt;&nbsp;一级分类</a></li>
		</ul>
	</li>
	<li><a>管理二级分类</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/adminCategorySecond_findAll.action?page=1" target="frameBord">&gt;&gt;&gt;&nbsp;二级分类</a></li>
		</ul>
	</li>
	<li><a>管理商品</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=1" target="frameBord">&gt;&gt;&gt;&nbsp;所有商品</a></li>
		</ul>
	</li>
	<li><a>管理订单</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=1" target="frameBord">&gt;&gt;&gt;&nbsp;所有订单</a></li>
		</ul>
	</li>
	<li><a href="${pageContext.request.contextPath}/admin_logout.action">退出系统</a></li>
</ul>
<iframe id="frameBord" name="frameBord" frameborder="0" width="100%" height="100%" src="${pageContext.request.contextPath}/admin_welcome.action"></iframe>
</body>
</html>

