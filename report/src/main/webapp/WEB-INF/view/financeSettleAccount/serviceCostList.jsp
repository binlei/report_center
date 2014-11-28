<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#financeServiceCost"),srchForm);
		getSuppliersModelCategory();
	});
	
	function rightMenu(){commRightMenu('financeServiceCost');}
	function clearServiceCost(){
		$(".service-cost").combobox("clear");
	}
	
</script>
</head>

<body class="easyui-layout" style="width:100%;height:101%;"  onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" style="height: 120px" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="financeServiceCost">
				<input type="hidden" name="method" value="page">
				<input type="hidden" id="doMethod" value="doServiceCost">
				<table class="tableForm datagrid-toolbar"  align="center" >
					<tr>							
						<th>供应商</th>
						<td><input id="supplier_id" name="supplier_id" class="service-cost"/></td>
						<th>分类</th>
						<td><input id="category" name="category" class="service-cost"/></td>
						<th>品牌</th>
						<td><input id="brand" name="brand" class="service-cost"/></td>
						<th>型号</th>
						<td><input id="model" class="com-model service-cost" name="model"/></td>
					</tr>
					<tr>
						<td colspan="8" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearServiceCost()">重置</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importExcel('importPage','导入销售报表明细');">导入销售报表明细</a>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div region="center">
			<table id="financeServiceCost" class="easy-table">
				<thead>   
			       <tr>
			       	   	<th data-options="field:'id',width:80,sortable : true, align:'center'">ID</th>
			           	<th data-options="field:'supplier',width:250,sortable : true, align:'center', formatter:supplier" rowspan="2">供应商</th>
						<th data-options="field:'category',width:220,sortable : true, align:'center'" rowspan="2">类别</th>
						<th data-options="field:'brand',width:200,sortable : true, align:'center'" rowspan="2">品牌</th>
						<th data-options="field:'model',width:200,sortable : true, align:'center'" rowspan="2">型号</th>
						<th colspan="4">服务费</th>
						<th data-options="field:'description',width:220,sortable : true, align:'center'" rowspan="2">说明</th>
					</tr>
					<tr>
						<th data-options="field:'serviceCharge',width:120,sortable : true, align:'center',formatter:serviceCharge">每台服务费</th>
						<th data-options="field:'extendProp1',width:120,sortable : true, align:'center'">内免收服务费</th>
						<th data-options="field:'extendProp2',width:120,sortable : true, align:'center',formatter:saleAmount">销售金额的百分比</th>
						<th data-options="field:'extendProp3',width:250,sortable : true, align:'center'">免收服务费</th>
			       </tr>   
			   </thead>
			</table>
		</div>
	 
		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="financeServiceCost_importExcel" onclick="importExcel('importPage','导入销售报表明细')">导入销售报表</div>
			<div class="menu-sep"></div>
			<div id="financeServiceCost_do_add" onclick="open('addServiceCostPage','add','服务费配置',650,400)">新增服务费</div>
			<div id="financeServiceCost_do_edit" onclick="open('editServiceCostPage','edit','服务费配置',650,400)">修改服务费</div>
			<div class="menu-sep"></div>
			<div id="financeServiceCost_do_delete" onclick="deleteByIds();">删除</div>
		</div>
		</body>
	</html>