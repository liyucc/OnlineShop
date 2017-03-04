<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/adminProduct_addPage.action";
			}
</script>
<style type="text/css">
body{
	margin:0;
	padding:0;
	background: url(images/bgbig.jpg) no-repeat;
}
</style>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/user/list.jsp"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
					<strong>商品列 表</strong></TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="添加"
							class="button_add" onclick="addProduct()">&#28155;&#21152;
						</button>

					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="2%">序号</td>
								<td align="center" width="10%">商品名称</td>
								<td align="center" width="8%">商品图片</td>
								<td align="center" width="41%">商品描述</td>
								<td align="center" width="5%">商城价格</td>
								<td align="center" width="5%">市场价格</td>
								<td align="center" width="2%">是否热门</td>
								<td width="3%" align="center">编辑</td>
								<td width="4%" align="center">删除</td>
							</tr>
							<s:iterator var="p" value="pageBean.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									<s:property value="#status.count" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									<s:property value="#p.pname" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									<img alt="商品图片" width="80" height="85" src="${pageContext.request.contextPath}/<s:property value="#p.image" />">
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									<s:property value="#p.pdesc" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									￥<s:property value="#p.shop_price" />元
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									￥<s:property value="#p.market_price" />元
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									<s:if test="#p.is_hot == 1">
										是
									</s:if>
									<s:else>
										否
									</s:else>
									</td>
									<td align="center" style="HEIGHT: 22px">
									<a href="${pageContext.request.contextPath}/adminProduct_edit.action?pid=<s:property value="#p.pid"/>">
									<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
									</a>
									</td>

									<td align="center" style="HEIGHT: 22px">
									<a href="${pageContext.request.contextPath}/adminProduct_delete.action?pid=<s:property value="#p.pid"/>">
									<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
									</a>
									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				<tr align="center">
					<td class="ta_01" align="center" bgColor="#afd1f3">
						第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页&nbsp;&nbsp;&nbsp;&nbsp;
						<s:if test="pageBean.page != 1">
							<a class="firstPage" href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=1">首页</a>
							<a class="previousPage" href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>
						</s:if>
						<s:iterator begin="1" end="pageBean.totalPage" var="i">
							<s:if test="pageBean.page!=#i">
								<a href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=<s:property value="#i"/>"><s:property value="#i"/></a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i"/> </span>
							</s:else>
						</s:iterator> 
						<s:if test="pageBean.page != pageBean.totalPage">
							<a class="nextPage" href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a> 
							<a class="lastPage" href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>
						</s:if>
					</TD>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

