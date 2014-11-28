<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil2.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<style type="text/css">
#srchForm table th{ float: right;}
</style>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#payReportData"),srchForm);
	});
	function rightMenu(){commRightMenu('payReportData');welcome();}
	
	function update() {
		var selectRow = $("#payReportData").datagrid('getSelected');
		if (null != selectRow) {
			$("#hiddenWin").window({
				modal:true,
				title:'修改预提数据',
				width:380,
				height:400,
				top:($(window).height() - 400) * 0.2,   
	            left:($(window).width() - 500) * 0.5
			});
			$("#hiddenWin").html("<iframe src='update?settleNo="+selectRow.settleNo +"' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>");
			$("#hiddenWin").window('open');
		} 
		else {
			alert("请勾选要修改的记录！");
		}
	}
	
	function addRetuBySettleNo() {
		var selectRow = $("#payReportData").datagrid('getSelected');
		if (null != selectRow) {
			$("#hiddenWin").window({
				modal:true,
				title:'增加预提冲回信息',
				width:380,
				height:350,
				top:($(window).height() - 400) * 0.2,   
	            left:($(window).width() - 500) * 0.5
			});
			$("#hiddenWin").html("<iframe src='addRetuBySettleNo?settleNo="+selectRow.settleNo +"' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>");
			$("#hiddenWin").window('open');
		} 
		else {
			alert("请勾选要新增的记录！");
		}
	}
	
	function updateRetuBySettleNo() {
		var selectRow = $("#payReportData").datagrid('getSelected');
		if (null != selectRow) {
			$("#hiddenWin").window({
				modal:true,
				title:'修改预提冲回信息',
				width:380,
				height:350,
				top:($(window).height() - 400) * 0.2,   
	            left:($(window).width() - 500) * 0.5
			});
			$("#hiddenWin").html("<iframe src='addRetuBySettleNo?settleNo="+selectRow.settleNo +"' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>");
			$("#hiddenWin").window('open');
		} 
		else {
			alert("请勾选要新增的记录！");
		}
	}
	
	function addInvoBySettleNo() {
		var selectRow = $("#payReportData").datagrid('getSelected');
		if (null != selectRow) {
			$("#hiddenWin").window({
				modal:true,
				title:'增加开票信息',
				width:345,
				height:380,
				top:($(window).height() - 400) * 0.2,   
	            left:($(window).width() - 500) * 0.5
			});
			$("#hiddenWin").html("<iframe src='addInvoBySettleNo?settleNo="+selectRow.settleNo +"' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>");
			$("#hiddenWin").window('open');
		} 
		else {
			alert("请勾选要新增的记录！");
		}
	}
	
	function updateInvoBySettleNo() {
		var selectRow = $("#payReportData").datagrid('getSelected');
		if (null != selectRow) {
			$("#hiddenWin").window({
				modal:true,
				title:'修改开票信息',
				width:345,
				height:380,
				top:($(window).height() - 400) * 0.2,   
	            left:($(window).width() - 500) * 0.5
			});
			$("#hiddenWin").html("<iframe src='addInvoBySettleNo?settleNo="+selectRow.settleNo +"' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>");
			$("#hiddenWin").window('open');
		} 
		else {
			alert("请勾选要新增的记录！");
		}
	}
	
	function addBackBySettleNo() {
		var selectRow = $("#payReportData").datagrid('getSelected');
		if (null != selectRow) {
// 			$.ajax({
// 				type : "POST",
// 				url : 'checkInvo?settleNo=' + selectRow.settleNo,
// 				dataType : "json",
// 				success : function(msg) {
// 					if (msg.data == '0') {
// 						alert("请先增加开票信息！");
// 					} else {
						$("#hiddenWin").window({
							modal:true,
							title:'增加回款信息',
							width:380,
							height:380,
							top:($(window).height() - 400) * 0.2,   
				            left:($(window).width() - 500) * 0.5
						});
						$("#hiddenWin").html("<iframe src='addBackBySettleNo?settleNo="+selectRow.settleNo +"' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>");
						$("#hiddenWin").window('open');
// 					}
// 				}
// 			});
		} 
		else {
			alert("请勾选要新增的记录！");
		}
	}
	
	function updateBackBySettleNo() {
		var selectRow = $("#payReportData").datagrid('getSelected');
		if (null != selectRow) {
			$("#hiddenWin").window({
				modal:true,
				title:'修改回款信息',
				width:380,
				height:380,
				top:($(window).height() - 400) * 0.2,   
	            left:($(window).width() - 500) * 0.5
			});
			$("#hiddenWin").html("<iframe src='addBackBySettleNo?settleNo="+selectRow.settleNo +"' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>");
			$("#hiddenWin").window('open');
		} 
		else {
			alert("请勾选要新增的记录！");
		}
	}
	
	function exportExcel(flag) {
		var settleNo = "";
		if (0 == flag) {
			settleNo = document.getElementById("settleNo").value;
		}
		// 所属期间
		var beginSsqj= $("#beginSsqj").parent().children(".combo").children(".combo-value").val();
		var endSsqj= $("#endSsqj").parent().children(".combo").children(".combo-value[name='endSsqj']").val();
		// 开票日期
		var beginKprq= $("#beginKprq").parent().children(".combo").children(".combo-value").val();
		var endKprq= $("#endKprq").parent().children(".combo").children(".combo-value[name='endKprq']").val();
		// 回款日期
		var beginhkrq= $("#beginhkrq").parent().children(".combo").children(".combo-value").val();
		var endhkrq= $("#endhkrq").parent().children(".combo").children(".combo-value[name='endhkrq']").val();
		// 区间日期
		var qjrq= $("#qjrq").parent().children(".combo").children(".combo-value").val();
		$.ajax({
			type : "POST",
			url : 'exportExcel?beginSsqj=' + beginSsqj + "&endSsqj=" + endSsqj
					+ "&beginKprq=" + beginKprq + "&endKprq=" + endKprq
					+ "&status=" + document.getElementById("status").value
					+ "&settleNo=" + settleNo
					+ "&setBook=" + document.getElementById("setBook").value
					+ "&project=" + document.getElementById("project").value
					+ "&supplier=" + document.getElementById("supplier").value
					+ "&beginhkrq=" + beginhkrq
					+ "&endhkrq=" + endhkrq
					+ "&qjrq=" + qjrq
					+ "&je=" + document.getElementById("je").value
					+ "&flag=" + flag,
			dataType : "json",
			success : function(msg) {
				exportByHref("${pageContext.request.contextPath}/" + msg.data);
			}
		});
	}

	function exportByHref(_href) {
		window.location.href = _href;
	}
	
	function aaa(value,row,index) {
		if (row != null) {
			var period = row.period.substring(0,7);
			return period;
		} else {
			return "-";
		}
	}
	
	function bbb(value,row,index) {
		if (row != null) {
			if (value != null) {
				var _value = value.toString();
				if (_value.indexOf('.') < 0) {
					_value = _value + ".00";
				}
				else if (_value.length - _value.indexOf('.') == '2') {
					_value = _value + "0";
				}
				return _value;
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
	
	function refresh() {
		$('#payReportData').datagrid('load');
	}
	
	function closeWin() {
		$("#hiddenWin").window('close');
	}
	
	function addRemark() {
		var selectRow = $("#payReportData").datagrid('getSelected');
		if (null != selectRow) {
			$("#hiddenWin").window({
				modal:true,
				title:'备注信息',
				width:380,
				height:350,
				top:($(window).height() - 400) * 0.2,   
	            left:($(window).width() - 500) * 0.5
			});
			$("#hiddenWin").html("<iframe src='addRemark?settleNo="+selectRow.settleNo +"' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>");
			$("#hiddenWin").window('open');
		} 
		else {
			alert("请勾选要新增的记录！");
		}
	}
	
	function addInfo() {
		var selectRow = $("#payReportData").datagrid('getSelections');
		var _selectRow = new Array();
		for (var i = 0; i < selectRow.length; ++i) {
			_selectRow.push(selectRow[i].settleNo);
		}
		if (null != selectRow) {
			$("#hiddenWin").window({
				modal:true,
				title:'回款确认',
				width:350,
				height:250,
				top:($(window).height() - 400) * 0.2,   
	            left:($(window).width() - 500) * 0.5
			});
			$("#hiddenWin").html("<iframe src='addInfo?settleNo="+_selectRow +"' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>");
			$("#hiddenWin").window('open');
		} 
		else {
			alert("请勾选要新增的记录！");
		}
	}
	
	function welcome() {
		// 39 48
		$("#menu").menu("disableItem","div[id$=_back]");
		for (var i = 39; i < 49; ++i) {
			if (i+"" == '${creatorId}') {
// 				$("#addBack").css('display','none');
// 				$("#updateBack").css('display','none');
// 				$("#addInfo").css('display','none');
// 				$("#update").css('display','none');
				$("#menu").menu("disableItem","div[id$=_back]");
			}
		} 
		if ("38" == '${creatorId}') {
			$("#menu").menu("enableItem","div[id=addInfo_back]");
		}
 		if ("49" == '${creatorId}') {
			$("#menu").menu("enableItem","div[id=addInfo_back]");
// 			$("#menu").menu("disableItem","div[id$=_retu]");
// 			$("#addRemark").css('display','none');
// 			$("#addRetu").css('display','none');
// 			$("#updateRetu").css('display','none');
// 			$("#addInvo").css('display','none');
// 			$("#updateInvo").css('display','none');
// 			$("#addBack").css('display','none');
// 			$("#updateBack").css('display','none');
		}
		if ("52" == '${creatorId}') {
			$("#menu").menu("enableItem","div[id=addInfo_back]");
			$("#menu").menu("disableItem","div[id*=exportExcel]");
// 			$("#exportExcel").css('display','none');
// 			$("#exportExcel1").css('display','none');
// 			$("#exportExcel2").css('display','none');
// 			$("#exportExcel3").css('display','none');
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
	 	<div class="Search-box" region="north" title="查询条件" style="height:150px;">
			<form id="srchForm" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="payReportData">
				<input type="hidden" name="method" value="page">
				<input type="hidden" name="flag" value="2" id="flag">
				<table align="center" >
					<tr>
						<th  >结算单号：</th> 
						<td >
							<input id="settleNo" name="settleNo" style="width: 160px;"/>
						</td>
						
						<th >帐套：</th> 
						<td >
							<input id="setBook" name="setBook" style="width: 160px;"/>
						</td>
						
						<th >所属期间：</th> 
						<td style="width: 200px;">
							<input id="beginSsqj" class="timeInput easyui-datebox"  name="beginSsqj" style="width: 90px;"/>
						~
							<input id="endSsqj" class="timeInput easyui-datebox" name="endSsqj" style="width: 90px;"/>
						</td>
						<th >项目：</th> 
						<td >
							<input id="project" name="project" style="width: 160px;"/>
						</td>
					</tr>
					
					<tr>
						<th >区间日期：</th> 
						<td>
							<input id="qjrq" class="timeInput easyui-datebox"  name="qjrq" style="width: 90px;"/>
						</td>
						<th  >供应商：</th> 
						<td >
							<input id="supplier" name="supplier" style="width: 160px;"/>
						</td>
						
						<th >开票日期：</th> 
						<td colspan="3">
							<input id="beginKprq" class="timeInput easyui-datebox"  name="beginKprq" style="width: 90px;"/>
						~
							<input id="endKprq" class="timeInput easyui-datebox" name="endKprq" style="width: 90px;"/>
						</td>
						
					</tr>
					
					<tr>
						<th >金额：</th> 
						<td>
							<input id="je" name="je" style="width: 160px;"/>
						</td>
						
						<th >状态：</th>
						<td>
							<select id="status" onchange="_status()" name="status" style="width: 165px;">
								<option value="">请选择</option>
								<option value="已预提未开票">已预提未开票</option>
								<option value="已开票未回款">已开票未回款</option>
								<option value="已回款未确认">已回款未确认</option>
								<option value="回款确认">回款确认</option>
							</select>
						</td>
						<th style="width: 80px; text-align: right;">回款日期：</th> 
						<td colspan="3">
							<input id="beginhkrq" class="timeInput easyui-datebox"  name="beginhkrq" style="width: 90px;"/>
						~
							<input id="endhkrq" class="timeInput easyui-datebox" name="endhkrq" style="width: 90px;"/>
						</td>
						
					</tr>
					
					<tr>
						<td colspan="10" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="tj_query()">查询</a>&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="exportExcel(0)" id="exportExcel">导出数据</a>&nbsp;&nbsp;
<!-- 							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="exportExcel(1)" id="exportExcel1">预提未开票明细表</a>&nbsp;&nbsp; -->
<!-- 							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="exportExcel(2)" id="exportExcel2">开票未回款明细表</a>&nbsp;&nbsp; -->
							<a href="javascript:void(0)" class="easyui-linkbutton"onclick="exportExcel(3)" id="exportExcel3">合计应收汇总表</a>&nbsp;&nbsp;
						</td>
					</tr>					
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div class="main-data" region="center">
			<table id="payReportData" class="easy-table">
				<thead>   
			       <tr style="width: 100%">
			       	   <th data-options="field:'id',width:120,sortable : true, align:'center'">ID</th>
				       <th data-options="field:'settleNo',width:120,sortable : true, align:'center'">结算单号</th>
				       <th data-options="field:'setBook',width:80,sortable : true, align:'center'">帐套</th>
			       	   <th data-options="field:'project',width:120,sortable : true, align:'center'">项目</th>
				       <th data-options="field:'supplier',width:200,sortable : true, align:'center'">供应商</th>
				       <th data-options="field:'period',width:120,sortable : true, align:'center'" formatter = 'aaa'>所属期间</th>
				       <th data-options="field:'uaMoney',width:120,sortable : true, align:'center'" formatter = 'bbb'>U8预提金额</th>
				       <th data-options="field:'uaMonth',width:120,sortable : true, align:'center'">U8预提月份</th>
				   	   <th data-options="field:'uaReturnMoney',width:120,sortable : true, align:'center'" formatter = 'bbb'>U8预提冲回金额</th>
				   	   <th data-options="field:'uaReturnMonth',width:120,sortable : true, align:'center'">U8预提冲回月份</th>
				   	   <th data-options="field:'zmytye',width:120,sortable : true, align:'center'" formatter = 'bbb'>U8帐面预提余额</th>
				   	   <th data-options="field:'invoiceDate',width:120,sortable : true, align:'center'">开票日期</th>
			           <th data-options="field:'title',width:200,sortable : true, align:'center'">开票抬头</th>
				       <th data-options="field:'invoiceNo1',width:120,sortable : true, align:'center'">发票号码1</th>
				       <th data-options="field:'tax1',width:70,sortable : true, align:'center'" formatter = '_tax1'>税率1</th>
				       <th data-options="field:'invoiceMoney1',width:120,sortable : true, align:'center'" formatter = 'bbb'>开票金额1</th>
				       <th data-options="field:'invoiceNo2',width:120,sortable : true, align:'center'">发票号码2</th>
			           <th data-options="field:'tax2',width:70,sortable : true, align:'center'" formatter = '_tax2'>税率2</th>
				       <th data-options="field:'invoiceMoney2',width:120,sortable : true, align:'center'" formatter = 'bbb'>开票金额2</th>
				       <th data-options="field:'backFundsMoney',width:120,sortable : true, align:'center'" formatter = 'bbb'>回款金额</th>
				       <th data-options="field:'backFundsDate',width:120,sortable : true, align:'center'">回款日期</th>
				       <th data-options="field:'backFundsBank',width:120,sortable : true, align:'center'">回款银行</th>
				       <th data-options="field:'adjustMoney',width:100,sortable : true, align:'center'">调整金额</th>
				       <th data-options="field:'ysje',width:100,sortable : true, align:'center'" formatter = 'bbb'>应收金额</th>
				       <th data-options="field:'wkpts',width:120,sortable : true, align:'center'">未开票天数</th>
				       <th data-options="field:'wdzts',width:120,sortable : true, align:'center'">未到账天数</th>
				       <th data-options="field:'dzts',width:120,sortable : true, align:'center'">到帐天数</th>
				       <th data-options="field:'jtkpcy',width:120,sortable : true, align:'center'" formatter = 'bbb'>计提开票差异</th>
				       <th data-options="field:'information',width:120,sortable : true, align:'center'">回款确认</th>
				       <th data-options="field:'status',width:120,sortable : true, align:'center'">状态</th>
				       <th data-options="field:'remark',width:120,sortable : true, align:'center'">备注</th>
				       <th data-options="field:'retuStatus',width:120,sortable : true, align:'center',hidden:true"></th>
				       <th data-options="field:'invoStatus',width:120,sortable : true, align:'center',hidden:true"></th>
				       <th data-options="field:'backStatus',width:120,sortable : true, align:'center',hidden:true"></th>
			       </tr>   
			   </thead>
			</table>
		</div>
		<div id="hiddenWin"  class="easyui-window" data-options="closed:true,iconCls:'icon-save'" ></div>
		<!--右键菜单div -->
		<div id="menu" class="easyui-menu" style="width: 140px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="exportExcel4" onclick="exportExcel(0)">导出数据</div>
			<div class="menu-sep"></div>
			<div onclick="update()" id="update_back">修改预提数据</div>
			<div onclick="addInfo()" id="addInfo_back">回款确认</div>
			<div onclick="addRemark()" id="addRemark_retu">备注信息</div>
			<div class="menu-sep"></div>
			<div id="addRetu_retu" onclick="addRetuBySettleNo();">增加预提冲回信息</div>
			<div id="updateRetu_retu" onclick="update();">修改预提冲回信息</div>
			<div class="menu-sep"></div>
			<div id="addInvo_retu" onclick="addInvoBySettleNo();">增加开票信息</div>
			<div id="updateInvo_retu" onclick="updateInvoBySettleNo();">修改开票信息</div>
			<div class="menu-sep"></div>
			<div id="addBack_retu" onclick="addBackBySettleNo();">增加回款信息</div>
			<div id="updateBack_retu" onclick="updateBackBySettleNo();">修改回款信息</div>
			<div class="menu-sep"></div>
			<div id="exportExcel1" onclick="exportExcel(1)">预提未开票明细表</div>
			<div id="exportExcel2" onclick="exportExcel(2)">开票未回款明细表</div>
			<div class="menu-sep"></div>
			<div id="exportExcel3" onclick="exportExcel(3)">合计应收汇总表</div>
		</div>
	</body>
</html>
