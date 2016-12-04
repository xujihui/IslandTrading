<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String proID = request.getParameter("proID");
	request.setAttribute("proID", proID);
	String proName = new String(request.getParameter("proName")
			.getBytes("ISO-8859-1"), "utf-8");
	request.getSession().setAttribute("proName", proName);
	//float proPrice = Float.parseFloat(request.getParameter("proPrice"));
	//request.getSession().setAttribute("proPrice", proPrice);
	String proImage = request.getParameter("proImage");
	request.setAttribute("proImage", proImage);
	String proPrice = request.getParameter("proPrice");
	request.setAttribute("proPrice", proPrice);
	String proT_describe = request.getParameter("proT_describe");
	request.setAttribute("proT_describe", proT_describe);
	String proClassify = request.getParameter("proClassify");
	request.setAttribute("proClassify",proClassify);
	String proSite = request.getParameter("proSite");
	request.setAttribute("proSite", proSite);
	String proTime = request.getParameter("proTime");
	request.setAttribute("proTime", proTime);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑商品-岛买岛卖后台管理系统</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
	$(function() {
		//导航切换
		$(".menuson li").click(function() {
			$(".menuson li.active").removeClass("active")
			$(this).addClass("active");
		});

		$('.title').click(function() {
			var $ul = $(this).next('ul');
			$('dd').find('ul').slideUp();
			if ($ul.is(':visible')) {
				$(this).next('ul').slideUp();
			} else {
				$(this).next('ul').slideDown();
			}
		});
	})
</script>


</head>

<body style="background: #f0f9fd;">
	<div class="lefttop">
		<span></span>后台管理
	</div>
	<dl class="leftmenu">
		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>商品管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="${ctx}/addProduct.jsp"
					target="rightFrame">添加</a><i></i></li>
				<li><cite></cite><a href="${ctx}/findProduct.jsp"
					target="rightFrame">查询</a><i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="${ctx}/images/leftico01.png" /></span>订单管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="${ctx}/findOrder.jsp"
					target="rightFrame">查询</a><i></i></li>
			</ul>
		</dd>
	</dl>
	<div class="center">
		<form action="${ctx}/product/update" method="post">
			<table align="center">
				<tr height="80">
					<td width="100">id：</td>
					<td><input type="text" size="40" value="${proID}"
						disabled="disabled" /> 
						<input type="hidden" name="proID" value="${proID}" id="proID" /></td>
					<td>${idMsg}</td>
				</tr>
				<tr height="80">
					<td>名称：</td>
					<td><input type="text" name="proName" size="40"
						value="${proName}" id="proName" /></td>
					<td>${nameMsg}</td>
				</tr>
				<tr height="80">
					<td>价格：</td>
					<td><input type="text" name="proPrice" size="40"
						value="${proPrice}" id="proPrice" /></td>
					<td>${priceMsg}</td>
				</tr>
				<tr height="80">
					<td>图片：</td>
					<td><input type="text" name="proImage" size="40"
						value="${proImage}" id="proImage" /></td>
					<td>${imageMsg}</td>
				</tr>
				<tr height="80">
					<td>描述：</td>
					<td><input type="text" name="proT_describe" size="40"
						value="${proT_describe}" id="proT_describe" /></td>
					<td>${describeMsg}</td>      
				</tr>
				<tr height="80">
					<td>分类：</td>
					<td><input type="text" name="proClassify" size="40"
						value="${proClassify}" id="proClassify" /></td>
					<td>${classifyMsg}</td>
				</tr>
				<tr height="80">
					<td>发布地点：</td>
					<td><input type="text" name="proSite" size="40"
						value="${proSite}" id="proSite" /></td>
					<td>${siteMsg}</td>
				</tr>	
				<tr height="80">
					<td>发布时间：</td>
					<td><input type="text" name="proTime" size="40"
						value="${proTime}" id="proTime" /></td>
					<td>${timeMsg}</td>
				</tr>					
				

				<tr height="80">
					<td colspan="2" align="center"><input type="submit" value="编辑"
						height="60"></td>
					<td><a href="${ctx}/product/list"><u>返回商品列表页</u></a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>