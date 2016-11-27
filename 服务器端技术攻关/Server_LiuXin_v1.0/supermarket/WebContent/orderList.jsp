<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表-超市结帐系统</title>
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
				<span><img src="images/leftico01.png" /></span><a
					href="${ctx}/product/list">商品管理</a>
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
		<table class="imgtable">
			<thead>
				<tr>
					<th width="100px;">订单ID</th>
					<th width="100px;">订单时间</th>
					<th width="100px;">订单总价</th>
					<th width="100px;" colspan="2">操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${orderlist}" var="order">
					<tr>
						<td>${order.oid}</td>
						<td>${order.date}</td>
						<td>${order.total}</td>
						<td><a href="${ctx}/order/detail/${order.oid}">订单详情</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
