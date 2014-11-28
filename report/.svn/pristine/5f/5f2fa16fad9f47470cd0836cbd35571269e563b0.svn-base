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
		margin-top:5px;
	}
</style>

<script type="text/javascript">
	function load() {
	}

	function aaa() {
		$.ajax({
			type: "POST",
			url : 'saveMoreInfo?settleNo='+document.getElementById("settleNo").value
					+"&information="+document.getElementById("information").value,
			dataType : "json",
			success : function(msg){
				if ("1" == msg.data) {
					alert("回款确认保存成功！");
					parent.closeWin();
					parent.refresh();
				} else if ("0" == msg.data) {
					alert("回款确认保存失败！");
					parent.closeWin();
					parent.refresh();
				}
			}
		});
	}
</script>
	
</head>

<body onload="load()">
	<form id="addInfo" name="addInfo" action="" method="post">
		<input type="hidden" name="settleNo" value='${settleNo}' id="settleNo">
		<table>
			<tr>
				<td>
					<textarea id="information" style="width: 300px; height: 150px;" name="information"></textarea>
				</td>
			</tr>
		</table>
		<div style="text-align: center; padding-top: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="aaa()" >提交</a>
		</div>
	</form>
	 
</body>
</html>
