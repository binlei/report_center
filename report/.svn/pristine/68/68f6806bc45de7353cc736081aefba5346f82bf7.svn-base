function ProgressBar(ctx,msg)
{
	this.msg = msg;
	 //显示进度条
	this.show = function()
	{
		var processBarBgObj = document.createElement("div");
		processBarBgObj.setAttribute("id", "ProgressBar_BG");
		var processBarObj = document.createElement("div");
		processBarObj.setAttribute("id", "ProgressBar_Load");
		
		document.body.appendChild(processBarBgObj);
		document.body.appendChild(processBarObj);
		document.getElementById("ProgressBar_Load").innerHTML="<img src='"+ctx+"/plugins/progressBar/images/loading.gif'/><center><font color='#FFFFFF'>"+msg+"</font><center>"; 	
	};
	//关闭进度条  
	this.close = function() 
	{
		var processBarBgObj=document.getElementById("ProgressBar_BG");
		processBarBgObj.parentNode.removeChild(processBarBgObj);
		var processBarObj=document.getElementById("ProgressBar_Load");
		processBarObj.parentNode.removeChild(processBarObj);
	};	
}