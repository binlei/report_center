<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/drylineListUtil.jsp"></jsp:include>
<%@ include file="/WEB-INF/view/common/dryLineCommon.jsp" %>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('expand','north');
// 		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#drylineReportMonthlyStatistics"),srchForm);
		getAllCarNoCar();
	});
	function rightMenu(){commRightMenu('drylineReportMonthlyStatistics');}// 右击事件
</script>
</head>
<body class="easyui-layout" style="width:100%;height:100%;" onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">

	 	<div class="Search-box" region="north" title="查询条件">
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="drylineReportMonthlyStatistics">
				<input type="hidden" name="method" value="page">
				<input type="hidden" name="type" id="type" value="DEPOSIT" />
				<table class="tableForm datagrid-toolbar"  align="center" >
					<tr>							
						<th>车牌号</th>
						<td><input id="carcard" name="carcard" class="combobox-carNo"/></td>
						<th>日期</th>
						<td colspan="1" >
							<input id="d411" class="Wdate" name="startTime" style="width: 90px;" 
								onfocus="var d412=$dp.$('d412');WdatePicker({onpicked:function(){d412.focus();},maxDate:'#F{$dp.$D(\'d412\')}',skin:'whyGreen',minDate:'2009-09-10'})" /> ~
							<input id="d412" class="Wdate" name="endTime" style="width: 90px;" 
								onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d411\')}'})" />
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div region="center">
			<table id="drylineReportMonthlyStatistics" class="easy-table" style="width:100%;height:100%;">
				<thead>  
					<tr> 
						<th data-options="field:'id',width:200,sortable : true,hidden:true">ID</th>
		           	    <th data-options="field:'carcard',width:120,sortable : true, align:'center'" rowspan="2">车号</th>
					    <th data-options="field:'useTime',width:120,sortable : true, align:'center'" rowspan="2">已使用时间(月)</th>
					    <th data-options="field:'monthlyDepreciation',width:120,sortable : true, align:'center'" rowspan="2">每月折旧金额</th>
					    <th data-options="field:'totalDepreciation',width:120,sortable : true, align:'center'" rowspan="2">累计折扣</th>
					    <th colspan="4">月度统计</th>
					    <th data-options="field:'monthlyCost',width:120,sortable : true, align:'center'" rowspan="2">月度费用小计</th>
					    <th colspan="3">月度统计</th>
					    
					    <th data-options="field:'hundredKilometerFuel',width:120,sortable : true, align:'center'" rowspan="2">百公里油耗</th>
					    <th data-options="field:'hundredKilometerCost',width:120,sortable : true, align:'center'" rowspan="2">百公里费用</th>
					    <th data-options="field:'transportCost',width:120,sortable : true, align:'center'" rowspan="2">单台干线运输成本</th>
				    </tr>
				    <tr>
					    <th data-options="field:'gasolineMoney',width:120,sortable : true, align:'center'">汽油金额</th>
					    <th data-options="field:'etcMoney',width:120,sortable : true, align:'center'">ETC 消费金额</th>
					    <th data-options="field:'maintenanceMoney',width:120,sortable : true, align:'center'">保养金额</th>
					    <th data-options="field:'repairMoney',width:120,sortable : true, align:'center'">维修金额</th>
					    
					    <th data-options="field:'transportQuantity',width:120,sortable : true, align:'center'">运输台数</th>
					    <th data-options="field:'travelMileage',width:120,sortable : true, align:'center'">行驶里程</th>
					    <th data-options="field:'consumptionQuantity',width:120,sortable : true, align:'center'">消费数量（出）</th>
			        </tr>
			   </thead>
			</table>
		</div>
		 
		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="drylineReportMonthlyStatistics_do_export" onclick="exportExecl('exportDrawoutRecord')">导出数据</div>
		</div>
</body>
</html>
