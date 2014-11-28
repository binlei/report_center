/**
*name:showMsg
*params:url(请求地址,字符串格式)，data(发送到后台的数据,是一个对象{})，timeout(请求超时时间，整型)
*description:请求方法默认为POST,后台接收可采用request.getParameterMap()
*
**/
function showMsg(url,data, timeout) {
	$.ajax({
		url : url,
		async : false,
		type : 'POST',
		contentType : "application/json; charset=utf-8",
		data : data,
		dataType : 'JSON',
		timeout : timeout,
		error : function() {
			$.messager.show({
				title : '失败',
				msg : '请求错误，请重试！'
			});
		},
		success : function(result) {//result格式为{status:"",msg:"",processTime:""}
			if (result.status == 'success') {
				$.messager.show({
					title : '成功',
					msg : result.msg + "<br>" + "耗时：" + result.processTime
				});
			} else if (result.status == 'fail') {
				$.messager.show({
					title : '失败',
					msg : result.msg + "<br>" + "耗时：" + result.processTime
				});
			}
		}
	});
}