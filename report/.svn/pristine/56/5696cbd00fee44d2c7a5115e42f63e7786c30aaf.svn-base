<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/taglibs.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>

<style type="text/css">
	form {
		margin-top:15px;
	}
</style>

<script type="text/javascript">
	function load() {
		if ("1" == '${result}') {
			alert("开票数据保存成功！");
			parent.refresh();
		}
		
		else if ("0" == '${result}') {
			alert("开票数据保存失败！");
			parent.refresh();
		}
	}

	function aaa() {
		if (document.getElementById("invoiceMoney1").value == '' && document.getElementById("invoiceMoney2").value == '' ) {
			alert("请输入开票金额");
			return;
		}
		document.getElementById("addInfo").submit();
		parent.closeWin();
	}
</script>
	
</head>

<body onload="load()">
	<form id="addInfo" name="addInfo" action="saveInvoInfo" method="post">
		<input type="hidden" name="extendProp1" value='${invoiceData.extendProp1}'>
		<input type="hidden" name="settleNo" value='${settleNo}'>
		<table>
			<tr>
				<td>
					结算单号：
				</td>
				<td>
					<input type="text" name="settleNo" value="${settleNo}" style="width: 210px;" disabled/>
				</td>
			</tr>
			<tr>
				<td>
					开票日期：
				</td>
				<td>
					<input class="timeInput easyui-datebox" value="${invoiceData.extendProp2}" name="invoiceDate" style="width: 214px;"/>
				</td>
			</tr>
			<tr>
				<td>
					开票抬头：
				</td>
				<td>
					<input type="text" name="title" value="${invoiceData.title}" style="width: 210px;"/>
				</td>
			</tr>
			<tr>
				<td>
					发票号码1：
				</td>
				<td>
					<input type="text" name="invoiceNo1" value="${invoiceData.invoiceNo1}" style="width: 210px;"/>
				</td>
			</tr>
			<tr>
				<td>
					税率1：
				</td>
				<td>
					<input type="text" name="tax1" value="${invoiceData.tax1}" style="width: 210px;"/>
				</td>
			</tr>
			<tr>
				<td>
					开票金额1：
				</td>
				<td>
					<input type="text" name="invoiceMoney1" value="${invoiceData.invoiceMoney1}" style="width: 210px;" id="invoiceMoney1"/>
				</td>
			</tr>
			<tr>
				<td>
					发票号码2：
				</td>
				<td>
					<input type="text" name="invoiceNo2" value="${invoiceData.invoiceNo2}" style="width: 210px;"/>
				</td>
			</tr>
			<tr>
				<td>
					税率2：
				</td>
				<td>
					<input type="text" name="tax2" value="${invoiceData.tax2}" style="width: 210px;"/>
				</td>
			</tr>
			<tr>
				<td>
					开票金额2：
				</td>
				<td>
					<input type="text" name="invoiceMoney2" value="${invoiceData.invoiceMoney2}" style="width: 210px;" id="invoiceMoney2"/>
				</td>
			</tr>
		</table>
		<div style="text-align: center; padding-top: 10px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="aaa()" >提交</a>
		</div>
	</form>
	 
</body>
</html>
