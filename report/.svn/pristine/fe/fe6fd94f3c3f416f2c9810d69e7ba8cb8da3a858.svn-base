<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/drylineListUtil.jsp"></jsp:include>
<%@ include file="/WEB-INF/view/common/dryLineCommon.jsp"%>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
<script type="text/javascript">
	var srchForm;
	$(function() {
		$('body').layout('expand', 'north');
		srchForm = $("#srchForm");
		sy.createDatagrid($("#dryLineEtccard"), srchForm);
		getAllCarNo();
	});
	function rightMenu() {
		commRightMenu('dryLineEtccard');
	}
</script>
</head>
<body class="easyui-layout" >

	<div class="Search-box" region="north" title="查询条件">
		<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
			<input type="hidden" name="bean" value="dryLineEtccard">
			<input type="hidden" name="method" value="page"> 
			<input type="hidden" id="doMethod" value="do">
			<table class="tableForm datagrid-toolbar" align="center">
				<tr>
					<th>etc号</th>
					<td><input id="etccard" name="etccard" /></td>
					<th>车牌号</th>
					<td><input id="carcard" name="carcard" /></td>
					<td align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>

	<div region="center">
		<table id="dryLineEtccard" class="easy-table" >
			<thead>
				<tr>
					<th data-options="field:'id',width:80,sortable : true, align:'center'">ID</th>
					<th data-options="field:'etcNo',width:160,sortable : true, align:'center'">etc卡号</th>
					<th data-options="field:'carcard',width:120,sortable : true, align:'center'">车号</th>
					<th data-options="field:'rechargeDate',width:160,sortable : true, align:'center'">充值时间</th>
					<th data-options="field:'rechargeMoney',width:80,sortable : true, align:'center'">充值金额</th>
					<th data-options="field:'balance',width:140,sortable : true, align:'center'">etc卡内余额</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="menu" class="easyui-menu" style="width: 140px; display: none;">
		<div onclick="refreshDagagrid();">刷新</div>
		<div onclick="expandQuery();">展开查询</div>
		<div onclick="collapseQuery();">折叠查询</div>
		<div class="menu-sep"></div>
		<div id="dryLineEtccard_add" onclick="open('addPage','add','新增 ETC',650,280)">新增 ETC</div>
		<div id="dryLineEtccard_do_edit" onclick="open('editPage','edit','修改 ETC',650,280)">修改 ETC</div>
		<div class="menu-sep"></div>
		<div id="dryLineEtccard_do_disable" onclick="deleteByIds();">删除</div>
	</div>
</body>
</html>

