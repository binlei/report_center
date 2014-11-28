<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/css/html/add.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/common/addAndEdit.js" type="text/javascript"></script>
</head>
<body>
	<form class="cmxform" id="commentForm" method="post">
		<table class="table_border" border="1">
			<tr>
				<td class="title"><font color="red">*&nbsp;</font>角色编号</td>
				<td><input type="hidden" name="id" value="${role.id}">
					<input name="code" id="code" value="${role.code}" readonly="readonly" class="easyui-validatebox"
					data-options="required:true" /></td>
				<td class="title"><font color="red">*&nbsp;</font>角色名称</td>
				<td><input name="name" id="name" value="${role.name }"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="title"><font color="red">*&nbsp;</font>状态</td>
				<td>
					<input type="radio" name="enabled" id="enabled" value="true" 
						<c:if test="${role.enabled eq true}">checked</c:if> required />有效 
					<input type="radio" name="enabled" id="disabled" value="false" 
						<c:if test="${role.enabled eq false}">checked</c:if> required />失效</td>
				<td class="title">&nbsp;&nbsp;备注</td>
				<td><textarea name="description" id="description" class="easyui-validatebox" validType="length[0,200]"
						invalidMessage="不能超过200个字符">${role.description}</textarea></td>
			</tr>
			<tr>
				<td align="center" valign="middle" colspan="4">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doData('role');">确认修改</a></td>
			</tr>
		</table>
	</form>
	<div class="msg"
		style="width: 100%; height: 20px; position: absolute; bottom: 0; background-color: #E9F4F7;">
	</div>
</body>
</html>