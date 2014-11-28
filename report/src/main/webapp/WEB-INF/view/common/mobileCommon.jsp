<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="ico/favicon.ico">
<link href="${pageContext.request.contextPath}/css/themes/gray/easyui.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/html/list.css">

<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/taglibs.jsp"></jsp:include>
<jsp:include page="../common/listUtil.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/format.js"></script>
<script type="text/javascript">
//渠道下拉框
$(function() {
	/* $("#extendProp4").combotree({
		url:postPath+"/user/getLegalChannel?flag=install",
		async : false,
		method:'get',
		lines:true,
		multiple:true,
		onLoadSuccess:function(node,data){//默认展开全部节点
			var t=$(this);
			if(data){
				$(data).each(function(index,item){
					t.tree('expandAll');
				});
			}
		}
	}); */
	getLegalChannel($("#extendProp4"));
});

</script>
</head>
<body>

</body>
</html>