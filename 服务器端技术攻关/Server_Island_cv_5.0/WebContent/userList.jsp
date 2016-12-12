<!--  
 * Function:userList
 * Date:2016.12.11
 * Author:LiuXin
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表-岛买岛卖后台管理系统</title>
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
				<li><cite></cite><a href="${ctx}/addOrder.jsp">添加</a><i></i></li>
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
	</dl>
	<div class="center">
		<table class="imgtable">
			<thead>
				<tr>
					<th width="100px;">用户ID</th>
					<th width="100px;">用户昵称</th>
					<th width="100px;">用户账号</th>
					<th width="100px;">用户密码</th>
					<th width="100px;">用户头像</th>
					<th width="100px;">用户权限</th>
					<th width="100px;">用户TKID</th>
					<th width="100px;">用户电话</th>
					<th width="100px;">环信账号</th>
					<th width="100px;">环信密码</th>
					<th width="100px;" colspan="2">操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${prouser}" var="pro">
					<tr>
						<td>${pro.User_Id}</td>
						<td>${pro.User_Nickname}</td>
						<td>${pro.User_Username}</td>
						<td>${pro.User_Password}</td>
						<td>${pro.User_Image}</td>
						<td>${pro.User_Power}</td>
						<td>${pro.User_TakingId}</td>
						<td>${pro.User_Tel}</td>
						<td>${pro.Hx_Username}</td>
						<td>${pro.Hx_Password}</td>
						<td><a href="${ctx}/user/deleteByID/${pro.User_Id}">删除</a></td>
						<td><a
							href="${ctx}/editUser.jsp?
							&USER_ID=${pro.User_Id}&USER_NICKNAME=${pro.User_Nickname}
							&USER_USERNAME=${pro.User_Username}&USER_PASSWORD=${pro.User_Password}
							&USER_IMAGE=${pro.User_Image}&USER_PASSWORD=${pro.User_Password}
							&USER_TAKINGID=${pro.User_TakingId}&USER_TEL=${pro.User_Tel}
							&HX_USRENAME=${pro.Hx_Username}&HX_PASSWORD=${pro.Hx_Password}
							">编辑</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>