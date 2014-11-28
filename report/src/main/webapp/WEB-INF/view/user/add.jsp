<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/html/add.css" rel="stylesheet" type="text/css" >
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/addAndEdit.js"></script>

<form class="cmxform" id="commentForm" method="post">
	<table class="table_border" border="1">
		<tr>
			<th class="title"><font color="red">*&nbsp;</font>用户名：</th>
			<td><input name="code" id="code" class="easyui-validatebox" data-options="required:true" missingMessage="该项为必填项" /></td>
			<th class="title"><font color="red">*&nbsp;</font>密码：</th>
			<td><input type="password" name="password" id="password"
				class="easyui-validatebox" data-options="required:true,validType:'password'"
				missingMessage="该项为必填项" /></td>
			<th class="title"><font color="red">*&nbsp;</font>确认密码：</th>
			<td><input type="password" name="repassword" id="repassword"
				required="true" class="easyui-validatebox"
				validType="equalTo['#password']" missingMessage="该项为必填项"
				invalidMessage="两次输入密码不匹配" /></td>
		</tr>
		<tr>
			<th class="title"><font color="red">*&nbsp;</font>昵称：</th>
			<td><input name="name" id="name" class="easyui-validatebox" data-options="required:true" missingMessage="该项为必填项" /></td>
			<th class="title"><font color="red">*&nbsp;</font>性别：</th>
			<td>
				<input type="radio" name="contact.sex" value="1" checked="checked" />男
				<input type="radio" name="contact.sex" value="0" />女
			</td>
			<th class="title">语言：</th>
			<td><input name="locale" id="locale" /></td>
		</tr>
		<tr>
			<th class="title">手机号：</th>
			<td><input name="contact.mobile" id="mobile" class="easyui-validatebox" data-options="validType:'mobile'" /></td>
			<th class="title">电话：</th>
			<td><input name="contact.tel" id="tel" class="easyui-validatebox" data-options="validType:'phone'" /></td>
			<th class="title">邮箱：</th>
			<td><input name="contact.email" id="email" class="easyui-validatebox" data-options="validType:'email'"
				invalidMessage="请填写正确的邮件格式" /></td>
		</tr>
		<tr>
			<th class="title">QQ：</th>
			<td><input name="contact.qq" id="qq" class="easyui-validatebox" data-options="validType:'qq'" /></td>
			<th class="title">状态：</th>
			<td>
				<input type="radio" name="enabled" id="disabled" value="false" />失效
				<input type="radio" name="enabled" id="enabled" value="true" checked="checked" />有效
			</td>
			<th></th>
			<td></td>
		</tr>
		<tr>
			<td align="center" valign="middle" colspan="6">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doData('add');">保存</a>&nbsp; 
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm();">重置</a>
			</td>
		</tr>
	</table>
</form>
<div class="msg" style="width: 98%; height: 20px; position: absolute; bottom: 0; background-color: #E9F4F7;">
</div>