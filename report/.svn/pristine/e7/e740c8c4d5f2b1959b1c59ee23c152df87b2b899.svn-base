<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/addCommonHeader.jsp" %>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#transport"),srchForm);
	});
	function rightMenu(){ commRightMenu('transport'); }
 
	function deleteMoreByOrderNo() {
		$("#hiddenWin").window({
			modal:true,
			title:'批量数据删除导入',
			width:400,
			height:200,
			top:($(window).height() - 400) * 0.5,   
            left:($(window).width() - 500) * 0.5
		});
		
		$("#hiddenWin").html("<iframe src='addDeleteMorePage' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
		$("#hiddenWin").window('open');	
	}
	
	function mateData() {
		$("#hiddenWin").window({
			modal:true,
			title:'匹配数据导入',
			width:400,
			height:200,
			top:($(window).height() - 400) * 0.5,   
            left:($(window).width() - 500) * 0.5
		});
		
		$("#hiddenWin").html("<iframe src='mateData' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
		$("#hiddenWin").window('open');	
	}
	
	function refresh() {
		$('#transport').datagrid('load');
	}
	
	function closeWin() {
		$("#hiddenWin").window('close');
	}
	
	function comeIn() {
		if ("34" == '${creatorId}' || "35" == '${creatorId}' || "36" == '${creatorId}' || "37" == '${creatorId}') {
			$("#outWarehouse").css('display','none');
			$("#importPiece").css('display','none');
			$("#deleteMoreByOrderNo").css('display','none');
			$("#transport_delete").css('display','none');
			$("#transport_add").css('display','none');
			$("#transport_do_edit").css('display','none');
			$("#mateData").css('display','');
		}
		if("36" == '${creatorId}'){
			$("#transport_do_edit").css('display','block');
		}
	}
	
</script>
</head>

<body class="easyui-layout" style="width:100%;height:101%;" onload="comeIn()"
	 onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" style="height:160px;">
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="transport">
				<input type="hidden" name="flag" value="2" id="flag">
				<input type="hidden" name="method" value="page">
				<input type="hidden" id="doMethod" value="doTransForm">
				<table class="tableForm datagrid-toolbar" align="center" >
					<tr >						
						<th >货主</th> 
						<td><input id="project" name="project" /></td>					
						<th >供应商</th>
						<td><input id="supplier" name="supplier" /></td>						
						<th >客户名称</th>
						<td><input id="customer" name="customer" /></td>
					</tr>
					<tr>						
						<th >订单号</th>
						<td><input id="orderNo" name="orderNo" /></td>						
						<th >运单号</th>
						<td><input id="transNo" name="transNo" /></td>						
						<th >终端号</th>
						<td><input id="terminalNo" name="terminalNo" /></td>						
					</tr>
					<tr>
						<th >订单日期</th> 
						<td >
							<input id="orderBeginTime" class="timeInput easyui-datebox"  name="orderBeginTime" style="width: 93px;"/>
						~
							<input id="orderEndTime" class="timeInput easyui-datebox" name="orderEndTime" style="width: 93px;"/>
						</td>					
						<th >签收日期</th> 
						<td >
							<input id="receiptBeginTime" class="timeInput easyui-datebox"  name="receiptBeginTime" style="width: 93px;"/>
						~
							<input id="receiptEndTime" class="timeInput easyui-datebox" name="receiptEndTime" style="width: 93px;"/>
						</td>	
						<th >交易参考号</th>
						<td><input id="referenceNo" name="referenceNo" /></td>				
					</tr>	
					<tr >
						<th >回款日期</th>
						<td >
							<input id="paymentsBeginTime" class="timeInput easyui-datebox"  name="paymentsBeginTime" style="width: 93px;"/>
						~
							<input id="paymentsEndTime" class="timeInput easyui-datebox" name="paymentsEndTime" style="width: 93px;"/>
						</td>
						<td colspan="6">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importExcel('outboundSummaryPage','导入出库汇总数据');" id="outWarehouse">导入出库汇总数据</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="importExcel('lanPiecesReportPage','导入揽件报表数据');" id="importPiece">导入揽件报表数据</a>&nbsp;&nbsp;&nbsp;
<!-- 							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="exportData()" id="exportData">导出数据</a>&nbsp;&nbsp;&nbsp; -->
<!-- 							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="deleteMoreByOrderNo()" id="deleteMoreByOrderNo">批量删除数据</a>&nbsp;&nbsp;&nbsp; -->
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="mateData()" id="mateData" style="display: none">数据匹配</a>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>				
				</table>
			</form>
		</div>
	<!-- 数据表格 -->		
		<div class="main-data" region="center">
			<table id="transport" class="easy-table">
				<thead>   
			       <tr>  
			       	   <th data-options="field:'id',width:120,sortable : true, align:'center'">ID</th>
			           <th data-options="field:'orderDate',width:160,sortable : true, align:'center'">订单日期</th>   
			           <th data-options="field:'project',width:160,sortable : true, align:'center'">货主</th>
			           <th data-options="field:'startCity',width:120,sortable : true, align:'center'">始发城市</th>
			           <th data-options="field:'endCity',width:120,sortable : true, align:'center'">目的站</th>
			           <th data-options="field:'supplier',width:200,sortable : true, align:'center'">供应商</th>
			           <th data-options="field:'customer',width:250,sortable : true, align:'center'">客户名称</th>
			           <th data-options="field:'delivery',width:120,sortable : true, align:'center'">代收货款</th>
			           <th data-options="field:'orderNo',width:200,sortable : true, align:'center'">订单号</th>
			           <th data-options="field:'transNo',width:120,sortable : true, align:'center'">运单号</th>			        
			           <th data-options="field:'receiptDate',width:160,sortable : true, align:'center'">签收日期</th>   
			           <th data-options="field:'terminalNo',width:160,sortable : true, align:'center'">终端编号</th>
			           <th data-options="field:'referenceNo',width:160,sortable : true, align:'center'">交易参考号</th>
			           <th data-options="field:'paymentsDate',width:160,sortable : true, align:'center'">回款日期</th>
			           <th data-options="field:'paymentsType',width:120,sortable : true, align:'center'">回款方式</th>
			           <th data-options="field:'paymentsCard',width:120,sortable : true, align:'center'">回款卡号</th>
			           <th data-options="field:'truePay',width:120,sortable : true, align:'center'">实收金额</th>
			           <th data-options="field:'money',width:120,sortable : true, align:'center'">合计打款/刷卡金额</th>
			           <th data-options="field:'remarks',width:200,sortable : true, align:'center'">备注</th>
			           <th data-options="field:'creator',width:140,sortable : true, align:'center',formatter:creator">导入用户</th>
			           <th data-options="field:'createdTime',width:140,sortable : true, align:'center',formatter:createdTime">导入时间</th>
			           <th data-options="field:'lastOperator',width:140,sortable : true, align:'center',formatter:lastOperator">修改人</th>
			           <th data-options="field:'lastOperatedTime',width:140,sortable : true, align:'center',formatter:lastOperatedTime">修改时间</th>
			           <th data-options="field:'extendProp1',width:200,sortable : true, align:'center',hidden:true"></th>
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
				<div id="transport_do_edit" onclick="open('editTransFormPage','edit','修改收款信息',630,320)">修改收款信息</div>
				<div id="transport_delete" onclick="deleteByIds('deleteInfoById')">删除</div>
				<div class="menu-sep"></div>
				<div id="transport_outWarehouse" onclick="importExcel('outboundSummaryPage','导入出库汇总数据');">导入出库汇总数据</div>
				<div id="transport_importPiece" onclick="importExcel('lanPiecesReportPage','导入揽件报表数据');">导入揽件报表数据</div>
				<div class="menu-sep"></div>
				<div id="transport_exportData" onclick="exportExecl('exportExcel')">导出数据</div>
				<div id="transport_mateData" onclick="mateData()">数据匹配</div>
				<div id="transport_deleteMoreByOrderNo" onclick="deleteMoreByOrderNo()">导入要删除的数据</div>
			</div>
	</body>
</html>
