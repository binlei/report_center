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
		sy.createDatagrid($("#deliNoInRetuData"),srchForm);
	});
	function rightMenu(){ commRightMenu('deliNoInRetuData'); }
	
	function deleteBySerialNo() {
		var selectRow = $("#deliNoInRetuData").datagrid('getSelected');
		if (null != selectRow) {
			if(confirm("确定要删除此记录吗?")) {
				$.ajax({
					type:"POST",
					url : 'deliNoInRetuDataDelete?id='+selectRow.id,
					dataType : "json",
					success : function(msg){
						if ("1" == msg.data) {
							alert("删除成功");
							$('#deliNoInRetuData').datagrid('load');
						} else if ("0" == msg.data) {
							alert("删除失败");						
						}
					}
				});
			}
		} else if (null == selectRow){
			alert("请选中你要删除的记录！");
		}
	}
	
	function importDelExcel() {
		$("#hiddenWin").window({
			modal:true,
			title:'删除数据导入',
			width:400,
			height:200,
			top:($(window).height() - 400) * 0.5,   
            left:($(window).width() - 500) * 0.5
		});
		
		$("#hiddenWin").html("<iframe src='deliNoInRetuMoreDele' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
		$("#hiddenWin").window('open');
		//window.open('importExcelForDeli', "发货数据导入", "height=300,width=400,top=250,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no");
	}
	
	function comeIn() { 
		if ("1" == '${creatorId}') {
			$("#importDelExcel").css('display','none');
			$("#menu").menu("disableItem","div[id$=importDelExcel]");
		}
	}
	
</script>

</head>

<body class="easyui-layout" style="width:100%;height:101%;" onload="comeIn()"
	 onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="deliNoInRetuData">
				<input type="hidden" name="method" value="page">
				<table class="tableForm datagrid-toolbar" align="center">
					<tr>
						<th>序列号</th> 
						<td ><input id="serialNo" name="serialNo"  /></td>					
						<td colspan="4" align="left">
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="importDelExcel()" id="importDelExcel">导入要删除的数据</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>
						</td>
					</tr>					
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div region="center">
			<table id="deliNoInRetuData" class="easy-table">
				<thead>   
			       <tr>  		
			       	   <th data-options="field:'id',width:120,sortable : true, align:'center'">ID</th>
			           <th data-options="field:'serialNo',width:300,sortable : true, align:'center'">序列号</th>
			           <th data-options="field:'customerName',width:300,sortable : true, align:'center'">客户名称</th>
			           <th data-options="field:'stationAddress',width:300,sortable : true, align:'center'">站点地</th>
			           <th data-options="field:'returnDate',width:300,sortable : true, align:'center'">退货日期</th>
			           <th data-options="field:'returnQty',width:300,sortable : true, align:'center'">退货数量</th>
			       </tr>   
			   </thead>
			</table>
		</div>
		
		<div id="hiddenWin"  class="easyui-window" data-options="closed:true,iconCls:'icon-save'" ></div>
		<div id="menu" class="easyui-menu" style="width: 140px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="deliNoInRetuData_delete" onclick="deleteBySerialNo()">删除</div>
			<div id="deliNoInRetuData_import" onclick="importDelExcel()">导入要删除的数据</div>
		</div>
</body>
</html>
