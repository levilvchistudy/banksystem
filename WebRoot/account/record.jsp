<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="author" content="Yeeku.H.Lee(CrazyIt.org)" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>交易记录</title>
<link rel="stylesheet" href="css/admin1.css"></link>
<style>
<!--
.datalist {
	border: 1px solid #0058a3; /* 表格边框 */
	font-family: Arial;
	border-collapse: collapse; /* 边框重叠 */
	background-color: #eaf5ff; /* 表格背景色 */
	font-size: 14px;
}

.datalist caption {
	padding-bottom: 5px;
	font: bold 1.4em;
	text-align: left;
}

.datalist th {
	border: 1px solid #0058a3; /* 行名称边框 */
	background-color: #4bacff; /* 行名称背景色 */
	color: #FFFFFF; /* 行名称颜色 */
	font-weight: bold;
	padding-top: 4px;
	padding-bottom: 4px;
	padding-left: 12px;
	padding-right: 12px;
	text-align: center;
}

.datalist td {
	border: 1px solid #0058a3; /* 单元格边框 */
	text-align: left;
	padding-top: 4px;
	padding-bottom: 4px;
	padding-left: 10px;
	padding-right: 10px;
}

.datalist tr.altrow {
	background-color: #c7e5ff; /* 隔行变色 */
}

.fanye {
	background-color: #30C;
}
-->
</style>
</head>
<body background="background.png">
	<table>
		<caption>
			<br />
		</caption>
		<tr>
			<th width="105" scope="col">交易类型</th>
			<th width="102" scope="col">己方账户</th>
			<th width="94" scope="col">对方账户</th>
			<th width="87" scope="col">交易金额</th>
			<th width="160" scope="col">交易时间</th>
		</tr>
		<s:iterator status="list" value="#request.record" id="id">
			<s:if test="#list.even">
				<!-- 偶数行 -->
				<tr class="altrow">
					<td><s:property value="#id.transactionType.name"/></td>
					<td><s:property value="#id.accountByAccountid.accountid"/></td>
					<td><s:property value="#id.accountByOtherid.accountid"/></td>
					<td><s:property value="#id.trMoney"/>¥</td>
					<td><s:property value="#id.datetime"/></td>
				</tr>
			</s:if>
			<s:else>
				<!-- 奇数行 -->
				<tr>
					<td><s:property value="#id.transactionType.name"/></td>
					<td><s:property value="#id.accountByAccountid.accountid"/></td>
					<td><s:property value="#id.accountByOtherid.accountid"/></td>
					<td><s:property value="#id.trMoney"/>¥</td>
					<td><s:property value="#id.datetime"/></td>
				</tr>
			</s:else>
		</s:iterator>
	</table>
</body>
</html>
