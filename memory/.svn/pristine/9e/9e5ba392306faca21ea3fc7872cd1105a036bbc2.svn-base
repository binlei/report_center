<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/memory.jsp"></jsp:include>


<script type="text/javascript">
	var srchForm ;
	var treeGrid;
	var isInit = true;
	$(function() {
		//$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#permission"),srchForm);
		sy.pageButtons($("#permission"));
		Enter_query('permission');
/* 		treeGrid = $("#permission").treegrid({
			url:'treeGrid',
			idField : 'id',
			//singleSelect:false,
			fit:true,
			fitColumns:true,
			treeField : 'code',
			parentField : 'pid',
			method:'post',
			pagination:true,
			rownumbers:true,
			toolbar : '#toolbar',
			onLoadSuccess : function(data) {
				treeGrid.treegrid('expandAll');
			}
		}); */
		//右键事件
		$("#permission").parent().children(".datagrid-view2").children(".datagrid-body").mousedown(function(e){ 
			$('body').bind("contextmenu",function(e){
		    	return false;
		    }); 
	        if(3 == e.which){ 
		      	$('#menu').menu('show', {
	                left: e.pageX,
	                top: e.pageY
	            });  
		    	return false;
	        }
	    });
	});
	
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
  	<div id="srchDiv" class="easyui-dialog" closed="true" title="查找" style="width:400px;height:200px;">
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="permission">
				<input type="hidden" name="method" value="page">
				<table class="tableForm"  align="center" style="width : 300px;">
					<tr>							
						<th>权限编号</th> 
						<td><input name="code"/></td>					
					</tr>
					<tr>
						<th>权限名称</th>
						<td><input name="name"/></td>
					</tr>
					<tr>
						<th>状态</th>
						<td>
							<select name="enabled" style="width:100px;padding-left:15px;">
										<option value="" selected="selected"></option>
										<option value="1">有效</option>								
										<option value="0">无效</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query('permission')">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>
						</td>
					</tr>					
				</table>
			</form>
		</div>
		
	<!-- 数据表格 -->
		<div region="center" style="width:100%;height:100%;border:0;">
			<table id="permission" style="text-align:center;">
				<thead>   
			       <tr>  
			       	   <th data-options="field:'id',width:120,sortable : true,hidden:'true'">ID</th>			        
					   <th data-options="field:'code',width:150,sortable : true">权限编号</th>
			           <th data-options="field:'name',width:150,sortable : true">权限名称</th>
			           <th data-options="field:'parentPermission',width:100,sortable : true" formatter = 'parentPermissionId'>上级权限名称</th>
			           <th data-options="field:'type',width:50,sortable : true">类型</th>
			           <th data-options="field:'url',width:180,sortable : true">链接地址</th>
			           <th data-options="field:'content',width:100,sortable : true">内容</th> 
			           <th data-options="field:'beTop',width:80,sortable : true" formatter = 'sy.format_enabled'>是否根节点</th>
			           <th data-options="field:'priority',width:50,sortable : true">优先级</th>  
			           <th data-options="field:'description',width:150,sortable : true">备注</th>
			           <th data-options="field:'enabled',width:70,sortable : true" formatter = 'format_enabled'>状态</th>
			       </tr>   
			   </thead>
			</table>
		</div>
	<div id="toolbar" style="display: none;">
		<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ok'">展开</a> <a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'edit_remove'">折叠</a> <a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'mini_refresh'">刷新</a>
	</div>
<!--显示窗体 -->
	<div id="showDialog" class="easyui-dialog" shadow="false" closed="true" buttons="#dlg-buttons" title="权限管理" style="width: 500px; height: 360px;">
		<iframe scrolling="auto" id='openReceiveFeedBack' name="openReceiveFeedBack" frameborder="0" src="" style="width: 100%; height: 98%;"></iframe>
	</div>	

	<div id="editDialog"></div>
	
	<!--右键菜单div-->
		<div id="menu" class="easyui-menu" style="width: 80px;">
			<div data-options="name:'search'" onclick="simpleSearch();">查找</div>
			<div class="addItem" data-options="name:'add'" onclick="addFun('permission','新建权限',430,375);;">新建</div>
			<div data-options="name:'edit'" onclick="editFun('permission','新建权限',430,375);">编辑</div>
			<!-- <div data-options="name:'delete'" onclick="del('permission');">删除</div> -->
		</div>
</body>
</html>
