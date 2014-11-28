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
		sy.createDatagrid($("#reimData"),srchForm);
	});
	function rightMenu(){ commRightMenu('reimData'); }
	
	function importExcel() {
		$("#hiddenWin").window({
			modal:true,
			title:'报账数据导入',
			width:400,
			height:200,
			top:($(window).height() - 400) * 0.5,   
            left:($(window).width() - 500) * 0.5
		});
		$("#hiddenWin").html("<iframe src='importExcelForReim' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
		$("#hiddenWin").window('open');
	}
	
	function deleteBySerialNo() {
		var selectRow = $("#reimData").datagrid('getSelected');
		if (null != selectRow) {
			if(confirm("确定要删除此记录吗?")) {
				$.ajax({
					type:"POST",
					url : 'reimDataDelete?id='+selectRow.id,
					dataType : "json",
					success : function(msg){
						if ("1" == msg.data) {
							alert("删除成功");
							$('#reimData').datagrid('load');
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
		
		$("#hiddenWin").html("<iframe src='importDelExcelForReim' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
		$("#hiddenWin").window('open');
		//window.open('importExcelForDeli', "发货数据导入", "height=300,width=400,top=250,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no");
	}
	
	function refresh() {
		$('#reimData').datagrid('load');
	}
	
	function comeIn() {
		if ("1" == '${creatorId}') {
			$("#importDelExcel").css('display','none');
			$("#importExcel").css('display','none');
		}
	}
</script>

</head>

<body class="easyui-layout" style="width:100%;height:101%;" onload="comeIn()"
	 onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件"  >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="reimData">
				<input type="hidden" name="method" value="page">
				<table class="tableForm datagrid-toolbar"  align="center" >
					<tr>							
						<th>序列号</th> 
						<td><input id="serialNo" name="serialNo"/></td>					
						<th>客户名称</th>
						<td><input id="customerName" name="customerName"/></td>						
						<td align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="importDelExcel()" id="importDelExcel">导入您所要删除的数据</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>
							&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="importExcel()" id="importExcel">导入数据</a>
						</td>
					</tr>					
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div region="center">
			<table id="reimData" class="easy-table">
				<thead>   
			       <tr>  	
			       	   <th data-options="field:'id',width:120,sortable : true, align:'center'">ID</th>
			           <th data-options="field:'serialNo',width:250,sortable : true, align:'center'">序列号</th>
			           <th data-options="field:'customerName',width:250,sortable : true, align:'center'">客户名称</th>
			           <th data-options="field:'stationAddress',width:250,sortable : true, align:'center'">站点地</th>
			           <th data-options="field:'ngbossSaleQty',width:250,sortable : true, align:'center'">NGBOSS销售</th>
			           <th data-options="field:'proCmccSaleQty',width:250,sortable : true, align:'center'">省移动报账销售</th>
			           <th data-options="field:'cityCmccSaleQty',width:250,sortable : true, align:'center'">地市移动报账销售</th>
			           <th data-options="field:'reimDate',width:250,sortable : true, align:'center'">报账日期</th>
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
			<div id="reimData_do_delete" onclick="deleteBySerialNo()">删除</div>
		</div>
</body>
</html>
