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
		margin-top:20px;
	}
</style>

<script type="text/javascript">
	function aaa() {
		$.ajax({
			type: "POST",
			url : 'saveInfo?id='+$("#chargeid").val()
					+"&category="+$("#category").val()
					+"&kind="+$("#kind").val(),
			dataType : "json",
			success : function(msg){
				if ("1" == msg.data) {
					alert("保存成功");
				} else if ("0" == msg.data) {
					alert("保存失败");						
				}
				parent.closeWin();
				parent.refresh();
			}
		});
	}
</script>
	
</head>

<body>
	<form id="addInfo" name="addInfo" action="" method="post">
		<input type="hidden" name="id" value='${charge.id}' id="chargeid">
		<table>
			<tr>
				<td>
					品类：
				</td>
				<td>
					<input type="text" name="category" value="${charge.category}" style="width: 210px;" id="category"/>
				</td>
			</tr>
			<tr>
				<td>
					型号：
				</td>
				<td>
					<input type="text" name="kind" value="${charge.kind}" style="width: 210px;" id="kind"/>
				</td>
			</tr>
		</table>
		<div style="text-align: center; padding-top: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="aaa()" >提交</a>
		</div>
	</form>
	 
</body>
</html>
