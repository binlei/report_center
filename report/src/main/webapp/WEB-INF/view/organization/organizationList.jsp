<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/taglibs.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/format.js"></script>
<script type="text/javascript">
	var srchForm;
	$(function() {
		$('body').layout('collapse', 'north');
		srchForm = $("#srchForm");
		sy.createDatagrid($("#organization"), srchForm);
	});
	function rightMenu() { }
</script>
</head>
<body class="easyui-layout">

	<!-- 查询表单 -->
	<div class="Search-box" region="north" title="查询条件">
		<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
			<input type="hidden" name="bean" value="organization"> 
			<input type="hidden" name="method" value="page">
			<input type="hidden" id="doMethod" value="do">
			<table class="tableForm datagrid-toolbar" align="center">
				<tr>
					<th>分站名称</th>
					<td><input id="substation" name="substation" /></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp; 
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a></td>
				</tr>
			</table>
		</form>
	</div>

	<div region="center">
		<table id="organization" class="easy-table">
			<thead>
				<tr>
					<th data-options="field:'id',width:120,sortable : true">ID</th>
					<th data-options="field:'code',width:150,sortable : true">分站编码</th>
					<th data-options="field:'name',width:150,sortable : true">分站名称</th>
					<th data-options="field:'description',width:200,sortable : true">备注</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="menu" class="easyui-menu" style="width: 80px;">
		<div id="organization_add" onclick="open('addPage','add',500,300);">增加分站</div>
		<div id="organization_do_add" onclick="open('editPage','edit',500,300);">编辑分站</div>
		<div id="organization_do_delete" onclick="deleteByIds();">删除</div>
	</div>
</body>
</html>
