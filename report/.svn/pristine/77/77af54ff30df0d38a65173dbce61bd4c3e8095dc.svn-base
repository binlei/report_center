<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
<script type="text/javascript"> 
$(function(){
	$("#role_tree").tree({
        method: "get",
        lines: true,
        url: postPath+'/role/getRolesByUserId/'+$("#uid").val(),
        onlyNodeExpand: true,
        toggleOnClick: false,
        checkbox: true,
        enableContextMenu: false,
        loadFiler:function(){
        	alert();
        },
        onClick:function(node){
        	if(node.checked){
	        	$(this).tree('uncheck', node.target);
        	}else{
	        	$(this).tree('check', node.target);
        	}
        } 
    });
});
</script>
</head>
<body class="easyui-layout">
 	<fieldset>
	 	<input type="hidden" value="${uid}" id="uid">
	 	<legend>所属角色</legend>
	 	<ul id="role_tree"></ul>
 	</fieldset>
 	<div align="right" style="height: 40px;"><a href="javascript:void(0)" class="easyui-linkbutton" onclick="userBindRole('role_tree','uid');">绑定角色</a></div>
</body>
</html>