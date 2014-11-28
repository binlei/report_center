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
	function save() {
		$.ajax({
			type: "POST",
			url : 'updataWithInfo',
			data : $("#saveWith").serialize(),
			dataType : "json",
			success : function(msg){
				if ("1" == msg.succ) {
					alert("保存成功！");
					parent.closeWin();
					parent.refresh();
				} else if ("0" == msg.succ) {
					alert("保存失败！");
					parent.closeWin();
					parent.refresh();
				}
			}
		});
	}
</script>
	
</head>

<body>
	<form id="saveWith">
		<input type="hidden" name="settleNo" id="settleNo" value="${withholdingData.settleNo}"/>
		<table>
			<tr>
				<td>
					结算单号：
				</td>
				<td>
					<input type="text" name="settleNo" value="${withholdingData.settleNo}" style="width: 210px;" disabled/>
				</td>
			</tr>
			<tr>
				<td>
					帐套：
				</td>
				<td>
					<input type="text" name="setBook" value="${withholdingData.setBook}" style="width: 210px;"/>
				</td>
			</tr>
			<tr>
				<td>
					项目：
				</td>
				<td>
					<input type="text" id="project" name="project" value="${withholdingData.project}" style="width: 210px;"/>
				</td>
			</tr>
			<tr>
				<td>
					供应商：
				</td>
				<td>
					<input type="text" value="${withholdingData.supplier}" name="supplier" id="supplier" style="width: 210px;"/>
				</td>
			</tr>
			<tr>
				<td>
					所属期间：
				</td>
				<td>
					<input class="timeInput easyui-datebox" value="${withholdingData.extendProp1}" name="period" style="width: 214px;"/>
				</td>
			</tr>
			<tr>
				<td>
					U8预提金额：
				</td>
				<td>
					<input type="text" value="${withholdingData.uaMoney}" name="uaMoney" id="uaMoney" style="width: 210px;"/>
				</td>
			</tr>
			<tr>
				<td>
					U8预提月份：
				</td>
				<td>
					<input class="timeInput easyui-datebox" value="${withholdingData.extendProp2}" name="uaMonth" style="width: 214px;"/>
				</td>
			</tr>
		</table>
		<div style="text-align: center; padding-top: 10px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()" >提交</a>
		</div>
	</form>
	 
</body>
</html>
