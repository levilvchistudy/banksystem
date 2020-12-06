<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="entity.Account" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>银行系统--用户</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
</head>
<body style="background-color:#f2f9fd;">
	<div class="header bg-main">
		<div class="logo margin-big-left fadein-top">
			<h1>
				<img src="images/user.jpg" class="radius-circle rotate-hover"
					height="50" alt="" /><%=((Account) session.getAttribute("account")).getUsername()%></h1>
		</div>
		<div class="head-l">
			<a class="button button-little bg-red" href="account_exit"><span
				class="icon-power-off"></span> 退出登录</a>
		</div>
	</div>
	<div class="leftnav">
		<div class="leftnav-title">
			<strong><span class="icon-list"></span>菜单列表</strong>
		</div>
		<h2>
			<span class="icon-pencil-square-o"></span>用户功能
		</h2>
		<ul style="display:block">
			<li><a href="account_deposit" target="right"><span
					class="icon-caret-right"></span> 存 款</a></li>
			<li><a href="account_withdraw" target="right"><span
					class="icon-caret-right"></span> 取 款</a></li>
			<li><a href="account_tras" target="right"><span
					class="icon-caret-right"></span> 转 账</a></li>
			<li><a href="account_record" target="right"><span
					class="icon-caret-right"></span>交易记录</a></li>
			<li><a href="account_info" target="right"><span
					class="icon-caret-right"></span>个人信息</a></li>
			<li><a href="account_reset" target="right"><span
					class="icon-caret-right"></span>修改密码</a></li>
		</ul>
	</div>

	<ul class="bread">
		<li><a href="account_info" target="right"
			class="icon-home"> 首页</a></li>
		<li><a href="##" id="a_leader_txt">用户信息</a></li>
		<li><b>当前语言：</b><span style="color:red;">中文</span></li>
	</ul>
	<div class="admin">
		<iframe src="account_info" name="right" width="100%" height="100%"></iframe>
	</div>
	<div style="text-align:center;">
		<p>
			来源:<a href="http://www.mycodes.net/" target="_blank">令十二525</a>
		</p>
	</div>
</body>
</html>