<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/addCommonHeader.jsp" %>
<%@ include file="/WEB-INF/view/common/dryLineCommon.jsp" %>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('expand','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#dryLineMaintenanceRecord"),srchForm);
		getAllCarNo();
	});
	function rightMenu(){commRightMenu('dryLineMaintenanceRecord');}
</script>

</head>

<body class="easyui-layout" style="width:100%;height:101%;"  
	 onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="dryLineMaintenanceRecord">
				<input type="hidden" name="method" value="page">
				<input type="hidden" id="doMethod" value="doMaintenance">
				<table class="tableForm datagrid-toolbar"  align="center" >
					<tr>							
						<th>日期</th>
						<td colspan="1" >
							<input id="d411" class="Wdate" name="beginTime" style="width: 90px;" 
								onfocus="var d412=$dp.$('d412');WdatePicker({onpicked:function(){d412.focus();},maxDate:'#F{$dp.$D(\'d412\')}',skin:'whyGreen',minDate:'2009-09-10'})" /> ~
							<input id="d412" class="Wdate" name="endTime" style="width: 90px;" 
								onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d411\')}'})" />
						</td>
						<th>车牌号</th>
						<td><input id="plateNumber" name="plateNumber" class="combobox-carNo" /></td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div region="center">
			<table id="dryLineMaintenanceRecord" class="easy-table">
				<thead>   
			       <tr>
			       	   	<th data-options="field:'id',width:80,sortable : true, align:'center'">ID</th>
			           	<th data-options="field:'carNo',width:120,sortable : true, align:'center',formatter:carCard">车号</th>
						<th data-options="field:'maintenanceDate',width:120,sortable : true, align:'center'">日期</th>
						<th data-options="field:'repairMaterials',width:120,sortable : true, align:'center'">维修材料</th>
						<th data-options="field:'maintenanceMaterial',width:120,sortable : true, align:'center'">保养材料</th>
						<th data-options="field:'quantity',width:80,sortable : true, align:'center'">数量</th>
						<th data-options="field:'price',width:120,sortable : true, align:'center'">单价</th>
						<th data-options="field:'maintenanceMoney',width:120,sortable : true, align:'center'">保养金额</th>
						<th data-options="field:'repairMoney',width:120,sortable : true, align:'center'">维修金额</th>
						<th data-options="field:'maintenanceProject',width:120,sortable : true, align:'center'">维修项目</th>
						<th data-options="field:'kmNode',width:120,sortable : true, align:'center'">公里节点</th>
						<th data-options="field:'maintenanceLocation',width:200,sortable : true, align:'center'">维修地点</th>
			       </tr>   
			   </thead>
			</table>
		</div>
		
		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="dryLineMaintenanceRecord_add" onclick="open('addMaintenancePage','add','修改保养记录',650,280)">新增保养记录</div>
			<div id="dryLineMaintenanceRecord_do_edit" onclick="open('editMaintenancePage','edit','修改保养记录',650,280)">修改保养记录</div>
			<div class="menu-sep"></div>
			<div id="dryLineMaintenanceRecord_do_delete" onclick="deleteByIds();">删除</div>
		</div>
</body>
</html>
