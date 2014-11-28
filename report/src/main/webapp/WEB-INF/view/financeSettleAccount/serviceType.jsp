<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
<script type="text/javascript">
	$(function() {
		$("table #category")
				.combobox(
						{
							valueField : "name",
							textField : "name",
							url : "${ctx}/financeServiceType/getCategory",
							method : "GET",
							editable : true,
							panelHeight : "auto",
							formatter : function(row) {
								var png = combobox_png(row.name.toLowerCase());
								return "<img class='item-img' src='${pageContext.request.contextPath}/images/"+ png +"'><span class='item-text'> "
										+ row.name + "</span>";
							},
							onShowPanel : function(row) {
								$(this).combobox("reload");
							}
						});
	});
</script>
</head>
<body>
	<div>
		<fieldset>
			<legend>增加服务费分类</legend>
			<form id="serviceTypeForm">
				<table align="center">
					<tr>
						<th><img src="${ctx}/images/category.png">品类：</th>
						<td><input name="name" class="easyui-validatebox" data-options="required:true" /></td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('serviceTypeForm','/financeServiceType/doType')">增加</a>
						</td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
	<div>
		<fieldset style="padding-top: 10px;margin-top: 10px">
			<legend>增加服务费分类信息</legend>
			<form id="commentForm" name="commentForm" style="float: center;">
				<input type="hidden" name="id" value='${financeServiceType.id}'>
				<table>
					<tr>
						<td class="proStyle"><span>*</span>分类</td>
						<td><input id="category" name="parentName" data-options="required:true" value='${financeServiceType.parentName}'/></td>
						<td class="proStyle"><span>*</span>品牌</td>
						<td><input id="brand" name="name" class="easyui-validatebox" data-options="required:true" value='${financeServiceType.name}' /></td>
						<td class="proStyle">型号</td>
						<td><input id="model" name="model" value='${financeServiceType.model}' /></td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>