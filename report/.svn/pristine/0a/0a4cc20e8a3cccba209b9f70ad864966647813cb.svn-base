<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/taglibs.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/list_right_menu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/addAndEdit.js"></script>
<script type="text/javascript">
	var srchForm;
	$(function() {
		//$('body').layout('collapse','north');
		srchForm = $("#srchForm");
		sy.createDatagrid($("#role"), srchForm);
		var pager = $('#role').datagrid('getPager'); //得到DataGrid页面
		pager.pagination({
			showPageList : false,
			buttons : [ {
				iconCls : 'icon-search',
				text : '查找角色',
				handler : function() {
					alert('search');
				}
			} ],
			onBeforeRefresh : function() {
				alert('before refresh');
				return true;
			}
		});
	});
	// 右击事件
	function rightMenu() {
		commRightMenu('role');
	}
	function getSelections() {
		return sy.getSelectionsIds($("#role"));
	}
	function submitForm(roleName) {
		$("#rname").val(roleName);
		$("#srchForm").submit();
	}
</script>
</head>
<body class="easyui-layout" style="width: 100%; height: 100%;"
	onkeydown="javascript:keyPressImpl(event,'role');"
	onkeyup="javascript:keyReleaseImpl(event,'role');">
	<!-- 查询表单 -->
	<div region="center" style="height: 80%" >
		<table id="role" class="datagrid easy-table" data-options="toolbar:'#tb'">
			<thead>
				<tr>
					<th data-options="field:'id',width:80,sortable : true">ID</th>
					<th data-options="field:'code',width:100,sortable : true">角色编号</th>
					<th data-options="field:'name',width:100,sortable : true">用户名</th>
					<th data-options="field:'description',width:100,sortable : true">描述</th>
				</tr>
			</thead>
		</table>
		<div id="tb" style="padding: 5px; height: auto">
			<div align="center">
				<form id="srchForm">
					<table>
						<tr>
							<td>
								<input type="hidden" id="rids" name="rids" value="${rids}" />
								<input type="hidden" name="bean" value="user" />
								<input type="hidden" name="method" value="page" /> 
								<input type="hidden" name="name" id="rname" /> 
								查找用户: <input type="text" style="width: 120px" name="roleName" id="roleName" /></td>
							<td width="20px"></td>
							<td><a href="#" class="easyui-linkbutton"
								iconCls="icon-search"
								onclick="submitForm($('#roleName').val());">查 找</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div style="height: 20%; background: red;"></div>
	<!--右键菜单div-->
	<div id="menu" class="easyui-menu">
		<div id="user_bind_role" data-options="name:'bindRole'" onclick="bindRole('rids');">绑定用户</div>
	</div>
</body>
</html>