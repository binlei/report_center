<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/drylineListUtil.jsp"></jsp:include>
<%@ include file="/WEB-INF/view/common/dryLineCommon.jsp" %>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('expand','north');
// 		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#dryLineTransferRecord"),srchForm);
		getAllCarNo();
	});
	function rightMenu(){commRightMenu('dryLineTransferRecord');}// 右击事件
</script>
</head>
<body class="easyui-layout" style="width:100%;height:100%;" onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">

	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" style="height:120px;" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="dryLineTransferRecord">
				<input type="hidden" name="method" value="page">
				<input type="hidden" name="type" id="type" value="CONSUMPTION" />
				<table class="tableForm datagrid-toolbar" align="center">
					<tr>		
						<th>消费日期</th>
						<td colspan="1" >
							<input id="d411" class="Wdate" name="beginTime" style="width: 90px;" 
								onfocus="var d412=$dp.$('d412');WdatePicker({onpicked:function(){d412.focus();},maxDate:'#F{$dp.$D(\'d412\')}',skin:'whyGreen',minDate:'2009-09-10'})" /> ~
							<input id="d412" class="Wdate" name="endTime" style="width: 90px;" 
								onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d411\')}'})" />
						</td>
						<th>主卡</th>
						<td><input id="mainCard" name="mainCard" /></td>
					</tr>
					<tr>
						<th>车牌号</th>
						<td><input id="carcard" name="carcard" /></td>					
						<th>油卡号</th>
						<td><input id="oilCardNo" name="oilCardNo" /></td>	
					</tr>
					<tr>
						<td colspan="4" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div region="center">
			<table id="dryLineTransferRecord" class="easy-table" style="width:100%;height:100%;">
				<thead>  
					<tr> 
						<th data-options="field:'id',width:80,sortable : true,hidden:true">ID</th>
						<th data-options="field:'carcard',width:120,sortable : true, align:'center'">车号</th>
			           	<th data-options="field:'oilCardNo',width:160,sortable : true, align:'center'">油卡号</th>
						<th data-options="field:'transferDate',width:200,sortable : true, align:'center'">消费时间</th>
						<th data-options="field:'kind',width:100,sortable : true, align:'center'">消费品种</th>
						<th data-options="field:'quantity',width:100,sortable : true, align:'center'">消费数量</th>
						<th data-options="field:'price',width:100,sortable : true, align:'center'">消费单价</th>
						<th data-options="field:'money',width:120,sortable : true, align:'center'">消费金额</th>
						<th data-options="field:'unbilled',width:120,sortable : true, align:'center'">油卡内余额</th>
			       </tr>   
			   </thead>
			</table>
		</div>
		 
		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="dryLineTransferRecord_consume" onclick="importExcel('requestConsumptionPage','消费记录导入');">导入消费记录</div>
<!-- 			<div class="menu-sep"></div> -->
<!-- 			<div id="dryLineTransferRecord_do_disable" onclick="deleteByIds()">删除</div> -->
		</div>
</body>
</html>
