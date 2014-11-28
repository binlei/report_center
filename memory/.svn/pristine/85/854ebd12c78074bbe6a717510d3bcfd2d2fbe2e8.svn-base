<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>utilPage</title>
</head>
<body>
</body>

<script type="text/javascript">
var sy = $.extend({},sy);/*定义全局对象*/
var reloades = $.extend({},reloades);/*定义全局对象*/
reloades  = $("div.main-data table");
var strPath = window.document.location.pathname;
var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);		// 得到项目路径

//扩展验证规则
$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    },
    phone : {// 验证电话号码 
        validator : function(value) { 
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        }, 
        message : '格式不正确,请使用下面格式:020-88888888' 
    }, 
    mobile : {// 验证手机号码 
        validator : function(value) { 
            return /^(13|15|18)\d{9}$/i.test(value); 
        }, 
        message : '手机号码格式不正确' 
    }, 
    qq : {// 验证QQ,从10000开始 
        validator : function(value) { 
            return /^[1-9]\d{4,12}$/i.test(value); 
        }, 
        message : 'QQ号码格式不正确,5到12位' 
    },
    password : {//验证密码
    	validator : function(value){
    		return /^[0-9a-zA-Z_]{4,16}$/i.test(value);
    	},
    	message : '密码必须是4到16的字母数字下划线的组合'
    }
});
   
sy.serialieObject = function(form) {/* 将from表单元素的值序列化成对象*/
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + $.trim(this['value']);
		} else {
			o[this['name']] = $.trim(this['value']);
		}
	});
	return o;
};

sy.serialieString = function(form) {/* 将from表单元素的值反序列化成参数字符串*/
	var s = "?";
	var obj = sy.serialieObject(form);
	for(var prop in obj){  
		s+=prop+"="+obj[prop]+"&";
    }
	s = s.substring(0, s.length-1);
	return s;
};
sy.createDatagrid = function(t,srchForm) {//t为table对象
	var isInit = true;
	var intiPageSize = parseInt((window.document.body.clientHeight-86)/25);//大概的计算，不精确
	t.datagrid({
		url:'${pageContext.request.contextPath}' + "/process", 
		fit:true,
		//singleSelect : true,//单行选中
		autoRowHeight:true,
		//nowrap:false,//td内容太长自动换行
		method:'POST',	
		//scrollbarSize:10,
		//resizeHandle:'both',
		striped: true,
		//rownumbers: true,//显示行数 1，2，3，4...			  
		pagination : true,//显示最下端的分页工具栏			
		pageSize : intiPageSize,//读取分页条数，即向后台读取数据时传过去的值			
		pageList : [10,15,20,30,40,50,100,intiPageSize], //可以调整每页显示的数据，即调整pageSize每次向后台请求数据时的数据			
		//fitColumns : true,//设置为true将自动使列适应表格宽度以防止出现水平滚动,false则自动匹配大小		
		queryParams : sy.serialieObject(srchForm),
		frozenColumns : [ [ {
			field : 'ids',
			checkbox : true,
			formatter:function(value,row,index) {
				if (row != null && row.id != null) {
					return row.id;
				} else {
					return "";
				}
			}
		}]],
		onClickRow:function(index,row){
			
		    //-------------for TEST 结合SHIFT,CTRL,ALT键实现单选或多选----------------      
		     if(index != selectIndexs.firstSelectRowIndex && !inputFlags.isShiftDown ){    
		        selectIndexs.firstSelectRowIndex = index; //alert('firstSelectRowIndex, sfhit = ' + index);  
		    }             
		    if(inputFlags.isShiftDown ) {  
		        t.datagrid('clearSelections');                  
		        selectIndexs.lastSelectRowIndex = index;  
		        var tempIndex = 0;  
		        if(selectIndexs.firstSelectRowIndex > selectIndexs.lastSelectRowIndex ){  
		            tempIndex = selectIndexs.firstSelectRowIndex;  
		            selectIndexs.firstSelectRowIndex = selectIndexs.lastSelectRowIndex;  
		            selectIndexs.lastSelectRowIndex = tempIndex;  
		        }  
		        for(var i = selectIndexs.firstSelectRowIndex ; i <= selectIndexs.lastSelectRowIndex ; i++){  
		           t.datagrid('selectRow', i);     
		        }     
		    }
		    if(!inputFlags.isShiftDown && !inputFlags.isCtrlDown) {
		    	var l = t.datagrid('getSelections').length;
		    	t.datagrid('clearSelections');
		    	if(l != 0) {//l==0为单击已选行
    				t.datagrid('selectRow', index);
		    	}
		    }
		    //-------------for TEST 结合SHIFT,CTRL,ALT键实现单选或多选----------------  
		},
		rowStyler: function(index,row) {
			if (index%2 != 0){
				return 'height:23.2px;';//background-color:#D9E8FB;color:black;
			} else {
				return 'height:23.2px;';
			}
		},
		onBeforeLoad : function() {
		},
		onLoadSuccess : function(data) {
			//datagrid-body宽度
			var bodyWidth = $(".datagrid-view2").children(".datagrid-body").width();
			//datagrid-btable宽度
			var tableWidth = $(".datagrid-view2").children(".datagrid-body").children(".datagrid-btable").width();
			
			//datagrid头部 table 的第一个tr 的td们，即columns的集合
            var headerTds = $(".datagrid-view2").children(".datagrid-header").find("table tr:first-child").children(":visible");
            //datagrid主体 table 的第一个tr 的td们，即第一个数据行
            var bodyTds = $(".datagrid-view2").children(".datagrid-body").find("table tr:first-child").children(":visible");
          	//如果tableWidth<bodyWidth扩充每一列(初始化)
            if((tableWidth<bodyWidth) && isInit) {
            	//每列平均增加宽度
            	var increment = (bodyWidth-tableWidth)/(headerTds.length)-1;
            	//循环扩充
            	for(var i=0;i<headerTds.length;i++) {
            		var headerTdWidth = $("div:first-child", headerTds.get(i)).width();
            		var bodyTdWidth = $("div:first-child", headerTds.get(i)).width();
	            	$("div:first-child", headerTds.get(i)).width(headerTdWidth+increment);//列标题扩充
	            	if (bodyTds.length>0) {
		            	$("div:first-child", bodyTds.get(i)).width(bodyTdWidth+increment);//数据主体扩充
	                }
                }
            	isInit = false;
            }
            else if(!isInit) {//防止点击刷新时再扩充
            	for(var i=0;i<headerTds.length;i++) {
            		var headerTdWidth = $("div:first-child", headerTds.get(i)).width();
            		var bodyTdWidth = $("div:first-child", headerTds.get(i)).width();
            		
	            	$("div:first-child", headerTds.get(i)).width(headerTdWidth);//列标题扩充
	            	if (bodyTds.length>0) {
			            $("div:first-child", bodyTds.get(i)).width(bodyTdWidth);//数据主体扩充
	                }
                }
            }
            //循环设置对齐方式
            for(var i=0;i<headerTds.length;i++) {
                $("div:first-child", headerTds.get(i)).css("text-align", "left");
                $("div:first-child", headerTds.get(i)).css("font-weight", "bold");
                if (bodyTds.length>0) {
	                $("div:first-child", bodyTds.get(i)).css("text-align", "left");
	                $("div:first-child", bodyTds.get(i)).css("margin-left", "1px");
                }
            }
            $.messager.show({
				//title: "" + data.additionalMsg.status,
				msg:"耗时："+data.additionalMsg.processTime+" 秒!",
				timeout:1500,
				width:150,
				height:40
			});
		},
		onLoadError : function() { 
			$.messager.alert('错误', "加载失败！", 'error');
		}
	});
	//无数据时滚动条显示问题修复
	$(".datagrid-view2").children(".datagrid-body").html("<div id='noneDis' style='width:"+$(".datagrid-header-row").eq(1).width()+"px;border:solid 0px;height:1px;'></div>");
	//默认隐藏id列
	t.datagrid('hideColumn', 'id');
	//右键事件
	t.parent().children(".datagrid-view2").children(".datagrid-body").mousedown(function(e){ 
			 $('body').bind("contextmenu",function(e){
		         return false;
		    }); 
          if(3 == e.which){ 
        	   $('#menu').menu('show', {
                  left: e.pageX,
                  top: e.pageY
              });  
		    return false;
          }
	    });
	 //全选
	/*var index = 1; 
	t.parent().find("div .datagrid-header-check").children("input[type='checkbox']").bind("click",function(){
		if(index%2==0){
			t.datagrid("uncheckAll");
		}else{
			//$(this).checked=true;
			t.datagrid("checkAll");
		}	
		index++;
	}); */
};

//结合SHIFT,CTRL,ALT键实现单选或多选  
//-------------------------------------------------------------------------------  
var KEY = { SHIFT:16, CTRL:17, ALT:18, DOWN:40, RIGHT:39, UP:38, LEFT:37};    
var selectIndexs = {firstSelectRowIndex:0, lastSelectRowIndex:0};  
var inputFlags = {isShiftDown:false, isCtrlDown:false, isAltDown:false};  
  
sy.keyPress = function keyPress(event,t){//响应键盘按下事件  
    var e = event || window.event;    
    var code = e.keyCode | e.which | e.charCode;        
    switch(code) {    
        case KEY.SHIFT:    
        inputFlags.isShiftDown = true;  
        //t.datagrid('options').singleSelect = false;             
        break; 
        
     	case KEY.CTRL:  
        inputFlags.isCtrlDown = true;  
        //t.datagrid('options').singleSelect = false;             
        break;  
    /*
    case KEY.ALT:    
        inputFlags.isAltDown = true; 
        $('#dataListTable').datagrid('options').singleSelect = false;            
        break; 
    */    
   default:          
    }  
};  
  
sy.keyRelease = function keyRelease(event,t) { //响应键盘按键放开的事件  
    var e = event || window.event;    
    var code = e.keyCode | e.which | e.charCode;        
    switch(code) {    
        case KEY.SHIFT:   
        inputFlags.isShiftDown = false;  
        selectIndexs.firstSelectRowIndex = 0;  
        //t.datagrid('options').singleSelect = true;              
        break;  
     	case KEY.CTRL:  
        inputFlags.isCtrlDown = false;  
        selectIndexs.firstSelectRowIndex = 0;  
        //t.datagrid('options').singleSelect = false;  
        break;   
    /* 
    case KEY.ALT:    
        inputFlags.isAltDown = false; 
        selectIndexs.firstSelectRowIndex = 0; 
        $('#dataListTable').datagrid('options').singleSelect = true;             
        break; 
    */  
     default:          
    }  
};


//键盘按下事件
function keyPressImpl(event,name) {
	sy.keyPress(event, $("#"+name));
}

//键盘松开事件
function keyReleaseImpl(event,name) {
	sy.keyRelease(event, $("#"+name));
} 

//打开查询窗口
function simpleSearch() {
	$("#srchDiv").dialog('open');
}

//查找按钮  
function tj_query(name){
	$("#srchDiv").dialog('close');
	$("#"+name).datagrid('load',sy.serialieObject(srchForm));  
}

//查找dialog回车查询功能
function Enter_query(name) {
	$('input').bind('keyup', function(event) {
		if (event.keyCode == "13") {
			tj_query(name);
		}
	});
}

//点击清空按钮触发事件		
sy.cleanSearch = function() {
	$("#srchForm").form('reset');
	//$(".timeInput").parent().children(".combo").children(".combo-value").val('');
	//document.getElementById("srchForm").reset(); 					
};

//格式化显示enabled
sy.format_enabled = function(value,row,index) {
	if (value){
        return "是";
   	}
    else {
        return "否";
	}
};

//根据%设置列宽
sy.getWidth = function getWidth(percent){ 
    return document.body.clientWidth*percent; 
};

//日期格式化
Date.prototype.format = function(format){ 
	var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
		"S" : this.getMilliseconds() //millisecond 
	} ;

	if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 

	for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
		} 
	} 
	return format; 
};
	
$(function(){
	$("#commentForm").form({success:function(data){
		if (data == "successed") {
			window.parent.showSuccessMsg();
			document.getElementById("commentForm").reset();
			window.parent.refreshSelf();
		} else if (data == "success") {
			$.messager.alert("提示", "操作成功！","info");
			resetFrom();
			window.parent.refreshSelf();
		} else if (data == "exist") {
			$.messager.alert("提示", "该应用包已存在！","warning");
		} else {
			$.messager.alert("提示", "操作失败！","error");
		}
	} });
});

/* function closeDialog(name) { // 关闭窗口
	$('#'+name).dialog('close');
	// 刷新
	//$('#'+name).datagrid('reload');
	parent.window.document.getElementById(name).datagrid('reload');
} */
/**
 * 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中 
 * start
 */
 var createGridHeaderContextMenu = function(e, field) {
		e.preventDefault();
		var grid = $(this);/* grid本身 */
		var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
		if (!headerContextMenu) {
			var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
			var fields = grid.datagrid('getColumnFields');
			for ( var i = 0; i < fields.length; i++) {
				var fildOption = grid.datagrid('getColumnOption', fields[i]);
				if (!fildOption.hidden) {
					$('<div iconCls="icon-checked" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
				} else {
					$('<div iconCls="icon-no_checked" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
				}
			}
			headerContextMenu = this.headerContextMenu = tmenu.menu({
				onClick : function(item) {
					var field = $(item.target).attr('field');
					if (item.iconCls == 'icon-checked') {
						grid.datagrid('hideColumn', field);
						$(this).menu('setIcon', {
							target : item.target,
							iconCls : 'icon-no_checked'
						});
					} else {
						grid.datagrid('showColumn', field);
						$(this).menu('setIcon', {
							target : item.target,
							iconCls : 'icon-checked'
						});
					}
				}
			});
		}
		headerContextMenu.menu('show', {
			left : e.pageX,
			top : e.pageY
		});
	};
	$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
	$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;

/**
 * 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中 
 * end
 */
//自定义分页栏按钮
 sy.pageButtons = function(t) {
	 var pager = t.datagrid('getPager');    // 得到datagrid的pager对象
	 pager.pagination({  
	     buttons:[
	     {  
	    	 text:'查找',
	         iconCls:'icon-search',  
	         handler:function() {
	        	 simpleSearch();
	         }
	     }
	     ]
	 });  
};
//获取所选记录id
sy.getSelectionsIds = function(t) {
	var ids = "";
	var selections = t.datagrid('getSelections');
	if(selections.length>0) {
		for(var i=0;i<selections.length;i++) {
			ids+=selections[i].id+",";
		}
		ids = ids.substring(0,ids.length-1);
	}
	return ids;
};

function closelimitDialog() { // 关闭导出窗口
	$("#limitDialog").dialog('close');
}

function openlimitDialog() { // 关闭导出窗口
	$("#limitDialog").dialog('open');
}

//新增tabs
function addTabs(title,url) {
	$("#menu").menu('hide');//隐藏本右键菜单
	var tabsObj = parent.$("#tabs");//获取父页面DOM
	if (tabsObj.tabs('exists', title)) {
		tabsObj.tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src='+url+' style="padding:0;margin:0;width:100%;height:100%;"></iframe>';
		tabsObj.tabs('add', {
			title : title,
			content : content,
			closable : true,
			fit : true
		});
	}
	parent.tabClose();//父页面js函数调用
	parent.tabCloseEven();
}

//编辑tabs
function editTabs(title,name) { // 编辑窗口
	// 获取选中的对象
	var selectRow = $("#"+name).datagrid('getSelections');
	indexName = name;
	if (selectRow.length == 1) {
		var url = name+"/edit/" + selectRow[0].id;
		console.info(url);
		addTabs(title,url);
	} else {
		$.messager.alert('Warning', '请勾选一行记录', 'warning');
	}
}

//绑定
function bind(title,name,url) {
	// 获取选中的对象
	var selectRow = $("#"+name).datagrid('getSelections');
	if(selectRow.length == 1){
		url = url+'?id='+selectRow[0].id+"&name="+selectRow[0].name;
		addTabs(title,url);
	}else{
		$.messager.alert('提示','请勾选一行记录!','warning');
	}
}
</script>
</html>