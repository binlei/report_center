<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${version.title }</title>

<link href="css/main/main.css" rel="stylesheet" type="text/css" />
<%@ include file="/WEB-INF/view/common/header.jsp" %>
<link href="css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
<script src="js/main/main.js" type="text/javascript"></script>
<style type="text/css">
body { font-size: 12px; padding: 0; margin: 0; overflow: hidden; }

#northPanel .top-bar { position: absolute; width: 100%; height: 52px; top: 0; left: 0; }
#northPanel .top-bar-left { position: absolute; width: 500px; height: 52px; }
#northPanel .top-bar-right { position: absolute; width: 400px; height: 52px; right: 0; }

#northPanel .top-toolbar { position: absolute; font-weight: normal; padding: 0; width: 100%; height: 26px; top: 50px; left: 0; border-top-width: 1px; border-bottom-width: 0; z-index: 100; }
#northPanel .top-toolbar-topmost { top: 0px; height: 25px; border-top-width: 0px; border-bottom-width: 1px; }
#northPanel .top-toolbar #infobar { position: absolute; height: 28px; line-height: 28px; left: 10px; }
#northPanel .top-toolbar #comm_msg { position: absolute; height: 26px; left: 720px; padding-top: 4px; }
#northPanel .top-toolbar #buttonbar { position: absolute; height: 25px; right: 5px;   text-align: right; }

</style>
</head> 
<body class="easyui-layout" >

	<div id="northPanel" data-options="region: 'north', border: false" style="height: 77px; overflow: hidden;">
        <div class="top-bg" >
			<div class="top-bg_logo" >
				<img src="images/logo.png" style="width:130px;height:50px; margin:0px auto;border: 0;"></img>
			</div>
			<div class="top-bg_title" style="margin-top: -10px"> 
				<div class="top-bg_title_top">
					<span><font style="font-family: 'microsoft yahei';" color="white"><strong>${version.name }</strong></font></span><span style="font-family: 'microsoft yahei';font-size: 12px;color: white;vertical-align: text-top;">&nbsp;&nbsp;${version.version }</span>
				</div>
			</div>
		</div>
		<div id="topbar" class="top-bar">
             <div class="top-bar-right">
                 <div id="top_tool_bar_date"></div>
             </div>
        </div>
        <div id="toolbar" class="panel-header panel-header-noborder top-toolbar">
            <div id="infobar">
                <span class="icon-user" style="padding-left: 25px; background-position: left center;">
		 欢迎进入${version.name }系统，[${username }] 用户您好，登录时间： ${loginTime }
                </span>
            </div>
            <div style="font-weight:bold; font-size:1.2em; font-family:楷体; color: red;" id="comm_msg"></div>
            <div id="buttonbar">
                <a id="btnFullScreen" class="easyui-linkbutton" onclick="pwd();" data-options="plain: true, iconCls: 'icon-pwd-edit',tooltip: '....'">修改密码</a>
                <a id="btnExit" class="easyui-linkbutton" onclick="javascript:window.location.href = '${pageContext.request.contextPath}/user/logout';" data-options="plain: true, iconCls: 'icon-signout'">退出系统</a>
            </div>
        </div>
   </div>

	<!-- 修改密码 -->
	<div id="modifPwdDialog" data-options="iconCls: 'icon-pwd-edit'" style="width: 350px; height: 230px; display: none;">
		<div id="loginTabs">
			<div title="输入方式" align="left"
				style="overflow: hidden; padding: 5px;">
				<form id="modifInputForm">
					<table class="tableForm">
						<tr>
							<th>输入旧密码</th>
							<td><input type="password" name="oldPwd" id="oldPwd"
								class="easyui-validatebox" required="true"
								onblur="checkOldPwd(this);" /> <label id="oldPwdMsg"></label></td>
						</tr>
						<tr>
							<th>输入新密码</th>
							<td><input type="password" name="password" id="password"
								validType="length[4,32]" class="easyui-validatebox"
								required="true" maxlength="100" /></td>
						</tr>
						<tr>
							<th>再输入一次新密码</th>
							<td><input type="password" name="repassword" id="repassword"
								required="true" class="easyui-validatebox"
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
				<div id="home" title="欢迎使用" data-options="iconCls:'icon-home'">
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