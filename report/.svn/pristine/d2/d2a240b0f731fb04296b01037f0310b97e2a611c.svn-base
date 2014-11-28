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
// 		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#financeServiceType"),srchForm);
		getAllCategory("categerName");
	});
	
	function rightMenu(){commRightMenu('financeServiceType');}
	function clearType(){
		$("#categerName").combobox("clear");
	}
</script>
</head>

<body class="easyui-layout" style="width:100%;height:101%;"  onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件"  >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="financeServiceType">
				<input type="hidden" name="method" value="page">
				<input type="hidden" id="doMethod" value="doType">
				<table class="tableForm datagrid-toolbar"  align="center" >
					<tr>							
						<th>品类</th>
						<td><input id="categerName" name="parentName" /></td>
						<th>品牌</th>
						<td><input id="brandName" name="name" /></td>
						<td align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearType()">重置</a>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div region="center">
			<table id="financeServiceType" class="easy-table">
				<thead>   
			       <tr>
			       	   	<th data-options="field:'id',width:80,sortable : true, align:'center'">ID</th>
			       	   	<th data-options="field:'parentName',width:120,sortable : true, align:'center'">分类</th>
			           	<th data-options="field:'name',width:120,sortable : true, align:'center'">品牌</th>
						<th data-options="field:'model',width:120,sortable : true, align:'center'">型号</th>
			       </tr>   
			   </thead>
			</table>
		</div>
		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="financeServiceType_add" onclick="open('addTypePage','add','新建分类配置',700,250)">新建分类配置</div>
			<div id="financeServiceType_edit" onclick="open('editTypePage','edit','修改分类配置',700,250)">修改分类配置</div>
<!-- 			<div class="menu-sep"></div> -->
<!-- 			<div id="financeServiceType_importExcel" onclick="importExcel('importPage','导入配置表')">导入配置表</div> -->
			<div class="menu-sep"></div>
			<div id="financeServiceType_do_delete" onclick="deleteByIds();">删除</div>
		</div>
</body>
</html>
