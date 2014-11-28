<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$(window).resize(function() {
			$('#tt').tabs({
				width : $(window).width(),
				height : $(window).height()
			});
		});
		$(function() {
			$('#tt').tabs({
				width : $(window).width(),
				height : $(window).height()
			});
		});
	});
</script>
</head>
<body class="easyui-layout" style="width: 100%; height: 100%;">

		<div id="tt" class="easyui-tabs" data-options="fit:true" >
			<div title="油卡余额查询">
				<iframe scrolling="NO" frameborder="0" src="balancePage" style="width: 100%; height: 100%;"></iframe>
			</div>
			<div title="油卡圈存记录">
				<iframe scrolling="NO" frameborder="0" src="depositPage" style="width: 100%; height: 100%;"></iframe>
			</div>
			<div title="油卡消费记录">
				<iframe scrolling="NO" frameborder="0" src="consumptionPage" style="width: 100%; height: 100%;"></iframe>
			</div>
		</div>
</body>
</html>
