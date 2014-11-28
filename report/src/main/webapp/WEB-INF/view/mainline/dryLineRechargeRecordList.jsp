<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/drylineListUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('expand','north');
		srchForm = $("#srchForm");		
		//sy.createDatagrid($("#dryLineRechargeRecord"), srchForm, rightMenuFun);
		sy.createDatagrid($("#dryLineRechargeRecord"),srchForm);
		getMaincard();
	});
	
	function rightMenu(){commRightMenu('dryLineRechargeRecord');}
    
	function getMaincard(){
		$(".combobox-maincard").combobox({
			url:"${ctx}/dryLineMaincard/getAllMaincard",
			valueField:"mainCard",
			textField:"mainCard",
			method:"GET",
			editable:true,
			formatter:function(row){
				return "<img class='item-img' src='${ctx}/images/maincard.png'><span class='item-text'> " + row.mainCard + "</span>";
			},
			onShowPanel:function(row){
				$(this).combobox("reload");
			}
		});
	}
</script>
</head>
<body class="easyui-layout" style="width:100%;height:101%;"
	 onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="dryLineRechargeRecord">
				<input type="hidden" name="method" value="page">
				<input type="hidden" id="doMethod" value="doRecharge">
				<table class="tableForm datagrid-toolbar"  align="center" >
					<tr>							
						<th>主卡</th>
						<td><input id="card" name="card" class="combobox-maincard"/></td>
						<th>充值日期</th>
						<td colspan="1" >
							<input id="d411" class="Wdate" name="beginTime" style="width: 90px;" 
								onfocus="var d412=$dp.$('d412');WdatePicker({onpicked:function(){d412.focus();},maxDate:'#F{$dp.$D(\'d412\')}',skin:'whyGreen',minDate:'2009-09-10'})" /> ~
							<input id="d412" class="Wdate" name="endTime" style="width: 90px;" 
								onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d411\')}'})" />
						</td>					
						<td >
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div region="center">
			<table id="dryLineRechargeRecord" class="easy-table">
				<thead>   
			       <tr>
						<th data-options="field:'id',width:80,sortable : true, align:'center'">ID</th>
						<th data-options="field:'card',width:160,sortable : true, align:'center'">卡号</th>
						<th data-options="field:'transferMoney',width:120,sortable : true, align:'center'">充值金额</th>
<!-- 						<th data-options="field:'rechargeMoney',width:120,sortable : true, align:'center'">转账金额</th> -->
<!-- 			           	<th data-options="field:'rechargeDate',width:140,sortable : true, align:'center'">转账日期</th> -->
<!-- 						<th data-options="field:'rechargeName',width:80,sortable : true, align:'center'">转账卡号</th> -->
						<th data-options="field:'mainBalance',width:120,sortable : true, align:'center'">主卡余额</th>
						<th data-options="field:'confirmed',width:120,sortable : true, align:'center'">出纳确认</th>
						<th data-options="field:'invoiceDate',width:140,sortable : true, align:'center'">发票日期</th>
						<th data-options="field:'invoiceMoney',width:120,sortable : true, align:'center'">发票金额</th>
						<th data-options="field:'invoiceNo',width:120,sortable : true, align:'center'">发票号码</th>
						<th data-options="field:'unbilled',width:120,sortable : true, align:'center'">未开票金额</th>
			       </tr>   
			   </thead>
			</table>
		</div>
		
 		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
 			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="dryLineRechargeRecord_add" onclick="open('addRechargePage','add','新增主卡充值',650,250)">新增主卡充值</div>
			<div id="dryLineRechargeRecord_edit" onclick="open('editRechargePage','edit','修改主卡充值',650,250)">修改主卡充值</div>
<!-- 			<div data-options="name:'add',iconCls:'icon-add'" onclick="viceCardDistribution()">导入预分配数据</div> -->
			<div class="menu-sep"></div>
			<div id="dryLineRechargeRecord_do_delete" onclick="deleteByIds()">删除</div>
		</div>
</body>
</html>
