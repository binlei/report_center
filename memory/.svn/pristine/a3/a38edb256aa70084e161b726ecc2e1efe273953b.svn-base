/**
 * @param 格式话显示上级组织名称
 * @returns
 */
function format_parentOrg_name(value, row, index) {
	if (row != null && row.parentOrg != null) {
		return row.parentOrg.name;
	} else {
		return "-";
	}
}


/**
 * @param 格式话管理者联系方式
 * @returns
 */
function format_managerContact_tel(value, row, index) {
	if (row != null && row.managerContact != null) {
		return row.managerContact.tel;
	} else {
		return "-";
	}
}

 

/**
 * @param 组织类型
 * @returns
 */
function format_orgType(value, row, index) {
	if (value != null && value.name != null) {
		return value.name;
	} else {
		return "-";
	}
}
/**
 * @param user list 组织名称
 * @returns
 */
function format_orgName(value, row, index) {
	if (row != null && row.organization != null) {
		return row.organization.name;
	} else {
		return "-";
	}
}


 

/**
 * @param 状态
 * @returns {String}
 */
function format_enabled(value, row, index) {
	if (value) {
		return "有效";
	} else {
		return "<span style='color:red;'>无效</span>";
	}
}

/**
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function format_installStatus(value,row,index){
	if(value>0){
		return "成功";
	}else{
		return "<span style='color:red;'>失败</span>";
	}
}

/**
 * @param  状态
 * @returns {String}
 */
function format_status(value, row, index) {
	if (row.status == 'AUDITED_PASS') {
		return "有效";
	} else {
		return "<span style='color:red;'>无效</span>";
	}
}

function format_pkgStatus(value, row, index){
	if (row.status == 'ENABLED') {
		return "有效";
	} else {
		return "<span style='color:red;'>无效</span>";
	}
}
 
function format_showM(value, rec, index) {
	return "<span>" + value + "(MB)</span>";
}

/**
 * @param  组织父
 * @returns
 */
function parentPermissionId(value, row, index) {
	if (row != null && row.parentPermission != null) {
		return row.parentPermission.name;
	}
	return "-";
}

/**
 * @param 格式化显示最后一次密码
 * @returns
 */
function format_lastPwd(value, row) {
	if (row != null) {
		return row.info.lastPwd;
	} else {
		return "-";
	}
}

/**
 * @param 最后一次修改密码时间
 * @returns
 */
function format_lastPwdTime(value, row) {
	if (row != null) {
		return row.info.lastMdPwdTime;
	} else {
		return "-";
	}
}

/**
 * @param 格式化创建时间
 * @returns
 */
function format_createTime(value, row) {
	if (row != null) {
		return row.info.createdTime;
	} else {
		return "-";
	}
}

function format_showShorCut(value, row, index) {
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
function format_email(value, row, index) {
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
function format_tel(value, row, index) {
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
function format_mobile(value, row, index) {
	if (row != null && row.contact != null) {
		return row.contact.mobile;
	} else {
		return "-";
	}
}

/**
 * description:格式化QQ
 * @param 
 * @returns
 */
function format_qq(value, row, index) {
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
function format_sex(value, row, index) {
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

 
 
