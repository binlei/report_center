var strPath = window.document.location.pathname;
var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);

var tabsObj = parent.$("#tabs");// 获取父页面tabs对象

// 关闭当前tabs
function previousPage() {
	var curTabIndex = tabsObj.tabs('getTabIndex', tabsObj.tabs('getSelected'));// 得到当前tabs索引
	tabsObj.tabs('close', curTabIndex);// 关闭当前tabs
	// tabsObj.tabs('select','用户管理');//返回list页面
}

/**
 * @param name
 *            访问的url
 * @param title
 *            dialog title
 * @param width
 *            dialog width
 * @param height
 *            dilog height
 */
function addFun(name, title, width, height) {
	$('#editDialog').dialog({
		title : title,
		width : width,
		height : height,
		maximizable : true,
		closed : true,
		cache : false,
		href : postPath + '/' + name + '/add',
		modal : true,
		onClose : function() {
			tj_query(name);
		}
	});
	$('#editDialog').dialog('open');
}

// 编辑(dialog)
function editFun(name, title, width, height) {
	var selections = $("#" + name).datagrid('getSelections');
	if (selections.length != 1) {
		$.messager.alert('提示', '请勾选一行记录!', 'warning');
	} else {
		$('#editDialog').dialog({
			title : title,
			width : width,
			height : height,
			maximizable : true,
			closed : true,
			cache : false,
			href : postPath + '/' + name + '/edit/' + selections[0].id,
			modal : true,
			onClose : function() {
				tj_query(name);
			}
		});
		$('#editDialog').dialog('open');
	}
}

// 重置表单
function resetForm() {
	$('#commentForm').form('reset');
}

/**
 * 保存
 * 
 * @param _requestUrl
 */
function doData(_requestUrl) {
	var vldate = $('#commentForm').form('validate');
	if (vldate) {
		$.ajax({
			// url : _requestUrl,
			method : 'POST',
			data : $("#commentForm").serialize(),
			dataType : 'text',
			success : function(data) {
				scrol(data);
			}
		});
	} else {
		$.messager.alert("提示", "表单信息填写不完整，带<font color='red'>*</font>为必填项！",
				"info");
	}
}

/**
 * @param _url 指定 请求路径 url
 */
function submitForm(_url) {
	var vldate = $('#commentForm').form('validate');
	var beanName = $("div .easy-table").attr("id");
	if (vldate) {
		$.ajax({
			url : postPath + "/" + beanName + "/" + _url,
			method : 'POST',
			data : $("#commentForm").serialize(),
			dataType : 'json',
			success : function(data) {
				formScrolMsg(data);
			}
		});
	} else {
		formValidateMsg();
	}
}

/**
 * @param _form 指定 form 表单
 * @param _url  指定 请求路径 url
 */
function submitForm(_form,_url) {
	var vldate = $('#'+_form).form('validate');
	if (vldate) {
		$.ajax({
			url : postPath + _url,
			method : 'POST',
			data : $("#"+_form).serialize(),
			dataType : 'text',
			success : function(data) {
				formScrolMsg(data);
				$('#'+_form).form('reset');
			}
		});
	} else {
		formValidateMsg();
	}
}

// 编辑
function editData() {
	var vldate = $('#commentForm').form('validate');
	if (vldate) {
		$.ajax({
			url : postPath + '/edit',
			method : 'post',
			data : $("#commentForm").serialize(),
			dataType : 'text',
			success : function(data) {
				scrol(data);
			}
		});
	} else {
		$.messager.alert("提示", "表单信息填写不完整，带<font color='red'>*</font>为必填项！",
				"info");
	}
}

/**
 * 用户绑定角色
 */
function userBindRole(_tree, uid) {
	var _rids = getTreeIds(_tree);
	var _uid = $("#" + uid).val();
	$.ajax({
		url : "confirmBindRole",
		type : "POST",
		data : {
			uid : _uid,
			rids : _rids
		},
		success : function(data) {
			scrol(data);
		}
	});
}

/**
 * 
 */
function saveBind() {
	var rolePermissions = new Array();
	var roleId = $("#roleId").val();
	var treeObj = $.fn.zTree.getZTreeObj("permission");
	var nodes = treeObj.getCheckedNodes(true);
	if (nodes.length < 1) {
		/* $.messager.alert('提示','至少选择一个权限!','warning'); */
		$.ajax({
			url : 'deletePermissionByRoleId',
			type : 'POST',
			data : roleId,
			contentType : "application/json; charset=utf-8",
			dataType : 'text',
			success : function(data) {
				scrol(data);
			}
		});
		return;
	}
	for (var i = 0; i < nodes.length; i++) {
		var rolePermission = new Object();
		rolePermission.roleId = roleId;
		rolePermission.permissionId = nodes[i].id;
		rolePermissions.push(rolePermission);
	}
	$.ajax({
		url : 'saveBindPermission',
		type : 'POST',
		data : JSON.stringify(rolePermissions),
		contentType : "application/json; charset=utf-8",
		dataType : 'text',
		success : function(data) {
			scrol(data);
		}
	});
}

