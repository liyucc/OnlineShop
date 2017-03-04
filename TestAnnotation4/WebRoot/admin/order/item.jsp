<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table width="100%" style="border:1px solid blue;background: #CCCCCC;">
<tr>
	<td>图片</td>
	<td>数量</td>
	<td>小计</td>
</tr>
<s:iterator value="list" var="orderItem">
  <tr>
	<td><img width="40" height="45" src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/>"/></td>
	<td><s:property value="#orderItem.count"/></td>
	<td><s:property value="#orderItem.subtotal"/></td>
  </tr>
  </s:iterator>
</table>

