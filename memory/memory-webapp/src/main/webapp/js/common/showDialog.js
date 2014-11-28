var indexName; // 标记属于哪个页面



function add(name) { //  添加窗口
	indexName = name;
	$('#showDialog').dialog({
		modal : true,
		closed : true,
		title : "新增",
		width :400,
		height:380
	});
	$('#openReceiveFeedBack')[0].src = 'add';
	$('#showDialog').dialog('open');
}

// 绑定角色
function bindRole(name){
	var selectRow = $("#user").datagrid('getSelections');
	if(selectRow.length == 1){
		$('#showDialog').dialog({
			modal : true,
			closed : true,
			title : "绑定角色",
			width :500,
			height:450
		});
		$('#openReceiveFeedBack')[0].src = 'bindRole?id='+selectRow[0].id+"&name="+selectRow[0].name;
		$('#showDialog').dialog('open');
	}else{
		$.messager.alert('提示','请勾选一行记录!','warning');
	}
}

//绑定用户
function bindUser() {
	var selectRow = $("#role").datagrid('getSelections');
	if(selectRow.length == 1){
		$('#showDialog').dialog({
			modal : true,
			closed : true,
			title : "绑定用户",
			width :500,
			height:450
		});
		$('#openReceiveFeedBack')[0].src = 'bindUser?id='+selectRow[0].id+"&name="+selectRow[0].name;
		$('#showDialog').dialog('open');
	}else{
		$.messager.alert('提示','请勾选一行记录!','warning');
	}
}

//绑定权限
function bindPermission() {
	var selectRow = $("#role").datagrid('getSelections');
	if(selectRow.length == 1){
		$('#showDialog').dialog({
			modal : true,
			closed : true,
			title : "绑定权限",
			width :400,
			height:380
		});
		$('#openReceiveFeedBack')[0].src = 'bindPermission?id='+selectRow[0].id+"&name="+selectRow[0].name;
		$('#showDialog').dialog('open');
	}else{
		$.messager.alert('提示','请勾选一行记录!','warning');
	}
}

function edit(name) { // 编辑窗口
	// 获取选中的对象
	var selectRow = $("#"+name).datagrid('getSelections');
	indexName = name;
	if (selectRow.length == 1) {
		$('#showDialog').dialog({
			modal : true,
			closed : true,
			title : "编辑",
			width :400,
			height:380
		});
		$('#openReceiveFeedBack')[0].src = 'edit/' + selectRow[0].id;
		$('#showDialog').dialog('open');
	} else {
		$.messager.alert('Warning', '请勾选一行记录', 'warning');
	}
}

// 删除
function del(name) {
	var selectRow = $("#"+name).datagrid('getSelections');
	indexName = name;
	if (selectRow.length == 1) {
		$.messager.confirm("操作", '确认要删除选中的数据吗？',"question", function(b) {
			if (b) {
				$.ajax({
					type : 'post',
					url : 'delete/' + selectRow[0].id,
					success : function(data) {
						if (data == "successed") {
							ShowMsg();
						} else if(data == "notEmpty"){
							$.messager.alert("提示","该不能删除，因为该类型还有应用！","warning");
						}  else {
							$.messager.alert("提示", "操作失败！","error");
						}
					}
				});
			}
		});
	} else {
		$.messager.alert('Warning', '请勾选一行记录', 'warning');
	}
}

/**
 * @param name datagrid Id  审核
 */
function audited(name){
	var selectRow = $("#"+name).datagrid('getSelected');
	if(selectRow){
		if(selectRow.status=='UNAUDITED'){
			$.ajax({
				type:'post',
				url:'audited/'+selectRow.id,
				success:function(data){
					if(data == "successed"){
						$("#"+name).datagrid("reload");
					}else{
						$.messager.alert("提示", "操作失败！","error");
					}
				}
			});
		}
	}
}

function closeDialog(name) { // 关闭窗口
	$('#showDialog').dialog('close');
	$("#"+name).datagrid('uncheckAll');
	clearDialog();
	// 刷新
	$("#"+name).datagrid('load');
}

/*function ShowMsg() { // 提示信息
	$('#showDialog').dialog('close');
	$.messager.show({
		title:'提示',
		msg:'操作成功!',
		timeout:6000,
		showType:'slide'
	});
	clearDialog();
}*/

function saveFail(){//保存失败
	$.messager.alert("提示", "操作失败！","info");
	closeDialog(indexName);
}

function showSuccessMsg() { // 提示信息
	$('#showDialog').dialog('close');
	$.messager.show({
		title:'提示',
		msg:'<h3>操作成功!</h3>',
		timeout:6000,
		showType:'slide',
		width:300,
		height:150
	});
	clearDialog();
}

function showFailedMsg(){
	$('#showDialog').dialog('close');
	$.messager.alert('Error', '<font color="red">操作错误!</font>', 'error');
	clearDialog();
//	// 刷新
//	$("#"+name).datagrid('load');
}

function operateFail(){//保存失败
	$.messager.alert("提示", "<font color='red'>操作失败！编号不能重复！</font>","info");
}

function vaFormFail(){//验证表单失败
	$.messager.alert("提示", "请正确填写表单信息！","info");
}

function clearDialog(){//清空窗口内容
	$('#openReceiveFeedBack')[0].src = "";
}

function refresh(name,status){//页面刷新
	$("#showDialog").dialog('close');
	loadPage(name,status);
}

function loadPage(name,status){
	$("#"+name).datagrid('reload');
	if(status == "successed"){
		showSuccessMsg();
	}else{
		operateFail();
	}
}
