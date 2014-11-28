<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/addCommonHeader.jsp" %>
<%@ include file="/WEB-INF/view/common/branshLineCommon.jsp" %>
<script type="text/javascript">
	var srchForm ;
	$(function() {
		$('body').layout('collapse','north');
		srchForm = $("#srchForm");		
		sy.createDatagrid($("#branchLineCarInfo"),srchForm);
		getSubstationId();
	});
	
	function rightMenu(){
		commRightMenu('branchLineCarInfo');
		
		var row = getGridSelectRow("branchLineCarInfo");
		if(row != null){
			if(row.status == '生效'){
				$("#menu").menu("disableItem", "div[id*='enabled_do']");
				$("#menu").menu("enableItem", "div[id*='disabled_do']");
			}else{
				$("#menu").menu("disableItem", "div[id*='disabled_do']");
				$("#menu").menu("enableItem", "div[id*='enabled_do']");
			}
		}
	}
	
	
	function come() {
		if ("1" != '${creatorId}' && "50" != '${creatorId}' && "15" != '${creatorId}' && "16" != '${creatorId}' && "17" != '${creatorId}' && "38" != '${creatorId}' && "53" != '${creatorId}') {
			$("#hidden1").css("display","none");
			$("#subStation").css("display","none");
		}
	}
 
</script>
</head>
<body class="easyui-layout" onkeydown="javascript:keyPressImpl(event);" onkeyup="javascript:keyReleaseImpl(event);">
	 
	 	<div class="Search-box" region="north" title="查询条件" >
			<form id="srchForm" class="frmSearch"  autocomplete='off'>
				<input type="hidden" name="bean" value="branchLineCarInfo">
				<input type="hidden" name="method" value="page">
				<input type="hidden" id="doMethod" value="do">
				<table class="tableForm datagrid-toolbar" >
					<tr>	
						<th><img class='item-img' src='${ctx}/images/city.png'>分站 </th> 
						<td><input id="subStation" name="subStation" class="combobox-substation"/></td>							
						<th>姓名 </th>
						<td><input id="nameId" name="name" /></td>						
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="tj_query()">查询</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sy.cleanSearch()">重置</a>&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="exportExecl('exportAutoRecord')">导出数据</a>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div region="center">
			<table id="branchLineCarInfo" class="easy-table">
				<thead>   
			       <tr>
			       	   <th data-options="field:'id',width:200,sortable : true, align:'center'">ID</th>
			           <th data-options="field:'subStation',width:100,sortable : true, align:'center',formatter:subSatation">分站</th>
			           <th data-options="field:'deputyCard',width:140,sortable : true, align:'center'">副卡油卡号</th>
			           <th data-options="field:'carKind',width:140,sortable : true, align:'center'">车型</th>
			           <th data-options="field:'licenseNo',width:120,sortable : true, align:'center'">车牌号</th>
			           <th data-options="field:'license',width:180,sortable : true, align:'center'">行驶证(一维码，非档案编号)</th>
			           <th data-options="field:'licenseDate',width:120,sortable : true, align:'center'">行驶证注册日期</th>
			           <th data-options="field:'licenseName',width:120,sortable : true, align:'center'">车辆所有人</th>
					   <th data-options="field:'inspectionDate',width:120,sortable : true, align:'center'">车辆检验有效期</th>
					   <th data-options="field:'name',width:80,sortable : true, align:'center'">姓名</th>
					   <th data-options="field:'idCard',width:140,sortable : true, align:'center'">身份证号码</th>
					   <th data-options="field:'ftReceive',width:120,sortable : true, align:'center'">初次领取驾驶证日期</th>
					   <th data-options="field:'changeDate',width:120,sortable : true, align:'center'">换证日期</th>
					   <th data-options="field:'telephone',width:80,sortable : true, align:'center'">联系电话</th>
					   <th data-options="field:'strongInsDate',width:120,sortable : true, align:'center'">交强险有效期</th>
					   <th data-options="field:'tLInsurance',width:120,sortable : true, align:'center'">三责险保额</th>
					   <th data-options="field:'tLInsuranceDate',width:120,sortable : true, align:'center'">三责险有效期</th>
					   <th data-options="field:'policeProve',width:160,sortable : true, align:'center'">派出所出具的无犯罪记录证明</th>
					   <th data-options="field:'householdCopy',width:120,sortable : true, align:'center'">户口本复印件</th>
					   <th data-options="field:'idCardCopy',width:120,sortable : true, align:'center'">身份证复印件</th>
					   <th data-options="field:'licenseCopy',width:120,sortable : true, align:'center'">驾驶证复印件</th>
					   <th data-options="field:'guaranRespon',width:120,sortable : true, align:'center'">担保责任书</th>
					   <th data-options="field:'guaranIncome',width:120,sortable : true, align:'center'">担保人收入证明</th>
					   <th data-options="field:'guaranHouseCopy',width:120,sortable : true, align:'center'">担保人户口本复印件</th>
					   <th data-options="field:'guaranIDCopy',width:120,sortable : true, align:'center'">担保人身份证复印件</th>
					   <th data-options="field:'driLicenseCopy',width:120,sortable : true, align:'center'">行驶证复印件</th>
					   <th data-options="field:'strongInsCopy',width:120,sortable : true, align:'center'">交强险复印件</th>
					   <th data-options="field:'commerInsuCopy',width:120,sortable : true, align:'center'">商业险复印件</th>
					   <th data-options="field:'certificate',width:120,sortable : true, align:'center'">车辆营运证</th>
					   <th data-options="field:'agreeDate',width:120,sortable : true, align:'center'">协议签订日期</th>
					   <th data-options="field:'rentalAgreement',width:120,sortable : true, align:'center'">租车协议</th>
					   <th data-options="field:'strongInsPrompt',width:120,sortable : true, align:'center'">交强险到期提示</th>
					   <th data-options="field:'tLInsurancePrompt',width:120,sortable : true, align:'center'">三责险到期提示</th>
					   <th data-options="field:'inspectionPrompt',width:120,sortable : true, align:'center'">车辆年检提示</th>
					   <th data-options="field:'changePrompt',width:120,sortable : true, align:'center'">换证提示</th>
					   <th data-options="field:'status',width:80,sortable : true, align:'center'">状态</th>
			       </tr>   
			   </thead>
			</table>
		</div>
 
		<div id="menu" class="easyui-menu" style="width: 140px; display: none;">
			<div onclick="refreshDagagrid();">刷新</div>
			<div onclick="expandQuery();">展开查询</div>
			<div onclick="collapseQuery();">折叠查询</div>
			<div class="menu-sep"></div>
			<div id="branchLineCarInfo_do_add" onclick="open('addPage','add','新增车辆信息',880,400)">新增车辆信息</div>
			<div id="branchLineCarInfo_do_edit" onclick="open('editPage','edit','修改车辆信息',880,400)">修改车辆信息</div>
			<div class="menu-sep"></div> 
			<div id="branchLineCarInfo_exportAutoRecord" onclick="exportExecl('exportAutoRecord')">导出数据</div>
			<div class="menu-sep"></div> 
			<div id="branchLineCarInfo_disabled_do" onclick="updateStatus('失效')">失效</div>
			<div id="branchLineCarInfo_enabled_do" onclick="updateStatus('生效')">生效</div>
			<div class="menu-sep"></div> 
			<div id="branchLineCarInfo_do_delete" onclick="deleteByIds()">删除</div>
		</div>
</body>
</html>
