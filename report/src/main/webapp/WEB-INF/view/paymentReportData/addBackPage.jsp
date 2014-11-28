<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/taglibs.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>

<style type="text/css">
	form {
		margin-top:35px;
	}
</style>

<script type="text/javascript">
	function load() {
		if ("1" == '${result}') {
			alert("回款数据保存成功！");
			parent.refresh();
		}
		
		else if ("0" == '${result}') {
			alert("回款数据保存失败！");
			parent.refresh();
		}
		
		var selectCount = document.getElementById("backFundsBank").options;  
        for(var i = 0 ; i < selectCount.length;i++){  
            if(selectCount[i].value == '${backFundsData.backFundsBank}'){  
                selectCount[i].selected = true;
            }  
        }  
	}

	function aaa() {
		document.getElementById("addInfo").submit();
		parent.closeWin();
	}
</script>
	
</head>

<body onload="load()">
	<form id="addInfo" name="addInfo" action="saveBackInfo" method="post">
		<input type="hidden" name="extendProp1" value='${backFundsData.extendProp1}'>
		<input type="hidden" name="settleNo" value='${settleNo}'>
		<table>
			<tr>
				<td>
					结算单号：
				</td>
				<td>
					<input type="text" name="settleNo" value="${settleNo}" style="width: 210px;" disabled/>
				</td>
			</tr>
			<tr>
				<td>
					回款金额：
				</td>
				<td>
					<input type="text" name="backFundsMoney" value="${backFundsData.backFundsMoney}" style="width: 210px;"/>
				</td>
			</tr>
			<tr>
				<td>
					回款日期：
				</td>
				<td>
					<input class="timeInput easyui-datebox" value="${backFundsData.extendProp2}" name="backFundsDate" style="width: 214px;"/>
				</td>
			</tr>
			<tr>
				<td>
					调整金额：
				</td>
				<td>
					<input value="${backFundsData.adjustMoney}" name="adjustMoney" style="width: 214px;"/>
				</td>
			</tr>
			<tr>
				<td>
					回款银行：
				</td>
				<td>
<%-- 					<input type="text" name="backFundsBank" value="${backFundsData.backFundsBank}" style="width: 210px;"/> --%>
					<select name="backFundsBank" style="width: 214px;" id="backFundsBank">
						<option value="">请选择</option>
						<option value="蜂星建行0734">蜂星建行0734</option>
						<option value="蜂星农行4691">蜂星农行4691</option>
						<option value="蜂星保理0001">蜂星保理0001</option>
						<option value="蜂星光大8676">蜂星光大8676</option>
						<option value="蜂星邮储2013">蜂星邮储2013</option>
						<option value="蜂云农行5325">蜂云农行5325</option>
						<option value="蜂云招行10801">蜂云招行10801</option>
						<option value="蜂星浦发3109">蜂星浦发3109</option>
						<option value="招行1966">招行1966</option>
						<option value="现金">现金</option>
					</select>
				</td>
			</tr>
		</table>
		<div style="text-align: center; padding-top: 70px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="aaa()" >提交</a>
		</div>
	</form>
	 
</body>
</html>
