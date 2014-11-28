<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="ico/favicon.ico">
<link href="${pageContext.request.contextPath}/css/themes/gray/easyui.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/html/list.css">
<link href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/taglibs.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script src="../js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
 <title>Insert title here</title>
 <script type="text/javascript">
 	var setting = {
		view : {
			dblClickExpand : false
			},
		data : {
			simpleData : {
			enable : true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: 0
			}
		},
		check : {
			enable: true,
			chkStyle: "checkbox"
		}
	};
 
	$(function() {
		$.ajax({
			url : '${pageContext.request.contextPath}'+"/permission/getPermissions",
			async : false,
			type : 'POST',
			dataType : 'json',
			timeout : 60000,
			success : function(result) {
				var zNodes = result;
				$.fn.zTree.init($("#permission"), setting, zNodes);
				var treeObj = $.fn.zTree.getZTreeObj("permission");
				/* treeObj.expandAll(true); */
				var ids = $("#ids input");
				for(var i = 0;i < ids.length;i++){
					var node = treeObj.getNodeByParam("id", ids[i].value, null);
					if(node != null){
						treeObj.checkNode(node);
					}
				}
			}
		});	
	});
	
	function saveBind(){
		var rolePermissions = new Array();
		var roleId = $("#roleId").val();
		var treeObj = $.fn.zTree.getZTreeObj("permission");
		var nodes = treeObj.getCheckedNodes(true);
		if(nodes.length < 1){
			/* $.messager.alert('提示','至少选择一个权限!','warning'); */
			$.ajax({
				url : 'deletePermissionByRoleId',
				type : 'POST',
				data : roleId,
				contentType : "application/json; charset=utf-8",
				dataType : 'text',
				success : function(data){
					if(data == "successed"){
						parent.showSuccessMsg();
					}else{
						parent.showFailedMsg();
					}	
				}
			});
			return;
		}
		for(var i = 0;i < nodes.length;i++){
			var rolePermission = new Object();
			rolePermission.roleId = roleId;
			rolePermission.permissionId = nodes[i].id;
			rolePermissions.push(rolePermission);
		}
		$.ajax({
			url : 'saveBindPermission',
			type : 'POST',
			data : JSON.stringify(rolePermissions),
			contentType : "application/json; charset=utf-8",
			dataType : 'text',
			success : function(data){
				if(data == "successed"){
					parent.showSuccessMsg();
				}else{
					parent.showFailedMsg();
				}	
			}
		});
	}
	
	var tabsObj = parent.$("#tabs");//获取父页面tabs对象
	//关闭当前tabs
	function previousPage() {
		var curTabIndex = tabsObj.tabs('getTabIndex',tabsObj.tabs('getSelected'));//得到当前tabs索引
		tabsObj.tabs('close',curTabIndex);//关闭当前tabs
		//tabsObj.tabs('select','用户管理');//返回list页面
	}
</script>
</head>
<body class="easyui-layout" style="width:100%;height:100%;">
	<!--当前角色  -->
	<div align="left" region = "north" border="false" style="height: 20px;">
		<span><strong>当前角色：${roleName }</strong></span>
		<input id="roleId" type="hidden" name="roleId" value="${roleId }"/>
		<div id="ids" >
				<c:forEach items="${rolePermissions }" var="r">
					<input type="hidden" value="${r.permissionId }"/>
				</c:forEach>
			</div>
	</div>
	<!--主要内容 -->
	<div region = "center" border="false">
		<div style="magin:0;padding:0;" align="center" >
			<!-- <div style="magin:0;padding:0;" align="center"><strong>请勾选要绑定的权限</strong></div> -->
			<!-- 权限列表 -->
			<div style="height:280px;width:98%;border:0;">
				<ul id="permission" class="ztree"></ul>
			</div>
		</div>
	</div>
	<!--按钮 -->
	<div region = "south" align="center" border="false">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveBind();">保存</a>&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="parent.closeDialog('permission');">关闭</a>
	</div>
</body>
</html>