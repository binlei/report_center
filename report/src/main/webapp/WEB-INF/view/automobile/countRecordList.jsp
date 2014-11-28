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
		sy.createDatagrid($("#countRecordData"),srchForm);
	});
	
	function rightMenu(){commRightMenu('countRecordData');}
	  
	function come() {
		if ("1" != '${creatorId}') {
			$("#hidden1").css("display","none");
			$("#subStation").css("display","none");
		}
	}

	function addClean() {
		var ids = "";
		var selections = $("#countRecordData").datagrid('getSelections');
		if(selections.length>0) {
			for(var i=0;i<selections.length;i++) {
				ids+=selections[i].id+",";
			}
			ids = ids.substring(0,ids.length-1);
		}
		if ("" != ids) {
			$.ajax({
				type:"POST",
				url : 'addClean?id='+ids,
				dataType : "json",
				success : function(msg){
					if ("1" == msg.data) {
						alert("录入成功");
						$('#countRecordData').datagrid('load');
					} else if ("0" == msg.data) {
						alert("录入失败");						
					}
				}
			});
		} else {
			alert("请选择！");
		}
	}

	function exportExcel() {
		$.ajax({
			type : "POST",
			url : 'exportExcel',
			data : $("#srchForm").serialize(),
			dataType : "json",
			success : function(msg) {
				exportByHref("${pageContext.request.contextPath}/" + msg.data);
			}
		});
	}

	function exportByHref(_href) {
		window.location.href = _href;
	}
	
	function _subStation(value,row,index) {
		if (row != null && row.subStation != null) {
			var subStation = row.subStation.toString();
			if (subStation == '33') {
				return '南京';
			} else if (subStation == '31') {
				return '苏州';
			} else if (subStation == '18') {
				return '南通';
			} else if (subStation == '19') {
				return '扬州';
			} else if (subStation == '20') {
				return '高邮';
			} else if (subStation == '21') {
				return '连云港';
			} else if (subStation == '22') {
				return '徐州';
			} else if (subStation == '23') {
				return '邳州';
			} else if (subStation == '24') {
				return '宿迁';
			} else if (subStation == '25') {
				return '淮安';
			} else if (subStation == '26') {
				return '盱眙';
			} else if (subStation == '27') {
				return '盐城';
			} else if (subStation == '28') {
				return '滨海';
			} else if (subStation == '29') {
				return '泰州';
			} else if (subStation == '30') {
				return '无锡';
			} else if (subStation == '32') {
				return '常州';
			} else if (subStation == '51') {
				return '镇江';
			}
		} else {
			return "-";
		}
	}
</script>

</head>

<body class="easyui-layout" style="width:100%;height:101%;" onload="come();"
	 onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 <!-- 查询表单 -->
	 	<div class="Search-box" region="north" title="查询条件" style="height: 135px" >
			<form id="srchForm" class="frmSearch" action="" autocomplete='off'>
				<input type="hidden" name="bean" value="countRecordData">
				<input type="hidden" name="method" value="page">
				<table class="tableForm datagrid-toolbar" align="center">
					<tr>							
						<th id="hidden1">分站</th> 
						<td>
							<select id="subStation" name="subStation">
								<option value="">请选择</option>
								<option value="33">南京</option>
								<option value="31">苏州</option>
								<option value="18">南通</option>
								<option value="19">扬州</option>
								<option value="20">高邮</option>
								<option value="21">连云港</option>
								<option value="22">徐州</option>
								<option value="23">邳州</option>
								<option value="24">宿迁</option>
								<option value="25">淮安</option>
								<option value="26">盱眙</option>
								<option value="27">盐城</option>
								<option value="28">滨海</option>
								<option value="29">泰州</option>
								<option value="30">无锡</option>
								<option value="32">常州</option>
								<option value="51">镇江</option>
							</select>
						</td>					
						<th >月份</th>
						<td >
							<select id="year" name="year">
								<option value="">请选择</option>
								<option value="2014">2014</option>
								<option value="2015">2015</option>
								<option value="2016">2016</option>
								<option value="2017">2017</option>
								<option value="2018">2018</option>
								<option value="2019">2019</option>
								<option value="2020">2020</option>
							</select>年
							<select id="month" name="month">
								<option value="">请选择</option>
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
								<option value="09">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>月
						</td>						
						<th>驾驶员</th>
						<td><input id="driver" name="driver"/></td>						
					</tr>
					<tr>
						<td colspan="6" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="exportExcel()">导出费用统计</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 数据表格 -->		
		<div region="center">
			<table id="countRecordData" class="easy-table">
				<thead>   
			       <tr>
			       	   	<th data-options="field:'id',width:120,sortable : true, align:'center'">ID</th>
			           	<th data-options="field:'month',width:120,sortable : true, align:'center'">月份</th>
						<th data-options="field:'subStation',width:120,sortable : true, align:'center'" formatter = '_subStation'>分站</th>
						<th data-options="field:'driver',width:120,sortable : true, align:'center'">驾驶员</th>
						<th data-options="field:'mileage',width:120,sortable : true, align:'center'">行驶里程</th>
						<th data-options="field:'ticketQuantity',width:120,sortable : true, align:'center'">取派票数</th>
						<th data-options="field:'hallQuantity',width:120,sortable : true, align:'center'">取派厅数</th>
						<th data-options="field:'pcsQuantity',width:120,sortable : true, align:'center'">取派台数</th>
						<th data-options="field:'fuelCosts',width:120,sortable : true, align:'center'">燃油费</th>
						<th data-options="field:'rentalFee',width:120,sortable : true, align:'center'">租车费</th>
						<th data-options="field:'parkingFee',width:120,sortable : true, align:'center'">停车路桥费</th>
						<th data-options="field:'award',width:120,sortable : true, align:'center'">奖惩</th>
						<th data-options="field:'subsidy',width:120,sortable : true, align:'center'">租车补贴</th>
						<th data-options="field:'allCosts',width:120,sortable : true, align:'center'">费用合计</th>
						<th data-options="field:'day',width:120,sortable : true, align:'center'">派送天数</th>
						<th data-options="field:'times',width:120,sortable : true, align:'center'">车次数</th>
						<th data-options="field:'clean',width:120,sortable : true, align:'center'">当月费用已报清</th>
						<th data-options="field:'oneMileage',width:120,sortable : true, align:'center'">单次里程</th>
						<th data-options="field:'onePcs',width:120,sortable : true, align:'center'">单次台数</th>
						<th data-options="field:'oneCosts',width:120,sortable : true, align:'center'">单次费用</th>
						<th data-options="field:'amongHall',width:120,sortable : true, align:'center'">厅间距</th>
						<th data-options="field:'oneTicket',width:120,sortable : true, align:'center'">单次票数</th>
						<th data-options="field:'oneHall',width:120,sortable : true, align:'center'">单次厅数</th>
						<th data-options="field:'branchLine',width:120,sortable : true, align:'center'">支线单台费用</th>
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
			<div onclick="addClean()">当月费用已报清</div>
			<div class="menu-sep"></div> 
			<div onclick="exportExcel()">导出费用统计</div>
		</div>
</body>
</html>
