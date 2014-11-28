<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${version.title }</title>

<link rel="shortcut icon" href="ico/favicon.ico">
<link href="css/main/main.css" rel="stylesheet" type="text/css" />
<link href="css/themes/bootstrap/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="css/zTreeStyle/zTreeStyle.css" rel="stylesheet"
	type="text/css" />
<jsp:include page="common/header.jsp"></jsp:include>
<jsp:include page="common/taglibs.jsp"></jsp:include>
<script src="js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
<script src="js/main/main.js" type="text/javascript"></script>

</head>

<body class="easyui-layout">

	<div id="north_panle" region="north" border="true">
		<div class="top-bg">
			<div class="top-bg_logo">
				<img src="images/logo.png" style="margin:0px auto;border: 0;"></img>
			</div>
			<div class="top-bg_title"> 
				<div class="top-bg_title_top">
					<span><font style="font-family: 'microsoft yahei';" color="white"><strong>${version.name }</strong></font></span><span style="font-family: 'microsoft yahei';font-size: 12px;color: white;vertical-align: text-top;">&nbsp;&nbsp;${version.version }</span>
				</div>
				<div class="top-bg_title_bottom"><font style="font-family: 'microsoft yahei'" color="white">&nbsp;${version.extendProp1 }</font>
				</div>
			</div>
			
			<div class="top-bg_context">
				<div id="top_tool_bar_date" class="top_tool_bar_date"></div>
				<div id="top_tool_bar_userinfo" class="top_tool_bar_userinfo">
					<font color="white" style="float:right;">
						<strong>您好，${username }</strong><font color="gray">&nbsp;<strong>|</strong>&nbsp;</font><strong>登录时间：${loginTime }</strong><font color="gray">&nbsp;<strong>|</strong></font><a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#main_north_kzmbMenu',iconCls:''"><font color="">控制面板</font></a><font color="gray"><strong>|</strong></font>
					</font>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改密码 -->
	<div id="modifPwdDialog"
		style="width: 350px; height: 230px; display: none;">
		<div id="loginTabs">
			<div title="输入方式" align="left"
				style="overflow: hidden; padding: 5px;">
				<form id="modifInputForm">
					<table class="tableForm">
						<tr>
							<th>输入旧密码</th>
							<td><input type="password" name="oldPwd" id="oldPwd"
								class="easyui-validatebox" required="true" missingMessage="该项为必填项"
								onblur="checkOldPwd(this);" /> <label id="oldPwdMsg"></label></td>
						</tr>
						<tr>
							<th>输入新密码</th>
							<td><input type="password" name="password" id="password"
								validType="length[4,32]" class="easyui-validatebox" missingMessage="该项为必填项"
								required="true" maxlength="100" /></td>
						</tr>
						<tr>
							<th>再输入一次新密码</th>
							<td><input type="password" name="repassword" id="repassword"
								required="true" class="easyui-validatebox" missingMessage="该项为必填项"
								validType="equalTo['#password']" invalidMessage="两次输入密码不匹配" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>

		<div id="south_panle" region="south" border="true">
			<div class="footer">江苏华博实业集团有限公司版权所有&nbsp;苏ICP备05053262号</div>
		</div>
	
		<div id="west" region="west" hide="true" split="true">
		
			<ul id="na_menu" class="ztree"></ul>
			<div style="position:absolute;right:5px;bottom:0px;width:100%;background-color: #DAEBFB;">
				<div id="na_menu_search" style="float: left;margin-left:10px;">
					<img border="0" alt="filter" src="images/filter.png" style="margin-top: 3px;"/>
					<input id="searchbox" style="width: 120px;vertical-align: center;"/>
				</div>
				<div id="na_menu_operater" style="float: right;">
					<img onclick="menuOperater('expandAll')" border="0" alt="+" src="images/expandAll.png" style="margin-top: 3px;">
					<img onclick="menuOperater('collapseAll')" border="0" alt="-" src="images/collapseAll.png" style="margin-top: 3px;">
				</div>
			</div>
		</div>  
	
		<div id="mainPanle" region="center" split="true">
			<div id="tabs" class="easyui-tabs" fit="true" border="false">
				<div id="home" title="欢迎使用">
					<h1>Welcome !</h1>
				</div>
			</div>
		</div>
	<div id="main_north_kzmbMenu" style="width: 100px; display: none;">
		<div onclick="pwd();">修改密码</div>
		<div onclick="javascript:window.location.href = '${pageContext.request.contextPath}/user/logout';">注销</div>
		<div id="fullScreen" onclick="fullScreen();" style="display: none;">全屏</div>
		<div id="existFullScreen" onclick="existFullScreen();"
			style="display: none;">退出全屏</div>
	</div>
	<div id="mm" class="easyui-menu">
		<div id="close">关闭</div>
		<div id="closeall">关闭全部</div>
		<div id="closeother">关闭其他</div>
		<div class="menu-sep"></div>
		<div id="closeright">关闭右侧</div>
		<div id="closeleft">关闭左侧</div>
	</div>
</body>
</html>