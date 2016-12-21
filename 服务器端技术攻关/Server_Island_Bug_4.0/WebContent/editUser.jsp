<!--  
 * Function:editUser
 * Date:2016.12.11
 * Author:LiuXin
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String USER_ID = request.getParameter("USER_ID");
	request.setAttribute("USER_ID", USER_ID);
	
	String USER_NICKNAME = new String(request.getParameter("USER_NICKNAME").getBytes("ISO-8859-1"),"utf-8");
	request.getSession().setAttribute("USER_NICKNAME",USER_NICKNAME);
	
	String USER_USERNAME = request.getParameter("USER_USERNAME");
	request.setAttribute("USER_USERNAME", USER_USERNAME);
	
	String USER_PASSWORD = request.getParameter("USER_PASSWORD");
	request.setAttribute("USER_PASSWORD", USER_PASSWORD);
	
	String USER_IMAGE = new String(request.getParameter("USER_IMAGE").getBytes("ISO-8859-1"),"utf-8");
	request.getSession().setAttribute("USER_IMAGE",USER_IMAGE);
	
	String USER_POWER = request.getParameter("USER_POWER");
	request.setAttribute("USER_POWER", USER_POWER);
	
	String USER_TAKINGID = request.getParameter("USER_TAKINGID");
	request.setAttribute("USER_TAKINGID", USER_TAKINGID);
	
	String USER_TEL = request.getParameter("USER_TEL");
	request.setAttribute("USER_TEL", USER_TEL);
	
	String HX_USERNAME = request.getParameter("HX_USERNAME");
	request.setAttribute("HX_USERNAME", HX_USERNAME);
	
	String HX_PASSWORD = request.getParameter("HX_PASSWORD");
	request.setAttribute("HX_PASSWORD", HX_PASSWORD);
%>
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
		<form action="${ctx}/user/update" method="post">
			<table align="center">
				<tr height="80">
					<td width="100">用户ID：</td>
					<td><input type="text" size="40" value="${USER_ID}"
						disabled="disabled" /> 
						<input type="hidden" name="USER_ID" value="${USER_ID}" id="USER_ID" /></td>
					<td>${idMsg}</td>
				</tr>
				<tr height="80">
					<td>用户昵称：</td>
					<td><input type="text" name="USER_NICKNAME" size="40"
						value="${USER_NICKNAME}" id="USER_NICKNAME" /></td>
					<td>${nicknameMsg}</td>
				</tr>
				<tr height="80">
					<td>用户账户：</td>
					<td><input type="text" name="USER_USERNAME" size="40"
						value="${USER_USERNAME}" id="USER_USERNAME" /></td>
					<td>${usernameMsg}</td>
				</tr>
				<tr height="80">
					<td>用户密码：</td>
					<td><input type="text" name="USER_PASSWORD" size="40"
						value="${USER_PASSWORD}" id="USER_PASSWORD" /></td>
					<td>${passwordMsg}</td>
				</tr>	
				<tr height="80">
					<td>用户头像：</td>
					<td><input type="text" name="USER_IMAGE" size="40"
						value="${USER_IMAGE}" id="USER_IMAGE" /></td>
					<td>${imageMsg}</td>
				</tr>	
				<tr height="80">
					<td>用户权限：</td>
					<td><input type="text" name="USER_POWER" size="40"
						value="${USER_POWER}" id="USER_POWER" /></td>
					<td>${powerMsg}</td>
				</tr>
				<tr height="80">
					<td>用户TAKINGID：</td>
					<td><input type="text" name="USER_TAKINGID" size="40"
						value="${USER_TAKINGID}" id="USER_TAKINGID" /></td>
					<td>${takingidMsg}</td>
				</tr>		
				<tr height="80">
					<td>用户电话：</td>
					<td><input type="text" name="USER_TEL" size="40"
						value="${USER_TEL}" id="USER_TEL" /></td>
					<td>${telMsg}</td>
				</tr>		
				<tr height="80">
					<td>环信账号：</td>
					<td><input type="text" name="HX_USERNAME" size="40"
						value="${HX_USERNAME}" id="HX_USERNAME" /></td>
					<td>${hx_usernameMsg}</td>
				</tr>		
				<tr height="80">
					<td>环信密码：</td>
					<td><input type="text" name="HX_PASSWORD" size="40"
						value="${HX_PASSWORD}" id="HX_PASSWORD" /></td>
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