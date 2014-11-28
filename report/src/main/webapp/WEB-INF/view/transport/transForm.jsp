<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
<script type="text/javascript">
$(function(){
	var count = document.getElementById("paymentsType").getElementsByTagName("option").length;
	for (var i = 0; i < count; i++) {
		if ($("#paymentsType").get(0).options[i].text == '${transport.paymentsType}') {
			$("#paymentsType").get(0).options[i].selected = true;
			break;
		}
	}
})
</script>
</head>
<body >
	<form id="commentForm" name="commentForm">
		<input type="hidden" name="id" value='${transport.id}'>  
		<table>
			<tr>
				<td class="proStyle">终端编号：</td>
				<td><input type="text" name="terminalNo" value="${transport.terminalNo}" class="easyui-validatebox" data-options="required:true,validType:'referenceNo[8]'"   /></td>
				<td class="proStyle">交易参考号：</td>
				<td><input type="text" name="referenceNo" value="${transport.referenceNo}" class="easyui-validatebox" data-options="required:true,validType:'terminalNo[12]'" /></td>
			</tr>
			<tr>
				<td class="proStyle">回款日期：</td>
				<td><input class="easyui-datebox" value="${transport.extendProp2}" name="paymentsDate" /></td>
				<td class="proStyle">回款方式：</td>
				<td><select name="paymentsType" id="paymentsType">
						<option value="">请选择</option>
						<option value="刷卡">刷卡</option>
						<option value="打卡">打卡</option>
						<option value="代刷卡">代刷卡</option>
						<option value="移动收款">移动收款</option>
				</select></td>
			</tr>
			<tr>
				<td class="proStyle">回款卡号：</td>
				<td><input type="text" name="paymentsCard" value="${transport.paymentsCard}" /></td>
				<td class="proStyle">实收金额：</td>
				<td>
					<c:if test="${transport.truePay eq null}">
						<input type="text" name="truePay" value="${transport.delivery}" class="easyui-validatebox" data-options="required:true,validType:'price'"  />
					</c:if>
					<c:if test="${transport.truePay ne null}">
						<input type="text" name="truePay" value="${transport.truePay}" class="easyui-validatebox" data-options="required:true,validType:'price'"  />
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="proStyle">合计打款：</td>
				<td><input type="text" name="money" value="${transport.money}" class="easyui-validatebox" data-options="validType:'price'" />
				</td>
			</tr>
			<tr>
				<td class="proStyle">备注：</td>
				<td colspan="3"><textarea id="remarks" name="remarks"  cols="62" rows="5" style="resize:none;">${transport.remarks}</textarea>  
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
