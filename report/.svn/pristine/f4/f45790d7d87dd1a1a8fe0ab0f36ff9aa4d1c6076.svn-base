<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/addCommonHeader.jsp" %>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('expand','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#dryLineCarRecord"),srchForm);
	});
	function rightMenu(){commRightMenu('dryLineCarRecord');}
 
</script>

</head>

<body class="easyui-layout" style="width:100%;height:101%;"  
	 onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="dryLineCarRecord">
				<input type="hidden" name="method" value="page">
				<input type="hidden" id="doMethod" value="doCar">
				<table class="tableForm datagrid-toolbar"  align="center" >
					<tr>							
						<th>购置时间</th>
						<td colspan="1" >
							<input id="d411" class="Wdate" name="beginTime" style="width: 90px;" 
							onfocus="var d412=$dp.$('d412');WdatePicker({onpicked:function(){d412.focus();},maxDate:'#F{$dp.$D(\'d412\')}',skin:'whyGreen',minDate:'2009-09-10'})" /> ~
							<input id="d412" class="Wdate" name="endTime" style="width: 90px;" 
							onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d411\')}'})" />
						</td>
						<th>购置公司</th>
						<td><input id="company" name="company"/></td>
						<td style="text-align: left; padding-left: 20px;">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
<!-- 							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="exportAutoRecord()">导出数据</a>&nbsp;&nbsp;&nbsp; -->
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div region="center">
			<table id="dryLineCarRecord" class="easy-table">
				<thead>   
			       <tr>
			       	   	<th data-options="field:'id',width:80,sortable : true, align:'center'">ID</th>
						<th data-options="field:'carCard',width:120,sortable : true, align:'center'">车号</th>
						<th data-options="field:'oilCard',width:120,sortable : true, align:'center'">油卡号</th>
						<th data-options="field:'etcCard',width:120,sortable : true, align:'center'">ETC号</th>
			           	<th data-options="field:'date',width:160,sortable : true, align:'center'">购置时间</th>
						<th data-options="field:'company',width:120,sortable : true, align:'center'">购置公司</th>
						<th data-options="field:'brand',width:80,sortable : true, align:'center'">品牌</th>
						<th data-options="field:'carKind',width:120,sortable : true, align:'center'">车型</th>
						<th data-options="field:'price',width:80,sortable : true, align:'center'">购置价格</th>
						<th data-options="field:'capacity',width:80,sortable : true, align:'center'">载重量</th>
						<th data-options="field:'amortizeAge',width:80,sortable : true, align:'center'">摊销年限</th>
						<th data-options="field:'salvageValue',width:80,sortable : true, align:'center'">残值</th>
						<th data-options="field:'oldMoney',width:120,sortable : true, align:'center'">每月折旧额</th>
						<th data-options="field:'allMoney',width:120,sortable : true, align:'center'">已摊销总额</th>
						<th data-options="field:'notMoney',width:120,sortable : true, align:'center'">未摊销金额</th>
			       </tr>   
			   </thead>
			</table>
		</div>
		
		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="dryLineCarRecord_do_add" onclick="open('addCarPage','add','新增车辆信息',650,250)">新增信息</div>
			<div id="dryLineCarRecord_do_edit" onclick="open('editCarPage','edit','修改车辆信息',650,250)">修改信息</div>
			<div class="menu-sep"></div>
			<div id="dryLineCarRecord_do_delete" onclick="deleteByIds()">删除</div>
		</div>
</body>
</html>
