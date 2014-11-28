<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/memory.jsp"></jsp:include>

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/bind.js"></script> --%>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		//$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#role"),srchForm);
		sy.pageButtons($("#role"));
		Enter_query('role');
	});
	
	function getSelections() {
		return sy.getSelectionsIds($("#role"));
	}
	
	function quickQuery() {
		$("#quickQueryDialog").dialog('open');
	}
</script>

</head>
<body class="easyui-layout" style="width:100%;height:100%;background-color: #eee;"
	onkeydown="javascript:keyPressImpl(event,'role');" onkeyup="javascript:keyReleaseImpl(event,'role');">
	<!-- 查询表单 -->
	<div id="srchDiv" class="easyui-dialog" closed="true" title="查找" style="width:400px;height:200px;">
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="role">
				<input type="hidden" name="method" value="page">
				<table class="tableForm"  border="0" align="center" style="width : 300px;">
					<tr>							
						<th>角色编号</th> 
						<td><input name="code"/></td>	
					</tr>
					<tr>				
						<th>角色名称</th>
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
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query('role')">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>
						</td>
					</tr>					
				</table>
			</form>
		</div>		  		
				
		<div region="center" style="width:100%;height: 100%;border:0;">
			<table id="role" style="text-align:center;">
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
		
	<div id="showDialog" class="easyui-dialog" shadow="false" modal="true" closed="true" buttons="#dlg-buttons" title="角色管理" style="width:500px; height:450px;">
		<iframe scrolling="no" id='openReceiveFeedBack' name="openReceiveFeedBack" frameborder="0" src="" style="width: 100%; height: 98%;"></iframe>
	</div>	
	
	<div id="limitDialog" class="easyui-dialog" closed="true" title="导出" style="width: 350px; height: 200px;">
		<iframe scrolling="no" frameborder="0" src="limitRecords" style="width: 100%; height: 98%;"></iframe>
	</div>
	
	<div id="quickQueryDialog" class="easyui-dialog" closed="true" title="快速查找" style="width: 350px; height: 200px;">
		<iframe scrolling="no" frameborder="0" src="quickQuery" style="width: 100%; height: 98%;">
		</iframe>
	</div>

	<div id="editDialog"></div>
		
	<!--右键菜单div-->
	<div id="menu" class="easyui-menu" style="width: 100px; display: none;">
			<!--放置一个隐藏的菜单Div-->
			<div data-options="name:'search'" onclick="simpleSearch();">查找</div>
			<div class="addItem" data-options="name:'add'" onclick="addFun('role','新建角色',430,375);">新建</div>
			<!--具体的菜单事件请自行添加，跟toolbar的方法是基本一样的-->
			<div  data-options="name:'edit'" onclick="editFun('role','编辑角色',430,375);">编辑</div>
			<!-- <div data-options="name:'delete'" onclick="del('role');">删除 </div> -->
			<div class="menu-sep"></div>
			<div data-options="name:'bindRole'" onclick="bindUser();">绑定用户 </div>
			<!-- <div data-options="name:'bindRole'" onclick="bind('绑定用户','role','role/bindUser');">绑定用户 </div> -->
			<div data-options="name:'bindPermission'" onclick="bindPermission()">绑定权限 </div>
			<!-- <div data-options="name:'bindPermission'" onclick="bind('绑定权限','role','role/bindPermission');">绑定权限 </div> -->
	</div>
</body>
</html>
