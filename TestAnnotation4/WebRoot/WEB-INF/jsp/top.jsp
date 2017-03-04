<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/index.action">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.jpg"/>
			</a>
		</div>
	</div>
	
	<div class="span9">
	<div class="headerAd">
		
	</div>	
	</div>
	
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
			<s:if test="#session.existUser == null">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user_loginPage.action">登录</a>|
				</li>
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user_registPage.action">注册</a>|
				</li>
			</s:if>
			<s:else>
			<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath}"><s:property value="#session.existUser.username"/> </a>|
				</li>
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/order_findByUid.action?page=1">我的订单</a>|
				</li>
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user_logout.action">退出</a>|
				</li>
				<li>
				<a>会员中心</a>|
				</li>
			</s:else>
				<li>
					<a>购物指南</a>|
				</li>
				<li>
					<a>关于我们</a>
				</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="${pageContext.request.contextPath}/cart_myCart.action">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>01234567891</strong>
			</div>
	</div>
	
    <div id="nav">
	<span class="navLeft"></span>
    <ul class="navCenter">
    	<li>
			<a href="${pageContext.request.contextPath}/index.action">首页</a>
		</li>
		<s:iterator value="#session.cList" var="c">
		<li>
			<a href=" ${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a>
		</li>
		</s:iterator>
        
    </ul>
    <span class="navRight"></span>
	</div>
	
</div>
