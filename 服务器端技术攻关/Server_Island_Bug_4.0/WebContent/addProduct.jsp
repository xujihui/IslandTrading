<!--  
 * Function:addProduct
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
<title>增加产品-岛买岛卖后台管理系统</title>
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
		<h1 align="center">${result}
			<a href="${ctx}/product/list"><u>返回商品列表页</u></a>
		</h1>
		<form action="${ctx}/product/save" method="post">
			<table align="center" class="imgtable">
				<tr height="80">
					<td>商品名称：</td>
					<td><input type="text" name="PRODUCT_NAME" size="40" id="PRODUCT_NAME" /></td>
					<td>*${nameMsg}</td>
				</tr>
				<tr height="80">
					<td>商品价格：</td>
					<td><input type="text" name="PRODUCT_PRICE" size="40" id="PRODUCT_PRICE" /></td>
					<td>*${priceMsg}</td>
				</tr>
				<tr height="80">
					<td>商品描述：</td>
					<td><input type="text" name="PRODUCT_DESCRIBE" size="40" id="PRODUCT_DESCRIBE" /></td>
					<td>*${describeMsg}</td>
				</tr>
				<tr height="80">
					<td>商品图片：</td>
					<td><input type="text" name="PRODUCT_IMAGE" size="40" id="PRODUCT_IMAGE" /></td>
					<td>*${imagebeMsg}</td>
				</tr>
				<tr height="80">
					<td>商品发布位置：</td>
					<td><input type="text" name="PRODUCT_SITE" size="40" id="PRODUCT_SITE" /></td>
					<td>*${siteMsg}</td>
				</tr>
				<tr height="80">
					<td>商品浏览量：</td>
					<td><input type="text" name="PRODUCT_VIEW" size="40" id="PRODUCT_VIEW" /></td>
					<td>*${viewMsg}</td>
				</tr>
				<tr height="80">
					<td>商品点赞量：</td>
					<td><input type="text" name="PRODUCT_POSITIVE" size="40" id="PRODUCT_POSITIVE" /></td>
					<td>*${positiveMsg}</td>
				</tr>
				<tr height="80">
					<td>商品状态：</td>
					<td><input type="text" name="PRODUCT_STATUS" size="40" id="PRODUCT_STATUS" /></td>
					<td>*${statusMsg}</td>
				</tr>
				<tr height="80">
					<td>商品留言：</td>
					<td><input type="text" name="PRODUCT_MESSAGE" size="40" id="PRODUCT_MESSAGE" /></td>
					<td>*${messageMsg}</td>
				</tr>
				<tr height="80">
					<td align="center" colspan="3"><input type="submit" value="添加"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
