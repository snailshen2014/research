<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ftd</title>
</head>
<style>
#header {
	background-color: black;
	color: white;
	text-align: center;
	padding: 5px;
}

#nav {
	line-height: 30px;
	background-color: #eeeeee;
	height: 800px;
	width: 100px;
	float: left;
	padding: 5px;
	outline: none;
}

#section {
	width: 350px;
	float: left;
	padding: 10px;
}

#footer {
	background-color: black;
	color: white;
	clear: both;
	text-align: center;
	padding: 5px;
	bottom: 0px;
}
</style>
<script src="jquery/jquery-3.2.1.js"> </script>

</head>

<body>
	<div id="header">
		<h1>ftdgl</h1>
	</div>

	<div id="nav">
		<button type="button" id="limit" onclick="setLimitValue()">设定阈值</button>
		<button type="button" id="result" onclick="showResult()">显示对比结果</button>
		<!--  
			设定阈值<br>
			显示对比结果<br>
			-->
	</div>

	<div id="section"></div>

	<div id="footer">Copyright bjbyg</div>

	<script>
		function setLimitValue() {
			
			// 创建一个 form 
			var form1 = document.createElement("form");
			form1.id = "setLimit";
			form1.name = "limit";
			// 添加到 body 中 
			document.getElementById('section').appendChild(form1); //添加到form中显示
			// 创建一个输入 
			var input = document.createElement("input");
			// 设置相应参数 
			input.type = "text";
			input.name = "limitValue";
			//input.value = "1234567";
			// 将该输入框插入到 form 中 
			form1.appendChild(input);
			// form 的提交方式 
			form1.method = "POST";
			// form 提交路径 
		    form1.action = "./servlet/ServletDemo";
			var input2 = document.createElement("input");
			input2.type="submit";
			input2.name="sub";
			input2.id="summitLimit";
			form1.appendChild(input2);
			// jquery 表单提交 
			//$("#setLimit").ajaxSubmit(function(message) { 
			// 对于表单提交成功后处理，message为提交页面saveReport.htm的返回内容 
			//}); 
			//return false;
			//form1.submit();
			// 删除该 form 
			//document.body.removeChild(form1);
			
		}
		function showResult() {
			document.getElementById("section").style.visibility = "hidden";//隐藏
			//document.getElementById("section").style.visibility="visible";//显示
		}
		
		function sendPost() {
			$.ajax({  
                cache: true,  
                type: "POST",  
                url:"./servlet/ServletDemo",  
                data:$('#form1').serialize(),// 你的formid  
                async: false,  
                error: function(request) {  
                    alert("Connection error:"+request.error);  
                },  
                success: function(data) {  
                    alert("SUCCESS!");  
                }  
            });  
		}
	</script>



</body>
</html>