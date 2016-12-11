<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑用户-岛买岛卖后台管理系统</title>
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
		<form action="${ctx}/user/update" method="post">
			<table align="center">
				<tr height="80">
					<td width="100">用户ID：</td>
					<td><input type="text" size="40" value="${USER_ID}"
						disabled="disabled" /> 
						<input type="hidden" name="proID" value="${USER_ID}" id="proID" /></td>
					<td>${idMsg}</td>
				</tr>
				<tr height="80">
					<td>用户昵称：</td>
					<td><input type="text" name="proNickname" size="40"
						value="${USER_NICKNAME}" id="proType" /></td>
					<td>${nicknameMsg}</td>
				</tr>
				<tr height="80">
					<td>用户账户：</td>
					<td><input type="text" name="proUsername" size="40"
						value="${USER_USERNAME}" id="proUsername" /></td>
					<td>${usernameMsg}</td>
				</tr>
				<tr height="80">
					<td>用户密码：</td>
					<td><input type="text" name="proPassword" size="40"
						value="${USER_PASSWORD}" id="proPassword" /></td>
					<td>${passwordMsg}</td>
				</tr>	
				<tr height="80">
					<td>用户头像：</td>
					<td><input type="text" name="proGravatar" size="40"
						value="${USER_IMAGE}" id="proGravatar" /></td>
					<td>${imageMsg}</td>
				</tr>	
				<tr height="80">
					<td>用户权限：</td>
					<td><input type="text" name="proPower" size="40"
						value="${USER_POWER}" id="proPower" /></td>
					<td>${powerMsg}</td>
				</tr>
				<tr height="80">
					<td>用户TAKINGID：</td>
					<td><input type="text" name="proPower" size="40"
						value="${USER_TAKINGID}" id="proPower" /></td>
					<td>${takingidMsg}</td>
				</tr>		
				<tr height="80">
					<td>用户电话：</td>
					<td><input type="text" name="proPower" size="40"
						value="${USER_TEL}" id="proPower" /></td>
					<td>${telMsg}</td>
				</tr>		
				<tr height="80">
					<td>环信账号：</td>
					<td><input type="text" name="proPower" size="40"
						value="${HX_USERNAME}" id="proPower" /></td>
					<td>${hx_usernameMsg}</td>
				</tr>		
				<tr height="80">
					<td>环信密码：</td>
					<td><input type="text" name="proPower" size="40"
						value="${HX_PASSWORD}" id="proPower" /></td>
					<td>${hx_passwordMsg}</td>
				</tr>				
				<tr height="80">
					<td colspan="2" align="center"><input type="submit" value="编辑"
						height="60"></td>
					<td><a href="${ctx}/user/list"><u>返回用户列表页</u></a></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>