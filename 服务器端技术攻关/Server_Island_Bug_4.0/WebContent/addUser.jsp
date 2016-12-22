<!--  
 * Function:addUser
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
<title>增加用户-岛买岛卖后台管理系统</title>
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
			<a href="${ctx}/user/list"><u>返回用户列表页</u></a>
		</h1>
		<form action="${ctx}/user/save" method="post">
			<table align="center" class="imgtable">
				<tr height="80">
					<td>用户昵称：</td>
					<td><input type="text" name="USER_NICKNAME" size="40"
						id="USER_NICKNAME" /></td>
					<td>*${nicknameMsg}</td>
				</tr>
				<tr height="80">
					<td>用户账户：</td>
					<td><input type="text" name="USER_USERNAME" size="40"
						id="USER_USERNAME" /></td>
					<td>*${usernameMsg}</td>
				</tr>
				<tr height="80">
					<td>用户密码：</td>
					<td><input type="text" name="USER_PASSWORD" size="40"
						id="USER_PASSWORD" /></td>
					<td>*${passwordMsg}</td>
				</tr>
				<tr height="80">
					<td>用户头像：</td>
					<td><input type="text" name="USER_IMAGE" size="40"
						id="USER_IMAGE" /></td>
					<td>*${imageMsg}</td>
				</tr>
				<tr height="80">
					<td>用户权限：</td>
					<td><input type="text" name="USER_POWER" size="40"
						id="USER_POWER" /></td>
					<td>*${powerMsg}</td>
				</tr>
				<tr height="80">
					<td>用户TakingId：</td>
					<td><input type="text" name="USER_TAKINGID" size="40"
						id="USER_TAKINGID" /></td>
					<td>*${takingidMsg}</td>
				</tr>
				<tr height="80">
					<td>用户电话：</td>
					<td><input type="text" name="USER_TEL" size="40" id="USER_TEL" /></td>
					<td>*${telMsg}</td>
				</tr>
				<tr height="80">
					<td>环信账户：</td>
					<td><input type="text" name="HX_USERNAME" size="40"
						id="HX_USERNAME" /></td>
					<td>*${hx_usernameMsg}</td>
				</tr>
				<tr height="80">
					<td>环信密码：</td>
					<td><input type="text" name="HX_PASSWORD" size="40"
						id="HX_PASSWORD" /></td>
					<td>*${hx_passwordMsg}</td>
				</tr>
				<tr height="80">
					<td align="center" colspan="3"><input type="submit" value="添加"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>