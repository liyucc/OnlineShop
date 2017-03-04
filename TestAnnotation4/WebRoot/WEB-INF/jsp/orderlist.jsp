<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>我的订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<%@ include file="top.jsp" %>
<div class="container cart">
		<div class="span24">
			<div class="step step1">
				<ul>
					
					<li  class="current"></li>
					<li  >我的订单</li>
				</ul>
			</div>
				<table>
					<tbody>
					<s:iterator value="pageBean.list" var="order">
					<tr>
						<th colspan="5">订单编号：<s:property value="#order.oid"/>&nbsp;&nbsp;&nbsp;
							订单状态：
							<s:if test="#order.state == 1">
							<a href="${pageContext.request.contextPath}/order_findByOid.action?oid=<s:property value="#order.oid"/>">未付款</a>
							</s:if>
							<s:if test="#order.state == 2">
							已付款
							</s:if>
							<s:if test="#order.state == 3">
							<a href="${pageContext.request.contextPath}/order_updateState.action?oid=<s:property value="#order.oid"/>">确认收货</a>
							</s:if>
							<s:if test="#order.state == 4">
							交易完成
							</s:if>
						</th>
					</tr>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					<s:iterator value="#order.orderItems" var="orderItem">
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22"/>
								<img src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/> "/>
							</td>
							<td>
								<a target="_blank"><s:property value="#orderItem.product.pname"/></a>
							</td>
							<td>
								<s:property value="#orderItem.product.shop_price"/>
							</td>
							<td class="quantity" width="60">
								<s:property value="#orderItem.count"/>
							</td>
							<td width="140">
								<span class="subtotal">￥<s:property value="#orderItem.subtotal"/>元</span>
							</td>
						</tr>
					</s:iterator>
					</s:iterator>
					<tr>
							<td colspan="5">
							<div class="pagination">
							<span>第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页</span>
								<s:if test="pageBean.page!=1">
								<a class="firstPage" href="${pageContext.request.contextPath}/order_findByUid.action?page=1">&nbsp;</a>
								<a class="previousPage" href="${pageContext.request.contextPath}/order_findByUid.action?page=<s:property value="pageBean.page-1"/>">&nbsp;</a>
								</s:if>
								<s:iterator var="i" begin="1" end="pageBean.totalPage">
									<s:if test="pageBean.page!=#i">
									<a href="${pageContext.request.contextPath}/order_findByUid.action?page=<s:property value="#i"/>"><s:property value="#i"/></a>
									</s:if>
									<s:else>
									<span class="currentPage"><s:property value="#i"/></span>
									</s:else>
								</s:iterator>
								<s:if test="pageBean.page!=pageBean.totalPage && pageBean.totalPage != 0">
								<a class="nextPage" href="${pageContext.request.contextPath}/order_findByUid.action?page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
								<a class="lastPage" href="${pageContext.request.contextPath}/order_findByUid.action?page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
								</s:if>
						</div>
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
<%@ include file="foot.jsp" %>
</body>
</html>