<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>

<style type="text/css">
#srchForm table th {
	width: 200px;
}
</style>

<script type="text/javascript">
	var srchForm;
	$(function() {
		$('body').layout('collapse', 'north');
		srchForm = $("#srchForm");
		sy.createDatagrid($("#reportData"), srchForm);
	});
	function rightMenu() {
		commRightMenu('reportData');
	}

	function _status() {
		var status = document.getElementById("status");
		var _value = status.options[status.selectedIndex].value;

		if (_value != "已报帐") {
			document.getElementById("proCmccSaleQty").setAttribute("disabled",
					"disabled");
		} else if (_value == "已报帐") {
			document.getElementById("proCmccSaleQty").removeAttribute(
					"disabled");
		}
	}

	function exportExcel() {
		var beginTime = $("#beginTime").parent().children(".combo").children(
				".combo-value").val();
		var endTime = $("#endTime").parent().children(".combo").children(
				".combo-value[name='endTime']").val();
		$.ajax({
			type : "POST",
			url : 'exportExcel?beginTime=' + beginTime + "&endTime=" + endTime
					+ "&status=" + document.getElementById("status").value
					+ "&proCmccSaleQty="
					+ document.getElementById("proCmccSaleQty").value
					+ "&stationAddress="
					+ document.getElementById("stationAddress").value
					+ "&hallProperty="
					+ document.getElementById("hallProperty").value
					+ "&serialNo=" + document.getElementById("serialNo").value,
			dataType : "json",
			success : function(msg) {
				exportByHref("${pageContext.request.contextPath}/" + msg.data);
			}
		});
	}

	function exportByHref(_href) {
		window.location.href = _href;
	}
</script>

</head>

<body class="easyui-layout" style="width: 100%; height: 101%;"
	onkeydown="javascript:keyPressImpl(event);"
	onkeyup="javascript:keyReleaseImpl(event);">

	<!-- 查询表单 -->
	<div class="Search-box" region="north" title="查询条件" style="height: 145px;">
		<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
			<input type="hidden" name="bean" value="reportData"> 
			<input type="hidden" name="method" value="page">
			<%-- 				<input type="hidden" name="flag" value="${flag}" id="flag"> --%>
			<input type="hidden" name="flag" value="" id="flag">
			<table class="tableForm datagrid-toolbar" align="center">
				<tr>
					<th>营业厅归属：</th>
					<td><input id="hallProperty" name="hallProperty" /></td>
					<th>报账状态：</th>
					<td><select id="proCmccSaleQty" onChange="cmccSaleQty()"
						name="proCmccSaleQty" style="width: 150px;" disabled>
							<option value="">请选择</option>
							<option value="1">省移动报账</option>
							<option value="0">地市移动报账</option>
					</select></td>
					<th>站点地：</th>
					<td><input id="stationAddress" name="stationAddress" /></td>
				</tr>
				<tr>
					<th>发货时间：</th>
					<td><input id="beginTime" class="timeInput easyui-datebox"
						name="beginTime" style="width: 90px;" /> ~ <input id="endTime"
						class="timeInput easyui-datebox" name="endTime"
						style="width: 90px;" /></td>
					<th>序列号：</th>
					<td><input id="serialNo" name="serialNo" /></td>
					<th>状态：</th>
					<td><select id="status" onchange="_status()" name="status">
							<option value="">请选择</option>
							<option value="">已发货</option>
							<option value="已发货未销售">已发货未销售</option>
							<option value="已销售未报账">已销售未报账</option>
							<option value="未销售已报帐">未销售已报帐</option>
							<option value="已报帐">已报帐</option>
							<option value="已退货">已退货</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a> &nbsp;&nbsp; 
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="exportExcel()" id="exportExcel">导出数据</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 数据表格 -->
	<div class="main-data" region="center">
		<table id="reportData" class="easy-table">
			<thead>
				<tr style="width: 100%">
					<th
						data-options="field:'id',width:80,sortable : true, align:'center'"></th>
					<th
						data-options="field:'orderLot',width:150,sortable : true, align:'center'">订单批次号</th>
					<th
						data-options="field:'serialNo',width:150,sortable : true, align:'center'">序列号</th>
					<th
						data-options="field:'itemCode',width:130,sortable : true, align:'center'">货品编号</th>
					<th
						data-options="field:'customerName',width:200,sortable : true, align:'center'">客户名称</th>
					<th
						data-options="field:'stationAddress',width:50,sortable : true, align:'center'">站点地</th>
					<th
						data-options="field:'deliveryDate',width:80,sortable : true, align:'center'">发货日期</th>
					<th
						data-options="field:'deliveryMonth',width:80,sortable : true, align:'center'">月份</th>
					<th
						data-options="field:'quantity',width:100,sortable : true, align:'center'">发货渠道库存数量</th>
					<th
						data-options="field:'jsQuantity',width:100,sortable : true, align:'center'">结算库剩余库存</th>
					<th
						data-options="field:'ngbossSaleQty',width:100,sortable : true, align:'center'">NGBOSS销售</th>
					<th
						data-options="field:'proCmccSaleQty',width:100,sortable : true, align:'center'">省移动报账销售</th>
					<th
						data-options="field:'cityCmccSaleQty',width:120,sortable : true, align:'center'">地市移动报账销售</th>
					<th
						data-options="field:'channelQty',width:80,sortable : true, align:'center'">渠道库存</th>
					<th
						data-options="field:'ngbossSaledNoRemQty',width:130,sortable : true, align:'center'">NGBOSS已销未报账数据</th>
					<th
						data-options="field:'returnQty',width:50,sortable : true, align:'center'">退货</th>
					<th
						data-options="field:'saleDate',width:120,sortable : true, align:'center'">终端销售时间</th>
					<th
						data-options="field:'status',width:100,sortable : true, align:'center'">终端业务状态</th>
					<th
						data-options="field:'hallProperty',width:100,sortable : true, align:'center'">营业厅归属</th>
				</tr>
			</thead>
		</table>
	</div>

	<!-- 		<div id="hiddenWin"  class="easyui-window" data-options="closed:true,iconCls:'icon-save'" ></div> -->
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="refreshDagagrid();">刷新</div>
		<div onclick="expandQuery();">展开查询</div>
		<div onclick="collapseQuery();">折叠查询</div>
		<div class="menu-sep"></div>
		<div id="reportData_exportExcel" onclick="exportExcel();">导出数据</div>
	</div>
</body>
</html>
