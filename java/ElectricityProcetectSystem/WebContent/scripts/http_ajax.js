//创建ajax请求对象 

var xmlHttp;
function createXMLHttpRequest() {
	if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}
}
/**
 * 表单提交
 */
function submit(url) {
	// 发送请求
	createXMLHttpRequest();
	try {
		xmlHttp.onreadystatechange = handleStateChange;
		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
	} catch (exception) {
		alert("您要访问的资源不存在!");
	}

}
function submitPost(url, data) {
	// 发送请求
	createXMLHttpRequest();
	try {
		xmlHttp.onreadystatechange = null;
		xmlHttp.open("POST", url, true);
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(data);
	} catch (exception) {
		alert("您要访问的资源不存在!");
	}

}
function handleStateChange() {

	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200 || xmlHttp.status == 0) {
			var jsonOjbect = eval("(" + xmlHttp.responseText + ")");
			var entity = jsonOjbect.data;
			console.log(entity);

			var tabData = "<tr> <th >采集时间</th><th >a相电量值</th> <th >B相电量值</th> <th>c相电量值</th> </tr> ";
			var span = '<span id="s"></span> '
					+ '<a href="#" onclick="page.firstPage();">首页</a>'
					+ '<a href="#" onclick="page.prePage();">上一页 </a>'
					+ '<a href="#" onclick="page.nextPage();"> 下一页</a>'
					+ '<a href="#" onclick="page.lastPage();"> 尾页</a>';

			for (var rec = 0; rec < entity.length; rec++) {
					// console.log(entity[rec]);
					// 动态添加表格数据
				tabData += "<tr><td>" + entity[rec].t +"</td><td>" + entity[rec].aValue + "</td><td>"
						+ entity[rec].bValue + "</td><td>" + entity[rec].cValue
						+ "</td></tr>"
			}
			// 在表格内填充数据
			document.getElementById("group_one").innerHTML = tabData.replace(
					/<td>undefined<\/td>/ig, "");
			page = new Page(10, "divtable", "group_one", "pageindex");
			// 创建分页href
			document.getElementById("hpos").innerHTML = span;
			
//			document.getElementById("qiedian").innerHTML = entity;
		}
	}
}	
	
	function getPromptInfo(url) {
		try {
			xmlHttp.onreadystatechange = handleStateChange2;
			xmlHttp.open("GET", url, true);
			xmlHttp.send(null);
		} catch (exception) {
			alert("您要访问的资源不存在!");
		}
	
	}
	function handleStateChange2() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200 || xmlHttp.status == 0) {
				var jsonOjbect = eval("(" + xmlHttp.responseText + ")");
				var entity = jsonOjbect.data;
				console.log(entity);
				document.getElementById("qiedian").innerHTML = entity;
			}
		}
	}
	function getDetailInfo(url) {
		try {
			xmlHttp.onreadystatechange = handleStateChange3;
			xmlHttp.open("GET", url, true);
			xmlHttp.send(null);
		} catch (exception) {
			alert("您要访问的资源不存在!");
		}
	
	}
	function handleStateChange3() {

		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200 || xmlHttp.status == 0) {
				var jsonOjbect = eval("(" + xmlHttp.responseText + ")");
				var entity = jsonOjbect.data;
				console.log(entity);

				var tabData = "<tr><th colspan=\"4\">A</th> <th colspan=\"4\">B</th></tr>";
				tabData += "<tr> <th >采集时间</th><th >a相电量值</th> <th >B相电量值</th> <th>c相电量值</th>  <th >采集时间</th><th >a相电量值</th> <th >B相电量值</th> <th>c相电量值</th> </tr> " ;
				var span = '<span id="s"></span> '
						+ '<a href="#" onclick="page.firstPage();">首页</a>'
						+ '<a href="#" onclick="page.prePage();">上一页 </a>'
						+ '<a href="#" onclick="page.nextPage();"> 下一页</a>'
						+ '<a href="#" onclick="page.lastPage();"> 尾页</a>';

				for (var rec = 0; rec < entity.length ; rec = rec+2) {
						// console.log(entity[rec]);
						// 动态添加表格数据
					tabData += "<tr><td>" + entity[rec].t +"</td><td>" + entity[rec].aValue + "</td><td>"
							+ entity[rec].bValue + "</td><td>" + entity[rec].cValue
							+ "</td>"
					tabData += "<td>" + entity[rec+1].t +"</td><td>" + entity[rec+1].aValue + "</td><td>"
							+ entity[rec+1].bValue + "</td><td>" + entity[rec+1].cValue
							+ "</td></tr>"
				}
				// 在表格内填充数据
				document.getElementById("group_one").innerHTML = tabData.replace(
						/<td>undefined<\/td>/ig, "");
				page = new Page(10, "divtable", "group_one", "pageindex");
				// 创建分页href
				document.getElementById("hpos").innerHTML = span;
				
//				document.getElementById("qiedian").innerHTML = entity;
			}
		}
	}	

