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
	$(function() {
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#role"),srchForm);
		// sy.pageButtons($("#role"));
		//Enter_query('role');
	});
	// 右击事件
	function rightMenu(){
		commRightMenu('role');
	}
	function getSelections() {
		return sy.getSelectionsIds($("#role"));
	}
	
</script>
</head>
<body class="easyui-layout" style="width:100%;height:100%;background-color: #eee;"
	onkeydown="javascript:keyPressImpl(event,'role');" onkeyup="javascript:keyReleaseImpl(event,'role');">
	<!-- 查询表单 -->
	<div class="Search-box" region="north" title="查询条件"  >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="role">
				<input type="hidden" name="method" value="page">
				<table class="tableForm datagrid-toolbar"  align="center">
					<tr>							
						<th>角色编号</th> 
						<td><input name="code"/></td>	
						<th>角色名称</th>
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
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query('role')">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>
						</td>
					</tr>					
				</table>
			</form>
		</div>		  		
		<div region="center">
			<table id="role" class="easy-table">
				<thead>   
			       <tr>  			        
					   <th data-options="field:'id',width:120,sortable : true">ID</th>
					   <th data-options="field:'code',width:320,sortable : true">角色编号</th>
			           <th data-options="field:'name',width:320,sortable : true">角色名称</th>   
			           <th data-options="field:'description',width:320,sortable : true">备注</th>
			           <th data-options="field:'enabled',width:128,sortable : true" formatter = 'format_enabled'>状态</th>
			       </tr>   
			   </thead>
			</table>
		</div>
		
	<div id="showDialog" class="easyui-dialog" closed="true" buttons="#dlg-buttons">
		<iframe scrolling="NO" id='openReceiveFeedBack' name="openReceiveFeedBack" frameborder="0" src=""></iframe>
	</div>	
	
	<div id="limitDialog" class="easyui-dialog" closed="true" title="导出" style="width: 350px; height: 200px;">
		<iframe scrolling="no" frameborder="0" src="limitRecords" style="width: 100%; height: 98%;"></iframe>
	</div>
		
	<!--右键菜单div-->
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="role_do_add" data-options="name:'add'" onclick="add('add','角色增加',630,210);">增加 </div>
			<div id="role_do_edit" data-options="name:'edit'" onclick="edit('edit','角色修改',630,210);">修改</div>
			<div id="role_do_disable" data-options="name:'disable'" onclick="disable('role');">失效</div>
			<div class="menu-sep"></div>
<!-- 			<div id="role_bind_user" data-options="name:'bindRole'" onclick="bindUser('role','绑定用户',850,410)">绑定用户 </div> -->
			<div id="role_bind_role" data-options="name:'bindPermission'" onclick="bindPermission('role','绑定权限',550,410)">绑定权限 </div>
	</div>
</body>
</html>
