<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/taglibs.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>

<style type="text/css">
	form {
		margin-top:50px;
	}
</style>

<script type="text/javascript">
	function load() {
		if ("3" == '${suss}') {
			alert("请导入xls或xlsx文件");
			refreshDagagrid();
		}
		
		else if ("4" == '${suss}') {
			alert("删除数据失败");
			refreshDagagrid();
		}
		
		else if ("1" == '${suss}') {
			alert("删除数据成功");
			refreshDagagrid();
		}
		
		else if ("2" == '${suss}') {
			alert("导入的文件表格不正确！");
			refreshDagagrid();
		}
		$("#progress").css('display','none');
		refreshDagagrid();
	}
	
	function aaa() {
		document.getElementById("aaa").style.display="none";
		document.getElementById("file_upload_id").submit();
		$("#progress").css('display','');
	}
	
</script>
	
</head>

<body onload="load()">
	<form id="file_upload_id" name="file_upload_name" action="deleteMoreByOrderNo" method="post" enctype="multipart/form-data">
		<input type="hidden" name="flag" value="${flag}"/>
		<div>
			请选择文件：<input type="file" name="excelFile"/>
		</div>
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="aaa()"  id="aaa">上传</a>
		</div>
		<div style="text-align: center;" id="progress">
			<img src="${pageContext.request.contextPath}/images/progress.gif">
		</div>
	</form>
	 
</body>
</html>
