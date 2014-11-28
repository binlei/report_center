<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/addCommonHeader.jsp" %>
<script type="text/javascript">
$(function(){
	getMaincardId();
	getAllCarNoCar();
})
</script>
</head>
<body>
	<div>
		<form id="commentForm" name="commentForm" >
			<input type="hidden" name="id" value="${common.id}">
			<table>
				<tr>
					<td class="proStyle">主卡号</td>
					<td><input id="do_maincard" name="mainCardId" value="${common.maincard}" class="combobox-maincard" /></td>
				</tr>
				<tr>	
					<td class="proStyle">副卡号</td>
					<td><input name="viceCard" id="viceCard" value="${common.vicecard}" class="easyui-validatebox" data-options="required:true,validType:['eqLength[19]','number']"/></td>
					<td class="proStyle">车牌号</td>
					<td><input name="carCard" id="carCard" value="${common.carcard}" class="combobox-carNo"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
