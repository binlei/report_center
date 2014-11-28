<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	
	function getSubstationId(obj){
		$(".combobox-substation").combobox({
			url:"${ctx}/branchLineSubstation/getAllSubstation",
			valueField:"id",
			textField:"name",
			method:"GET",
			editable:false,
			formatter:function(row){
				return "<img class='item-img' src='${ctx}/images/city.png'><span class='item-text'> " + row.name + "</span>";
			},
			onShowPanel:function(row){
				$(this).combobox("reload");
			},
			onLoadSuccess:function(row){
				if(row.length == 1){
					$(this).combobox("select",row[0].id);
				}
			}
		});
	}
	
	function getCarDriver(){
		$(".combobox-driver").combobox({
			url:"${ctx}/branchLineCarInfo/getAllCarInfo",
			valueField:"name",
			textField:"name",
			method:"GET",
			editable:false,
			formatter:function(row){
				return "<img class='item-img' src='${ctx}/images/drive-user.png'><span class='item-text'> " + row.name + "</span>";
			},
			onShowPanel:function(row){
				$(this).combobox("reload");
			},
			onSelect:function(row){
				$("#licenseNo").val(row.licenseNo);
			}
		});
	}
	
</script>
