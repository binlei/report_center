<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/taglibs.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/getComboBox.js"></script>
<title>Insert title here</title>
</head>
<body>
<form class="cmxform" id="commentForm" method="post" action="add">
	<fieldset>
		<legend>带<font color="red">&nbsp;*&nbsp;</font>为必填项</legend>
		<table width="100%" class="table_border">
					<tr>
						<td class="title" width="100">
							<font color="red">*&nbsp;</font>组织编号
						</td>
						<td>
							<input name="code" id="code"  maxlength="100" required />
						</td>
					</tr>
					<tr>
						<td class="title" width="100">
							<font color="red">*&nbsp;</font>组织名称
						</td>
						<td>
							<input name="name" id="name"  maxlength="100" required />
						</td>
					</tr>
					<tr>
						<td class="title" width="100">
							<font color="red">*&nbsp;</font>上级组织名称
						</td>
						<td>
							<select id="parentOrganization" name="parentOrg.id" ></select>
						</td>
					</tr>
					<tr>
						<td class="title" width="100">
							<font color="red">*&nbsp;</font>管理者
						</td>
						<td>
							<input name="manager" id="manager"  maxlength="100" required />
						</td>
					</tr>
					<tr>
						<td class="title" width="100">
							<font color="red">*&nbsp;</font>管理者联系方式
						</td>
						<td>
							<input name="managerContact.tel" id="managerContact.tel"  maxlength="100" required />
						</td>
					</tr>
					<tr>
						<td class="title" width="100">
							<font color="red">*&nbsp;</font>组织类型
						</td>
						<td>
							<select id="orgType" name="type.id"></select>
						</td>
					</tr>
					<tr>
						<td class="title" width="100">
							 备注
						</td>
						<td>
							<textarea  name="description" id="description"  maxlength="100" ></textarea>
						</td>
					</tr>
					<tr>
						<td class="title" width="100">
							<font color="red">*&nbsp;</font>是否可用
						</td>
						<td>
							<select id="enabled" name="enabled">
								<option value="1">可用</option>
								<option value="2">不可用</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center" valign="middle" colspan="3">
							<input type="submit" value="提交" class="submit" id="save">
							<input type="button" value="取消" class="blue_mod_btn" onclick="parent.closeDialog();">
						</td>
					</tr>
				</table>
	</fieldset>
</form>
</body>
</html>