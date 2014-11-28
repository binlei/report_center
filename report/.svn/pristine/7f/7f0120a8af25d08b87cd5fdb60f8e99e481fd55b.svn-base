<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<style type="text/css">
form {
	margin-top: 20px;
}
td, option {
	font-size: 14px;
}
</style>

<script type="text/javascript">
	
	function load() {
		if ("1" == '${suss}') {
			alert("新增成功！");
			parent.refresh();
		}

		else if ("0" == '${suss}') {
			alert("新增失败！");
			parent.refresh();
		}
		
		var count = document.getElementById("warehouse").getElementsByTagName("option").length;
		for (var i = 0; i < count; i++) {
			if ($("#warehouse").get(0).options[i].text == '${serialInfo.warehouse}') {
				$("#warehouse").get(0).options[i].selected = true;
				break;
			}
		}
		if ($("#warehouse").val() == '入库') {
			$("#status").html('<option value="拒签">拒签</option><option value="退货">退货</option>');
			var count1 = document.getElementById("status").getElementsByTagName("option").length;
			for (var i = 0; i < count1; i++) {
				if ($("#status").get(0).options[i].text == '${serialInfo.status}') {
					$("#status").get(0).options[i].selected = true;
					break;
				}
			}
		} else {
			$("#status").html('<option value="'+'${serialInfo.status}'+'">'+'${serialInfo.status}'+'</option>');
		}
		
	}

	function bbb() {
		if (document.getElementById("serialNo").value == '') {
			$("#_serialNo").css('display','');
			return false;
		}
		else if (document.getElementById("product").value == '') {
			$("#_product").css('display','');
			return false;
		}
		if (document.getElementsByName("serialDate")[0].value == '') {
			$("#_serialDate").css('display','');
			return false;
		}
		else if (document.getElementById("quantity").value == '') {
			$("#_quantity").css('display','');
			return false;
		}
		else if (document.getElementById("price").value == '') {
			$("#_price").css('display','');
			return false;
		}
		else if (document.getElementById("money").value == '') {
			$("#_money").css('display','');
			return false;
		}
		else if (document.getElementById("address").value == '') {
			$("#_address").css('display','');
			return false;
		}
		else if (document.getElementById("customer").value == '') {
			$("#_customer").css('display','');
			return false;
		}
		else if (document.getElementById("supplier").value == '') {
			$("#_supplier").css('display','');
			return false;
		}
		else if (document.getElementById("orderNo").value == '') {
			$("#_orderNo").css('display','');
			return false;
		}
		else if (document.getElementById("billNo").value == '') {
			$("#_billNo").css('display','');
			return false;
		}
		else if (document.getElementById("warehouse").value == '') {
			$("#_warehouse").css('display','');
			return false;
		}
		else if (document.getElementById("status").value == '') {
			$("#_status").css('display','');
			return false;
		}
		return true;
	}
	
	function aaa() {
		var b = bbb();
		if(b) {
			$.ajax({
				url : 'saveInfo?'+$("#addInfo").serialize(),
				type: "post",
				dataType : "json",
				success : function(msg){
					if (msg.data == '0') {
						alert("保存失败！");
					} else {
						alert("保存成功！");
						refreshDagagrid("${beanName}");
					} 
				}
			});
		}
	}
	
	function check(value) {
		if ($("#"+value).val() == '') {
			$("#_"+value).css('display', '');
		} else {
			$("#_"+value).css('display', 'none');
		}
	}
	
	function calculate() {
		var quantity = $("#quantity").val();
		var price = $("#price").val();
		if (quantity != '' && price != '') {
			$("#money").val(Number(quantity) * Number(price));
		}
		if (document.getElementsByName("serialDate")[0].value == '') {
			$("#_serialDate").css('display', '');
		} else {
			$("#_serialDate").css('display', 'none');
		}
	}
	
	function changeStatus() {
		var warehouse = $("#warehouse").val();
		if('出库' == warehouse) {
			$("#status").html('<option value="发货">发货</option>');
		} else if ('入库' == warehouse) {
			$("#status").html('<option value="拒签">拒签</option><option value="退货">退货</option>');
		} else if ('' == warehouse) {
			$("#status").html('<option value="">请选择</option>');
		}
	}
	
</script>

</head>

<body onload="load()" style="background-color: #FFFFFF">
	<form id="addInfo" name="addInfo" action="" method="post">
		<input type="hidden" value="${serialInfo.id}" name="id"/>
		<table style="padding-left: 30px;">
			<tr>
				<td>串号：</td>
				<td><input type="text" value="${serialInfo.serialNo}" name="serialNo" id="serialNo" onblur="check(this.id);"/></td>
				<td><font style="color: red">*</font></td>
				<td style="width: 60px;"><font id="_serialNo" style="display: none; color: red">必填</font></td>
				<td>商品:</td>
				<td><input type="text" value="${serialInfo.product}" name="product" id="product" onblur="check(this.id);"/></td>
				<td><font style="color: red">*</font></td>
				<td style="width: 60px;"><font id="_product" style="display: none; color: red">必填</font></td>
			</tr>
			<tr>
				<td>日期：</td>
				<td><input class="timeInput easyui-datebox" value="${serialInfo.extendProp1}" name="serialDate" id="serialDate" onblur="checkDate();"/></td>
				<td><font style="color: red">*</font></td>
				<td><font id="_serialDate" style="display: none; color: red">必填</font></td>
				<td>数量：</td>
				<td><input type="text" value="${serialInfo.quantity}" name="quantity" id="quantity" onblur="check(this.id); calculate()"/></td>
				<td><font style="color: red">*</font></td>
				<td><font id="_quantity" style="display: none; color: red">必填</font></td>
			</tr>
			<tr>
				<td>单价：</td>
				<td><input type="text" value="${serialInfo.price}" name="price" id="price" onblur="check(this.id); calculate()"/></td>
				<td><font style="color: red">*</font></td>
				<td><font id="_price" style="display: none; color: red">必填</font></td>
				<td>金额：</td>
				<td><input type="text" value="${serialInfo.money}" name="money" id="money" onblur="check(this.id);"/></td>
				<td><font style="color: red">*</font></td>
				<td><font id="_money" style="display: none; color: red">必填</font></td>
			</tr>
			<tr>
				<td>客户：</td>
				<td><input type="text" value="${serialInfo.customer}" name="customer" id="customer" onblur="check(this.id);"/></td>
				<td><font style="color: red">*</font></td>
				<td><font id="_customer" style="display: none; color: red">必填</font></td>
				<td>供应商：</td>
				<td><input type="text" value="${serialInfo.supplier}" name="supplier" id="supplier" onblur="check(this.id);"/></td>
				<td><font style="color: red">*</font></td>
				<td><font id="_supplier" style="display: none; color: red">必填</font></td>
			</tr>
			<tr>
				<td>订单号：</td>
				<td><input type="text" value="${serialInfo.orderNo}" name="orderNo" id="orderNo" onblur="check(this.id);"/></td>
				<td><font style="color: red">*</font></td>
				<td><font id="_orderNo" style="display: none; color: red">必填</font></td>
				<td>运单号：</td>
				<td><input type="text" value="${serialInfo.billNo}" name="billNo" id="billNo" onblur="check(this.id);"/></td>
				<td><font style="color: red">*</font></td>
				<td><font id="_billNo" style="display: none; color: red">必填</font></td>
			</tr>
			<tr>
				<td>地区：</td>
				<td colspan="5"><input type="text" value="${serialInfo.address}" name="address" style="width: 99%;" id="address" onblur="check(this.id);"/></td>
				<td><font style="color: red">*</font></td>
				<td><font id="_address" style="display: none; color: red">必填</font></td>
			</tr>
			<tr>
				<td>出/入库：</td>
				<td>
					<select id="warehouse" name="warehouse" style="width: 95%;" onblur="check(this.id);" onchange="changeStatus();">
						<option value="">请选择</option>
						<option value="出库">出库</option>
						<option value="入库">入库</option>
					</select>
				</td>
				<td><font style="color: red">*</font></td>
				<td><font id="_warehouse" style="display: none; color: red">必填</font></td>
				<td>状态：</td>
				<td>
					<select id="status" name="status" style="width: 100%;" onblur="check(this.id);">
						<option value="">请选择</option>
<!-- 						<option value="发货">发货</option> -->
<!-- 						<option value="拒签">拒签</option> -->
<!-- 						<option value="退货">退货</option> -->
					</select>
				</td>
				<td><font style="color: red">*</font></td>
				<td><font id="_status" style="display: none; color: red">必填</font></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td>
					<textarea name="remarks" style="width: 278%; height: 95px;">${serialInfo.remarks}</textarea>
				</td>
			</tr>
						<tr>
				<td align="center" valign="middle" colspan="4">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="aaa();">保存</a>&nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm();">重置</a></td>
			</tr>
		</table>
	</form>
</body>
</html>
