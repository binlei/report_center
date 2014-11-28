<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="dlg-buttons">
	<table cellpadding="0" cellspacing="0" style="width: 100%">
		<tr>
			<td><span id="msg" style="color: red;"></span></td>
			<td style="text-align: right">
				<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="javascript:$('#msg').html('123')">保存</a> 
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#showDialog').dialog('destroy')">关闭</a>
			</td>
		</tr>
	</table>
</div>
