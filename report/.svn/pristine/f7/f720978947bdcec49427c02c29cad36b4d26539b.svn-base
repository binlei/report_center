$(function(){
    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
	// 获取组织类型
	$.post(postPath+'/organization/getOrgTypes', function(datas) {
		if (datas != null) {
			var option = "<option vlaue=''>--请选择--</option>";
			$.each(datas, function(i) {
				if($("#orgTypeId").val() == datas[i].id){
					option += "<option value='"+datas[i].id+"' selected>"+datas[i].name+"</option>";
				}
					option += "<option value='"+datas[i].id+"'>"+datas[i].name+"</option>";
			});
			$("#orgType").html(option);
		}
	}, 'json');
	
	// 获取组织名称
	$.post(postPath+'/organization/getOrgName', function(datas) {
		if (datas != null) {
			var option = "<option vlaue=''>--请选择--</option>";
			$.each(datas, function(i) {
				if($("#parentOrgId").val() == datas[i].id){
					option += "<option value='"+datas[i].id+"' selected>"+datas[i].name+"</option>";
				}
					option += "<option value='"+datas[i].id+"'>"+datas[i].name+"</option>";
			});
			$("#parentOrganization").html(option);
		}
	}, 'json');
	
	$.post(postPath+'/permission/getPermissionNames', function(datas) {
		if (datas != null) {
			var option = "<option vlaue=''>--请选择--</option>";
			$.each(datas, function(i) {
				if($("#parentPer").val()==datas[i].id){
					option += "<option value='"+datas[i].id+"' selected >"+datas[i].name+"</option>";
				}
					option += "<option value='"+datas[i].id+"'>"+datas[i].name+"</option>";
			});
			$("#parentPermission").html(option);
		}
	}, 'json');
});

