<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		sy.createDatagrid($("#organization"),srchForm);
		sy.pageButtons($("#organization"));
		Enter_query('organization');
	});
	
</script>
</head>
<body class="easyui-layout" style="width:100%;height:100%;" onkeydown="javascript:keyPressImpl(event,'organization');" onkeyup="javascript:keyReleaseImpl(event,'organization');">
	 
	 <!-- 查询表单 -->
	 	<div id="srchDiv" class="easyui-dialog" closed="true" title="查找" style="width:400px;height:200px;">
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="organization">
				<input type="hidden" name="method" value="page">
				<table class="tableForm"  align="center" style="width : 300px;">
					<tr>							
						<th>组织名称</th> 
						<td><input id="orgName" name="name"/></td>		
					</tr>
					<tr>			
						<th>上级组织名称</th>
						<td><input id="pname" name="pname"/></td>						
					</tr>
					<tr>
						<td colspan="2" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query('organization')">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>
						</td>
					</tr>					
				</table>
			</form>
		</div>
	<!-- 数据表格 -->		
		<div region="center" style="width:100%;height: 100%;border:0;">
			<table id="organization">
				<thead>   
			       <tr>  
			       	   <th data-options="field:'id',width:80,sortable : true">ID</th>			        
					   <th data-options="field:'code',width:100,sortable : true">组织编号</th>
			           <th data-options="field:'name',width:100,sortable : true">组织名称</th>   
			           <th data-options="field:'type',width:100,sortable : true,formatter:format_orgType" >组织类型</th>
			           <th data-options="field:'parentOrg.name',width:150,sortable : true,formatter:format_parentOrg_name">上级组织名称</th>
			           <th data-options="field:'manager',width:100,sortable : true">管理者</th>
			           <th data-options="field:'managerContact.tel',width:100,sortable : true,formatter:format_managerContact_tel">管理者联系方式</th>
			           <th data-options="field:'description',width:200,sortable : true">备注</th>
			           <th data-options="field:'enabled',width:58,sortable : true,formatter:format_enabled">状态</th>
			       </tr>   
			   </thead>
			</table>
		</div>

	<div id="showDialog" class="easyui-dialog" shadow="false" closed="true" buttons="#dlg-buttons" title="组织管理" style="width: 500px; height: 380px;">
		<iframe scrolling="auto" id='openReceiveFeedBack' name="openReceiveFeedBack" frameborder="0" src="" style="width: 100%; height: 98%;"></iframe>
	</div>
	
	<div id="editDialog"></div>
	
	<!--右键菜单div-->
	<div id="menu" class="easyui-menu" style="width: 80px;">
		<!--放置一个隐藏的菜单Div-->
		<div data-options="name:'search'" onclick="simpleSearch();">查找</div>
		<div class="addItem" data-options="name:'add'" onclick="addFun('organization','新建组织',430,415);">新建</div>
		<!--具体的菜单事件请自行添加，跟toolbar的方法是基本一样的-->
		<div data-options="name:'edit'" onclick="editFun('organization','编辑组织',430,415);">编辑</div>
<!-- 		<div data-options="name:'delete'" onclick="del('organization');">移除</div>
 -->	</div>
</body>
</html>
