<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#backMoney"),srchForm);
	});
	
	function rightMenu(){commRightMenu('backMoney');}
	
</script>
</head>
<body class="easyui-layout"  onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="backMoney">
				<input type="hidden" name="method" value="page">
				<input type="hidden" id="doMethod" value="do">
				<table class="tableForm datagrid-toolbar"  align="center"  >
					<tr>						
						<th>结算单号</th> 
						<td><input id="checkoutOrder" name="checkoutOrder" style="width: 198px;"/></td>	
						<th>是否回款确认</th> 
						<td>
							<select id="confirm" name="confirm" style="width: 198px;">
								<option value="">请选择</option>
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</td>
						<td align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importExcel('importPage','导入回款数据')">导入数据</a>
						</td>
					</tr>				
				</table>
			</form>
		</div>
		
		<div region="center">
			<table id="backMoney" class="easy-table">
				<thead> 
					<tr>  
			       	   <th data-options="field:'id',width:120,sortable : true, align:'center'">ID</th>
			           <th data-options="field:'settleNo',width:140,sortable : true, align:'center'">结算单号</th>   
			           <th data-options="field:'backFundsMoney',width:120,sortable : true, align:'center'">回款金额</th>
			           <th data-options="field:'adjustMoney',width:120,sortable : true, align:'center'">调整金额</th>
			           <th data-options="field:'backFundsDate',width:160,sortable : true, align:'center'">回款日期</th>
			           <th data-options="field:'backFundsBank',width:120,sortable : true, align:'center'">回款银行</th>
			           <th data-options="field:'information',width:200,sortable : true, align:'center'">回款确认</th>
			       </tr>   
			   </thead>
			</table>
		</div>
		
		<div id="menu" class="easyui-menu" style="width: 140px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="backMoney_delete" onclick="deleteByOrder()">批量(单号)删除</div>
			<div id="backMoney_do_delete" onclick="deleteByIds()">删除</div>
			<div class="menu-sep"></div>
			<div id="backMoney_import" onclick="importExcel('importPage','导入回款数据')">导入回款数据</div>
			<div id="backMoney_importUpdateBackPage" onclick="importExcel('importUpdateBackPage','导入调整金额')">导入调整金额</div>
			<div id="backMoney_do_comfig" onclick="backMoneyConfirm()">回款确认</div>
		</div>
		
	</body>
</html>
