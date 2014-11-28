<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function getAllCarNo() {
		$("input[class='combobox-carNo']")
				.combobox(
						{
							valueField : "id",
							textField : "carCard",
							editable : false,
							url : "${ctx}/dryLineCarRecord/getPlates",
							method : "get",
							formatter : function(row) {
								return "<img class='item-img' src='${pageContext.request.contextPath}/images/car.png'><span class='item-text'> "
										+ row.carCard + "</span>";
							},
							onLoadSuccess : function(data) {
								var carcard = "${common.dryLineCarRecord.id}";
								if (carcard != "") {
									var carcards = carcard.split(",");
									for (var i = 0; i < carcards.length; i++) {
										$(this).combobox("select", carcards[i]);
									}
								}
							},
						});
	}
	
	function getAllCarNoCar() {
		$("input[class='combobox-carNo']")
				.combobox(
						{
							valueField : "carCard",
							textField : "carCard",
							url : "${ctx}/dryLineCarRecord/getPlates",
							method : "get",
							editable:true,
							panelHeight:"auto",
							formatter : function(row) {
								return "<img class='item-img' src='${pageContext.request.contextPath}/images/car.png'><span class='item-text'> "
										+ row.carCard + "</span>";
							},
							onLoadSuccess : function(data) {
								var carcard = "${common.dryLineCarRecord.id}";
								if (carcard != "") {
									var carcards = carcard.split(",");
									for (var i = 0; i < carcards.length; i++) {
										$(this).combobox("select", carcards[i]);
									}
								}
							},
						});
	}
	
	function getMaincardId(){
		$(".combobox-maincard").combobox({
			url:"${ctx}/dryLineMaincard/getAllMaincard",
			valueField:"id",
			textField:"mainCard",
			method:"GET",
			editable:false,
			formatter:function(row){
				return "<img class='item-img' src='${ctx}/images/maincard.png'><span class='item-text'> " + row.mainCard + "</span>";
			},
			onShowPanel:function(row){
				$(this).combobox("reload");
			}
		});
	}
	
	function getMaincardCard(){
		$(".combobox-maincard").combobox({
			url:"${ctx}/dryLineMaincard/getAllMaincard",
			valueField:"mainCard",
			textField:"mainCard",
			method:"GET",
			editable:true,
			panelHeight:"auto",
			formatter:function(row){
				return "<img class='item-img' src='${ctx}/images/maincard.png'><span class='item-text'> " + row.mainCard + "</span>";
			},
			onShowPanel:function(row){
				$(this).combobox("reload");
			}
		});
	}
</script>
