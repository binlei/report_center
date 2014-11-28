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
</head>
<body>
	<div>
		<form id="commentForm" name="commentForm" >
			<input type="hidden" name="id" value='${common.id}'>
			<table>
				<tr>
					<td class="proStyle">实际出库日期</td>
					<td><input name="realityDate" data-options="required:true" value="${common.realityDate}" class="easyui-validatebox Wdate" data-options="required:true"
					 onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
					<td class="proStyle">发运类别</td>
					<td><input id="despatchCategory" name="despatchCategory"  value="${common.despatchCategory}" data-options="required:true"/></td>
					<td class="proStyle">发运区域</td>
					<td><input id="despatchLocation" name="despatchLocation" value="${common.despatchLocation}" data-options="required:true"/></td>
				</tr>
				<tr>
					<td class="proStyle">始发地</td>
					<td><input name="startCity" data-options="required:true" value="${common.startCity}" /></td>
					<td class="proStyle">一级地市</td>
					<td><input id="oneCities" name="oneCities" value="${common.oneCities}" data-options="required:true"/></td>
					<td class="proStyle">目的地</td>
					<td><input id="dstination" name="dstination" value="${common.dstination}" data-options="required:true"/></td>
				</tr>
				<tr>
					<td class="proStyle">承运类别</td>
					<td><input name="acceptCategory" data-options="required:true" value="${common.acceptCategory}" /></td>
					<td class="proStyle">件数</td>
					<td><input id="piece" name="piece" value="${common.piece}" data-options="required:true"/></td>
					<td class="proStyle">重量</td>
					<td><input id="weight" name="weight" value="${common.weight}" data-options="required:true"/></td>
				</tr>
				<tr>
					<td class="proStyle">计费重量</td>
					<td><input name="chargeWeight" data-options="required:true" value="${common.chargeWeight}" /></td>
					<td class="proStyle">运费金额</td>
					<td><input id="freightAmount" name="freightAmount" value="${common.freightAmount}" data-options="required:true"/></td>
					<td class="proStyle">承运方</td>
					<td><input id="shipper" name="shipper" value="${common.shipper}" data-options="required:true"/></td>
				</tr>
				<tr>
					<td class="proStyle">运单号</td>
					<td><input name="wayBillNo" data-options="required:true" value="${common.wayBillNo}" /></td>
					<td class="proStyle">票数</td>
					<td><input id="poll" name="poll" value="${common.poll}" data-options="required:true"/></td>
					<td class="proStyle">结算金额</td>
					<td><input id="closeMoney" name="closeMoney" value="${common.closeMoney}" data-options="required:true"/></td>
				</tr>
				<tr>
					<td class="proStyle">税后金额</td>
					<td><input name="afterMoney" data-options="required:true" value="${common.afterMoney}" /></td>
					<td class="proStyle">单台运费</td>
					<td><input id="singleMoney" name="singleMoney" value="${common.singleMoney}" data-options="required:true"/></td>
					<td class="proStyle">保价</td>
					<td><input id="saveMoney" name="saveMoney" value="${common.saveMoney}"  data-options="required:true"/></td>
				</tr>
				<tr>
					<td class="proStyle">描述</td>
					<td colspan="5"><textarea cols="102" rows="5" name="description" style="resize:none;">${common.description}</textarea>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>
