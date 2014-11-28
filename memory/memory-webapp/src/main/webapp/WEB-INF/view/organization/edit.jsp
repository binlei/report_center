<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form class="cmxform" id="commentForm" method="post" >
		<table class="table_border" border="1" width="400px" height="350px">
					<tr>
						<td class="title" >
							&nbsp;&nbsp;组织编号
						</td>
						<td>
							<input name="code" id="code"    value="${organization.code}" readonly="readonly"/>
							<input type="hidden" id="id" name="id" value="${organization.id }"/>
						</td>
					</tr>
					<tr>
						<td class="title" >
							<font color="red">*&nbsp;</font>组织名称
						</td>
						<td>
							<input name="name" id="name"   value="${organization.name}" class="easyui-validatebox" data-options="required:true" missingMessage="该项为必填项" />
						</td>
					</tr>
					<tr>
						<td class="title" >
							<font color="red">*&nbsp;</font>上级组织名称
						</td>
						<td>
							<input type="hidden"  id="parentOrgId" value="${organization.parentOrg.id}" />
							<select id="parentOrganization" name="parentOrg.id"></select>
						</td>
					</tr>
					<tr>
						<td class="title" >
							&nbsp;&nbsp;管理者
						</td>
						<td>
							<input name="manager" id="manager"   value="${organization.manager}" />
						</td>
					</tr>
					<tr>
						<td class="title" >
							&nbsp;&nbsp;管理者联系方式
						</td>
						<td>
							<input name="managerContact.tel" id="managerContact.tel" value="${organization.managerContact.tel }"  class="easyui-validatebox" data-options="validType:'mobile'"/>
						</td>
					</tr>
					<tr>
						<td class="title" >
							<font color="red">*&nbsp;</font>组织类型
						</td>
						<td>
							<input type="hidden" id="channelTypeId" value="${organization.type.id }" />
							<select id="getChannelType" name="type.id" > 
							</select>
						</td>
					</tr>
					<tr>
						<td class="title" >
							<font color="red">*&nbsp;</font>状态
						</td>
						<td> 
						<div class="radioDiv">
							<input type="radio" name="enabled" id="enabled"  value="true" <c:if test="${organization.enabled eq true}">checked</c:if>  required />有效
						</div>
						<div class="radioDiv">
							<input type="radio" name="enabled" id="enabled" value="false" <c:if test="${organization.enabled eq false}">checked</c:if> required />失效
						</div>
						</td>
					</tr>
					<tr>
						<td class="title" >
							 &nbsp;&nbsp;备注
						</td>
						<td>
							<textarea  name="description" id="description"  class="easyui-validatebox" validType="length[0,200]" invalidMessage="不能超过200个字符">${organization.description }</textarea>
						</td>
					</tr>
					<tr>
						<td align="center" valign="middle" colspan="2">
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="editData('organization');">保存</a>&nbsp;&nbsp;&nbsp;
							<!-- <a href="javascript:void(0)" class="easyui-linkbutton"onclick="resetForm();">重置</a> -->
						<!-- 	<input type="button" value="提交" class="submit" id="save" onclick="saveSubmit('organization');"/>
							<input type="button" value="取消" class="blue_mod_btn" onclick="parent.closeDialog();"/> -->
						</td>
					</tr>
				</table>
</form>
<div class="msg" style="width:100%;height:20px;position:absolute;bottom:0;background-color:#E9F4F7;">
</div>
<script type="text/javascript">
<!--
	getChannel('${organization.code}');
//-->
</script>