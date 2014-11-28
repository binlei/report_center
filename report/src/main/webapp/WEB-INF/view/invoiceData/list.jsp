<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>

<style type="text/css">
	.datagrid-cell {
		width: 200px;	
	}
</style>

<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#invoiceData"),srchForm);
	});
	
	function rightMenu(){ commRightMenu('invoiceData'); }
	
	function importExcel() {
		$("#hiddenWin").window({
			modal:true,
			title:'开票数据导入',
			width:400,
			height:200,
			top:($(window).height() - 400) * 0.5,   
            left:($(window).width() - 500) * 0.5
		});
		
		$("#hiddenWin").html("<iframe src='importExcelForInvo' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
		$("#hiddenWin").window('open');
		//window.open('importExcelForDeli', "发货数据导入", "height=300,width=400,top=250,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no");
	}
	
	function importDelExcel() {
		$("#hiddenWin").window({
			modal:true,
			title:'删除数据导入',
			width:400,
			height:200,
			top:($(window).height() - 400) * 0.5,   
            left:($(window).width() - 500) * 0.5
		});
		
		$("#hiddenWin").html("<iframe src='importDelExcelForInvo' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
		$("#hiddenWin").window('open');
		//window.open('importExcelForDeli', "发货数据导入", "height=300,width=400,top=250,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no");
	}
	
	function deleteBySettleNo() {
		var selectRow = $("#invoiceData").datagrid('getSelected');
		if (null != selectRow) {
			if(confirm("确定要删除此记录吗?")) {
				$.ajax({
					type:"POST",
					url : 'invoiceDataDelete?id='+selectRow.id,
					dataType : "json",
					success : function(msg){
						if ("1" == msg.data) {
							alert("删除成功");
							$('#invoiceData').datagrid('load');
						} else if ("0" == msg.data) {
							alert("删除失败");						
						}
					}
				});
			}
		} else if (null == selectRow){
			alert("请选中你要删除的记录！");
		}
	}
	
	function refresh() {
		$('#invoiceData').datagrid('load');
	}
	
	function bbb(value,row,index) {
		if (row != null) {
			var invoiceMoney1 = row.invoiceMoney1;
			if (null != invoiceMoney1) {
				invoiceMoney1 = invoiceMoney1.toString();
				if ("0" == invoiceMoney1) {
					return "0";
				}
				else if (invoiceMoney1.indexOf('.') < 0) {
					invoiceMoney1 = invoiceMoney1 + ".00";
				}
				else if (invoiceMoney1.length - invoiceMoney1.indexOf('.') == '2') {
					invoiceMoney1 = invoiceMoney1 + "0";
				}
				return invoiceMoney1;
			} else {
				return "";
			}
		} else {
			return "-";
		}
	}
	
	function ccc(value,row,index) {
		if (row != null) {
			var invoiceMoney2 = row.invoiceMoney2;
			if (null != invoiceMoney2) {
				invoiceMoney2 = invoiceMoney2.toString();
				if ("0" == invoiceMoney2) {
					return "0";
				}
				else if (invoiceMoney2.indexOf('.') < 0) {
					invoiceMoney2 = invoiceMoney2 + ".00";
				}
				else if (invoiceMoney2.length - invoiceMoney2.indexOf('.') == '2') {
					invoiceMoney2 = invoiceMoney2 + "0";
				}
				return invoiceMoney2;
			} else {
				return "";
			}
		} else {
			return "-";
		}
	}
	
	function _tax1(value,row,index) {
		if (row != null) {
			var tax1 = row.tax1;
			if (null != tax1) {
				tax1 = tax1.toString();
				if (tax1.indexOf("%") > 0) {
					return tax1;
				} else if ("0" == tax1) {
					return "0";
				} else {
					tax1 = tax1.substring(2, tax1.length);
			        if (tax1.length == 2) {
			        	tax1 = tax1 + "%";
			        } 
			        else if (tax1.length == 1) {
			        	tax1 = tax1 + "0%";
			        }
			        else {
			        	tax1 = tax1.substring(0, 2) + "." + tax1.substring(2, tax1.length) + "%";
			        }
				}
				return tax1;
			} else {
				return "";
			}
		} else {
			return "-";
		}
	}

	function _tax2(value,row,index) {
		if (row != null) {
			var tax2 = row.tax2;
			if (null != tax2) {
				tax2 = tax2.toString();
				if (tax2.indexOf("%") > 0) {
					return tax2;
				} else if ("0" == tax2) {
					return "0";
				} else {
					tax2 = tax2.substring(2, tax2.length);
			        if (tax2.length == 2) {
			        	tax2 = tax2 + "%";
			        } 
			        else if (tax2.length == 1) {
			        	tax2 = tax2 + "0%";
			        }
			        else {
			        	tax2 = tax2.substring(0, 2) + "." + tax2.substring(2, tax2.length) + "%";
			        }
				}
				return tax2;
			} else {
				return "";
			}
		} else {
			return "-";
		}
	}
</script>

</head>

<body class="easyui-layout" style="width:100%;height:101%;"
	 onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件">
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="invoiceData">
				<input type="hidden" name="method" value="page">
				<table class="tableForm datagrid-toolbar"  align="center" >
					<tr>						
						<th style="padding-left: 100px;">结算单号</th> 
						<td><input id="settleNo" name="settleNo"/></td>					
						<td colspan="4" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importDelExcel()">导入您所要删除的数据</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a>
							&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importExcel()">导入数据</a>
						</td>
					</tr>					
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div class="main-data" region="center">
			<table id="invoiceData" class="easy-table">
				<thead>
			       	   <th data-options="field:'id',width:120,sortable : true, align:'center'">ID</th>
			           <th data-options="field:'settleNo',width:200,sortable : true, align:'center'">结算单号</th>   
			           <th data-options="field:'invoiceDate',width:200,sortable : true, align:'center'">开票日期</th>
			           <th data-options="field:'title',width:200,sortable : true, align:'center'">开票抬头</th>
			           <th data-options="field:'invoiceNo1',width:200,sortable : true, align:'center'">发票号码1</th>
			           <th data-options="field:'tax1',width:200,sortable : true, align:'center'" formatter = '_tax1'>税率1</th>
			           <th data-options="field:'invoiceMoney1',width:200,sortable : true, align:'center'" formatter = 'bbb'>开票金额1</th>
			           <th data-options="field:'invoiceNo2',width:200,sortable : true, align:'center'">发票号码2</th>
			           <th data-options="field:'tax2',width:200,sortable : true, align:'center'" formatter = '_tax2'>税率2</th>
			           <th data-options="field:'invoiceMoney2',width:200,sortable : true, align:'center'" formatter = 'ccc'>开票金额2</th>
			       </tr>   
			   </thead>
			</table>
		</div>
		
		<div id="hiddenWin"  class="easyui-window" data-options="closed:true,iconCls:'icon-save'" ></div>
		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="invoiceData_do_delete" onclick="deleteBySettleNo()">删除</div>
		</div>
	</body>
</html>
