<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<%@ include file="/plugins/progressBar.jsp" %>
<script type="text/javascript">
function change(obj){
	$("#message span").text("");
	var filePate = $("#excelFile").val();
	if(!validFileFormat(filePate,"xls","xlsx")){
		$("#message span").text("* 请导入 xls 或者 xlsx 的文件!");
	}else{
		$("#improtButton").linkbutton("enable");
	}
}

function init(){
	$("#improtButton").linkbutton("disable");
}
</script>
</head>
<body onload="init();">
	<div align="center" id="file_upload">
		<form id="file_upload_id" name="file_upload_name" enctype="multipart/form-data">
			<table>
				<tr>
					<th>请选择文件：</th>
					<td>
						<div id="message" align="right"><span style="color: red"></span></div>
						<input type="file" id="excelFile" name="excelFile" style="border-style: ridge;" onchange="change(this);" />
					</td>
					<td><img style="margin-left:5px; text-align: center; display: none; width: 26px;height: 26px" id="progress"></td>
				</tr>
				<tr><td height="5px;"></td></tr>
				<tr>
					<th></th>
					<td align="right"><a href="javascript:void(0)" class="easyui-linkbutton" onclick="ajaxUpload('${data}');" id="improtButton">导入数据</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
</html>
