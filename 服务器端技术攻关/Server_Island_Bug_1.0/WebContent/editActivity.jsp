<!--  
 * Function:editActivity
 * Date:2016.12.11
 * Author:LiuXin
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String ACTIVITY_ID = request.getParameter("ACTIVITY_ID");
	request.setAttribute("ACTIVITY_ID", ACTIVITY_ID);

	String ACTIVITY_CONTENT = request.getParameter("ACTIVITY_CONTENT");
	request.setAttribute("ACTIVITY_CONTENT", ACTIVITY_CONTENT);
	
	String ACTIVITY_ORGANIZER = request.getParameter("ACTIVITY_ORGANIZER");
	request.setAttribute("ACTIVITY_ORGANIZER", ACTIVITY_ORGANIZER);
	
	String ACTIVITY_TIME = request.getParameter("ACTIVITY_TIME");
	request.setAttribute("ACTIVITY_TIME", ACTIVITY_TIME);
	
	String ACTIVITY_SITE = request.getParameter("ACTIVITY_SITE");
	request.setAttribute("ACTIVITY_SITE", ACTIVITY_SITE);
	
	String ACTIVITY_NAME = request.getParameter("ACTIVITY_NAME");
	request.setAttribute("ACTIVITY_NAME", ACTIVITY_NAME);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑活动-岛买岛卖后台管理系统</title>
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
		<form action="${ctx}/activity/update" method="post">
			<table align="center">
				<tr height="80">
					<td width="100">活动ID：</td>
					<td><input type="text" size="40" value="${ACTIVITY_ID}"
						disabled="disabled" /> <input type="hidden" name="ACTIVITY_ID"
						value="${ACTIVITY_ID}" id="ACTIVITY_ID" /></td>
					<td>${idMsg}</td>
				</tr>
				<tr height="80">
					<td>活动名称：</td>
					<td><input type="text" name="ACTIVITY_NAME" size="40"
						value="${ACTIVITY_NAME}" id="ACTIVITY_NAME" /></td>
					<td>${nameMsg}</td>
				</tr>
				<tr height="80">
					<td>活动内容：</td>
					<td><input type="text" name="ACTIVITY_CONTENT" size="40"
						value="${ACTIVITY_CONTENT}" id="ACTIVITY_CONTENT" /></td>
					<td>${contentMsg}</td>
				</tr>
				<tr height="80">
					<td>活动组织单位：</td>
					<td><input type="text" name="ACTIVITY_ORGANIZER" size="40"
						value="${ACTIVITY_ORGANIZER}" id="ACTIVITY_ORGANIZER" /></td>
					<td>${organizerMsg}</td>
				</tr>
				<tr height="80">
					<td>活动发布时间：</td>
					<td><input type="text" name="ACTIVITY_TIME" size="40"
						value="${ACTIVITY_TIME}" id="ACTIVITY_TIME" /></td>
					<td>${timeMsg}</td>
				</tr>
				<tr height="80">
					<td>活动发布位置：</td>
					<td><input type="text" name="ACTIVITY_SITE" size="40"
						value="${ACTIVITY_SITE}" id="ACTIVITY_SITE" /></td>
					<td>${siteMsg}</td>
				</tr>

				<tr height="80">
					<td colspan="2" align="center"><input type="submit" value="编辑"
						height="60"></td>
					<td><a href="${ctx}/activity/list"><u>返回活动列表页</u></a></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>