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
// 		$('body').layout('expand','north');
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#dryLineWarningRecord"),srchForm);
	});
	function rightMenu(){commRightMenu('dryLineWarningRecord');}// 右击事件
</script>
</head>
<body class="easyui-layout" style="width:100%;height:100%;" onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">

	 	<div class="Search-box" region="north" title="查询条件">
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="dryLineWarningRecord">
				<input type="hidden" name="method" value="page">
				<input type="hidden" name="type" id="type" value="DEPOSIT" />
				<table class="tableForm datagrid-toolbar"  align="center" >
					<tr>							
						<th>车牌号</th>
						<td><input id="carcard" name="carcard" /></td>
						<th>日期</th>
						<td colspan="1" >
							<input id="d411" class="Wdate" name="startTime" style="width: 90px;" 
								onfocus="var d412=$dp.$('d412');WdatePicker({onpicked:function(){d412.focus();},maxDate:'#F{$dp.$D(\'d412\')}',skin:'whyGreen',minDate:'2009-09-10'})" /> ~
							<input id="d412" class="Wdate" name="endTime" style="width: 90px;" 
								onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'d411\')}'})" />
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div region="center">
			<table id="dryLineWarningRecord" class="easy-table" style="width:100%;height:100%;">
				<thead>  
					<tr>
						<th data-options="field:'id',width:80,sortable : true,hidden:true">ID</th>
		           	    <th data-options="field:'carCard',width:120,sortable : true, align:'center'">车号</th>
					    <th data-options="field:'carNo',width:160,sortable : true, align:'center'">卡号</th>
					    <th data-options="field:'balance',width:120,sortable : true, align:'center'">余额</th>
					    <th data-options="field:'createdTime',width:160,sortable : true, align:'center',formatter:createdTime">创建时间</th>
					    <th data-options="field:'warning',width:300,sortable : true, align:'center'">警告信息</th>
					</tr>
			   </thead>
			</table>
		</div>
		 
		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
		</div>
</body>
</html>
