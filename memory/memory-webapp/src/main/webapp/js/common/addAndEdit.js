var strPath = window.document.location.pathname;
var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);

var tabsObj = parent.$("#tabs");//获取父页面tabs对象
//添加操作
function addOperate() {
	//document.forms[0].submit(); 
	//表单提交
	var vldate = $('#commentForm').form('validate');
	if(vldate) {
		$.ajax({
			url:'add',
			method:'post',
			data:$("#commentForm").serialize(),
			dataType : 'text',
			success : function(data){
				//$("#commentForm").form('reset');
				if(data == "successed"){
					$.messager.show({
						title:'提示',
						msg:'<h4>操作成功!</h4>',
						timeout:2000,
						showType:'slide',
						width:250,
						height:120
					});
				}else if(data == "defeated") {
					$.messager.alert("提示", "<font color='red'>操作失败！编号不能重复！</font>","info");
				}
			}
		});
	} else {
		$.messager.alert("提示", "请正确填写表单信息！","info");
	}	
}

//编辑操作
function editOperate(name) {
	//document.forms[0].submit(); 
	//表单提交
	var vldate = $('#commentForm').form('validate');
	if(vldate) {
		$.ajax({
			url:postPath+'/'+name+'/edit',
			method:'post',
			data:$("#commentForm").serialize(),
			dataType : 'text',
			success : function(data){
				//$("#commentForm").form('reset');
				if(data == "successed"){
					$.messager.show({
						title:'提示',
						msg:'<h4>操作成功!</h4>',
						timeout:2000,
						showType:'slide',
						width:250,
						height:120
					});
				}else if(data == "defeated") {
					$.messager.alert("提示", "<font color='red'>操作失败！</font>","info");
				}
			}
		});
	} else {
		$.messager.alert("提示", "请正确填写表单信息！","info");
	}	
}

//关闭当前tabs
function previousPage() {
	var curTabIndex = tabsObj.tabs('getTabIndex',tabsObj.tabs('getSelected'));//得到当前tabs索引
	tabsObj.tabs('close',curTabIndex);//关闭当前tabs
	//tabsObj.tabs('select','用户管理');//返回list页面
}

//添加(dialog)
function addFun(name,title,width,height) {
	$('#editDialog').dialog({    
	    title: title,    
	    width: width,    
	    height: height,
	    maximizable:true,
	    closed: true,
	    cache: false,    
	    href: postPath+'/'+name+'/add',    
	    modal: true,
	    onClose: function() {
	    	tj_query(name);
	    }
	});
	$('#editDialog').dialog('open');
}

//编辑(dialog)
function editFun(name,title,width,height){
	var selections = $("#"+name).datagrid('getSelections');
	if(selections.length != 1) {
		$.messager.alert('提示','请勾选一行记录!','warning');
	}else {
	$('#editDialog').dialog({    
	    title: title,    
	    width: width,    
	    height: height,
	    maximizable:true,
	    closed: true,
	    cache: false,    
	    href: postPath+'/'+name+'/edit/'+selections[0].id,    
	    modal: true,
	    onClose: function() {
	    	tj_query(name);
	    }
	});
	$('#editDialog').dialog('open');
	}
}

//重置表单
function resetForm() {
	$('#commentForm').form('reset');
}
//保存
function saveData() {
	var vldate = $('#commentForm').form('validate');
	if(vldate) {
		$.ajax({
			url:'add',
			method:'post',
			data:$("#commentForm").serialize(),
			dataType : 'text',
			success : function(data){
				if(data == "successed"){
					$(".msg").html("<b style='padding-left:10px;color:blue;'>操作成功!</b>");
				}else if(data == "defeated") {
					$(".msg").html("<b style='padding-left:10px;color:red;'>操作失败！</b>");
				}
			}
		});
	} else {
		$.messager.alert("提示", "表单信息填写不完整，带<font color='red'>*</font>为必填项！","info");
	}
}

//编辑
function editData() {
	var vldate = $('#commentForm').form('validate');
	if(vldate) {
		$.ajax({
			url:'edit',
			method:'post',
			data:$("#commentForm").serialize(),
			dataType : 'text',
			success : function(data){
				if(data == "successed"){
					$(".msg").html("<b style='padding-left:10px;color:blue;'>操作成功!</b>");
				}else if(data == "defeated") {
					$(".msg").html("<b style='padding-left:10px;color:red;'>操作失败！</b>");
				}
			}
		});
	} else {
		$.messager.alert("提示", "表单信息填写不完整，带<font color='red'>*</font>为必填项！","info");
	}
}