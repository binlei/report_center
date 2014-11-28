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
<script src="${pageContext.request.contextPath}/js/common/permissionUtil.js" type="text/javascript"></script>
</head>
<body>
<form class="cmxform" id="commentForm" method="post">
		<input type="hidden" name="id" value="${permission.id }"/> 
		<table class="table_border" border="1">
					<tr>
						<td class="title"> <font color="red">*&nbsp;</font>权限编号 ：</td>
						<td> <input name="code" id="code" value="${permission.code}" readonly="readonly"/>
						</td>
						<td class="title"> <font color="red">*&nbsp;</font>权限名称： </td>
						<td> <input name="name" id="name" value="${permission.name}" class="easyui-validatebox" data-options="required:true" missingMessage="该项为必填项"/> </td>
					</tr>
					<tr>
						<td class="title"> <font color="red">*&nbsp;</font>上级权限 ：</td>
						<td>  
							<select name="parentPermission.id" id="parentPermission"></select>
							<input type="hidden" id="parentPer" value="${permission.parentPermission.id}"/>
						</td>
						<td class="title"><font color="red">*&nbsp;</font>类型 ：</td>
						<td> <select name="type" id="type">
								<c:choose>  
								   <c:when test="${permission.type == 'MENU'}">
								   		<option value="MENU" selected="selected">MENU</option>
								   </c:when>  
								   <c:otherwise>
								   		<option value="MENU">MENU</option>
								   </c:otherwise>  
								</c:choose>
								<c:choose>  
								   <c:when test="${permission.type == 'BUTTON'}">
								   		<option value="BUTTON" selected="selected">BUTTON</option>
								   </c:when>  
								   <c:otherwise>
								   		<option value="BUTTON">BUTTON</option>
								   </c:otherwise>  
								</c:choose>
							</select></td>
					</tr>
					<tr>
						<td class="title">&nbsp;&nbsp;链接地址 ：</td>
						<td> <input name="url" id="url" value="${permission.url}"/> </td>
						<td class="title"> <font color="red">*&nbsp;</font>优先级 ：</td>
						<td> <input name="priority" id="priority" value="${permission.priority }" class="easyui-numberbox" data-options="required:true" min="1" validType="" missingMessage="必须填写不小于1的数字" /> </td>
					</tr>
					<tr>
						<td class="title"> <font color="red">*&nbsp;</font>是否根节点 ：</td>
						<td> 
							<input type="radio" name="beTop" id="beTopYes"  value="true" <c:if test="${permission.beTop eq true}">checked</c:if>  required />是
							<input type="radio" name="beTop" id="beTopNo" value="false" <c:if test="${permission.beTop eq false}">checked</c:if> required />否
						</td>
						<td class="title">&nbsp;&nbsp;内容 ：</td>
						<td> <input name="content" id="content" /> </td>
					</tr>
					<tr>
						<td class="title"> <font color="red">*&nbsp;</font>状态 ：</td>
						<td> 
							<input type="radio" name="enabled" id="enabled"  value="true" <c:if test="${permission.enabled eq true}">checked</c:if>  required />有效
							<input type="radio" name="enabled" id="disabled" value="false" <c:if test="${permission.enabled eq false}">checked</c:if> required />失效
						</td>
						<td class="title">  &nbsp;&nbsp;备注 ：</td>
						<td> <textarea name="description" id="description" class="easyui-validatebox" validType="length[0,200]" invalidMessage="不能超过200个字符" >${permission.description}</textarea> </td>
					</tr>
					<tr>
						<td align="center" valign="middle" colspan="4">
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="doData('permission');">确认修改</a>
						</td>
					</tr>
				</table>
</form>
<div class="msg" style="width:100%;height:20px;position:absolute;bottom:0;background-color:#E9F4F7;">
</div>