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
		if ("1" == '${suss}') {
			alert("删除数据成功");
		}
		
		else if ("2" == '${suss}') {
			alert("删除数据失败，请检查文件是否正确！");
		}
		
		else if ("3" == '${suss}') {
			alert("请导入xls或xlsx文件");
		}
		
		else if ("4" == '${suss}') {
			alert("删除数据失败");
		}
	}
	
</script>
	
</head>

<body onload="load()">
	<form id="file_upload_id" name="file_upload_name" action="deleteMoreSalesNoInDeli" method="post" enctype="multipart/form-data">
		<div>
			请选择文件：<input type="file" name="excelFile"/>
		</div>
		<div>
			<input type="submit" value="上传" class="easyui-linkbutton"/>
		</div>
	</form>
	 
</body>
</html>
