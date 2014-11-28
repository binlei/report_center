<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/addCommonHeader.jsp" %>
<%@ include file="/WEB-INF/view/common/dryLineCommon.jsp" %>
</head>
<script type="text/javascript">
$(function(){
	getAllCarNo();
})
</script>
<body>
	<form id="commentForm" name="commentForm">
		<input type="hidden" name="id" value='${common.id}'>
		<table>
			<tr>
				<td class="proStyle">车牌号</td>
				<td><input name="carcardId" id="carcardId" class="combobox-carNo" class="easyui-validatebox" data-options="required:true" /></td>
				<td class="proStyle">日期</td>
				<td><input name="date" id="d413" class="Wdate" value="${common.date}" onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10'})"/></td>
				<td class="proStyle">驾驶员</td>
				<td><input name="driver" id="driver" value="${common.driver}" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="proStyle">线路号</td>
				<td><input name="lineNo" id="lineNo" value="${common.lineNo}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">起点</td>
				<td><input name=start id="start" value="${common.start}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">终点</td>
				<td><input name="end" id="end"  value="${common.end}" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="proStyle">出发时间</td>
				<td><input name="startTime" class="Wdate" id="startTime" value="${common.startTime}" 
				onfocus="var endTime=$dp.$('endTime');WdatePicker({onpicked:function(){endTime.focus();},dateFmt:'H:mm:ss'})" /></td>
				<td class="proStyle">到达时间</td>
				<td><input name="endTime" class="Wdate" id="endTime" value="${common.endTime}" 
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'H:mm:ss',onpicked:pickedFunc})" /></td>
				<td class="proStyle">出车里程</td>
				<td><input name="startMileage" value="${common.startMileage}" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="proStyle">单程/往返</td>
				<td> 
					<select name="flag" >
						<option value="1" <c:if test="${common.flag eq 1 }">selected="selected"</c:if>>单程</option>
						<option value="2" <c:if test="${common.flag eq 2 }">selected="selected"</c:if>>往返</option>
					</select>
				</td>
				<td class="proStyle">到达里程</td>
				<td><input name="endMileage" value="${common.endMileage}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">运输台数</td>
				<td><input name="transportQuantity" value="${common.transportQuantity}" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="proStyle">行驶里程</td>
				<td><input name="transportMileage" id="transportMileage" value="${common.transportMileage}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">用时</td>
				<td><input name="time" id="time" value="${common.time}" readonly="readonly" /></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
	
	function pickedFunc(){
		
		var time_h = $dp.cal.getP('H') - $dp.$D('startTime').H;
		var time_m = $dp.cal.getP('m') - $dp.$D('startTime').m;
		var time_s = $dp.cal.getP('s') - $dp.$D('startTime').s;
		var time;
		if(time_h == 0 && time_m == 0){
			time = "0";
		}else if(time_h == 0){
			time = "00:" + time_m + "";
		}else{
			time = time_h + ":" +time_m + ":" + time_s;
		}
		$("#time").val(time);
	}
</script>
</body>
</html>
