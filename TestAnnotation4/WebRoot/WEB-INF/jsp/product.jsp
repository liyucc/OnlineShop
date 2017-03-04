<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>
<script>
	function joinCart(){
		document.getElementById("cartForm").submit();
	}
</script>
</head>
<body>
<%@ include file="top.jsp" %>
<div class="container productContent">
	<div class="span6">
		<div class="hotProductCategory">
		<s:iterator value="#session.cList" var="c">
		<dl>
			<dt>
				<a href=" ${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a>
			</dt>
			<s:iterator value="#c.categorySeconds" var="cs">
				<dd>
					<a  href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="#cs.csid"/>&page=1" ><s:property value="#cs.csname"/></a>
				</dd>
			</s:iterator>
		</dl>
		</s:iterator>
		</div> 
		
	
	</div>
		<div class="span18 last">
			
			<div class="productImage">
					<a title="" style="outline-style: none; text-decoration: none;" id="zoom" rel="gallery">
						<img style="opacity: 1;" title="" class="medium" src="${pageContext.request.contextPath}/<s:property value="model.image"/>"/>
					</a>
			</div>
			<div class="name"><s:property value="model.pname"/></div>
			<div class="sn">
				<div>编号：<s:property value="model.pid"/></div>
			</div>
			<div class="info">
				<dl>
					<dt>商城价:</dt>
					<dd>
						<strong>￥：<s:property value="model.shop_price"/>元/份</strong>
							参 考 价：
							<del>￥<s:property value="model.market_price"/>元/份</del>
					</dd>
				</dl>
			</div>
			<form action="${pageContext.request.contextPath}/cart_addCart.action" method="post" id="cartForm">
			<input value="<s:property value="model.pid"/>" name="pid" type="hidden"/>
			<div class="action">
				<dl class="quantity">
					<dt>购买数量:</dt>
					<dd>
						<input id="count" name="count" value="1" maxlength="4" type="text"/>
					</dd>
					<dd>
						件
					</dd>
				</dl>
			<div class="buy">
				<input id="addCart" class="addCart" value="加入购物车" type="button" onclick="joinCart();"/>
			</div>
			</div>
			</form>
			<div id="bar" class="bar">
				<ul>
						<li id="introductionTab">
							<a href="#introduction">商品介绍</a>
						</li>
						
				</ul>
			</div>
				
			<div id="introduction" name="introduction" class="introduction">
				<div class="title">
					<strong> <s:property value="model.pdesc" /> </strong>
				</div>
				<div>
					<img src="${pageContext.request.contextPath}/<s:property value="model.image" />"/>
				</div>
			</div>
		</div>
</div>
<%@ include file="foot.jsp" %>
</body>
</html>
