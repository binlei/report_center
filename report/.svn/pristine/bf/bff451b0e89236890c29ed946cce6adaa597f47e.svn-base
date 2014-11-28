var strPath = window.document.location.pathname;
var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);

/**
 * @param _obj
 */
function commRightMenu(_obj){
	var rows = $('#'+_obj).datagrid('getSelections');
	$('#menu').menu('disableItem',"div[id*='edit']");
	$('#menu').menu('disableItem',"div[id*='bind']");
	if(rows.length == 1){
		$('#menu').menu('enableItem',"div[id*='"+_obj+"']");
	}else if(rows.length > 1){
		$('#menu').menu('enableItem',"div[id*='bind']");
	}
}