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
	var srchForm ;
	$(function() {
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#printOrderData"),srchForm);
	});
	function rightMenu(){commRightMenu('printOrderData');}
	 
	function importExcel() {
		$("#hiddenWin").window({
			modal:true,
			title:'打印数据导入',
			width:400,
			height:200,
			top:($(window).height() - 400) * 0.5,   
            left:($(window).width() - 500) * 0.5
		});
		
		$("#hiddenWin").html("<iframe src='upload' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
		$("#hiddenWin").window('open');
	}
	
	function printById() {
		var selectRow = $("#printOrderData").datagrid('getSelections');
		var _selectRow = new Array();
		for (var i = 0; i < selectRow.length; ++i) {
			_selectRow.push(selectRow[i].id);
		}
		if (null != selectRow) {
			$.ajax({
				type:"POST",
				url : 'printById?id='+_selectRow,
				dataType : "json",
				success : function(msg){
					exportByHref("${pageContext.request.contextPath}/"+msg.data);
				}
			});
		} else if (null == selectRow){
			alert("请选中你要打印的记录！");
		}
	}
	
	function exportByHref(_href) {
		window.location.href=_href;
		window.print();
	}
	
	function refresh() {
		$('#printOrderData').datagrid('load');
	}
	function _close() {
		$("#hiddenWin").window('close');
	}
</script>

</head>

<body class="easyui-layout" style="width:100%;height:101%;"
	 onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="printOrderData">
				<input type="hidden" name="method" value="page">
				<table class="tableForm datagrid-toolbar"  align="center" >
<!-- 					<tr>						 -->
<!-- 						<th style="padding-left: 100px;">结算单号</th>  -->
<!-- 						<td><input id="settleNo" name="settleNo"/></td>					 -->
<!-- 					</tr> -->
					<tr>
						<td colspan="4" align="center">
<!-- 							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp; -->
<!-- 							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp; -->
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importExcel()">导入数据</a>
						</td>
					</tr>					
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div class="main-data" region="center">
			<table id="printOrderData" class="easy-table">
				<thead>   
			       	   <th data-options="field:'id',width:120,sortable : true, align:'center'">ID</th>
			           <th data-options="field:'origin',width:200,sortable : true, align:'center'">始发地</th>   
			           <th data-options="field:'cargoOwner',width:200,sortable : true, align:'center'">单位名称</th>
			           <th data-options="field:'mailAddress',width:200,sortable : true, align:'center'">寄件地址</th>
			           <th data-options="field:'weight',width:200,sortable : true, align:'center'">重量</th>
			           <th data-options="field:'destinationCity',width:200,sortable : true, align:'center'">目的地城市</th>
			           <th data-options="field:'recipientName',width:200,sortable : true, align:'center'">收件人姓名</th>
			           <th data-options="field:'unitName',width:200,sortable : true, align:'center'">单位名称</th>
			           <th data-options="field:'shippingAddress',width:200,sortable : true, align:'center'">收件地址</th>
			           <th data-options="field:'phone',width:200,sortable : true, align:'center'">联系电话</th>
			           <th data-options="field:'collectionLoans',width:200,sortable : true, align:'center'">代收贷款</th>
			       </tr>   
			   </thead>
			</table>
		</div>
		
		<div id="hiddenWin"  class="easyui-window" data-options="closed:true,iconCls:'icon-save'" ></div>
		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="printOrderData_print" onclick="printById()">打印预览</div>
		</div>
	</body>
</html>
