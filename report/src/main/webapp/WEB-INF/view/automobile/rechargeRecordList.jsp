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
		//sy.createDatagrid($("#rechargeRecordData"), srchForm, rightMenuFun);
		sy.createDatagrid($("#rechargeRecordData"),srchForm);
	});
	function rightMenu(){commRightMenu('rechargeRecordData');}
 
	function deleteRechargeRecord() {
		var ids = "";
		var selections = $("#rechargeRecordData").datagrid('getSelections');
		if(selections.length>0) {
			for(var i=0;i<selections.length;i++) {
				ids+=selections[i].id+",";
			}
			ids = ids.substring(0,ids.length-1);
		}
		if ("" != ids) {
			if(confirm("确定要删除记录吗?")) {
				$.ajax({
					type:"POST",
					url : 'deleteRechargeRecord?id='+ids,
					dataType : "json",
					success : function(msg){
						if ("1" == msg.data) {
							alert("删除成功");
							$('#rechargeRecordData').datagrid('load');
						} else if ("0" == msg.data) {
							alert("删除失败");						
						}
					}
				});
			}
		} else {
			alert("请选择！");
		}
	}
	
	function show() {
		alert("未开票金额：" + '${unbilled}' + "\n" + "主卡余额：" + '${mainBalance}');
	}

	function exportByHref(_href) {
		window.location.href = _href;
	}
	
</script>
</head>
<body class="easyui-layout" style="width:100%;height:101%;"
	 onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件">
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="rechargeRecordData">
				<input type="hidden" name="method" value="page">
				<table class="tableForm datagrid-toolbar" align="center">
					<tr>							
						<th>充值人</th>
						<td><input id="rechargeName" name="rechargeName"/></td>						
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="show()">余额显示</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="exportExecl('exportRechargeRecord','数据导出')">导出数据</a>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div region="center">
			<table id="rechargeRecordData" class="easy-table">
				<thead>   
			       <tr>
						<th data-options="field:'id',width:120,sortable : true, align:'center'">ID</th>
			           	<th data-options="field:'rechargeDate',width:120,sortable : true, align:'center'">充值日期</th>
						<th data-options="field:'rechargeMoney',width:120,sortable : true, align:'center'">充值金额</th>
						<th data-options="field:'rechargeName',width:120,sortable : true, align:'center'">充值人</th>
						<th data-options="field:'confirmed',width:120,sortable : true, align:'center'">出纳确认</th>
						<th data-options="field:'predistributionDate',width:120,sortable : true, align:'center'">预分配时间</th>
						<th data-options="field:'subStation',width:120,sortable : true, align:'center'">分站</th>
						<th data-options="field:'name',width:120,sortable : true, align:'center'">姓名</th>
						<th data-options="field:'cardNo',width:120,sortable : true, align:'center'">油卡副卡卡号</th>
						<th data-options="field:'predistributionMoney',width:120,sortable : true, align:'center'">预分配金额</th>
						<th data-options="field:'earlierPeriod',width:120,sortable : true, align:'center'">出车期间</th> 
						<th data-options="field:'mainBalance',width:120,sortable : true, align:'center'">主卡余额</th>
						<th data-options="field:'invoiceDate',width:120,sortable : true, align:'center'">发票日期</th>
						<th data-options="field:'invoiceMoney',width:120,sortable : true, align:'center'">发票金额</th>
						<th data-options="field:'invoiceNO',width:120,sortable : true, align:'center'">发票号码</th>
						<th data-options="field:'unbilled',width:120,sortable : true, align:'center'">未开票金额</th>
			       </tr>   
			   </thead>
			</table>
		</div>
		
		<div id="showDialog" class="easyui-dialog" closed="true" buttons="#dlg-buttons">
			<iframe scrolling="NO" id='openReceiveFeedBack' name="openReceiveFeedBack" frameborder="0" src=""></iframe>
		</div>	
		
		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="rechargeRecordData_do_add" onclick="add('saveRechargeRecord','新增主卡充值记录',680,330)">新增主卡充值</div>
			<div id="rechargeRecordData_do_import" onclick="importExcel('viceCardDistribution','导入预分配数据')">导入预分配数据</div>
			<div id="rechargeRecordData_do_export" onclick="exportExecl('exportRechargeRecord','导出预分配数据')">导出预分配数据</div>
			<div id="rechargeRecordData_do_disable" onclick="deleteRechargeRecord()">删除</div>
		</div>
</body>
</html>
