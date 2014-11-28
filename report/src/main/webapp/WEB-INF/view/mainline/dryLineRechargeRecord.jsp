<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/drylineListUtil.jsp"></jsp:include>
<%@ include file="/WEB-INF/view/common/dryLineCommon.jsp"%>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
<script type="text/javascript">
	function countUnblled(){
		var transferMoney = $("#transferMoney").val();
		var invoiceMoney = $("#invoiceMoney").val();
		$("#unbilled").val(transferMoney-invoiceMoney);
	}
	$(function(){
		getMaincardCard();
	})
</script>
</head>
<body>
	<div>
		<form id="commentForm" name="commentForm" >
			<input type="hidden" name="id" value="${common.id}">
			<table>
				<tr>
					<td class="proStyle">卡号</td>
					<td><input name="card" value="${common.card}" data-options="required:true" class="combobox-maincard"/></td>
					<td class="proStyle">充值日期</td>
					<td>
						<input id="d413" name="rechargeDate" class="Wdate" value="${common.extendProp1}" 
							class="easyui-validatebox" data-options="required:true" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'d414\')}'})"/>
					</td>
				</tr>
				<tr>
					<td class="proStyle">充值金额</td>
					<td><input name="transferMoney" id="transferMoney" value="${common.transferMoney}" class="easyui-validatebox" data-options="required:true,validType:'price'"/></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td class="proStyle">充值金额</td> -->
<%-- 					<td><input name="rechargeMoney" id="rechargeMoney" value="${common.rechargeMoney}" class="easyui-validatebox" data-options="required:true,validType:'price'"/></td> --%>
<!-- 					<td class="proStyle">充值人</td> -->
<%-- 					<td><input name="rechargeName" value="${common.rechargeName}"  class="easyui-validatebox" data-options="required:true"/></td> --%>
<!-- 				</tr> -->
				<tr>
					<td class="proStyle">出纳确认</td>
					<td>
						<select name="confirmed">
							<option value="已付款"<c:if test="${common.confirmed eq '已付款'}"> selected="selected"</c:if>>已付款</option>
							<option value="未付款"<c:if test="${common.confirmed eq '未付款'}"> selected="selected"</c:if>>未付款</option>
						</select>
					<td class="proStyle">发票日期</td>
					<td>
						<input id="d414" name="invoiceDate" class="Wdate" value="${common.extendProp2}" class="easyui-validatebox" data-options="required:true" 
							onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d413\')}'})"/>
					</td>
				</tr>
				<tr>
					<td class="proStyle">发票号码</td>
					<td><input name="invoiceNo" value="${common.invoiceNo}" class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>	
					<td class="proStyle">发票金额</td>
					<td><input name="invoiceMoney" id="invoiceMoney" value="${common.invoiceMoney}" class="easyui-validatebox" onblur="countUnblled();" data-options="required:true,validType:'price'"/></td>
					<td class="proStyle">未开票金额</td>
					<td><input name="unbilled" id="unbilled" value="${common.unbilled}" readonly="readonly"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
