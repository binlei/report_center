<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/memory.jsp"></jsp:include>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/organizationUtil.js"></script>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		//$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#user"),srchForm);
		//getLegalChannel($("#organizationId"),"install");
		sy.pageButtons($("#user"));
		Enter_query('user');
	});
	
</script>
</head>

<body class="easyui-layout" style="width:100%;height:100%;" onkeydown="javascript:keyPressImpl(event,'user');" onkeyup="javascript:keyReleaseImpl(event,'user');">
<!-- 查询表单 -->
	<div id="srchDiv" class="easyui-dialog" closed="true" title="查找" style="width:400px;height:200px;">
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="user">
				<input type="hidden" name="method" value="page">
				<table class="tableForm"  align="center" style="width : 300px;">
					<tr>							
						<th>账户</th> 
						<td><input id="userName" name="code"/></td>	
					</tr>
					<tr>				
						<th>昵称</th>
						<td><input id="userAlias" name="name"/></td>
					</tr>
					<tr>						
						<th>所属组织</th>
						<td><input id="orgName" name="orgName"/></td>						
					</tr>
					<tr>
						<td colspan="6" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query('user')">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
	<div id="showDialog" class="easyui-dialog" shadow="false" modal="true" closed="true" buttons="#dlg-buttons" title="用户管理" style="width: 420px; height: 365px;">
		<iframe scrolling="no" id='openReceiveFeedBack' name="openReceiveFeedBack" frameborder="0" src="" style="width: 100%; height: 98%;"></iframe>
	</div>	
		
	<div region="center" style="width:100%;height: 100%;border:0;">
		<table id="user">
			<thead>   
			       <tr> 
			       	   <th data-options="field:'id',width:120,sortable : true">ID</th> 			        
					   <th data-options="field:'code',width:100,sortable : true">账户</th>
			           <th data-options="field:'name',width:100,sortable : true">昵称</th>
			           <th data-options="field:'contact.sex',width:100,sortable : true" formatter='format_sex'>性别</th>
			           <th data-options="field:'organization.name',width:100,sortable : true" formatter='format_orgName'>所属组织</th>
			           <th data-options="field:'locale',width:100,sortable : true">语言</th>
			           <th data-options="field:'contact.mobile',width:100,sortable : true" formatter='format_mobile'>手机</th>   
			           <th data-options="field:'contact.tel',width:100,sortable : true" formatter='format_tel'>电话</th>
			           <th data-options="field:'contact.email',width:100,sortable : true" formatter="format_email">邮箱</th>
			           <th data-options="field:'contact.qq',width:100,sortable : true" formatter="format_qq">QQ</th>
			           <!-- <th data-options="field:'lastPwd',width:100,sortable : true" formatter = 'format_lastPwd'>最后一次密码</th> -->
			           <!-- <th data-options="field:'lastMdPwdTime',width:100,sortable : true" formatter = 'format_lastPwdTime'>最后修改时间</th> -->
			           <th data-options="field:'enabled',width:100,sortable : true" formatter="format_enabled">状态</th>
			       </tr>   
			   </thead>
		</table>
	</div>

	<div id="editDialog"></div>
	
	<!--右键菜单div-->
	<div id="menu" class="easyui-menu">
<!-- 		<div class="addItem" data-options="name:'add'" onclick="add('user');">新建</div> -->
		<div data-options="name:'search'" onclick="simpleSearch();">查找</div>
		<div class="addItem" data-options="name:'add'" onclick="addFun('user','新建用户',430,375);">新建</div>
		<div data-options="name:'edit'" onclick="editFun('user','编辑用户',430,375);">编辑</div>
		<!-- <div data-options="name:'disable'">失效</div> -->
		<div class="menu-sep"></div>
		<div data-options="name:'bindRole'" onclick="bindRole();">绑定角色</div>
		<!-- <div data-options="name:'bindRole'" onclick="bind('绑定角色','user','user/bindRole');">绑定角色</div> -->
	</div>
</body>
</html>