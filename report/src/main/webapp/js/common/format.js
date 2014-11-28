var flag = "-";
//格式化创建人员  @author: jing.huang
function creator(value,row,index) {
	if (row.info != null && row.info.creator != null) {
		return row.info.creator;
	} else {
		return "-";
	}
}
//格式化创建时间 @author: jing.huang
function createdTime(value,row,index) {
	if (row.info != null && row.info.createdTime != null) {
		return row.info.createdTime;
	} else {
		return "-";
	}
}
//格式化最后操作人员 @author: jing.huang
function lastOperator(value,row,index) {
	if (row.info != null && row.info.lastOperator != null) {
		return row.info.lastOperator;
	} else {
		return "-";
	}
}
//格式化最后操作时间 @author: jing.huang
function lastOperatedTime(value,row,index) {
	if (row.info != null && row.info.lastOperatedTime != null) {
		return row.info.lastOperatedTime;
	} else {
		return "-";
	}
}

	//格式话显示上级组织名称
function format_parentOrg_name(value,row,index) {
	if (row != null && row.parentOrg != null) {
		return row.parentOrg.name;
	} else {
		return "-";
	}
}
//格式话管理者联系方式
function format_managerContact_tel(value,row,index) {
	if (row != null && row.managerContact != null) {
		return row.managerContact.tel;
	} else {
		return "-";
	}
}

// 组织类型
function format_orgType(value,row,index){
	if (row != null && row.type != null) {
		return row.type.name;
	} else {
		return "-";
	}
}

//   状态
function format_enabled(value,row,index){
	if (value) {
		return "有效";
	} else {
		return "<span style='color:red;'>无效</span>";
	}
}

//  组织父
function parentPermissionId(value,row,index) {
	if (row != null && row.parentPermission != null){
		return row.parentPermission.name;
	}
	return "-";
}

//格式化显示最后一次密码
function format_lastPwd(value,row) {
	if (row != null) {
		return row.lastPwd;
	} else {
		return "-";
	}
}
function format_lastPwdTime(value,row) {
	if (row != null) {
		return row.lastMdPwdTime;
	} else {
		return "-";
	}
}
//格式话显示邮箱
function format_email(value,row,index) {
	if (row != null && row.contact != null) {
		return row.contact.email;
	} else {
		return "-";
	}
}
// 电话
function format_tel(value,row,index){
	if (row != null && row.contact != null) {
		return row.contact.tel;
	} else {
		return "-";
	}
}

// 手机
function format_mobile(value,row,index){
	if (row != null && row.contact != null) {
		return row.contact.mobile;
	} else {
		return "-";
	}
}

// 性别
function format_sex(value,row,index){
	if (row != null && row.contact != null) {
		if(row.contact.sex== 1){
			return "男";
		}else{
			return "女";
		}
	} else {
		return "-";
	}
}
 
/**
 * @param  状态
 * @returns {String}
 */
function status(value, row, index) {
	if (row.status == 'ENABLED') {
		return "有效";
	} else {
		return "<span style='color:red;'>无效</span>";
	}
}

/** 
 * @param 显示图片
 * @param {} row
 * @param {} index
 */
function img(value, row, index){
	var imgName = "<span style='margin-top: 330px;height:auto;'>"+row.name+"</span>";
/*	return "<img src='D:/home/yuanlianghe/storage/mobile_apks"+ row.icoPath +"' />"+row.name;*/
	return "<img src='"+row.icoPath+"' width='20px' height='20px' />"+imgName;
}

/**
 * @param 操作
 * @returns {String}
 */
function oper(value,row, index) {
//	var download = "<img src='../../images/download.png' alt='下载' onclick='downloadFile("+ row.filePath +")' /> ";
	var download = "<a href='"+postPath+"/appInfo/downloadFile?fileName="+row.fileName+"&filePath="+row.filePath+"'><img src='"+postPath+"/images/download.png' alt='下载' /> </a>";
	var del = " <img src='"+postPath+"/images/cross.png' alt='移除' onclick='rem("+row.id+");' />";
	return del + download;
}

/**
 * @param 操作
 * @returns {String}
 */
function yesOrNo(value,rowData, index) {
	var aisData = $("#ais").datagrid("getData");
	var appId = rowData.id;
	for (var i = 0; i < aisData.total; i++) {
		if(aisData.rows[i].id == appId){
			return "<input type='button' style='background:#00ff00' disabled='disabled' value='已添加' />";
		}else{
			if(rowData.status == 'DISABLED'){
				return "<input type='button' style='background:#D10B0B' disabled='disabled' value='未审核' />";
			}
		}
	}
	return "<input type='button' value='未添加' onclick='addApkInPkg(this,"+appId+")' />";
}

/**
 * 下载文件
 * @param sourceFile
 * @param targetFileNm
 */
function downloadFile(downloadFile){
	downloadFile =encodeURI(encodeURI(downloadFile));
	window.open(postPath+"/appInfo/downloadFile/"+downloadFile);
}


function showM(value, rec, index) {
	return "<span>" + value + "(MB)</span>";
}

function showShorCut(value, row, index) {
	if (row.showShorCut) {
		return "显示";
	} else {
		return "不显示";
	}
}

/**
 * @param 格式话显示邮箱
 * @returns
 */
function email(value, row, index) {
	if (row != null && row.contact != null) {
		return row.contact.email;
	} else {
		return "-";
	}
}

/**
 * @param 电话
 * @returns
 */
function tel(value, row, index) {
	if (row != null && row.contact != null) {
		return row.contact.tel;
	} else {
		return "-";
	}
}

/**
 * @param 手机
 * @returns
 */
function mobile(value, row, index) {
	if (row != null && row.contact != null) {
		return row.contact.mobile;
	} else {
		return "-";
	}
}

/**
 * @description:格式化QQ
 * @param 
 * @returns
 */
function qq(value, row, index) {
	if (row != null && row.contact != null) {
		return row.contact.qq;
	} else {
		return "-";
	}
}

/**
 * @param 性别
 * @returns {String}
 */
function sex(value, row, index) {
	if (row != null && row.contact != null) {
		if (row.contact.sex == 1) {
			return "男";
		} else {
			return "女";
		}
	} else {
		return "-";
	}
}

/**
 * @param 审核
 * @returns {String}
 */
function audited(value, row, index){
	if (row.status == 'ENABLED') {
		return "审核通过";
	} else {
		return "<span style='color:red;'>未审核</span>";
	}
}

/**
 * @description 组织
 * @param value
 * @param row
 * @param index
 * @returns 
 */
function supplier(value, row, index){
	 if(row.financeSupplier == null) return flag;
	 return row.financeSupplier.name;
}

/**
 * @description 服务费
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function serviceCharge(value, row, index){
	return value;
}

/**
 * @description 销售金额
 */
function saleAmount(value, row, index){
	if(row.extendProp2 == null) return flag;
	return row.extendProp2 + "%";
}

/**
 * @description 车牌号
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function carCard(value,row,index){
	if(row.dryLineCarRecord != null) return row.dryLineCarRecord.carCard;
	return "-";
}

/**
 * @description 主卡
 * @param value
 * @param row
 * @param index
 * @returns
 */
function mainCard(value,row,index){
	if(row.dryLineMaincard != null) return row.dryLineMaincard.mainCard;
	return "-";
}

// **************************** 支线 ******************************** //
/**
 * @description 分站
 * @param value
 * @param row
 * @param index
 * @returns
 */
function subSatation(value,row,index){
	if(row.subStation != null) return row.subStation.name;
	return "-";
}
