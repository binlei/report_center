<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#financeExpressReport"),srchForm);
	});
	
	function rightMenu(){commRightMenu('financeExpressReport');}
	
</script>
</head>

<body class="easyui-layout" style="width:100%;height:101%;"  onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" style="height: 120px" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="financeExpressReport">
				<input type="hidden" name="method" value="page">
				<input type="hidden" id="doMethod" value="do">
				<table class="tableForm datagrid-toolbar"  align="center" >
					<tr>							
						<th>出库确认日期</th>
						<td><input id="confirmDate" name="confirmDate"/></td>
						<th>销售出库单号</th>
						<td><input id="outboundOrderNo" name="outboundOrderNo"/></td>
						<th>实际出库日期</th>
						<td><input id="realityDate" name="realityDate"/></td>
					</tr>
					<tr>
						<th>始发地</th>
						<td><input id="startCity" name="startCity"/></td>
						<th>目的地</th>
						<td><input id="dstination" name="dstination"/></td>
						<th>运单号</th>
						<td><input id="wayBillNo" name="wayBillNo"/></td>
					</tr>
					<tr>
						<td colspan="8" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="exportExecl('exprot','导出第三方快递结算');">导出第三方快递结算</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div region="center">
			<table id="financeExpressReport" class="easy-table">
				<thead>   
			       <tr>
			       	   	<th data-options="field:'id',width:80,sortable : true, align:'center'">ID</th>
			           	<th data-options="field:'confirmDate',width:160,sortable : true, align:'center'">出库确认日期</th>
						<th data-options="field:'customer',width:120,sortable : true, align:'center'">客户名称</th>
						<th data-options="field:'outboundOrderNo',width:140,sortable : true, align:'center'">销售出库单号</th>
						<th data-options="field:'kindStandard',width:80,sortable : true, align:'center'">品名规格</th>
						<th data-options="field:'unit',width:80,sortable : true, align:'center'">计量单位</th>
						<th data-options="field:'color',width:80,sortable : true, align:'center'">颜色</th>
						<th data-options="field:'outboundQuantity',width:80,sortable : true, align:'center'">出库数量</th>
						<th data-options="field:'customerCategory',width:80,sortable : true, align:'center'">客户类别</th>
						<th data-options="field:'department',width:80,sortable : true, align:'center'">部门</th>
						<th data-options="field:'location',width:80,sortable : true, align:'center'">地区</th>
						
						<th data-options="field:'realityDate',width:160,sortable : true, align:'center'">实际出库日期</th>
						<th data-options="field:'despatchCategory',width:80,sortable : true, align:'center'">发运类别</th>
						<th data-options="field:'despatchLocation',width:80,sortable : true, align:'center'">发运区域</th>
						<th data-options="field:'startCity',width:80,sortable : true, align:'center'">始发地</th>
						<th data-options="field:'oneCities',width:80,sortable : true, align:'center'">一级地市</th>
						<th data-options="field:'dstination',width:80,sortable : true, align:'center'">目的地</th>
						<th data-options="field:'acceptCategory',width:80,sortable : true, align:'center'">承运类别</th>
						<th data-options="field:'piece',width:60,sortable : true, align:'center'">件数</th>
						<th data-options="field:'weight',width:80,sortable : true, align:'center'">重量</th>
						<th data-options="field:'chargeWeight',width:80,sortable : true, align:'center'">计费重量</th>
						<th data-options="field:'freightAmount',width:80,sortable : true, align:'center'">运费金额</th>
						<th data-options="field:'shipper',width:80,sortable : true, align:'center'">承运方</th>
						<th data-options="field:'wayBillNo',width:80,sortable : true, align:'center'">运单号</th>
						<th data-options="field:'poll',width:60,sortable : true, align:'center'">票数</th>
						<th data-options="field:'closeMoney',width:80,sortable : true, align:'center'">结算金额</th>
						<th data-options="field:'afterMoney',width:80,sortable : true, align:'center'">税后金额</th>
						<th data-options="field:'singleMoney',width:80,sortable : true, align:'center'">单台运费</th>
						<th data-options="field:'saveMoney',width:80,sortable : true, align:'center'">保价</th>
						<th data-options="field:'description',width:200,sortable : true, align:'center'">备注</th>
			       </tr>   
			   </thead>
			</table>
		</div>
	 
		<div id="menu" class="easyui-menu" style="width: 160px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="financeExpressReport_importExcel" onclick="importExcel('importPage','导入第三方快递结算')">导入第三方快递结算</div>
			<div id="financeExpressReport_exportExcel" onclick="exportExecl('exprot','导出第三方快递结算')">导出第三方快递结算</div>
			<div class="menu-sep"></div>
			<div id="financeExpressReport_do_edit" onclick="open('editPage','edit','修改（新建）快递结算',900,380)">修改（新建）快递结算</div>
			<div class="menu-sep"></div>
			<div id="financeExpressReport_do_delete" onclick="deleteByIds();">删除</div>
		</div>
		</body>
	</html>