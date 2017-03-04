<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>商城</title>
<link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>	
<%@ include file="top.jsp" %>
<div class="container index">
<div class="span24">
	<div id="hotProduct" class="hotProduct clearfix">
	<div class="title">
		<strong>热门商品</strong>
	</div>
	<ul class="tab"></ul>
			
	<ul class="tabContent" style="display: block;">
	<s:iterator value="hList" var="p">
		<li style="border:2px solid red;">
			<a target="_blank" href="${pageContext.request.contextPath}/product_findById.action?pid=<s:property value="#p.pid"/>">
			<img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>" style="display: block;"/>
			</a>
		</li>
	</s:iterator>
	</ul>
	</div>
</div>
<div class="span24">
	<div id="newProduct" class="newProduct clearfix">
	<div class="title">
		<strong>最新商品</strong>
		<a  target="_blank"></a>
	</div>
	<ul class="tab"></ul>
	<ul class="tabContent" style="display: block;">
	<s:iterator value="nList" var="p">
		<li style="border:2px solid red;">
			<a target="_blank" href="${pageContext.request.contextPath}/product_findById.action?pid=<s:property value="#p.pid"/>"><img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>" style="display: block;"/></a>
		</li>
	</s:iterator>
	</ul>		
	</div>
</div>

</div>
<%@ include file="foot.jsp" %>
</body>
</html>
