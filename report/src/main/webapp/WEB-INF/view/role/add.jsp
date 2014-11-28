<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/html/add.css" rel="stylesheet" type="text/css" >

<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/addAndEdit.js"></script>
</head>
<body>
	<form class="cmxform" id="commentForm" method="post">
		<table class="table_border" border="1">
			<tr>
				<td class="title"><font color="red">*&nbsp;</font>角色编号</td>
				<td><input name="code" id="code" class="easyui-validatebox"
					data-options="required:true" missingMessage="该项为必填项" /></td>
				<td class="title"><font color="red">*&nbsp;</font>角色名称</td>
				<td><input name="name" id="name" class="easyui-validatebox"
					data-options="required:true" missingMessage="该项为必填项" /></td>
			</tr>
			<tr>
				<td class="title"><font color="red">*&nbsp;</font>状态</td>
				<td>
					<input type="radio" name="enabled" id="enabled" value="true" checked="checked" />有效 
					<input type="radio" name="enabled" id="disabled" value="false" />失效</td>
				<td class="title">&nbsp;&nbsp;备注</td>
				<td><textarea name="description" id="description"
						class="easyui-validatebox" validType="length[0,200]"
						invalidMessage="不能超过200个字符"></textarea></td>
			</tr>
			<tr>
				<td align="center" valign="middle" colspan="4">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doData('add');">保存</a>&nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm();">重置</a></td>
			</tr>
		</table>
	</form>
	<div class="msg"
		style="width: 100%; height: 20px; position: absolute; bottom: 0; background-color: #E9F4F7;">
	</div>
</body>
</html>