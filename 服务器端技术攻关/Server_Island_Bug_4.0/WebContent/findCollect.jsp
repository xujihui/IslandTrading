<!--  
 * Function:findCollect
 * Date:2016.12.11
 * Author:LiuXin
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查找收藏-岛买岛卖后台管理系统</title>
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
		<form action="${ctx}/collect/findByID" method="post">
			收藏ID：<input type="text" name="Collect_Id" size="40" id="Collect_Id" /> <input
				type="submit" style="background: #DDDDFF" value="查找"><a
				href="${ctx}/collect/list" target="rightFrame"><u>返回收藏列表页</u></a>
		</form>

		<div>
			<br> <br>
			<c:if test="${collect!=null}">
				<table class="imgtable">
					<thead>
						<tr>
							<th width="100px;">收藏ID</th>
							<th width="100px;">收藏状态</th>
							<th width="100px;">收藏时间</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td>${collect.Collect_Id}</td>
							<td>${collect.Collect_Status}</td>
							<td>${collect.Collect_Time}</td>
						</tr>
					</tbody>
				</table>
			</c:if>
		</div>

	</div>
</body>
</html>