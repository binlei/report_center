function enabled_submit(){
	$("#_login_img").removeAttr("disabled");
}

function login() {
	$("#_login_img").attr("disabled","disabled");
	var username = $("#_username");
	var password = $("#_password");
	var vmsg = $("#d_login_msg");

	var _username = username.val();
	var _password = password.val();

	if (_username == "") {
		vmsg.html("用户名为空，请输入。");
		enabled_submit();
		
		username.focus();
		return false;
	}
	if (_password == "") {
		vmsg.html("密码为空，请输入。");
		enabled_submit();
		
		password.focus();
		return false;
	}
	var user = {
		username : _username,
		password : _password
	};
	
	$.ajax({
		url : 'user/login',
		type : 'POST',
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(user),
		dataType : 'JSON',
		timeout : 60000,
		error : function() {
			
			vmsg.html("加载失败，请刷新重新登录。。。");
			enabled_submit();
		},
		success : function(result) {
			if (result.status == 'success') {
				$("#_formLogin").attr("action", "main").attr("method", "GET").submit();
				$("#d_login_msg").html("认证成功！");
				
			} else {
				
				$("#d_login_msg").html("用户名或密码错误，请重新输入。");
				enabled_submit();
				//vu.val("");
				password.val("");
				password.focus();
			}
		}
	});
};
function loginProcess() {
	$("#d_login_process_bar").show();
}
function clearProcessBar() {
	$("#d_login_process_bar").hide();
}