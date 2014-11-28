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
		margin-top:35px;
	}
</style>

<script type="text/javascript">
	function load() {
		if ("1" == '${result}') {
			alert("预提冲回数据保存成功！");
			parent.refresh();
		}
		
		else if ("0" == '${result}') {
			alert("预提冲回数据保存失败！");
			parent.refresh();
		}
	}

	function aaa() {
		document.getElementById("addInfo").submit();
		parent.closeWin();
	}
</script>
	
</head>

<body onload="load()">
	<form id="addInfo" name="addInfo" action="saveRetuInfo" method="post">
		<input type="hidden" name="extendProp1" value='${withholdingReturnData.extendProp1}'>
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
					U8预提冲回金额：
				</td>
				<td>
					<input type="text" name="uaReturnMoney" value="${withholdingReturnData.uaReturnMoney}" style="width: 210px;"/>
				</td>
			</tr>
			<tr>
				<td>
					U8预提冲回月份：
				</td>
				<td>
					<input class="timeInput easyui-datebox" value="${withholdingReturnData.extendProp2}" name="uaReturnMonth" style="width: 214px;"/>
				</td>
			</tr>
		</table>
		<div style="text-align: center; padding-top: 120px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="aaa()" >提交</a>
		</div>
	</form>
	 
</body>
</html>
