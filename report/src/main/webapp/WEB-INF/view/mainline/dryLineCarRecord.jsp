<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<form id="commentForm" name="commentForm" >
		<input type="hidden" name="id" value='${common.id}'>
		<table>
			<tr>
				<td class="proStyle">购置时间</td>
				<td style="width: 180px;">
					<input id="d411" name="date" class="Wdate" value="${common.extendProp1}" class="easyui-validatebox" data-options="required:true"
					 onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10'})"/>
				</td>
				<td class="proStyle">购置公司</td>
				<td><input name="company" value="${common.company}" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="proStyle">车号</td>
				<td><input name="carCard" value="${common.carCard}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">油卡号</td>
				<td><input name="oilCard" value="${common.oilCard}" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="proStyle">品牌</td>
				<td><input name="brand" value="${common.brand}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">车型</td>
				<td><input name="carKind" value="${common.carKind}" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="proStyle">购置价格</td>
				<td><input name="price" value="${common.price}" class="easyui-validatebox" data-options="required:true,validType:'price'"/></td>
				<td class="proStyle">载重量</td>
				<td><input name="capacity" value="${common.capacity}" class="easyui-validatebox" data-options="validType:'weight'"/></td>
			</tr>
			<tr>
				<td class="proStyle">ETC号</td>
				<td><input name="etcCard" value="${common.etcCard}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">摊销年限</td>
				<td><input name="amortizeAge" value="${common.amortizeAge}" class="easyui-validatebox" data-options="required:true,validType:'number'"/></td>
			</tr>
		</table>
	</form>
</body>
</html>
