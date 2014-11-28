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
		sy.createDatagrid($("#serialData"),srchForm);
	});
	
	function rightMenu(){ commRightMenu('serialData'); }

	function updateInfo() {
		var selectRow = $("#serialData").datagrid('getSelected');
		if (null != selectRow) {
			$("#hiddenWin").window({
				modal : true,
				title : '修改串码',
				width : 710,
				height : 450,
				top : 50,
				left : 500
			});
			$("#hiddenWin")
					.html(
							"<iframe src='addInfo?id="
									+ selectRow.id
									+ "' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
			$("#hiddenWin").window('open');
		} else {
			alert("请勾选要修改的记录！");
		}
	}

	function deleteById() {
		var selectRow = $("#serialData").datagrid('getSelections');
		var _selectRow = new Array();
		for (var i = 0; i < selectRow.length; ++i) {
			_selectRow.push(selectRow[i].id);
		}
		if (null != selectRow) {
			if (confirm("确定要删除此记录吗?")) {
				$.ajax({
					type : "POST",
					url : 'deleteById?id=' + _selectRow,
					dataType : "json",
					success : function(msg) {
						if ("1" == msg.data) {
							$('#serialData').datagrid('reload');
						} else if ("0" == msg.data) {
							alert("删除失败");
						}
					}
				});
			}
		} else if (null == selectRow) {
			alert("请选中你要删除的记录！");
		}
	}
</script>
</head>
<body class="easyui-layout" style="width: 100%; height: 101%;" onkeydown="javascript:keyPressImpl(event);"
	onkeyup="javascript:keyReleaseImpl(event);">
	<!-- 查询表单 -->
	<div class="Search-box" region="north" title="查询条件" style="height: 145px;">
		<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
			<input type="hidden" name="bean" value="serialData"> 
			<input type="hidden" name="method" value="page">
			<table class="tableForm datagrid-toolbar" align="center">
				<tr>
					<th>串号：</th>
					<td><input id="serialNo" name="serialNo"  /></td>
					<th >商品：</th>
					<td><input id="product" name="product"  /></td>
					<th >地区：</th>
					<td><input id="address" name="address"  /></td>
				</tr>
				<tr>
					<th >客户：</th>
					<td><input id="customer" name="customer"  /></td>
					<th >运单号：</th>
					<td><input id="billNo" name="billNo"  /></td>
					<th >供应商：</th>
					<td><input id="supplier" name="supplier"  /></td>
				</tr>
				<tr>
					<th >订单号：</th>
					<td><input id="orderNo" name="orderNo"  /></td>
					<th >状态：</th>
					<td><select id="status" name="status" >
							<option value="">请选择</option>
							<option value="发货">发货</option>
							<option value="拒签">拒签</option>
							<option value="退货">退货</option>
					</select></td>
					<th >出/入库：</th>
					<td><select id="warehouse" name="warehouse" >
							<option value="">请选择</option>
							<option value="出库">出库</option>
							<option value="入库">入库</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="8" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp; 
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a> &nbsp;&nbsp;
				</tr>
			</table>
		</form>
	</div>
	<!-- 数据表格 -->
	<div region="center">
		<table id="serialData" class="easy-table">
			<thead>
				<tr>
					<th data-options="field:'id',width:120,sortable : true, align:'center'">ID</th>
					<th data-options="field:'serialNo',width:250,sortable : true, align:'center'">串号</th>
					<th data-options="field:'product',width:250,sortable : true, align:'center'">商品</th>
					<th data-options="field:'serialDate',width:250,sortable : true, align:'center'">日期</th>
					<th data-options="field:'quantity',width:250,sortable : true, align:'center'">数量</th>
					<th data-options="field:'price',width:250,sortable : true, align:'center'">单价</th>
					<th data-options="field:'money',width:250,sortable : true, align:'center'">金额</th>
					<th data-options="field:'address',width:250,sortable : true, align:'center'">地区</th>
					<th data-options="field:'customer',width:250,sortable : true, align:'center'">客户</th>
					<th data-options="field:'supplier',width:250,sortable : true, align:'center'">供应商</th>
					<th data-options="field:'orderNo',width:250,sortable : true, align:'center'">订单号</th>
					<th data-options="field:'billNo',width:250,sortable : true, align:'center'">运单号</th>
					<th data-options="field:'warehouse',width:250,sortable : true, align:'center'">出/入库</th>
					<th data-options="field:'status',width:250,sortable : true, align:'center'">状态</th>
					<th data-options="field:'flag',width:250,sortable : true, align:'center'">数值</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="showDialog" class="easyui-dialog" closed="true" buttons="#dlg-buttons">
		<iframe scrolling="NO" id='openReceiveFeedBack' name="openReceiveFeedBack" frameborder="0" src=""></iframe>
	</div>	

	<div id="menu" class="easyui-menu" style="width: 130px; display: none;">
		<div onclick="refreshDagagrid();">刷新</div>
		<div onclick="expandQuery();">展开查询</div>
		<div onclick="collapseQuery();">折叠查询</div>
		<div class="menu-sep"></div>
		<div id="serialData_do_add" onclick="add('addInfo','增加',680,430)">新增</div>
		<div id="serialData_do_edit" onclick="edit('addInfo','serialData','修改',680,430)">修改</div>
		<div id="serialData_do_disable" onclick="deleteById()">删除</div>
		<div class="menu-sep"></div>
		<div id="serialData_do_import" onclick="importExcel('importExcel','串码导入')">串码导入</div>
		<div id="serialData_do_export" onclick="exportExecl('exportExcel')">串码流转明细导出</div>
	</div>
</body>
</html>
