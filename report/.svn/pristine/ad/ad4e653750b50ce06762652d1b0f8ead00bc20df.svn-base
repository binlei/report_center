<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
<style type="text/css">
	form {
		margin-top:35px;
	}
 	.proStyle { 
		text-align: right; 
		width:120px;
 	} 
	#addInfo tr input{
		width:150px;
	}
</style>

</head>
<body>
	<form id="commentForm" name="addInfo" action="" method="post">
		<table>
			<tr>
				<td class="proStyle">充值日期</td>
				<td><input type="text" name="rechargeDate" class="timeInput easyui-datebox"/></td>
				<td class="proStyle" style="width: 100px;">充值金额</td>
				<td><input type="text" name="rechargeMoney" /></td>
			</tr>
			<tr>
				<td class="proStyle">充值人</td>
				<td><input type="text" name="rechargeName" /></td>
				<td class="proStyle">出纳确认</td>
				<td><input type="text" name="confirmed" /></td>
			</tr>
			<tr>
				<td class="proStyle">发票日期</td>
				<td><input type="text" name="invoiceDate" class="timeInput easyui-datebox"/></td>
				<td class="proStyle">发票金额</td>
				<td><input type="text" name="invoiceMoney" /></td>
			</tr>
			<tr>	
				<td class="proStyle">发票号码</td>
				<td><input type="text" name="invoiceNO" /></td>
			</tr>
			<tr>
				<td align="center" valign="middle" colspan="4">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doData('saveRechargeRecord');">保存</a>&nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm();">重置</a></td>
			</tr>
		</table>
	</form>
	 
</body>
</html>
