<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/addCommonHeader.jsp" %>
<%@ include file="/WEB-INF/view/common/dryLineCommon.jsp" %>
</head>
<body>
	<form id="commentForm" name="commentForm" >
		<input type="hidden" name="id" value='${common.id}'>
		<table>
			<tr>
				<td class="proStyle">车牌号</td>
				<td><input name="carcard" id="carcard"/></td>
				<td class="proStyle">ETC卡号</td>
				<td><input name="etcNo" value="${common.etcNo}" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="proStyle">充值时间</td>
				<td><input id="d412" name="rechargeDate" class="Wdate" value="${common.rechargeDate}" 
					onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
				<td class="proStyle">充值金额</td>
				<td><input name="rechargeMoney" value="${common.rechargeMoney}" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="proStyle">入口站名</td>
				<td><input name="inStationName" id="inStationName" value="${common.inStationName}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">出口站名</td>
				<td><input name="outStationName" id="outStationName" value="${common.outStationName}" class="easyui-validatebox" data-options="required:true"/></td>
				
			</tr>
			<tr>
				<td class="proStyle">入口时间</td>
				<td><input id="d413" name="inStationStarttime" class="Wdate" value="${common.inStationStarttime}" 
					onfocus="var d414=$dp.$('d414');WdatePicker({onpicked:function(){d414.focus();},maxDate:'#F{$dp.$D(\'d414\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
				<td class="proStyle">出口时间</td>
				<td><input id="d414" name="outStationStarttime" class="Wdate" value="${common.outStationStarttime}" 
					onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d413\')}'})"/></td>
			</tr>
			<tr>
				<td class="proStyle">实收金额</td>
				<td><input name="actualAmount" value="${common.actualAmount}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">ETC卡内余额</td>
				<td><input name="etcBalance" value="${common.etcBalance}" disabled="disabled"/></td>
			</tr>
		</table>
	</form>
</body>
</html>
