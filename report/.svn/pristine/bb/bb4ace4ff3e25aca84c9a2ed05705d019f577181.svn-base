<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/addCommonHeader.jsp" %>
<%@ include file="/WEB-INF/view/common/dryLineCommon.jsp" %>
<script type="text/javascript">
$(function(){
	getAllCarNo();
	$("#price").change(function(){
		var total = $("#quantity").val() * $("#price").val();
		$("#maintenanceMoney").val(total);
	});
})
</script>
</head>
<body>
	<form id="commentForm" name="addInfo" action="" method="post">
		<input type="hidden" name="id" value='${common.id}'>
		<table>
			<tr>
				<td class="proStyle">车牌号</td>
				<td><input name="carCardId" id="carCardId" class="combobox-carNo"/></td>
				<td class="proStyle">保养日期</td>
				<td><input id="d411" name="maintenanceDate" class="Wdate" value="${common.maintenanceDate}" 
					onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10'})"/></td>
			</tr>
			<tr>
				<td class="proStyle">维修项目</td>
				<td><input name="maintenanceProject" value="${common.maintenanceProject}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">保养材料</td>
				<td><input name="maintenanceMaterial" id="maintenanceMaterial" value="${common.maintenanceMaterial}"/></td>
			</tr>
			<tr>
				<td class="proStyle">维修材料</td>
				<td><input name="repairMaterials" id="repairMaterials" value="${common.repairMaterials}"/></td>
				<td class="proStyle">维修金额</td>
				<td><input name="repairMoney" value="${common.repairMoney}" class="easyui-validatebox" data-options="validType:'price'"/></td>
			</tr>
			<tr>
				<td class="proStyle">数量</td>
				<td><input id="quantity" name="quantity" value="${common.quantity}" class="easyui-validatebox" data-options="required:true,validType:'number'"/></td>
				<td class="proStyle">单价</td>
				<td><input name="price" id="price" value="${common.price}" class="easyui-validatebox" data-options="required:true,validType:'price'"/></td>
			</tr>
			<tr>
				<td class="proStyle">保养金额</td>
				<td><input name="maintenanceMoney" id="maintenanceMoney"  value="${common.maintenanceMoney}" readonly="readonly"/></td>
				<td class="proStyle">公里节点</td>
				<td><input name="kmNode" value="${common.kmNode}" /></td>
			</tr>
			<tr>
				<td class="proStyle">维修地点</td>
				<td><input name="maintenanceLocation" value="${common.maintenanceLocation}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
