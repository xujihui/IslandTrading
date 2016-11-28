<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录-岛买岛卖后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
	});
</script>

</head>

<body
	style="background-color: #1c77ac; background-image: url(images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">



	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>


	<div class="logintop">
		<span>欢迎登录岛买岛卖后台管理系统</span>
		<!--  
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>   -->
	</div>
<form action="login" method="post">
	<div class="loginbody">

		<span class="systemlogo"></span>

		<div class="loginbox">
			${mess}
			<ul>
				<li><input name="userName" type="text" class="loginuser" value="admin"
					onclick="JavaScript:this.value=''" /></li>
				<li><input name="userPwd" type="text" class="loginpwd" value="密码"
					onclick="JavaScript:this.value=''" /></li>
				<li><input type="submit" class="loginbtn" value="登录"/></li>
			</ul>


		</div>

	</div>
</form>
	<div class="loginbm">
		版权所有 2016 <a href="#" target="_blank">岛买到卖项目组</a>
	</div>



</body>
</html>
