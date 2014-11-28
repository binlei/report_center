<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/demo.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/main/getId.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$()
			.ready(
					function() {
						/*请求参数转json对象*/
						function requestParamToJson(paramArray) {
							var jsonObj = {};
							$(paramArray).each(function() {
								jsonObj[this.name] = this.value;
							});
							console.log(jsonObj);
							return jsonObj;
						}
						// 手机号码验证    
						jQuery.validator
								.addMethod(
										"isMobile",
										function(value, element) {
											var length = value.length;
											return this.optional(element)
													|| (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
															.test(value));
										}, "请正确填写您的手机号码");

						var v = $("#addUserForm").validate(
								{
									submitHandler : function() {
										var rs = requestParamToJson($(
												"#addUserForm")
												.serializeArray());
									},
									rules : {
										code : {
											required : true,
											maxlength : 12
										},
										password : {
											required : true,
											minlength : 6,
											maxlength : 15
										},
										currentPass : {
											required : true,
											minlength : 6,
											maxlength : 15,
											equalTo : "#userPass"
										},
										mobie : {
											required : true,
											isMobile : true
										}
									},
									messages : {
										code : {
											required : "用户名不能为空",
											maxlength : "用户不能大于{0}个字符"
										},
										password : {
											required : "密码不能为空",
											minlength : "密码不能小于{0}个字符",
											maxlength : "密码不能大于{0}个字符"
										},
										currentPass : {
											required : "确认密码不能为空",
											minlength : "确认密码不能小于{0}个字符",
											maxlength : "确认密码不能大于{0}个字符",
											equalTo : "两次输入的密码不一致"
										},
										userName : {
											required : true,
											maxlength : 12
										},
										mobie : {
											required : "手机号码不能为空",
											isMobile : "请输入正确的手机号"
										}
									},
									errorPlacement : function(error, element) { //      
										error.appendTo(element.parent("td")
												.next("td"));
									},
									highlight : function(element, errorClass) { //针对验证的表单设置高亮  
										$(element).addClass(errorClass);
									},
									success : function(label) {
										label.addClass("valid").text("验证通过");//添加样式为valid   
									}

								});

					});
</script>
</head>
<body>
	<form id="addUserForm">

			<div class="easyui-tabs" style="width: 400px; height: 250px" align="center">
				<div title="基本信息" style="padding: 10px">
					<table>
						<tr>
							<td>用户名</td>
							<td><input type="text" name="user.code"  /></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>密码</td>
							<td><a href="#">修改密码</a></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>性别</td>
							<td><input type="radio" value="男" name="sex"
								checked="checked" />男 <input type="radio" value="女" name="sex" />女
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>手机</td>
							<td><input type="text" name="user.contact.mobie" /></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
						</tr>
					</table>
				</div>
				<div title="其他信息">
				<table>
					<tr>
						<td>身份证</td>
						<td><input name="user.contact.identityNo" /></td>
						<td></td>
					</tr>
					<tr>
						<td>生日</td>
						<td><input name="user.contact.birthDate" /></td>
						<td></td>
					</tr>
					<tr>
						<td>地址</td>
						<td><input name="user.contact.address" /></td>
						<td></td>
					</tr>
					<tr>
						<td>电话</td>
						<td><input name="user.contact.tel" /></td>
						<td></td>
					</tr>
					<tr>
						<td>邮箱</td>
						<td><input name="user.contact.email" /></td>
						<td></td>
					</tr>
					<tr>
						<td>qq</td>
						<td><input name="user.contact.qq" /></td>
						<td></td>
					</tr>
				</table>
				</div>
			</div>
			<div>
				<input type="submit" value="提交" /> <input type="button"
								value="关闭" /></td>
				</div>
		</form>
</body>
</html>