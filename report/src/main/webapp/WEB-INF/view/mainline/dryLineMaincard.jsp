<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/addCommonHeader.jsp" %>
</head>
<body>
	<div>
		<form id="commentForm" name="commentForm" >
			<input type="hidden" name="id" value="${common.id}">
			<table>
				<tr>
					<td class="proStyle">卡号</td>
					<td><input name="mainCard" value="${common.mainCard}" class="easyui-validatebox" data-options="required:true,validType:'eqLength[19]'"/></td>
				</tr>
				<tr>
					<td class="proStyle">主卡余额</td>
					<td><input name="balance" value="${common.balance}" class="easyui-validatebox" data-options="required:true,validType:'price'"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
