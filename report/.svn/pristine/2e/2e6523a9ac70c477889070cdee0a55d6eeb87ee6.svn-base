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
			alert("回款确认添加成功！");
			parent.refresh();
		}
		
		else if ("0" == '${suss}') {
			alert("回款确认添加失败！");
			parent.refresh();
		}
	}
	
	function aaa() {
		if (document.getElementById("contactus").value.length > 100) {
			alert("限输入100个字！");
			return;
		}
		document.getElementById("addInfo").submit();
		parent.closeWin();
	}
	
</script>
	
</head>

<body onload="load()">
	<form id="addInfo" name="addInfo" action="saveInfo" method="post">
		<input type="hidden" name="settleNo" value='${settleNo}'>
		<textarea cols="62" rows="10" id="contactus" name="contactus">${information}</textarea>
		<div>
			<font color="red">限输入100个汉字</font>
		</div>
		<div style="text-align: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="aaa()" >提交</a>
		</div>
	</form>
	 
</body>
</html>
