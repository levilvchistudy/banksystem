<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="css/admin2.css"></link>
</head>

<body background="background.png">
	<p>
		当前是第<%=request.getAttribute("now")%>页
	</p>
	<table>
		<tr>
			<s:iterator value="#request.no">
				&nbsp;<a
					href="admin_<%=request.getAttribute("type") == null ? "page"
						: request.getAttribute("type")%>?page=<s:property />">&nbsp;<s:property />&nbsp;
				</a>&nbsp;
			</s:iterator>
		</tr>
	</table>

	<table width="599">
		<thead>
			<tr>
				<th width="92">编号</th>
				<th width="99">用户名</th>
				<th width="111">余额</th>
				<th width="131">账户状态</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="#request.text" id="id">
				<tr>
					<td><s:property value="#id.accountid" /></td>
					<td><s:property value="#id.username" /></td>
					<td><s:property value="#id.balance" /></td>
					<td><s:property value="#id.status.name" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</body>
</html>
