<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/addCommonHeader.jsp" %>
<%@ include file="/WEB-INF/view/common/dryLineCommon.jsp" %>
</head>
<body>
	<form id="commentForm" name="commentForm" >
		<input type="hidden" name="id" value='${common.id}'>
		<table>
			<tr>
				<td class="proStyle">etc卡号</td>
				<td><input name="etccard" value="${common.etcCard}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">车牌号</td>
				<td><input name="carcard" id="carcard"/></td>
			</tr>
			<tr>
				<td class="proStyle">充值时间</td>
				<td><input id="d412" name="rechargeDate" class="Wdate" value="${common.rechargeDate}" 
					onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
				<td class="proStyle">充值金额</td>
				<td><input name="rechargeMoney" value="${common.rechargeMoney}" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="proStyle">ETC卡内余额</td>
				<td><input name="etcBalance" value="${common.balance}" disabled="disabled"/></td>
			</tr>
		</table>
	</form>
</body>
</html>
