<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/js/common/showDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/common/addAndEdit.js"></script>
<style type="text/css">
	form {
		margin-top:15px;
	}
 	.proStyle { 
		text-align: right; 
 	} 
 	select {
 		width: 155px;
 	}
</style>

<script type="text/javascript">
	function load() {
		if ("1" == '${result}') {
			alert("保存成功！");
			parent.refresh();
		}
		
		else if ("0" == '${result}') {
			alert("保存失败！");
			parent.refresh();
		}
		
		if ("33" == '${creatorId}') {
			$("#subStation").html('<option value="33">南京</option>');
		}
		else if ("31" == '${creatorId}') {
			$("#subStation").html('<option value="31">苏州</option>');
		}
		else if ("18" == '${creatorId}') {
			$("#subStation").html('<option value="18">南通</option>');
		}
		else if ("19" == '${creatorId}') {
			$("#subStation").html('<option value="19">扬州</option>');
		}
		else if ("20" == '${creatorId}') {
			$("#subStation").html('<option value="20">高邮</option>');
		}
		else if ("21" == '${creatorId}') {
			$("#subStation").html('<option value="21">连云港</option>');
		}
		else if ("22" == '${creatorId}') {
			$("#subStation").html('<option value="22">徐州</option>');
		}
		else if ("23" == '${creatorId}') {
			$("#subStation").html('<option value="23">邳州</option>');
		}
		else if ("24" == '${creatorId}') {
			$("#subStation").html('<option value="24">宿迁</option>');
		}
		else if ("25" == '${creatorId}') {
			$("#subStation").html('<option value="25">淮安</option>');
		}
		else if ("26" == '${creatorId}') {
			$("#subStation").html('<option value="26">盱眙</option>');
		}
		else if ("27" == '${creatorId}') {
			$("#subStation").html('<option value="27">盐城</option>');
		}
		else if ("28" == '${creatorId}') {
			$("#subStation").html('<option value="28">滨海</option>');
		}
		else if ("29" == '${creatorId}') {
			$("#subStation").html('<option value="29">泰州</option>');
		}
		else if ("30" == '${creatorId}') {
			$("#subStation").html('<option value="30">无锡</option>');
		}
		else if ("32" == '${creatorId}') {
			$("#subStation").html('<option value="32">常州</option>');
		}
		else if ("51" == '${creatorId}') {
			$("#subStation").html('<option value="51">镇江</option>');
		} else if ("1" != '${creatorId}' || "50" != '${creatorId}' || "15" != '${creatorId}' || "16" != '${creatorId}' || "17" != '${creatorId}' || "38" != '${creatorId}' || "53" != '${creatorId}') {
			$("#subStation").html('<option value="33">南京</option><option value="31">苏州</option><option value="18">南通</option><option value="19">扬州</option><option value="20">高邮</option><option value="21">连云港</option><option value="22">徐州</option><option value="23">邳州</option><option value="24">宿迁</option><option value="25">淮安</option><option value="26">盱眙</option><option value="27">盐城</option><option value="28">滨海</option><option value="29">泰州</option><option value="30">无锡</option><option value="32">常州</option><option value="51">镇江</option>');
		}
		
		var selectCount1 = document.getElementById("subStation").options;  
        for(var i = 0 ; i < selectCount1.length;i++){  
            if(selectCount1[i].value == '${autoRecordData.subStation}'){  
                selectCount1[i].selected = true;
            }  
        }
		var selectCount2 = document.getElementById("policeProve").options;  
        for(var i = 0 ; i < selectCount2.length;i++){  
            if(selectCount2[i].value == '${autoRecordData.policeProve}'){  
                selectCount2[i].selected = true;
            }  
        }
		var selectCount3 = document.getElementById("householdCopy").options;  
        for(var i = 0 ; i < selectCount3.length;i++){  
            if(selectCount3[i].value == '${autoRecordData.householdCopy}'){  
                selectCount3[i].selected = true;
            }  
        }
		var selectCount4 = document.getElementById("idCardCopy").options;  
        for(var i = 0 ; i < selectCount4.length;i++){  
            if(selectCount4[i].value == '${autoRecordData.idCardCopy}'){  
                selectCount4[i].selected = true;
            }  
        }
		var selectCount5 = document.getElementById("licenseCopy").options;  
        for(var i = 0 ; i < selectCount5.length;i++){  
            if(selectCount5[i].value == '${autoRecordData.licenseCopy}'){  
                selectCount5[i].selected = true;
            }  
        }
		var selectCount6 = document.getElementById("guaranRespon").options;  
        for(var i = 0 ; i < selectCount6.length;i++){  
            if(selectCount6[i].value == '${autoRecordData.guaranRespon}'){  
                selectCount6[i].selected = true;
            }  
        }
		var selectCount7 = document.getElementById("guaranIncome").options;  
        for(var i = 0 ; i < selectCount7.length;i++){  
            if(selectCount7[i].value == '${autoRecordData.guaranIncome}'){  
                selectCount7[i].selected = true;
            }  
        }
		var selectCount8 = document.getElementById("guaranHouseCopy").options;  
        for(var i = 0 ; i < selectCount8.length;i++){  
            if(selectCount8[i].value == '${autoRecordData.guaranHouseCopy}'){  
                selectCount8[i].selected = true;
            }  
        }
		var selectCount9 = document.getElementById("guaranIDCopy").options;  
        for(var i = 0 ; i < selectCount9.length;i++){  
            if(selectCount9[i].value == '${autoRecordData.guaranIDCopy}'){  
                selectCount9[i].selected = true;
            }  
        }
		var selectCount10 = document.getElementById("driLicenseCopy").options;  
        for(var i = 0 ; i < selectCount10.length;i++){  
            if(selectCount10[i].value == '${autoRecordData.driLicenseCopy}'){  
                selectCount10[i].selected = true;
            }  
        }
		var selectCount11 = document.getElementById("strongInsCopy").options;  
        for(var i = 0 ; i < selectCount11.length;i++){  
            if(selectCount11[i].value == '${autoRecordData.strongInsCopy}'){  
                selectCount11[i].selected = true;
            }  
        }
		var selectCount12 = document.getElementById("commerInsuCopy").options;  
        for(var i = 0 ; i < selectCount12.length;i++){  
            if(selectCount12[i].value == '${autoRecordData.commerInsuCopy}'){  
                selectCount12[i].selected = true;
            }  
        }
		var selectCount13 = document.getElementById("certificate").options;  
        for(var i = 0 ; i < selectCount13.length;i++){  
            if(selectCount13[i].value == '${autoRecordData.certificate}'){  
                selectCount13[i].selected = true;
            }  
        }
		var selectCount14 = document.getElementById("rentalAgreement").options;  
        for(var i = 0 ; i < selectCount14.length;i++){  
            if(selectCount14[i].value == '${autoRecordData.rentalAgreement}'){  
                selectCount14[i].selected = true;
            }  
        }
	}

	function saveAutoRecord() {
		$.ajax({
			type:"POST",
			url : 'saveAutoRecord',
			data: $("#addInfo").serialize(),
			dataType : "json",
			success : function(msg){
				if ("1" == msg.data) {
					alert("保存成功");
					parent.refresh();
					parent.closeWin();
				} else if ("0" == msg.data) {
					alert("保存失败");						
				}
			}
		});
	}

	function isExist() {
		$.ajax({
			type:"POST",
			url : 'isExist?value=' + $("#name").val() + "&subStation=" + $('#subStation').val(),
			dataType : "json",
			success : function(msg){
				if ("1" == msg.data) {
					alert('姓名已存在，请输入别名。');
				} 
			}
		});
	}
</script>
	
</head>

<body onload="load()">
	<form id="commentForm" name="addInfo" action="" method="post">
		<input type="hidden" name="id" value='${autoRecordData.id}'>
		<table>
			<tr>
				<td class="proStyle">分站</td>
				<td>
					<select name="subStation" style="width: 155px;" id="subStation" onchange="isExist();">
					</select>
				</td>
				<td class="proStyle">副卡油卡号</td>
				<td><input type="text" name="deputyCard" value="${autoRecordData.deputyCard}" /></td>
				<td class="proStyle">车型</td>
				<td><input type="text" name="carKind" value="${autoRecordData.carKind}" /></td>
			</tr>
			<tr>
				<td class="proStyle">车牌号</td>
				<td><input type="text" name="licenseNo" value="${autoRecordData.licenseNo}" /></td>
				<td class="proStyle">行驶证(一维码，非档案编号)</td>
				<td><input type="text" name="license" value="${autoRecordData.license}" /></td>
				<td class="proStyle">行驶证注册日期</td>
				<td><input type="text" name="licenseDate" value="${licenseDate}" class="timeInput easyui-datebox" style="width: 155px;"/></td>
			</tr>
			<tr>
				<td class="proStyle">车辆所有人</td>
				<td><input type="text" name="licenseName" value="${autoRecordData.licenseName}" /></td>
				<td class="proStyle">车辆检验有效期</td>
				<td><input type="text" name="inspectionDate" value="${inspectionDate}" class="timeInput easyui-datebox" style="width: 155px;"/></td>
				<td class="proStyle">姓名</td>
				<td><input type="text" name="name" id="name" value="${autoRecordData.name}" onblur="isExist();"/></td>
			</tr>
			<tr>
				<td class="proStyle">身份证号码</td>
				<td><input type="text" name="idCard" value="${autoRecordData.idCard}" /></td>
				<td class="proStyle">初次领取驾驶证日期</td>
				<td><input type="text" name="ftReceive" value="${ftReceive}" class="timeInput easyui-datebox" style="width: 155px;"/></td>
				<td class="proStyle">换证日期</td>
				<td><input type="text" name="changeDate" value="${changeDate}" class="timeInput easyui-datebox" style="width: 155px;"/></td>
			</tr>
			<tr>
				<td class="proStyle">联系电话</td>
				<td><input type="text" name="telephone" value="${autoRecordData.telephone}" /></td>
				<td class="proStyle">交强险有效期</td>
				<td><input type="text" name="strongInsDate" value="${strongInsDate}" class="timeInput easyui-datebox" style="width: 155px;"/></td>
				<td class="proStyle">三责险保额</td>
				<td><input type="text" name="tLInsurance" value="${autoRecordData.tLInsurance}" /></td>
			</tr>
			<tr>
				<td class="proStyle">三责险有效期</td>
				<td><input type="text" name="tLInsuranceDate" value="${tLInsuranceDate}" class="timeInput easyui-datebox" style="width: 155px;"/></td>
				<td class="proStyle">派出所出具的无犯罪记录证明</td>
				<td>
					<select name="policeProve" id="policeProve">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
				<td class="proStyle">户口本复印件</td>
				<td>
					<select name="householdCopy" id="householdCopy">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="proStyle">身份证复印件</td>
				<td>
					<select name="idCardCopy" id="idCardCopy">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
				<td class="proStyle">驾驶证复印件</td>
				<td>
					<select name="licenseCopy" id="licenseCopy">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
				<td class="proStyle">担保责任书</td>
				<td>
					<select name="guaranRespon" id="guaranRespon">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="proStyle">担保人收入证明</td>
				<td>
					<select name="guaranIncome" id="guaranIncome">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
				<td class="proStyle">担保人户口本复印件</td>
				<td>
					<select name="guaranHouseCopy" id="guaranHouseCopy">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
				<td class="proStyle">担保人身份证复印件</td>
				<td>
					<select name="guaranIDCopy" id="guaranIDCopy">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="proStyle">行驶证复印件</td>
				<td>
					<select name="driLicenseCopy" id="driLicenseCopy">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
				<td class="proStyle">交强险复印件</td>
				<td>
					<select name="strongInsCopy" id="strongInsCopy">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
				<td class="proStyle">商业险复印件</td>
				<td>
					<select name="commerInsuCopy" id="commerInsuCopy">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="proStyle">车辆营运证</td>
				<td>
					<select name="certificate" id="certificate">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
				<td class="proStyle">协议签订日期</td>
				<td><input type="text" name="agreeDate" value="${agreeDate}" class="timeInput easyui-datebox" style="width: 155px;"/></td>
				<td class="proStyle">租车协议</td>
				<td>
					<select name="rentalAgreement" id="rentalAgreement">
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
				</td>
			</tr>
			<tr align="center"><td colspan="6">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doData('save');">保存</a>&nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm();">重置</a></td>
			</tr> 
		</table>
	</form>
	 
</body>
</html>
