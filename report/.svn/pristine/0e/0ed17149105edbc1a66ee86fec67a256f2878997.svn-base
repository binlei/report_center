<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/addCommonHeader.jsp" %>
<%@ include file="/WEB-INF/view/common/branshLineCommon.jsp" %>
<script type="text/javascript">
$(function(){
	getSubstationId();
});
</script>
</head>
<body>
	<form id="commentForm" name="commentForm" action="" method="post">
		<input type="hidden" name="id" value='${common.id}'>
		<table>
			<tr>
				<td class="proStyle">分站 </td>
				<td><input id="subStation" name="subStation" value="${common.subStation.id}" class="combobox-substation"/></td>
				<td class="proStyle">副卡油卡号</td>
				<td><input type="text" name="deputyCard" value="${common.deputyCard}" /></td>
				<td class="proStyle">车型</td>
				<td><input type="text" name="carKind" value="${common.carKind}" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="proStyle">车牌号</td>
				<td><input type="text" name="licenseNo" value="${common.licenseNo}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">行驶证(一维码，非档案编号)</td>
				<td><input type="text" name="license" value="${common.license}" /></td>
				<td class="proStyle">行驶证注册日期</td>
				<td><input id="d411" name="licenseDate" class="Wdate" value="${common.licenseDate}" onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10'})"/>
				</td>
			</tr>
			<tr>
				<td class="proStyle">车辆所有人</td>
				<td><input type="text" name="licenseName" value="${common.licenseName}" class="easyui-validatebox" data-options="required:true"/></td>
				<td class="proStyle">车辆检验有效期</td>
				<td><input id="d412" name="inspectionDate" class="Wdate" value="${common.inspectionDate}" onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10'})"/></td>
				<td class="proStyle">姓名</td>
				<td><input type="text" name="name" id="name" value="${common.name}" onblur="isExist();" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td class="proStyle">身份证号码</td>
				<td><input type="text" name="idCard" value="${common.idCard}" class="easyui-validatebox" data-options="validType:'idcard'"/></td>
				<td class="proStyle">初次领取驾驶证日期</td>
				<td><input id="d413" name="ftReceive" class="Wdate" value="${common.ftReceive}" onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10'})"/></td>
				<td class="proStyle">换证日期</td>
				<td><input id="d414" name="changeDate" class="Wdate" value="${common.changeDate}" onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10'})"/></td>
			</tr>
			<tr>
				<td class="proStyle">联系电话</td>
				<td><input type="text" name="telephone" value="${common.telephone}" class="easyui-validatebox" data-options="required:true,validType:'mobile'"/></td>
				<td class="proStyle">交强险有效期</td>
				<td><input id="d415" name="strongInsdate" class="Wdate" value="${common.strongInsdate}" onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10'})"/></td>
				<td class="proStyle">三责险保额</td>
				<td><input type="text" name="tlInsurance" value="${common.tlInsurance}" class="easyui-validatebox" data-options="validType:'price'"/></td>
			</tr>
			<tr>
				<td class="proStyle">三责险有效期</td>
				<td><input id="d416" name="tlInsuranceDate" class="Wdate" value="${common.tlInsuranceDate}" onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10'})"/></td>
				<td class="proStyle">派出所出具的无犯罪记录证明</td>
				<td>
					<select name="policeProve" id="policeProve">
						<option value="合格" <c:if test="${common.policeProve eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.policeProve eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
				<td class="proStyle">户口本复印件</td>
				<td>
					<select name="householdCopy" id="householdCopy">
						<option value="合格" <c:if test="${common.householdCopy eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.householdCopy eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="proStyle">身份证复印件</td>
				<td>
					<select name="idcardCopy" id="idcardCopy">
						<option value="合格" <c:if test="${common.idcardCopy eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.idcardCopy eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
				<td class="proStyle">驾驶证复印件</td>
				<td>
					<select name="licenseCopy" id="licenseCopy">
						<option value="合格" <c:if test="${common.licenseCopy eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.licenseCopy eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
				<td class="proStyle">担保责任书</td>
				<td>
					<select name="guaranRespon" id="guaranRespon">
						<option value="合格" <c:if test="${common.guaranRespon eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.guaranRespon eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="proStyle">担保人收入证明</td>
				<td>
					<select name="guaranIncome" id="guaranIncome">
						<option value="合格" <c:if test="${common.guaranIncome eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.guaranIncome eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
				<td class="proStyle">担保人户口本复印件</td>
				<td>
					<select name="guaranhouseCopy" id="guaranhouseCopy">
						<option value="合格" <c:if test="${common.guaranhouseCopy eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.guaranhouseCopy eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
				<td class="proStyle">担保人身份证复印件</td>
				<td>
					<select name="guaranidCopy" id="guaranidCopy">
						<option value="合格" <c:if test="${common.guaranidCopy eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.guaranidCopy eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="proStyle">行驶证复印件</td>
				<td>
					<select name="drilicenseCopy" id="drilicenseCopy">
						<option value="合格" <c:if test="${common.drilicenseCopy eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.drilicenseCopy eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
				<td class="proStyle">交强险复印件</td>
				<td>
					<select name="stronginsCopy" id="stronginsCopy">
						<option value="合格" <c:if test="${common.stronginsCopy eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.stronginsCopy eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
				<td class="proStyle">商业险复印件</td>
				<td>
					<select name="commerinsuCopy" id="commerinsuCopy">
						<option value="合格" <c:if test="${common.commerinsuCopy eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.commerinsuCopy eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="proStyle">车辆营运证</td>
				<td>
					<select name="certificate" id="certificate">
						<option value="合格" <c:if test="${common.certificate eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.certificate eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
				<td class="proStyle">协议签订日期</td>
				<td><input id="d417" name="agreeDate" class="Wdate" value="${common.agreeDate}" onfocus="WdatePicker({skin:'whyGreen',minDate:'2009-09-10'})"/></td>
				<td class="proStyle">租车协议</td>
				<td>
					<select name="rentalAgreement" id="rentalAgreement">
						<option value="合格" <c:if test="${common.rentalAgreement eq '合格'}">selected="selected"</c:if>>合格</option>
						<option value="不合格" <c:if test="${common.rentalAgreement eq '不合格'}">selected="selected"</c:if>>不合格</option>
					</select>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
