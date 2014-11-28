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
		sy.createDatagrid($("#dryLineEtcConsumeRecord"), srchForm);
		getAllCarNo();
	});
	function rightMenu() {
		commRightMenu('dryLineEtcConsumeRecord');
	}
</script>
</head>
<body class="easyui-layout" style="width: 100%; height: 100%;"
	onkeydown="javascript:keyPressImpl(event);"
	onkeyup="javascript:keyReleaseImpl(event);">

	<div class="Search-box" region="north" title="查询条件">
		<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
			<input type="hidden" name="bean" value="dryLineEtcConsumeRecord">
			<input type="hidden" name="method" value="page"> <input
				type="hidden" id="doMethod" value="doEtcConsume">
			<table class="tableForm datagrid-toolbar" align="center">
				<tr>
					<th>日期</th>
					<td colspan="1"><input id="d411" class="Wdate"
						name="beginTime" style="width: 90px;"
						onfocus="var d412=$dp.$('d412');WdatePicker({onpicked:function(){d412.focus();},maxDate:'#F{$dp.$D(\'d412\')}',skin:'whyGreen',minDate:'2009-09-10'})" />
						~ <input id="d412" class="Wdate" name="endTime"
						style="width: 90px;"
						onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d411\')}'})" />
					</td>
					<th>车牌号</th>
					<td><input id="carcard" name="carcard" /></td>
					<th>ETC号</th>
					<td><input id="etcNo" name="etcNo" /></td>
					<td align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>

	<div region="center">
		<table id="dryLineEtcConsumeRecord" class="easy-table" >
			<thead>
				<tr>
					<th data-options="field:'id',width:80,sortable : true, align:'center'">ID</th>
					<th data-options="field:'carcard',width:120,sortable : true, align:'center'">车号</th>
					<th data-options="field:'etcNo',width:160,sortable : true, align:'center'">ETC卡号</th>
					<th data-options="field:'rechargeDate',width:160,sortable : true, align:'center'">充值时间</th>
					<th data-options="field:'rechargeMoney',width:80,sortable : true, align:'center'">充值金额</th>
					<th data-options="field:'inStationName',width:120,sortable : true, align:'center'">入口站名</th>
					<th data-options="field:'inStationStarttime',width:160,sortable : true, align:'center'">入口时间</th>
					<th data-options="field:'outStationName',width:120,sortable : true, align:'center'">出口站名</th>
					<th data-options="field:'outStationStarttime',width:160,sortable : true, align:'center'">出口时间</th>
					<th data-options="field:'actualAmount',width:100,sortable : true, align:'center'">实收金额</th>
					<th data-options="field:'etcBalance',width:140,sortable : true, align:'center'">ETC卡内余额</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="menu" class="easyui-menu" style="width: 140px; display: none;">
		<div onclick="refreshDagagrid();">刷新</div>
		<div onclick="expandQuery();">展开查询</div>
		<div onclick="collapseQuery();">折叠查询</div>
		<div class="menu-sep"></div>
		<div id="dryLineEtcConsumeRecord_importExcel" onclick="importExcel('importPage','导入')">导入ETC记录</div>
		<div class="menu-sep"></div>
		<div id="dryLineEtcConsumeRecord_add" onclick="open('addEtcConsumePage','add','新增出车登记',650,280)">新增ETC记录</div>
		<div id="dryLineEtcConsumeRecord_do_edit" onclick="open('editEtcConsumePage','edit','修改出车登记',650,280)">修改ETC记录</div>
		<div class="menu-sep"></div>
		<div id="dryLineEtcConsumeRecord_do_disable" onclick="deleteByIds();">删除</div>
	</div>
</body>
</html>

