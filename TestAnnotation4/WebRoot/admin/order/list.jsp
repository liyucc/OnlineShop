<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
body{
	margin:0;
	padding:0;
	background: url(images/bgbig.jpg) no-repeat;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript">
  	function showdetail(oid){
  		//获得按钮
  		var btn = document.getElementById("btn"+oid);
  		//获得div
  		var div1 = document.getElementById("div"+oid);
  		if(btn.value=="订单详情"){
  		//1.创建异步加载的对象
  		var xmlHttp;
		if(window.XMLHttpRequest){
			xmlHttp=new XMLHttpRequest();
		}else{
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
  		//2.设置监听
		xmlHttp.onreadystatechange=function(){
			if(xmlHttp.readyState==4 && xmlHttp.status==200){
				div1.innerHTML=xmlHttp.responseText;
			}
		};
  		//3.打开链接
  		xmlHttp.open("get","${pageContext.request.contextPath}/adminOrder_findOrderItem.action?time="+new Date().getTime()+"&oid="+oid,true);
  		//4.发送
  		xmlHttp.send(null);
  		btn.value = "关闭";
  		}else {
			btn.value="订单详情";
			div1.innerHTML="";
		}
  	}
</script>
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
					<strong>订单列表</strong></TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="5%">序号</td>
								<td align="center" width="5%">订单编号</td>
								<td align="center" width="5%">总金额</td>
								<td align="center" width="55%">地址</td>
								<td align="center" width="5%">收货人</td>
								<td align="center" width="5%">电话</td>
								<td align="center" width="5%">订单状态</td>
								<td align="center" width="15%">订单详情</td>
							</tr>
							<s:iterator var="o" value="pageBean.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									<s:property value="#status.count" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									<s:property value="#o.oid" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									￥<s:property value="#o.total" />元
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									<s:property value="#o.addr" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									<s:property value="#o.name" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									<s:property value="#o.phone" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
									<s:if test="#o.state == 1">
										未付款
									</s:if>
									<s:if test="#o.state == 2">
										<a href="${pageContext.request.contextPath}/adminOrder_updateState.action?oid=<s:property value="#o.oid" />"><font color="blue">发货</font></a>
									</s:if>
									<s:if test="#o.state == 3">
										未确认收货
									</s:if>
									<s:if test="#o.state == 4">
										交易完成
									</s:if>
									</td>
									<td align="center" style="HEIGHT: 22px">
										<input type="button" value="订单详情" id="btn<s:property value="#o.oid"/>" onclick="showdetail(<s:property value="#o.oid"/>)">
										<div id="div<s:property value="#o.oid"/>">
										
										</div>
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
							<a class="firstPage" href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=1">首页</a>
							<a class="previousPage" href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>
						</s:if>
						<s:iterator begin="1" end="pageBean.totalPage" var="i">
							<s:if test="pageBean.page!=#i">
								<a href="${pageContext.request.contextPath}/adminOrder_find.action?page=<s:property value="#i"/>"><s:property value="#i"/></a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i"/> </span>
							</s:else>
						</s:iterator> 
						<s:if test="pageBean.page != pageBean.totalPage">
							<a class="nextPage" href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a> 
							<a class="lastPage" href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>
						</s:if>
					</TD>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

