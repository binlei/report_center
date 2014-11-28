<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/html/add.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/addAndEdit.js"></script>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<jsp:include page="../common/header.jsp"></jsp:include>
<form class="cmxform" id="commentForm" method="post">
	<table class="table_border" border="1">
		<tr>
			<td class="title"><font color="red">*&nbsp;</font>用户名：</td>
			<td><input name="code" id="code" readonly="readonly" value="${user.code}" /> 
				<input type="hidden" id="id" name="id" value="${user.id }" /></td>
			<td class="title"><font color="red">*&nbsp;</font>昵称：</td>
			<td><input name="name" id="name" value="${user.name}"
				class="easyui-validatebox" data-options="required:true"
				missingMessage="该项为必填项" /></td>
			<td class="title"><font color="red">*&nbsp;</font>性别：</td>
			<td><input type="radio" name="contact.sex" value="1"
				<c:if test="${user.contact.sex eq 1}">checked </c:if> />男 <input
				type="radio" name="contact.sex" value="0"
				<c:if test="${user.contact.sex eq 0}">checked </c:if> />女</td>
		</tr>
		<tr>
			<td class="title">语言：</td>
			<td><input name="locale" id="locale" value="${user.locale}" />
			</td>
			<td class="title">电话：</td>
			<td><input name="contact.tel" id="tel"
				value="${user.contact.tel }" class="easyui-validatebox"
				data-options="validType:'phone'" /></td>
			<td class="title">手机号：</td>
			<td><input name="contact.mobile" id="mobile"
				value="${user.contact.mobile}" class="easyui-validatebox"
				data-options="validType:'mobile'" /></td>
		</tr>
		<tr>
			<td class="title">邮箱：</td>
			<td><input name="contact.email" id="email"
				value="${user.contact.email}" class="easyui-validatebox"
				data-options="validType:'email'" invalidMessage="请填写正确的邮件格式" /></td>
			<td class="title">QQ：</td>
			<td><input name="contact.qq" id="qq" value="${user.contact.qq}"
				class="easyui-validatebox" data-options="validType:'qq'" /></td>
			<td class="title">状态：</td>
			<td><input type="radio" name="enabled" id="enabled" value="true"
				<c:if test="${user.enabled eq true}">checked</c:if> />有效 <input
				type="radio" name="enabled" id="disabled" value="false"
				<c:if test="${user.enabled eq false}">checked</c:if> />失效</td>
		</tr>
		<tr>
			<td align="center" valign="middle" colspan="6" style="height:40px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doData('user','edit');">确认修改</a></td>
		</tr>
	</table>
</form>
<div class="msg"
	style="width: 98%; height: 20px; position: absolute; bottom: 0; background-color: #E9F4F7;">
</div>