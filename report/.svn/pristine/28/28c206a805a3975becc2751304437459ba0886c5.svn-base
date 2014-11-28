 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>utilPage</title>
</head>
<body>
</body>

<script type="text/javascript">
	var sy = $.extend({}, sy);/*定义全局对象*/
	var strPath = window.document.location.pathname;
	var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1); // 得到项目路径
	
	sy.serialieObject = function(form) {/* 将from表单元素的值序列化成对象*/
		var o = {};
		$.each(form.serializeArray(),
				function(index) {
					if (o[this['name']]) {
						o[this['name']] = o[this['name']] + ","
								+ $.trim(this['value']);
					} else {
						o[this['name']] = $.trim(this['value']);
					}
				});
		return o;
	};

	sy.serialieString = function(form) {/* 将from表单元素的值反序列化成参数字符串*/
		var s = "?";
		var obj = sy.serialieObject(form);
		for ( var prop in obj) {
			s += prop + "=" + obj[prop] + "&";
		}
		s = s.substring(0, s.length - 1);
		return s;
	};
	sy.createDatagrid = function(t, srchForm) {//t为table对象
		var intiPageSize = parseInt((window.document.body.clientHeight - 86) / 25);//大概的计算，不精确
		t
				.datagrid({
					url : '${pageContext.request.contextPath}' + "/process",
					fit : true,
					//singleSelect : true,//单行选中
					method : 'POST',
					scrollbarSize : 18,
					resizeHandle : 'left',
					striped : true,
					rownumbers : true,//显示行数 1，2，3，4...			  
					pagination : true,//显示最下端的分页工具栏			
					pageSize : intiPageSize,//读取分页条数，即向后台读取数据时传过去的值			
					pageList : [ 10, 15, 20, 30, 40, 50, 100, intiPageSize ], //可以调整每页显示的数据，即调整pageSize每次向后台请求数据时的数据			
					//fitColumns : true,//设置为true将自动使列适应表格宽度以防止出现水平滚动,false则自动匹配大小		
					queryParams : sy.serialieObject(srchForm),
					frozenColumns : [ [ {
						field : 'ids',
						checkbox : true,
						formatter : function(value, row, index) {
							if (row != null && row.id != null) {
								return row.id;
							} else {
								return "";
							}
						}
					} ] ],
					onClickRow : function(index, row) {
						//-------------for TEST 结合SHIFT,CTRL,ALT键实现单选或多选----------------      
						if (index != selectIndexs.firstSelectRowIndex
								&& !inputFlags.isShiftDown) {
							selectIndexs.firstSelectRowIndex = index; //alert('firstSelectRowIndex, sfhit = ' + index);  
						}
						if (inputFlags.isShiftDown) {
							t.datagrid('clearSelections');
							selectIndexs.lastSelectRowIndex = index;
							var tempIndex = 0;
							if (selectIndexs.firstSelectRowIndex > selectIndexs.lastSelectRowIndex) {
								tempIndex = selectIndexs.firstSelectRowIndex;
								selectIndexs.firstSelectRowIndex = selectIndexs.lastSelectRowIndex;
								selectIndexs.lastSelectRowIndex = tempIndex;
							}
							for (var i = selectIndexs.firstSelectRowIndex; i <= selectIndexs.lastSelectRowIndex; i++) {
								t.datagrid('selectRow', i);
							}
							;
						}
						if (!inputFlags.isShiftDown && !inputFlags.isCtrlDown) {
							var l = t.datagrid('getSelections').length;
							t.datagrid('clearSelections');
							if (l != 0) {//l==0为单击已选行
								t.datagrid('selectRow', index);
							}
						}
						//-------------for TEST 结合SHIFT,CTRL,ALT键实现单选或多选----------------  
					},
					onRowContextMenu: function(e,index,row){
						e.preventDefault();
						$('#menu').menu('show', {
			                  left: e.pageX,
			                  top: e.pageY
			              });
						if (row.status == '已预提未开票') {
							if (row.retuStatus == null) {
								$("#menu").menu('enableItem', $('#addRetu'));
								$("#menu").menu('disableItem', $('#updateRetu'));
								$('#addRetu').attr("onclick","addRetuBySettleNo()");
								$('#updateRetu').attr("onclick","");
							} else if (row.retuStatus == '1') {
								$("#menu").menu('enableItem', $('#updateRetu'));
								$("#menu").menu('disableItem', $('#addRetu'));
								$('#updateRetu').attr("onclick","updateRetuBySettleNo()");
								$('#addRetu').attr("onclick","");
							}
							if (row.invoStatus == null) {
								$("#menu").menu('enableItem', $('#addInvo'));
								$("#menu").menu('disableItem', $('#updateInvo'));
								$('#addInvo').attr("onclick","addInvoBySettleNo()");
								$('#updateInvo').attr("onclick","");
							} else if (row.invoStatus == '1') {
								$("#menu").menu('enableItem', $('#updateInvo'));
								$("#menu").menu('disableItem', $('#addInvo'));
								$('#updateInvo').attr("onclick","updateInvoBySettleNo()");
								$('#addInvo').attr("onclick","");
							}
							$("#menu").menu('disableItem', $('#addBack'));
							$("#menu").menu('disableItem', $('#updateBack'));
							$('#addBack').attr("onclick","");
							$('#updateBack').attr("onclick","");
						} else if (row.status == '已开票未回款' || row.status == '已回款未确认' || row.status == '回款确认') {
							if (row.retuStatus == null) {
								$("#menu").menu('enableItem', $('#addRetu'));
								$("#menu").menu('disableItem', $('#updateRetu'));
								$('#addRetu').attr("onclick","addRetuBySettleNo()");
								$('#updateRetu').attr("onclick","");
							} else if (row.retuStatus == '1') {
								$("#menu").menu('enableItem', $('#updateRetu'));
								$("#menu").menu('disableItem', $('#addRetu'));
								$('#updateRetu').attr("onclick","updateRetuBySettleNo()");
								$('#addRetu').attr("onclick","");
							}
							if (row.invoStatus == null) {
								$("#menu").menu('enableItem', $('#addInvo'));
								$("#menu").menu('disableItem', $('#updateInvo'));
								$('#addInvo').attr("onclick","addInvoBySettleNo()");
								$('#updateInvo').attr("onclick","");
							} else if (row.invoStatus == '1') {
								$("#menu").menu('enableItem', $('#updateInvo'));
								$("#menu").menu('disableItem', $('#addInvo'));
								$('#updateInvo').attr("onclick","updateInvoBySettleNo()");
								$('#addInvo').attr("onclick","");
							}
							if (row.backStatus == null) {
								$("#menu").menu('enableItem', $('#addBack'));
								$("#menu").menu('disableItem', $('#updateBack'));
								$('#addBack').attr("onclick","addBackBySettleNo()");
								$('#updateBack').attr("onclick","");
							} else if (row.backStatus == '1') {
								$("#menu").menu('enableItem', $('#updateBack'));
								$("#menu").menu('disableItem', $('#addBack'));
								$('#updateBack').attr("onclick","updateBackBySettleNo()");
								$('#addBack').attr("onclick","");
							}
						}
					}
				});
		//默认隐藏id列
		t.datagrid('hideColumn', 'id');
		//右键事件
		t.parent().children(".datagrid-view2").children(".datagrid-body")
				.mousedown(function(e) {
					$('body').bind("contextmenu", function(e) {
						return false;
					});
					if (3 == e.which) {
						rightMenu(); // 处理右击事件
						$('#menu').menu('show', {
							left : e.pageX,
							top : e.pageY
						});
						return false;
					}
				});

		//
	};

	//结合SHIFT,CTRL,ALT键实现单选或多选  
	//-------------------------------------------------------------------------------  
	var KEY = {
		SHIFT : 16,
		CTRL : 17,
		ALT : 18,
		DOWN : 40,
		RIGHT : 39,
		UP : 38,
		LEFT : 37
	};
	var selectIndexs = {
		firstSelectRowIndex : 0,
		lastSelectRowIndex : 0
	};
	var inputFlags = {
		isShiftDown : false,
		isCtrlDown : false,
		isAltDown : false
	};

	sy.keyPress = function keyPress(event, t) {//响应键盘按下事件  
		var e = event || window.event;
		var code = e.keyCode | e.which | e.charCode;
		switch (code) {
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

	sy.keyRelease = function keyRelease(event, t) { //响应键盘按键放开的事件  
		var e = event || window.event;
		var code = e.keyCode | e.which | e.charCode;
		switch (code) {
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
	function keyPressImpl(event, name) {
		sy.keyPress(event, $("#" + name));
	}

	//键盘松开事件
	function keyReleaseImpl(event, name) {
		sy.keyRelease(event, $("#" + name));
	}

	//查找按钮  
	function tj_query() {
		var beanName = $("div .easy-table").attr("id");
		$("#" + beanName).datagrid('load', sy.serialieObject(srchForm));
	}

	//点击清空按钮触发事件		
	sy.cleanSearch = function() {
		$(".timeInput").parent().children(".combo").children(".combo-value")
				.val('');
		document.getElementById("srchForm").reset();
	};

	//格式化显示enabled
	sy.format_enabled = function(value, row, index) {
		if (value) {
			return "是";
		} else {
			return "否";
		}
	};

	//根据%设置列宽
	sy.getWidth = function getWidth(percent) {
		return document.body.clientWidth * percent;
	};

	//日期格式化
	Date.prototype.format = function(format) {
		var o = {
			"M+" : this.getMonth() + 1, //month 
			"d+" : this.getDate(), //day 
			"h+" : this.getHours(), //hour 
			"m+" : this.getMinutes(), //minute 
			"s+" : this.getSeconds(), //second 
			"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter 
			"S" : this.getMilliseconds()
		//millisecond 
		};

		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}

		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	};

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
			for (var i = 0; i < fields.length; i++) {
				var fildOption = grid.datagrid('getColumnOption', fields[i]);
				if (!fildOption.hidden) {
					$('<div iconCls="icon-ok" field="' + fields[i] + '"/>')
							.html(fildOption.title).appendTo(tmenu);
				} else {
					$('<div iconCls="icon-empty" field="' + fields[i] + '"/>')
							.html(fildOption.title).appendTo(tmenu);
				}
			}
			headerContextMenu = this.headerContextMenu = tmenu.menu({
				onClick : function(item) {
					var field = $(item.target).attr('field');
					if (item.iconCls == 'icon-ok') {
						grid.datagrid('hideColumn', field);
						$(this).menu('setIcon', {
							target : item.target,
							iconCls : 'icon-empty'
						});
					} else {
						grid.datagrid('showColumn', field);
						$(this).menu('setIcon', {
							target : item.target,
							iconCls : 'icon-ok'
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
		var pager = t.datagrid('getPager'); // 得到datagrid的pager对象
		pager.pagination({
			buttons : [ {
				text : '导出',
				iconCls : 'icon-save',
				handler : function() {
					$("#limitDialog").dialog('open');
				}
			} ]
		});
	};
	//获取所选记录id
	sy.getSelectionsIds = function(t) {
		var ids = "";
		var selections = t.datagrid('getSelections');
		if (selections.length > 0) {
			for (var i = 0; i < selections.length; i++) {
				ids += selections[i].id + ",";
			}
			ids = ids.substring(0, ids.length - 1);
		}
		return ids;
	};

	function closelimitDialog() { // 关闭导出窗口
		$("#limitDialog").dialog('close');
	}

   /*
	*   刷新
	**/
	function refreshDagagrid() {
		var beanName = $("div .easy-table").attr("id");
		$("#" + beanName).datagrid("load", sy.serialieObject($("#srchForm")));
	}
	
	/*
	 *  重置表单
	 **/
	function resetForm() {
		$('#commentForm').form('reset');
	}

	
	/*
	 *  获取 table ids
	 **/
	function getIds(_table) {
		var rows = $("#" + _table).datagrid("getSelections");
		return ids(rows);
	}
	
	function ids(obj){
		var _ids = [];
		for (var i = 0; i < obj.length; i++) {
			_ids.push(obj[i].id);
		}
		return _ids.join(",");
	}
	
	/*
	 *  获取 tree ids
	 **/
	function getTreeIds(_tree){
		var nodes = $("#"+_tree).tree("getChecked");
		return ids(nodes);
	}

	/** 
	 *	右键菜单事件
	 * 
	 */
	function commRightMenu(_obj) {
		var rows = $('#' + _obj).datagrid('getSelections');
		var rowsData = $('#' + _obj).datagrid('getRows');
		if(rowsData.length == 0){
			$('#menu').menu("disableItem","div[id*=export]");
		}else{
			$('#menu').menu("enableItem","div[id*=export]");
		}
		$('#menu').menu('disableItem', "div[id*='edit']");
		$('#menu').menu('disableItem', "div[id*='bind']");
		$('#menu').menu('disableItem', "div[id*='disable']");
		if (rows.length == 1) {
			$('#menu').menu('enableItem', "div[id*='" + _obj + "']");
		} else if (rows.length > 1) {
			$('#menu').menu('enableItem', "div[id*='bind']");
		}
	}

	/**
	 *  数据导入
	 */
	function ajaxUpload(_url) {
		var progressBar = new ProgressBar('${ctx}', "数据正在导入中...");
		progressBar.show();
		$.ajaxFileUpload({
			url : _url,
			secureuri : false,
			fileElementId : 'excelFile',
			dataType : 'json',
			secureuri : false,
			data : {
				flag : $("#flag").val()
			},
			success : function(data) {
				progressBar.close();
				if (data == "invalid") {
					$("#progress").attr("src", "${ctx}/images/cross_.gif").show();
					$("#message span").html(" * 文件格式无效");
				} else {
					$("#progress").attr("src", "${ctx}/images/tick_.gif").show();
				}
			},
			error : function(data, status, e) {
				progressBar.close();
				$("#progress").attr("src", "${ctx}/images/cross_.gif").show();
			}
		});
	}

	/**
	 *  数据导出 表单提交
	 */
	function exportExcel(_url) {
		$("div #srchForm").attr("method", "get");
		$("div #srchForm").attr("action", _url);
		$("div #srchForm").submit();
	}

</script>
</html>		