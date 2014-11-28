<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/format.js"></script>

<script type="text/javascript">
	var srchForm ;
	var treeGrid;
	var isInit = true;
	$(function() {
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#permission"),srchForm);
	});
	// 右击事件
	function rightMenu(){
		commRightMenu('permission');
	}
	function redo() {
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			treeGrid.treegrid('expandAll', node.id);
		} else {
			treeGrid.treegrid('expandAll');
		}
	}

	function undo() {
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			treeGrid.treegrid('collapseAll', node.id);
		} else {
			treeGrid.treegrid('collapseAll');
		}
	}
</script>
</head>
<body class="easyui-layout" style="width:100%;height:100%;"
	onkeydown="javascript:keyPressImpl(event,'permission');" onkeyup="javascript:keyReleaseImpl(event,'permission');">
  	<!-- 查询表单 -->
  	<div class="Search-box" region="north" title="查询条件" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="permission">
				<input type="hidden" name="method" value="page">
				<table class="tableForm datagrid-toolbar" align="center" >
					<tr>							
						<th>权限编号</th> 
						<td><input name="code"/></td>					
						<th>权限名称</th>
						<td><input name="name"/></td>
						<th>状态</th>
						<td>
							<select name="enabled" style="width:100px;padding-left:15px;">
										<option value="" selected="selected">--请选择--</option>
										<option value="1">有效</option>								
										<option value="0">无效</option>
							</select>
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query('permission')">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>
						</td>
					</tr>					
				</table>
			</form>
		</div>
		
	<!-- 数据表格 -->
		<div region="center">
			<table id="permission" class="easy-table">
				<thead>   
			       <tr>  
			       	   <th data-options="field:'id',width:120,sortable : true,hidden:'true'">ID</th>			        
					   <th data-options="field:'code',width:180,sortable : true">权限编号</th>
			           <th data-options="field:'name',width:180,sortable : true">权限名称</th>
			           <th data-options="field:'parentPermission',width:160,sortable : true" formatter = 'parentPermissionId'>上级权限名称</th>
			           <th data-options="field:'type',width:80,sortable : true">类型</th>
			           <th data-options="field:'url',width:280,sortable : true">链接地址</th>
			           <th data-options="field:'content',width:200,sortable : true">内容</th> 
			           <th data-options="field:'beTop',width:80,sortable : true" formatter = 'sy.format_enabled'>是否根节点</th>
			           <th data-options="field:'priority',width:80,sortable : true">优先级</th>  
			           <th data-options="field:'description',width:250,sortable : true">备注</th>
			           <th data-options="field:'enabled',width:80,sortable : true" formatter = 'format_enabled'>状态</th>
			       </tr>   
			   </thead>
			</table>
		</div>
	<div id="toolbar" style="display: none;">
		<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ok'">展开</a> <a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'edit_remove'">折叠</a> <a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'mini_refresh'">刷新</a>
	</div>
	
	<div id="showDialog" class="easyui-dialog" shadow="false" closed="true" buttons="#dlg-buttons" title="权限管理" style="width: 500px; height: 360px;">
		<iframe scrolling="NO" id='openReceiveFeedBack' name="openReceiveFeedBack" frameborder="0" src="" style="width: 100%; height: 98%;"></iframe>
	</div>	

	<!--右键菜单div-->
		<div id="menu" class="easyui-menu"  style="width: 120px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="permission_do_add" data-options="name:'add'" onclick="add('add','新增权限',630,340);">新增</div>
			<div id="permission_do_edit" data-options="name:'edit'" onclick="edit('edit','新增权限',630,340);">编辑</div>
			<div id="permission_do_disable" data-options="name:'disable'" onclick="disable('permission');">失效</div>
		</div>
</body>
</html>
