<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>

<style type="text/css">
.datagrid-cell {
	width: 200px;
}
</style>

<script type="text/javascript">
	var srchForm;
	$(function() {
		$('body').layout('collapse', 'north');
		srchForm = $("#srchForm");
		sy.createDatagrid($("#serviceChargeData"), srchForm);
	});
	function rightMenu() {
		commRightMenu('serviceChargeData');
	}

	function refresh() {
		$('#serviceChargeData').datagrid('load');
	}

	function closeWin() {
		$("#hiddenWin").window('close');
	}

	function importCharge() {
		$("#hiddenWin").window({
			modal : true,
			title : '导入服务费数据',
			width : 400,
			height : 200,
			top : ($(window).height() - 400) * 0.5,
			left : ($(window).width() - 500) * 0.5
		});

		$("#hiddenWin")
				.html(
						"<iframe src='importCharge?flag=1' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
		$("#hiddenWin").window('open');
	}

	function addCharge() {
		$("#hiddenWin").window({
			modal : true,
			title : '新增数据',
			width : 350,
			height : 200,
			top : ($(window).height() - 400) * 0.5,
			left : ($(window).width() - 500) * 0.5
		});

		$("#hiddenWin")
				.html(
						"<iframe src='addCharge?flag=1' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
		$("#hiddenWin").window('open');
	}

	function importLANpiece() {
		$("#hiddenWin").window({
			modal : true,
			title : '导入销售明细表',
			width : 400,
			height : 200,
			top : ($(window).height() - 400) * 0.5,
			left : ($(window).width() - 500) * 0.5
		});

		$("#hiddenWin")
				.html(
						"<iframe src='importCharge?flag=2' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
		$("#hiddenWin").window('open');
	}

	function editCharge() {
		var selectRow = $("#serviceChargeData").datagrid('getSelected');
		if (null != selectRow) {
			$("#hiddenWin").window({
				modal : true,
				title : '修改数据',
				width : 350,
				height : 200,
				top : ($(window).height() - 400) * 0.5,
				left : ($(window).width() - 500) * 0.5
			});

			$("#hiddenWin")
					.html(
							"<iframe src='addCharge?flag=2&id="
									+ selectRow.id
									+ "' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
			$("#hiddenWin").window('open');
		} else {
			alert("请勾选要修改的记录！");
		}
	}

	function deleteCharge() {
		var selectRow = $("#serviceChargeData").datagrid('getSelected');
		if (null != selectRow) {
			if (confirm("确定要删除此记录？")) {
				$.ajax({
					type : "POST",
					url : 'deleteCharge?id=' + selectRow.id,
					dataType : "json",
					success : function(msg) {
						if ("1" == msg.data) {
							alert("删除成功");
							refresh();
						} else if ("0" == msg.data) {
							alert("删除失败");
						}
					}
				});
			}
		} else {
			alert("请勾选要删除的记录！");
		}
	}
</script>

</head>

<body class="easyui-layout" style="width: 100%; height: 101%;"
	onkeydown="javascript:keyPressImpl(event);"
	onkeyup="javascript:keyReleaseImpl(event);">

	<!-- 查询表单 -->
	<div class="Search-box" region="north" title="查询条件" style="height:110px;"> 
		<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
			<input type="hidden" name="bean" value="serviceChargeData"> 
			<input type="hidden" name="method" value="page">
			<table class="tableForm datagrid-toolbar" align="center">
				<tr>
					<th>品类</th>
					<td><input id="category" name="category" /></td>
					<th>型号</th>
					<td><input id="kind" name="kind" /></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importCharge()">导入配置表</a>&nbsp;&nbsp;&nbsp; 
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp; 
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp; 
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importLANpiece()">导入销售明细表</a>&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 数据表格 -->
	<div class="main-data" region="center">
		<table id="serviceChargeData" class="easy-table">
			<thead>
				<tr style="width: 100%">
					<th
						data-options="field:'id',width:120,sortable : true, align:'center'">ID</th>
					<th
						data-options="field:'category',width:120,sortable : true, align:'center'">品类</th>
					<th
						data-options="field:'kind',width:200,sortable : true, align:'center'">型号</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="hiddenWin" class="easyui-window"
		data-options="closed:true,iconCls:'icon-save'"></div>
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="refreshDagagrid();">刷新</div>
		<div onclick="expandQuery();">展开查询</div>
		<div onclick="collapseQuery();">折叠查询</div>
		<div class="menu-sep"></div>
		<div id="serviceChargeData_add" onclick="addCharge();">新增</div>
		<div id="serviceChargeData_do_edit" onclick="editCharge();">修改</div>
		<div id="serviceChargeData_delete" onclick="deleteCharge()">删除</div>
		<div class="menu-sep"></div>
		<div id="serviceChargeData_importLANpiece" onclick="importCharge()">导入配置表</div>
		<div id="serviceChargeData_importLANpiece" onclick="importLANpiece()">导入销售明细表</div>
	</div>
</body>
</html>
