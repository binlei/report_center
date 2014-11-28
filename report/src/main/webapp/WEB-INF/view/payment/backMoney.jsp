<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
</head>
<body>
	<form id="commentForm" name="commentForm">
		<input type="hidden" name="extendProp1" value='${common.extendProp1}'> 
		<table>
			<tr>
				<td class="proStyle">结算单号</td>
				<td><input name="settleNo" value="${common.settleNo}" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="proStyle">回款金额</td>
				<td><input name="backFundsMoney" value="${common.backFundsMoney}" /></td>
			</tr>
			<tr>
				<td class="proStyle">回款日期</td>
				<td> 
					<input class="Wdate" name="backFundsDate" value="${common.backFundsDate}" 
							onfocus="WdatePicker({skin:'whyGreen'})" />
				</td>
			</tr>
			<tr>
				<td class="proStyle">调整金额</td>
				<td><input value="${common.adjustMoney}" name="adjustMoney" /></td>
			</tr>
			<tr>
				<td class="proStyle">回款银行</td>
				<td><select name="backFundsBank" id="backFundsBank">
						<option value="">请选择</option>
						<option value="蜂星建行0734">蜂星建行0734</option>
						<option value="蜂星农行4691">蜂星农行4691</option>
						<option value="蜂星保理0001">蜂星保理0001</option>
						<option value="蜂星光大8676">蜂星光大8676</option>
						<option value="蜂星邮储2013">蜂星邮储2013</option>
						<option value="蜂云农行5325">蜂云农行5325</option>
						<option value="蜂云招行10801">蜂云招行10801</option>
						<option value="蜂星浦发3109">蜂星浦发3109</option>
						<option value="招行1966">招行1966</option>
						<option value="现金">现金</option>
				</select></td>
			</tr>
		</table>
	</form>
</body>
</html>
