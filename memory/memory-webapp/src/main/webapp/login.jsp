<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="ico/favicon.ico">
<link href="css/main/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
<script type="text/javascript" src="js/main/login.js"></script>
</head>
<body>
	<div class="content">
		<table border="0px" bordercolor="#0000FF" width="680px" height="390px"
			cellspacing="0" cellpadding="0" align='center'>
			<tr>
				<td align='center'>
					<div class="d_main">
						<table width="680px" height="390px" border="0px">
							<tr>
								<td><div class="top_left"></div></td>
								<td><div class="top_right"></div></td>
							</tr>
							<tr>
								<td align="center" colspan="2"><div class="top_center"></div></td>
							</tr>
							<tr>
								<td><div class="content_left"></div></td>
								<td>
									<div class="content_right">
										<form id="_formLogin">
											<table border="0" width="340px" height="110px">
												<tr>
													<td align="right"><div class="d_txt">账户</div></td>
													<td><div class="d_val">
															<input type="text" id="_username" tabindex="1" />
														</div></td>
												</tr>
												<tr>
													<td align="right"><div class="d_txt">密码</div></td>
													<td><div class="d_val">
															<input type="password" id="_password" tabindex="2" />
														</div></td>
												</tr>
												<tr>
													<td align="right"><div class="d_txt">语言</div></td>
													<td><div class="d_val">
															<select id="locale" tabindex="3" style="width: 194px;height: 30px;">
																<option value="chinese">中文</option>
																<!--<option value="english">English</option>-->
															</select>
														</div></td>
												</tr>
												<tr>
													<td><div class="d_txt"></div></td>
													<td align="center"><div class="d_val">
															<a tabindex="4"><img id="_login_img"
																src="images/btn_login.png" onClick="javascript:login()" /></a>
														</div></td>
												</tr>
											</table>
										</form>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2"><div id="d_login_process_bar"
										class="bottom_1">
										<img src='images/login_processbar.gif'></img>
									</div></td>
							</tr>
							<tr>
								<td colspan="2"><div id="d_login_msg" class="bottom_2"></div></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>