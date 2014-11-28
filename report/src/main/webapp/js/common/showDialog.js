
var indexName; // 标记属于哪个页面
var strPath = window.document.location.pathname;
var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1); // 得到项目路径
/** 公用 dialog 
 * @param _src
 * @param _title
 * @param _width
 * @param _height
 */
function dialogOpen(_src, _title, _width, _height) {
	var beanName = $("div .easy-table").attr("id");
	$("#openReceiveFeedBack")[0].src = _src;
	$('#showDialog').dialog({
		title : _title,
		width : _width,
		height : _height,
		cache: false,
		modal : true,
		onClose:function(){
			$("#openReceiveFeedBack")[0].src = "";
			$("#" + beanName).datagrid('reload');
		}
	});
	$('#showDialog').dialog('open');
}
 
/**
 * @description 编辑 / 新增 窗口
 */
function open(_url,_flag, _title, _width, _height) {
	var beanName = $("div .easy-table").attr("id");
	if(_flag  == 'edit'){
		var rowData = $("#"+beanName).datagrid("getSelected");
		_url  = _url  + "/" + rowData.id;
	}
	mainOpen(beanName, _url, _flag, _title, _width, _height);
}

/**
 * @description 主要 打开 dialog function
 */
function mainOpen(beanName,_url,_flag, _title, _width, _height){
	$("<div id='showDialog' iconCls='icon-dlg'>").dialog({
		title : _title,
		width : _width,
		height : _height, 
		href:_url,
		cache: false,
		modal : true,
		onClose:function(){
			$("#" + beanName).datagrid('reload');
			$(this).dialog('destroy');
		},
		buttons : [ {
			text : '提交',
			iconCls : 'icon-save',
			handler : function() {
				var vldate = $('#commentForm').form('validate');
				if(vldate){
					$.ajax({
						 url : postPath +"/"+beanName +"/" + $("#doMethod").val(),
						 type : 'POST',
						 data : $("#commentForm").serialize(),
						 dataType : 'text',
						 success : function(data){
							 formScrolMsg(data);
//							 $('#commentForm').form('reset');
							 $('#showDialog').dialog('close');
						 }
					 });
				}else{
					formValidateMsg();
				}
			}
		}, {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#showDialog').dialog('close');
			}
		} ]
	});
}
/*****************************************************************************/
/*******************************财务应收应付************************************/
/**
 * @description 根据 结算单号 
 */
function updateBackBySettleNo(_url,_flag, _title, _width, _height) {
	var beanName = $("div .easy-table").attr("id");
	if(_flag  == 'edit'){
		var rowData = $("#"+beanName).datagrid("getSelected");
		_url  = postPath + "/backMoney/" + _url  + "/" + rowData.settleNo;
	}
	$("<div id='showDialog' iconCls='icon-dlg'>").dialog({
		title : _title,
		width : _width,
		height : _height, 
		href:_url,
		cache: false,
		modal : true,
		onClose:function(){
			$("#" + beanName).datagrid('reload');
			$(this).dialog('destroy');
		},
		buttons : [ {
			text : '提交',
			iconCls : 'icon-save',
			handler : function() {
				var vldate = $('#commentForm').form('validate');
				if(vldate){
					$.ajax({
						 url : postPath +"/backMoney/" + $("#doBackMoney").val(),
						 type : 'POST',
						 data : $("#commentForm").serialize(),
						 dataType : 'text',
						 success : function(data){
							 formScrolMsg(data);
//							 $('#commentForm').form('reset');
							 $('#showDialog').dialog('close');
						 }
					 });
				}else{
					formValidateMsg();
				}
			}
		}, {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#showDialog').dialog('close');
			}
		} ]
	});
}

/*****************************************************************************/

/**
 * @param _target 目标
 * @param _url 请求地址
 * @param _title 标题
 * @param _height 高度
 * @param _width 宽度
 * @param _form 表单
 */
function openCustomDialog(_target,_url,_title,_width,_height,_form){
	$("#"+_target).dialog({
		title:_title,
		width:_height,
		height:_width,
		modal:true,
		resizable:true,
		buttons:[{
			text:"保存",
			iconCls:'icon-save', 
			handler:function(){ 
				$.ajax({
					url : postPath + _url,
					type : "POST",
					data : $("#"+_form).serialize(),
					success : function(data) {
						scrol(data);
					}
				});
			} 
		}]
	});
	$("#"+_target).dialog("open");
}

function openCurr(_dialog,_form,_url){
	var target = $('#'+_dialog);
	var form = $("#"+_form);
	target.dialog({
		buttons : [ {
			text : '提交',
			iconCls : 'icon-ok',
			handler : function() {
				 $.ajax({
					 url : postPath + _url,
					 type : 'post',
					 data : form.serialize(),
					 dataType : 'json',
					 success : function(data){
						 scrol(data);
						 form.form('reset');
					 }
				 });
			}
		}, {
			text : '取消',
			handler : function() {
				target.dialog('close');
			}
		} ]
	});
	target.dialog('open');
}

function dialogClose(){
	$('#showDialog').dialog('close');
}

/** 添加窗口
 * @param iframe
 * @param _src
 * @param _title
 * @param _width
 * @param _height
 */
function add(_src, _title, _width, _height) {
	dialogOpen(_src, _title, _width, _height);
}

/** 编辑窗口
 * @param iframe
 * @param _table
 * @param _title
 * @param _width
 * @param _height
 */
function edit(_url, _title, _width, _height) {
	var beanName = $("div .easy-table").attr("id");
	// 获取选中的对象
	var selectRow = $("#" + beanName).datagrid('getSelected');
	var _src = _url + '/' + selectRow.id;
	dialogOpen(_src, _title, _width, _height);
}

/** 绑定角色
 * @param iframe
 * @param _name
 * @param _title
 * @param _width
 * @param _height
 */
function bindRole(_table, _title, _width, _height) {
    var ids = getIds(_table);
    var _src = "bindRole/"+ids;
	dialogOpen(_src, _title, _width, _height);
}

/** 绑定用户
 * @param iframe 
 * @param _name
 * @param _title
 * @param _width
 * @param _height
 */
function bindUser(_table, _title, _width, _height) {// bindUser
	var ids = getIds(_table);
	var _src = "bindUser/"+ids;
	dialogOpen(_src, _title, _width, _height);
}

/** 绑定权限
 * @param iframe
 * @param _name
 * @param _title
 * @param _width
 * @param _height
 */
function bindPermission(_table, _title, _width, _height) { // bindPermission
	var ids = getIds(_table);
	var _src = "bindPermission/"+ids;
	dialogOpen(_src, _title, _width, _height);
}

/**
 * @description 批量删除
 * @param _url 请求地址
 */
function deleteByIds(_url){
	$.messager.confirm("操作提示", "确定要删除选中的信息 ?", function(r){
		if(r){
			var _ids = getGridIds();
			$.ajax({
				url : "deleteIds",
				type : 'POST',
				data : {
					ids : _ids
				},
				dataType : 'text',
				success : function(data) {
					scrol(data);
				}
			});
		}
	});
}

function updateStatus(_status){
	var _ids = getGridIds();
	$.ajax({
		url : "updateStatus",
		type : 'POST',
		data : {
			ids : _ids,
			status : _status
		},
		dataType : 'text',
		success : function(data) {
			scrol(data);
		}
	});
 
}


/**
 * @param  
 */
function confirmData(){
	var _ids = getGridIds();
	$.ajax({
		url : "confirmData",
		type : 'POST',
		data : {
			ids : _ids
		},
		dataType : 'text',
		success : function(data) {
			scrol(data);
		}
	});
}

function backMoneyConfirm(){
	var _ids = getGridIds();
	$.ajax({
		url : "backMoneyConfirm",
		type : 'POST',
		data : {
			ids : _ids
		},
		dataType : 'text',
		success : function(data) {
			scrol(data);
		}
	});
}
function getGridIds(){
	var _ids = [];
	var ids = getGridSelectIds(getGridIdValue());
	for (var i = 0; i < ids.length; i++) {
		_ids.push(ids[i].id);
	}
	return _ids.join(',');
}
function getGridIdValue(){
	return $("div .easy-table").attr("id");
}
function getGridSelectIds(grid){
    return $("#"+grid).datagrid("getSelections");
}
function getGridSelectRow(grid){
    return $("#"+grid).datagrid("getSelected");
}
 
/**
 * @param _src url
 * @param _title 
 */
function importExcel(_url,_title){
	$("<div id='showDialog'>").dialog({
		title : _title,
		width:450,
		height:250,
		iconCls:'icon-export',
		href:_url,
		cache: false,
		modal : true,
		maximizable:true,
		onClose:function(){
			refreshDagagrid();
			$(this).dialog('destroy');
		}
	});
}

/**
 * 关闭窗口
 * @param name
 */
function closeDialog(name) {
	parent.$('#showDialog').dialog('close');
	refreshDagagrid();
}

/**
 *  展开查询
 */
function expandQuery(){
	$('body').layout('expand','north');
}

/**
 *  折叠查询
 */
function collapseQuery(){
	$('body').layout('collapse','north');
}