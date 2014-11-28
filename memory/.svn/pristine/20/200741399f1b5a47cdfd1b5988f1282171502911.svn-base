<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="ico/favicon.ico">
<link href="${pageContext.request.contextPath}/css/themes/gray/easyui.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/html/list.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/showDialog.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/bind.js"></script> --%>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/taglibs.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<title>绑定用户</title>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		srchForm = $("#srchForm");
		//已绑定用户的列表
		$("#user").datagrid({
			url:'${pageContext.request.contextPath}' + "/process", 
			fit:true,
			//singleSelect : true,//单行选中
			autoRowHeight:false,
			method:'POST',	
			//scrollbarSize:18,
			//resizeHandle:'right',
			striped: true,
			rownumbers: true,//显示行数 1，2，3，4...			  
			pagination : true,//显示最下端的分页工具栏			
			pageSize : 10,//读取分页条数，即向后台读取数据时传过去的值			
			pageList : [10,15,20,30,40,50,100], //可以调整每页显示的数据，即调整pageSize每次向后台请求数据时的数据			
			fitColumns : true,//设置为true将自动使列适应表格宽度以防止出现水平滚动,false则自动匹配大小		
			queryParams : sy.serialieObject(srchForm), 
			frozenColumns : [ [ {
				field : 'ids',
				checkbox : true,
				formatter:function(value,row,index) {
					if (row != null && row.id != null) {
						return row.id;
					} else {
						return "";
					}
				}
			}]],
			rowStyler: function(index,row) {
				return "height:23.2px;";
			},
			onLoadSuccess : function(data) {
				var userRoles = data.rows;
				var ids = $("#ids input");
				for(var i = 0;i < ids.length;i++){
					for(var j = 0;j < userRoles.length;j++){
						if(ids[i].value == userRoles[j].id){
							//$('#datagrid-row-r1-1-'+j+' input').attr("checked",true);
							$("#user").datagrid('selectRow',j );
						}
					}
				} 
			},
			onLoadError : function() { 
				$.messager.alert('错误', "加载失败！", 'error');
			}
		});
		//默认隐藏id列
		$("#user").datagrid('hideColumn', 'id');
	});
	
	//保存选择的角色
	function saveBind(){
		var userRoles = new Array();
		var roleId = $("#roleId").val();
		var selectedRows = $("#user").datagrid('getChecked');
		if(selectedRows.length < 1){
			/* $.messager.alert('提示','至少选择一个用户!','warning'); */
			$.ajax({
				url : 'deleteBindByRoleId',
				type : 'POST',
				data : roleId,
				contentType : "application/json; charset=utf-8",
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
		for(var i = 0;i < selectedRows.length;i++){
			var userRole = new Object();
			userRole.roleId = roleId;
			userRole.userId = selectedRows[i].id;
			userRoles.push(userRole);
		}
		$.ajax({
			url : 'saveBindUser',
			type : 'POST',
			data : JSON.stringify(userRoles),
			contentType : "application/json; charset=utf-8",
			//dataType : 'json',
			success : function(data){
				if(data == "successed"){
					parent.showSuccessMsg();
				}else{
					parent.showFailedMsg();
				}	
			}
		});	
	}
	
</script>
</head>
<body class="easyui-layout" style="width:100%;height:100%;border: 0;" >
	<!--当前角色  -->
	<div region = "north" align="left" border="false" style="height: 20px;">
		<span><strong>当前角色：${roleName }</strong></span>
		<input id="roleId" type="hidden" name="roleId" value="${roleId }"/>
		<div id="ids">
			<c:forEach items="${userRoles }" var="u">
				<input type="hidden" value="${u.userId }"/>
			</c:forEach>
		</div>
	</div>
	<!--主要内容 -->
	<div region = "center" style="width:100%;height:100%;border:0;">
		<div style="magin:0;padding:0;height: 100%" align="center" >
			<div>
				<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
					<input type="hidden" name="bean" value="user">
					<input type="hidden" name="method" value="page">
					<input type="hidden" name="code"/>
					<table class="tableForm"  align="center" style="width : 80%;">
						<tr align="center">
							<th>用户名称</th>												
							<td><input name="name" id="name"></td>
							<td>
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query('user')">查询</a>&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<!-- <tr>
							<td colspan="2" align="center">
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query('user')">查询</a>&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>
							</td>
						</tr>	 -->									
					</table>
				</form>
			</div>
			<div style="height:300px;width:98%;">
				<table id="user" style="text-align: center;" >
					<thead>   
				       <tr>  			        
						   <th id="id" data-options="field:'id',width:60" >ID</th>
						   <th data-options="field:'code',width:50">用户编号</th>
				           <th data-options="field:'name',width:80">用户名称</th>   			           
				       </tr>   
				   </thead>
				</table>
			</div>
		</div>
	</div>
	<!--按钮 -->
	<div region = "south" align="center" border="false">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveBind();">保存</a>&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="parent.closeDialog('user')">关闭</a>
	</div> 
</body>
</html>