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
		sy.createDatagrid($("#user"),srchForm);
		//sy.pageButtons($("#user"));
	});
			// 右击事件
	function rightMenu(){
		commRightMenu('user');
	}
	function getSelections() {
		return sy.getSelectionsIds($("#user"));
	}
</script>
</head>
<body class="easyui-layout" style="width:100%;height:101%;" onkeydown="javascript:keyPressImpl(event,'user');" onkeyup="javascript:keyReleaseImpl(event,'user');">
	<!-- 查询表单 -->
	<div class="Search-box" region="north" title="查询条件"  >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="user">
				<input type="hidden" name="method" value="page">
				<table class="tableForm datagrid-toolbar"  >
					<tr>							
						<th>用户名</th> 
						<td><input id="userName" name="code" /></td>					
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query('user')">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
	<div id="showDialog" class="easyui-dialog" closed="true" buttons="#dlg-buttons">
		<iframe scrolling="auto" id='openReceiveFeedBack' name="openReceiveFeedBack" frameborder="0" src=""></iframe>
	</div>	
		
	<div id="limitDialog" class="easyui-dialog" closed="true" title="导出" style="width: 350px; height: 200px;">
		<iframe scrolling="NO" frameborder="0" src="limitRecords" style="width: 100%; height: 98%;"></iframe>
	</div>
		
	<div region="center">
		<table id="user" class="datagrid easy-table">
			<thead>   
			       <tr>  	
			           <th data-options="field:'id',width:120,sortable : true">ID</th>		        
					   <th data-options="field:'code',width:100,sortable : true">用户编号</th>
			           <th data-options="field:'name',width:100,sortable : true">用户名</th>
			           <th data-options="field:'contact.sex',width:100,sortable : true" formatter='format_sex'>性别</th>
			           <th data-options="field:'lastPwd',width:100,sortable : true" formatter = 'format_lastPwd'>最后一次密码</th>
			           <th data-options="field:'lastMdPwdTime',width:100,sortable : true" formatter = 'format_lastPwdTime'>最后修改时间</th>
			           <th data-options="field:'contact.tel',width:100,sortable : true" formatter='format_tel'>电话</th>
			           <th data-options="field:'contact.mobile',width:100,sortable : true" formatter='format_mobile'>手机</th>   
			           <th data-options="field:'contact.email',width:100,sortable : true" formatter="format_email">邮箱</th>
			           <th data-options="field:'locale',width:100,sortable : true">语言</th>
			           <th data-options="field:'enabled',width:100,sortable : true" formatter="format_enabled">状态</th>
			       </tr>   
			   </thead>
		</table>
	</div>

	<!--右键菜单div-->
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="refreshDagagrid();">刷新</div>
		<div onclick="expandQuery();">展开查询</div>
		<div onclick="collapseQuery();">折叠查询</div>
		<div class="menu-sep"></div>
		<div id="user_add" data-options="name:'add'" onclick="add('add','新建用户',900,280);">新建</div>
		<div id="user_edit" data-options="name:'edit'" onclick="edit('edit','修改用户',900,280);">修改</div>
		<div id="user_disable" data-options="name:'disable'">失效</div>
		<div class="menu-sep"></div> 
		<div id="user_bind_role" data-options="name:'bindRole'" onclick="bindRole('user','绑定角色',600,400);">绑定角色</div>
	</div>
</body>
</html>