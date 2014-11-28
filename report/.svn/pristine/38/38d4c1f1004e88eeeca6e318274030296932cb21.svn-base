<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/drylineListUtil.jsp"></jsp:include>
<%@ include file="/WEB-INF/view/common/dryLineCommon.jsp"%>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('expand','north');
		srchForm = $("#srchForm");		
		//sy.createDatagrid($("#dryLineMaincard"), srchForm, rightMenuFun);
		sy.createDatagrid($("#dryLineMaincard"),srchForm);
		getMaincardCard();
	});
	function rightMenu(){commRightMenu('dryLineMaincard');}

</script>
</head>
<body class="easyui-layout" onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="dryLineMaincard">
				<input type="hidden" name="method" value="page">
				<input type="hidden" id="doMethod" value="doMaincard">
				<table class="tableForm datagrid-toolbar"  align="center" >
					<tr>							
						<th>主卡</th>
						<td><input id="maincard" name="maincard" class="combobox-maincard"/></td>
						<td >
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div region="center">
			<table id="dryLineMaincard" class="easy-table">
				<thead>   
			       <tr>
						<th data-options="field:'id',width:$(this).width() * 0.4,sortable : true, align:'center'">ID</th>
						<th data-options="field:'mainCard',width:$(this).width() * 0.2,sortable : true, align:'center'">卡号</th>
						<th data-options="field:'balance',width:$(this).width() * 0.2,sortable : true, align:'center'">余额</th> 
			       </tr>   
			   </thead>
			</table>
		</div>
		
 		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
 			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="dryLineMaincard_add" onclick="open('addMaincardPage','add','新增主卡',430,180)">新增主卡</div>
			<div class="menu-sep"></div>
			<div id="dryLineMaincard_do_delete" onclick="deleteByIds()">删除</div>
		</div>
</body>
</html>
