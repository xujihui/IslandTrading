<!--  
 * Function:productList
 * Date:2016.12.11
 * Author:LiuXin
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品列表-岛买岛卖后台管理系统</title>
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
				<span><img src="${ctx}/images/leftico01.png" /></span>用户管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="${ctx}/addUser.jsp">添加</a><i></i></li>
				<li><cite></cite><a href="${ctx}/findUser.jsp"
					target="rightFrame"><label><b>查询</b></label></a><i></i></li>
			</ul>
		</dd>

		<dd>
			<div class="title">
				<span><img src="${ctx}/images/leftico01.png" /></span>分类管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="${ctx}/addClassify.jsp">添加</a><i></i></li>
				<li><cite></cite><a href="${ctx}/findClassify.jsp"
					target="rightFrame"><label><b>查询</b></label></a><i></i></li>
			</ul>
		</dd>

		<dd>
			<div class="title">
				<span><img src="${ctx}/images/leftico01.png" /></span>商品管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="${ctx}/addProduct.jsp">添加</a><i></i></li>
				<li><cite></cite><a href="${ctx}/findProduct.jsp"
					target="rightFrame"><label><b>查询</b></label></a><i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="${ctx}/images/leftico01.png" /></span>订单管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="${ctx}/findOrder.jsp"
					target="rightFrame"><label><b>查询</b></label></a><i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="${ctx}/images/leftico01.png" /></span>活动管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="${ctx}/addActivity.jsp">添加</a><i></i></li>
				<li><cite></cite><a href="${ctx}/findActivity.jsp"
					target="rightFrame"><label><b>查询</b></label></a><i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="${ctx}/images/leftico01.png" /></span>反馈管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="${ctx}/findFeedback.jsp"
					target="rightFrame"><label><b>查询</b></label></a><i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="${ctx}/images/leftico01.png" /></span>收藏管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="${ctx}/findCollect.jsp"
					target="rightFrame"><label><b>查询</b></label></a><i></i></li>
			</ul>
		</dd>
	</dl>
	<div class="center">
		<table class="imgtable">
			<thead>
				<tr>
					<th width="70px;">商品ID</th>
					<th width="70px;">商品名称</th>
					<th width="70px;">商品价格</th>
					<th width="70px;">商品描述</th>
					<th width="70px;">商品图片</th>
					<th width="100px;">商品发布时间</th>
					<th width="100px;">商品发布位置</th>
					<th width="100px;">商品浏览量</th>
					<th width="100px;">商品点赞量</th>
					<th width="70px;">商品状态</th>
					<th width="70px;">商品留言</th>
					<th width="100px;" colspan="2">操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${prolist}" var="pro">
					<tr>
						<td>${pro.Product_Id}</td>
						<td>${pro.Product_Name}</td>
						<td>${pro.Product_Price}</td>
						<td>${pro.Product_Describe}</td>
						<td>${pro.Product_Image}</td>
						<td>${pro.Product_Time}</td>
						<td>${pro.Product_Site}</td>
						<td>${pro.Product_View}</td>
						<td>${pro.Product_Positive}</td>
						<td>${pro.Product_Status}</td>
						<td>${pro.Product_Message}</td>
						<td><a href="${ctx}/product/deleteByID/${pro.Product_Id}">删除</a></td>
						<td><a
							href="${ctx}/editProduct.jsp?
							PRODUCT_ID=${pro.Product_Id}&PRODUCT_NAME=${pro.Product_Name}
							&PRODUCT_PRICE=${pro.Product_Price}&PRODUCT_DESCRIBE=${pro.Product_Describe}
							&PRODUCT_IMAGE=${pro.Product_Image}&PRODUCT_TIME=${pro.Product_Time}
							&PRODUCT_SITE=${pro.Product_Site}&PRODUCT_VIEW=${pro.Product_View}
							&PRODUCT_POSITIVE=${pro.Product_Positive}&PRODUCT_STATUS=${pro.Product_Status}
							&PRODUCT_MESSAGE=${pro.Product_Message}
							">编辑</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
