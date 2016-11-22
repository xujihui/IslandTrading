<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品-超市结帐系统</title>
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
		<h1 align="center">${result}
			<a href="${ctx}/product/list"><u>返回商品列表页</u></a>
		</h1>
		<form action="${ctx}/product/save" method="post">
			<table align="center" class="imgtable">
				<tr height="80">
					<td width="100">商品ID：</td>
					<td><input type="text" name="proID" size="40" id="proID"
						height="60" /></td>
					<td>*${idMsg}</td>
				</tr>
				<tr height="80">
					<td>商品名称：</td>
					<td><input type="text" name="proName" size="40" id="proName" /></td>
					<td>*${nameMsg}</td>
				</tr>
				<tr height="80">
					<td>单价：</td>
					<td><input type="text" name="proPrice" size="40" id="proPrice" /></td>
					<td>*${priceMsg}</td>
				</tr>
				<tr height="80">
					<td align="center" colspan="3"><input type="submit" value="添加"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
