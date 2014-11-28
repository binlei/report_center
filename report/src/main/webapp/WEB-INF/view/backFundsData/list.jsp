<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>

<style type="text/css">
	.datagrid-cell {
		width: 200px;	
	}
	.white_content {
		display: none;
		position: absolute;
		top: 25%;
		left: 35%;
		width: 19%;
		height:25%;
		border: 2px solid #AAAAAA;
		z-index:1002;
	}
	.black_overlay {
		display: none;
		position: absolute;
		top: 0%;
		left: 0%;
		width: 100%;
		height: 100%;
		background-color:#f5f5f5;
		z-index:1001;
		-moz-opacity: 0.8;
		opacity:.80;
		filter: alpha(opacity=80);
	}
	.close {
		float:right;
		clear:both;
		width:100%;
		text-align:right;
		margin:0 0 2px 0
	}
	.close a {
		color:#333;
		text-decoration:none;
		font-size:14px;
		font-weight:200
	}
	.con {
/* 		text-indent:1.5pc; */
		line-height:21px;
	}
</style>

<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#backFundsData"),srchForm);
	});
	
	function rightMenu(){commRightMenu('backFundsData');}
	
	function importToExcel() {
		$("#hiddenWin").window({
			modal:true,
			title:'回款数据导入',
			width:400,
			height:200,
			top:($(window).height() - 400) * 0.5,   
            left:($(window).width() - 500) * 0.5
		});
		
		$("#hiddenWin").html("<iframe src='importExcelForBack' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>")
		$("#hiddenWin").window('open');
	}
	function closeWin() {
		$("#hiddenWin").window('close');
	}
	function refresh() {
		$('#backFundsData').datagrid('load');
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
		
		$("#hiddenWin").html("<iframe src='importDelExcelForBack' style='overflow:hidden;width:97.9%;height:97.9%;'></iframe>");
		$("#hiddenWin").window('open');
	}
	
	function deleteBySettleNo() {
		var selectRow = $("#backFundsData").datagrid('getSelected');
		if (null != selectRow) {
			if(confirm("确定要删除此记录吗?")) {
				$.ajax({
					type:"POST",
					url : 'backFundsDataDelete?id='+selectRow.id,
					dataType : "json",
					success : function(msg){
						if ("1" == msg.data) {
							alert("删除成功");
							$('#backFundsData').datagrid('load');
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
	
	function addInfo() {
		
		var selectRow = $("#backFundsData").datagrid('getSelected');
		if (null != selectRow) {
			$.ajax({
				type:"POST",
				url : 'addInfoPage?settleNo='+selectRow.settleNo,
				dataType : "json",
				success : function(msg){
					if (undefined != msg.information) {
						document.getElementById("contactus").value = msg.information;
					} else {
						document.getElementById("contactus").value = "";
					}
					document.getElementById("newSettleNo").value = msg.settleNo;
					show('light');
				}
			});
		} else if (null == selectRow){
			alert("请选中你要添加说明的记录！");
		}
	}
	
	function aaa() {
		if (document.getElementById("contactus").value.length > 100) {
			alert("限输入100个字！");
			return;
		} 
		$.ajax({
			type : "POST",
			url : 'saveInfo?contactus='
					+ document.getElementById("contactus").value + "&settleNo="
					+ document.getElementById("newSettleNo").value,
			dataType : "json",
			success : function(msg) {
				if (msg.succ == '1') {
					alert("保存成功！");
				} else if (msg.succ == '0'){
					alert("保存失败！");
				}
				hide('light');
				refresh();
			}
		});
	}

	function show(tag) {
		var light = document.getElementById(tag);
		var fade = document.getElementById('fade');
		light.style.display = 'block';
		fade.style.display = 'block';
	}
	function hide(tag) {
		var light = document.getElementById(tag);
		var fade = document.getElementById('fade');
		light.style.display = 'none';
		fade.style.display = 'none';
	}

</script>

</head>

<body class="easyui-layout" style="width:100%;height:101%;"
	 onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" style="height: 110px">
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="backFundsData">
				<input type="hidden" name="method" value="page">
				<table class="tableForm datagrid-toolbar"  align="center"  >
					<tr>						
						<th>结算单号</th> 
						<td><input id="settleNo" name="settleNo" style="width: 198px;"/></td>	
						<th>是否回款确认</th> 
						<td>
							<select id="status" name="status" style="width: 198px;">
								<option value="">请选择</option>
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</td>
					</tr>	
					<tr>
						<td colspan="4" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importDelExcel()">导入您所要删除的数据</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a>
							&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importToExcel()">导入数据</a>
						</td>
					</tr>				
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div class="main-data" region="center">
			<table id="backFundsData" class="easy-table">
				<thead>   
					<tr>
			       	   <th data-options="field:'id',width:120,sortable : true, align:'center'">ID</th>
			           <th data-options="field:'settleNo',width:200,sortable : true, align:'center'">结算单号</th>   
			           <th data-options="field:'adjustMoney',width:200,sortable : true, align:'center'" >调整金额</th>
			           <th data-options="field:'adjustDate',width:200,sortable : true, align:'center'" >调整日期</th>
			           <th data-options="field:'backFundsMoney',width:200,sortable : true, align:'center'" >回款金额</th>
			           <th data-options="field:'backFundsDate',width:200,sortable : true, align:'center'">回款日期</th>
			           <th data-options="field:'backFundsBank',width:200,sortable : true, align:'center'">回款银行</th>
			           <th data-options="field:'information',width:200,sortable : true, align:'center'">回款确认</th>
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
			<div id="backFundsData_do_disable" onclick="deleteBySettleNo()">删除</div>
			<div class="menu-sep"></div>
			<div id="backFundsData_importUpdateBackPage" onclick="importExcel('importUpdateBackPage','导入调整金额')">导入调整金额</div>
			<div class="menu-sep"></div>
			<div id="backFundsData_do_comfig" onclick="addInfo()">回款确认</div>
		</div>
		
		<!--  -->
		<div id="light" class="white_content">
			<div style="background-color: #E0FFFF;">
				<a style="font-size: 18px; vertical-align: top; text-align: left;"><font color="9999FF">回款确认</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
				<img onclick="hide('light')" src="${ctx}/images/close.jpg" width="20px" height="20px" style="cursor:pointer; text-align: right;" />
      		</div>
      		
      		<div class="con"> 
				<input type="hidden" name="settleNo" id="newSettleNo">
				<textarea  id="contactus" style="width: 320px; height: 130px;" name="contactus"></textarea>
				<div>
					<font color="red">限输入100个汉字&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					<input type="submit" value="保存" class="easyui-linkbutton" onclick="aaa()"/>
				</div>
      		</div>
		</div>
		<div id="fade" class="black_overlay"></div>
	</body>
</html>
