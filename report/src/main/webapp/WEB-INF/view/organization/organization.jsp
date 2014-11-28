<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/taglibs.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
</head>
<body>
	<form class="commentForm" id="commentForm" method="post">
			<table width="100%" class="table_border">
				<tr>
					<td class="title" width="100">分站编号</td>
					<td>
						<input name="code" id="code" maxlength="100" value="${organization.code}" required /> 
						<input type="hidden" name="id" value="${organization.id }" /></td>
				</tr>
				<tr>
					<td class="title" width="100">分站名称</td>
					<td>
						<input name="name" id="name" maxlength="100" value="${organization.name}" required /></td>
				</tr>
				<tr>
					<td class="title" width="100">备注</td>
					<td>
					<textarea name="description" id="description" maxlength="100">${organization.description }</textarea></td>
				</tr>
			</table>
	</form>
</body>
</html>