<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/addCommonHeader.jsp"%>
<%@ include file="/WEB-INF/view/common/branshLineCommon.jsp"%>
<script type="text/javascript">

	$(function(){
		getSubstationId();
		getCarDriver();
	});
	function _mileage() {
		var startMileage = $("#startMileage").val();
		var stopMileage = $("#stopMileage").val();
		var fuelPrice = $("#fuelPrice").val();
		var mileage = Number(stopMileage) - Number(startMileage);
		$("#mileage").val(mileage);
		$("#fuelCosts").val(Number(fuelPrice) * Number(mileage));
	}

	function _fuelPrice() {
		var fuelPrice = $("#fuelPrice").val();
		var mileage = $("#mileage").val();
		$("#fuelCosts").val(Number(fuelPrice) * Number(mileage));
	}
</script>
</head>
<body>
	<form id="commentForm" name="addInfo" action="" method="post">
		<input type="hidden" name="id" value='${common.id}'>
		<table>
			<tr>
				<td class="proStyle">分站</td>
				<td><input name="subStation" id="subStation" class="combobox-substation" /></td>
				<td class="proStyle">日期</td>
				<td><input id="d412" name="date" value="${common.date }" class="Wdate" onfocus="WdatePicker({skin:'whyGreen'})" /></td>
				<td class="proStyle">驾驶员</td>
				<td><input class="combobox-driver" name="driver" id="driver" value="${common.driver}" /></td>
			</tr>
			<tr>
				<td class="proStyle">车牌号</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="licenseNo" id="licenseNo" value="${common.licenseNo}" /></td>
				<td class="proStyle">派送线路</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="line" value="${common.line}" /></td>
				<td class="proStyle">GPS编号</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="gpsNo" value="${common.gpsNo}" /></td>
			</tr>
			<tr>
				<td class="proStyle">出车时间</td>
				<td><input name="startTime" class="Wdate" id="startTime" value="${common.startTime}" onfocus="var d412=$dp.$('stopTime');WdatePicker({onpicked:function(){d412.focus();},dateFmt:'H:mm:ss'});" /></td>
				<td class="proStyle">收车时间</td>
				<td><input name="stopTime" class="Wdate" id="stopTime" value="${common.stopTime}" onfocus="WdatePicker({manDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'H:mm:ss'})" /></td>
				<td class="proStyle">发车里程(公里)</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="startMileage" id="startMileage"
					value="${common.startMileage}" onblur="_mileage();" />
				</td>
			</tr>
			<tr>
				<td class="proStyle">收车里程(公里)</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="stopMileage" id="stopMileage" value="${common.stopMileage}" onblur="_mileage();" />
				</td>
				<td class="proStyle">行驶里程(公里)</td>
				<td><input class="easyui-validatebox" data-options="required:true" id="mileage" name="mileage" value="${common.mileage}" readonly="readonly" /></td>
				<td class="proStyle">取派票数</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="ticketQuantity" value="${common.ticketQuantity}" /></td>
			</tr>
			<tr>
				<td class="proStyle">取派厅数</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="hallQuantity" value="${common.hallQuantity}" /></td>
				<td class="proStyle">取派件数</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="pieQuantity" value="${common.pieQuantity}" /></td>
				<!-- <td class="proStyle">取派台数</td>
				<td>
					<input name="pcsQuantity" value="${common.pcsQuantity}" />
				</td> -->
			</tr>
			<tr>
				<td class="proStyle">燃油单价</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="fuelPrice" id="fuelPrice" value="${common.fuelPrice}" onblur="_fuelPrice();" /></td>
				<td class="proStyle">燃油费</td>
				<td><input class="easyui-validatebox" data-options="required:true" id="fuelCosts" name="fuelCosts" value="${common.fuelCosts}" readonly="readonly" /></td>
				<td class="proStyle">半日/全日</td>
				<td><select name="day" id="day">
						<option value="0.5"
							<c:if test="${common.day eq 0.5 }">selected="selected"</c:if>>半日0.5</option>
						<option value="1"
							<c:if test="${common.day eq 1 }">selected="selected"</c:if>>全日1</option>
				</select></td>
			</tr>
			<tr>
				<td class="proStyle">租车费</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="rentalFee" value="${common.rentalFee}" /></td>
				<td class="proStyle">停车路桥费</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="parkingFee" value="${common.parkingFee}" /></td>
				<td class="proStyle">奖惩</td>
				<td><input name="award" value="${common.award}" /></td>
			</tr>
			<tr>
				<td class="proStyle">燃油费是否报销</td>
				<td><select name="isReim" id="isReim">
						<option value="是"
							<c:if test="${common.isReim eq '是' }">selected="selected"</c:if>>是</option>
						<option value="否"
							<c:if test="${common.isReim eq '否' }">selected="selected"</c:if>>否</option>
				</select></td>
				<td class="proStyle">未妥投票数</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="noDelivery" value="${common.noDelivery}" /></td>
				<td class="proStyle">拒签票数</td>
				<td><input name="refusal" value="${common.refusal}" /></td>
			</tr>
			<tr>
				<td class="proStyle">签收票数</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="voteSign" value="${common.voteSign}" /></td>
				<td class="proStyle">推广厅数</td>
				<td><input class="easyui-validatebox" data-options="required:true" name="extendProp2" value="${common.extendProp2}" /></td>
			</tr>
			<tr>
				<td class="proStyle">备注</td>
				<td colspan="5"><textarea id="remarks" name="remarks" style="width: 99%; height: 50px; resize:none;">${common.remarks}</textarea></td>
			</tr>
		</table>
	</form>
</body>
</html>
