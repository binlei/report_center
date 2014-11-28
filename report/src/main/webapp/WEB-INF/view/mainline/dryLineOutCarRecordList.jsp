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
		sy.createDatagrid($("#dryLineOutCarRecord"),srchForm);
		getAllCarNo();
	});
	
	function rightMenu(){commRightMenu('dryLineOutCarRecord');}
</script>
</head>
<body class="easyui-layout" style="width:100%;height:101%;"  
	 onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="dryLineOutCarRecord">
				<input type="hidden" name="method" value="page">
				<input type="hidden" id="doMethod" value="doOutCar">
				<table class="tableForm datagrid-toolbar"  align="center" >
					<tr>							
						<th>日期</th>
						<td colspan="1" >
							<input id="d411" class="Wdate" name="beginTime" style="width: 90px;" onfocus="var d412=$dp.$('d412');WdatePicker({onpicked:function(){d412.focus();},maxDate:'#F{$dp.$D(\'d412\')}',skin:'whyGreen',minDate:'2009-09-10'})" /> ~
							<input id="d412" class="Wdate" name="endTime" style="width: 90px;"  onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d411\')}'})" />
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
			<table id="dryLineOutCarRecord" class="easy-table">
				<thead>   
			       <tr>
			       	   	<th data-options="field:'id',width:80,sortable : true, align:'center'">ID</th>
			           	<th data-options="field:'carNo',width:120,sortable : true, align:'center',formatter:carCard">车号</th>
						<th data-options="field:'date',width:140,sortable : true, align:'center'">日期</th>
						<th data-options="field:'driver',width:120,sortable : true, align:'center'">驾驶员</th>
						<th data-options="field:'lineNo',width:120,sortable : true, align:'center'">线路号</th>
						<th data-options="field:'startTime',width:120,sortable : true, align:'center'">出车时间</th>
						<th data-options="field:'startMileage',width:80,sortable : true, align:'center'">发车里程</th>
						<th data-options="field:'start',width:120,sortable : true, align:'center'">起点</th>
						<th data-options="field:'end',width:120,sortable : true, align:'center'">终点</th>
						<th data-options="field:'single',width:80,sortable : true, align:'center'">单程/往返</th>
						<th data-options="field:'endTime',width:140,sortable : true, align:'center'">到达时间</th>
						<th data-options="field:'endMileage',width:80,sortable : true, align:'center'">到达里程</th>
						<th data-options="field:'transportQuantity',width:80,sortable : true, align:'center'">运输台数</th>
						<th data-options="field:'mileage',width:80,sortable : true, align:'center'">行驶里程</th>
						<th data-options="field:'money',width:80,sortable : true, align:'center'">金额</th>
						<th data-options="field:'time',width:120,sortable : true, align:'center'">用时</th>
			       </tr>   
			   </thead>
			</table>
		</div>

	<div id="menu" class="easyui-menu" style="width: 140px; display: none;">
		<div onclick="refreshDagagrid();">刷新</div>
		<div onclick="expandQuery();">展开查询</div>
		<div onclick="collapseQuery();">折叠查询</div>
		<div class="menu-sep"></div>
		<div id="dryLineOutCarRecord_import" onclick="importExcel('outCarRecordPage','导入车辆出车记录');">导入车辆出车记录</div>
		<div class="menu-sep"></div>
		<div id="dryLineOutCarRecord_add" onclick="open('addOutCarPage','add','新增出车登记',880,320)">新增出车登记</div>
		<div id="dryLineOutCarRecord_do_edit" onclick="open('editOutCarPage','edit','修改出车登记',880,320)">修改出车登记</div>
		<div class="menu-sep"></div>
		<div id="dryLineOutCarRecord_do_delete" onclick="deleteByIds();">删除</div>
	</div>
</body>
</html>