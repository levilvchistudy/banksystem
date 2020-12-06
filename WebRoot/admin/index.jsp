<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="entity.Admin" %>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>银行系统</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
</head>
<body>
	<div class="header bg-main">
		<div class="logo margin-big-left fadein-top">
			<h1>
				<img src="images/tubiao.jpg" class="radius-circle rotate-hover"
					height="50" alt="" /><%= ((Admin) session.getAttribute("admin")).getUsername() %>
			</h1>
		</div>
		<div class="head-l">
			&nbsp;&nbsp;<a class="button button-little bg-red"
				href="admin_exit"><span class="icon-power-off"></span>
				退出登录</a>
		</div>
	</div>

	<div class="leftnav">
		<div class="leftnav-title">
			<strong><span class="icon-list"></span>操作列表</strong>
		</div>

		<h2>
			<span class="icon-user"></span>所有账户
		</h2>
		<ul>
			<li><a href="admin_open" target="right"><span
					class="icon-caret-right"></span>已开启的账户</a></li>
			<li><a href="admin_freeze" target="right"><span
					class="icon-caret-right"></span>已冻结的账户</a></li>
		</ul>

		<h2>
			<span class="icon-user"></span>开户
		</h2>
		<ul>
			<li><a href="admin_add" target="right"><span
					class="icon-caret-right"></span>开户</a></li>
		</ul>

		<h2>
			<span class="icon-user"></span>修改密码
		</h2>
		<ul>
			<li><a href="admin_reset" target="right"><span
					class="icon-caret-right"></span>修改密码</a></li>
		</ul>

	</div>

	<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
	<ul class="bread">
		<li><a href="admin_page" target="right" class="icon-home"> 首页</a></li>
		<li><a href="##" id="a_leader_txt">管理界面</a></li>
	</ul>
	<div class="admin">
		<iframe src="admin_page" name="right" width="100%" height="100%"></iframe>
	</div>
	<div style="text-align:center;"></div>
</body>
</html>