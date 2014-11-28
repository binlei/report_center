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
<%@ include file="/WEB-INF/view/common/branshLineCommon.jsp" %>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#branchLineDrawoutRecord"),srchForm);
		getSubstationId();
	});
	
	function rightMenu(){
		commRightMenu('branchLineDrawoutRecord');
		$("#menu").menu("disableItem","div[id$='confirm']");
		var row = getRowData('branchLineDrawoutRecord');
		if(row != null){
			if(row.extendProp1 != '已确认'){
				$("#menu").menu("enableItem","div[id$='confirm']");
			}else{
				$("#menu").menu("disableItem","div[id$='_disable']");
			}
		}
	}
</script>
</head>
<body class="easyui-layout" onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 	<div class="Search-box" region="north" title="查询条件" style="height: 100px;">
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="branchLineDrawoutRecord">
				<input type="hidden" name="method" value="page">
				<input type="hidden" id="doMethod" value="do">
				<table class="tableForm datagrid-toolbar" align="center">
					<tr>							
						<th><img class='item-img' src='${ctx}/images/city.png'> 分站 </th> 
						<td><input id="subStation" name="subStation" class="combobox-substation"/></td> 
						<th>日期</th>
						<td colspan="1">
							  <input id="d411" class="Wdate" name="beginTime" style="width: 90px;" onfocus="var d412=$dp.$('d412');WdatePicker({onpicked:function(){d412.focus();},maxDate:'#F{$dp.$D(\'d412\')}',skin:'whyGreen',minDate:'2009-09-10'})" />
							~ <input id="d412" class="Wdate" name="endTime" style="width: 90px;" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d411\')}'})" />
						</td>
						</tr>
						<tr>						
						<th><img class='item-img' src='${ctx}/images/drive-user.png'> 驾驶员</th>
						<td><input id="driver" name="driver"/></td>
						<td align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="exportExecl('exportDrawoutRecord','登记数据导出')">导出数据</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div region="center"> 
			<table id="branchLineDrawoutRecord" class="easy-table">
				<thead>   
			       <tr>
			       	   	<th data-options="field:'id',width:80,sortable : true, align:'center'">ID</th>
						<th data-options="field:'extendProp1',width:120,sortable : true, align:'center'">数据确认</th>
			           	<th data-options="field:'subStation',width:120,sortable : true, align:'center',formatter:subSatation">分站</th>
						<th data-options="field:'date',width:120,sortable : true, align:'center'">日期</th>
						<th data-options="field:'licenseNo',width:120,sortable : true, align:'center'">车牌号</th>
						<th data-options="field:'driver',width:120,sortable : true, align:'center'">驾驶员</th>
						<th data-options="field:'line',width:160,sortable : true, align:'center'">派送线路</th>
						<th data-options="field:'gpsNo',width:120,sortable : true, align:'center'">GPS编号</th>
						<th data-options="field:'startTime',width:120,sortable : true, align:'center'">出车时间</th>
						<th data-options="field:'startMileage',width:120,sortable : true, align:'center'">发车里程(公里)</th>
						<th data-options="field:'stopTime',width:120,sortable : true, align:'center'">收车时间</th>
						<th data-options="field:'stopMileage',width:120,sortable : true, align:'center'">收车里程(公里)</th>
						<th data-options="field:'mileage',width:120,sortable : true, align:'center'">行驶里程(公里)</th>
						<th data-options="field:'extendProp2',width:80,sortable : true, align:'center'">推广厅数</th>
						<th data-options="field:'ticketQuantity',width:120,sortable : true, align:'center'">取派票数</th>
						<th data-options="field:'hallQuantity',width:120,sortable : true, align:'center'">取派厅数</th>
						<th data-options="field:'pieQuantity',width:120,sortable : true, align:'center'">取派件数</th>
<!-- 						<th data-options="field:'pcsQuantity',width:120,sortable : true, align:'center'">取派台数</th> -->
						<th data-options="field:'fuelPrice',width:90,sortable : true, align:'center'">燃油单价</th>
						<th data-options="field:'fuelCosts',width:80,sortable : true, align:'center'">燃油费</th>
						<th data-options="field:'day',width:120,sortable : true, align:'center'">半日/全日</th>
						<th data-options="field:'rentalFee',width:80,sortable : true, align:'center'">租车费</th>
						<th data-options="field:'parkingFee',width:120,sortable : true, align:'center'">停车路桥费</th>
						<th data-options="field:'award',width:80,sortable : true, align:'center'">奖惩</th>
						<th data-options="field:'isReim',width:120,sortable : true, align:'center'">燃油费是否报销</th>
						<th data-options="field:'noDelivery',width:90,sortable : true, align:'center'">未妥投票数</th>
						<th data-options="field:'refusal',width:80,sortable : true, align:'center'">拒签票数</th>
						<th data-options="field:'voteSign',width:80,sortable : true, align:'center'">签收票数</th>
						<th data-options="field:'remarks',width:200,sortable : true, align:'center'">备注</th>
			       </tr>
			   </thead>
			</table>
		</div>
 
		<div id="menu" class="easyui-menu" style="width: 140px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="branchLineDrawoutRecord_do_confirm" onclick="confirmData();">数据确认</div>
			<div class="menu-sep"></div>
			<div id="branchLineDrawoutRecord_add" onclick="open('addPage','add','新增车辆登记',890,420)">新增车辆登记</div>
			<div id="branchLineDrawoutRecord_do_edit" onclick="open('editPage','edit','修改车辆登记',890,420)">修改车辆登记</div>
			<div class="menu-sep"></div>
			<div id="branchLineDrawoutRecord_do_export" onclick="exportExecl('exportDrawoutRecord')">导出登记数据</div>
			<div class="menu-sep"></div>
			<div id="branchLineDrawoutRecord_do_delete" onclick="deleteByIds()">删除</div>
		</div>
</body>
</html>
