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
	getSMC();
	$("#do_brand").combobox("disable");
	$("#do_model").combobox("disable");
	$("#extendProp3").combobox("disable");
})
</script>
</head>
<body>
	<div>
		<fieldset>
			<legend>增加供应商</legend>
			<form id="supplierForm">
				<table align="center">
					<tr>
						<th><img src="${pageContext.request.contextPath}/images/suppliers.png">供应商：</th>
						<td><input name="name" id="supplierName" class="easyui-validatebox" data-options="required:true" /></td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('supplierForm','/financeSupplier/doSupplier')">增加</a>
						</td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
	<div>
		<fieldset style="padding-top: 10px;margin-top: 10px">
			<legend>增加服务费</legend>
			<form id="commentForm" name="commentForm" >
				<input type="hidden" name="id" value='${serviceCost.id}'>
				<table>
					<tr>
						<td class="proStyle"><span>*</span>供应商</td>
						<td><input name="supplier_id" data-options="required:true" value="${serviceCost.financeSupplier.id}" /></td>
						<td class="proStyle"><span>*</span>分类</td>
						<td>
							<input id="do_category" name="temp_category" data-options="required:true"/>
							<input type="hidden" id="hidden_category" name="category"/>
						</td>
					</tr>
					<tr>
						<td class="proStyle">品牌</td>
						<td>
							<input id="do_brand" name="temp_brand" />
							<input type="hidden" id="hidden_brand" name="brand"  />
						</td>
						<td class="proStyle">型号</td>
						<td>
							<input id="do_model" name="temp_model"  />
							<input type="hidden" id="hidden_model" name="model" />
						</td>
					</tr>
					<tr>
						<td class="proStyle">每台服务费</td>
						<td><input name="serviceCharge" class="easyui-numberspinner" data-options="min:0" value="${serviceCost.serviceCharge}" /></td>
						<td class="proStyle">多少元内免收服务费</td>
						<td>
							<input class="easyui-numberspinner" data-options="min:0" name="extendProp1" value="${serviceCost.extendProp1}" />
							</td>
					</tr>
					<tr>
						<td class="proStyle">销售金额的百分比</td>
						<td>
							<input name="extendProp2" class="easyui-numberspinner" data-options="min:0,max:100"  value="${serviceCost.extendProp2}"  style="width:80px;"> %
						</td>
						<td class="proStyle">免收服务费品牌</td>
						<td>
							<input id="extendProp3" name="temp_extendProp3" value="${serviceCost.extendProp3}" />
							<input type="hidden" id="hidden_extendProp3" name="extendProp3" />
						</td>
					</tr>
					<tr>
						<td class="proStyle">描述</td>
						<td colspan="3"><textarea cols="62" rows="5" name="description" style="resize:none;">${serviceCost.description}</textarea>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
	
</body>
</html>
