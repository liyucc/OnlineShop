<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<%@ include file="top.jsp" %>	
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
			<s:iterator value="#session.cList" var="c"> 
			<dl>
				<dt>
					<a href=" ${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname" /> </a>
				</dt>
				<s:iterator value="#c.categorySeconds" var="cs">
				<dd>
					<a href=" ${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="#cs.csid"/>&page=1"><s:property value="#cs.csname" /> </a>
				</dd>
				</s:iterator>
			</dl>
			</s:iterator>
		</div>
	</div>
	<div class="span18 last">
			<div id="result" class="result table clearfix">
				<ul>
				<s:iterator var="p" value="pageBean.list">
				<li>
					<a href="${pageContext.request.contextPath}/product_findById.action?pid=<s:property value="#p.pid"/>">
						<img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>" width="170" height="170"  style="display: inline-block;"/>
						   
						<span style='color:green'>
						 <s:property value="#p.pname"/>
						</span>
						 
						<span class="price">
							商城价： ￥<s:property value="#p.shop_price"/>/份
						</span>
									 
					</a>
				</li>
				</s:iterator>
				</ul>
			</div>
		<div class="pagination">
			<span>第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页</span>
			<s:if test="cid!=null">
				<s:if test="pageBean.page!=1">
				<a class="firstPage" href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&page=1">&nbsp;</a>
				<a class="previousPage" href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page-1"/>">&nbsp;</a>
				</s:if>
				<s:iterator var="i" begin="1" end="pageBean.totalPage">
					<s:if test="pageBean.page!=#i">
					<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>"><s:property value="#i"/></a>
					</s:if>
					<s:else>
					<span class="currentPage"><s:property value="#i"/></span>
					</s:else>
				</s:iterator>
				<s:if test="pageBean.page!=pageBean.totalPage && pageBean.totalPage != 0">
				<a class="nextPage" href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
				<a class="lastPage" href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
				</s:if>
			</s:if>
				
			<s:if test="csid!=null">
				<s:if test="pageBean.page!=1">
				<a class="firstPage" href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&page=1">&nbsp;</a>
				<a class="previousPage" href="${pageContext.request.contextPath}/product_findByCsid.action?cid=<s:property value="csid"/>&page=<s:property value="pageBean.page-1"/>">&nbsp;</a>
				</s:if>
				<s:iterator var="i" begin="1" end="pageBean.totalPage">
					<s:if test="pageBean.page!=#i">
					<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="#i"/>"><s:property value="#i"/></a>
					</s:if>
					<s:else>
					<span class="currentPage"><s:property value="#i"/></span>
					</s:else>
				</s:iterator>
				<s:if test="pageBean.page!=pageBean.totalPage && pageBean.totalPage != 0">
				<a class="nextPage" href="${pageContext.request.contextPath}/product_findByCsid.action?cid=<s:property value="csid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
				<a class="lastPage" href="${pageContext.request.contextPath}/product_findByCsid.action?cid=<s:property value="csid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
				</s:if>
			</s:if>
		</div>
	</div>
</div>
<%@ include file="foot.jsp" %>
</body></html>