<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
	1).在JSP页面上输入中文，请求页面后不出现乱码：保证contentType="text/html;charset=UTF-8","
	pageEncoding="UTF-8" charset和pageEncoding的编码一直，且都支持中文。通常建议取值为UTF-8
	还需要保证浏览器的显示的字符编码也和请求的JSP页面编码一致。
	2）获取中文参数：默认参数在传输过程中使用的编码为ISO-8859-1
	对于POST请求：只需要在获取请求的信息之前，调用request.SetCharacterEncoding("UTF-8")即可
	对于GET请求：前面的方式对于GET无效，可通过修改Tomcat的server.xml
	userBodyEncodingForURI属性，为Connector节点添加userBodyEncodingForURI="True"即可
	</p>
</body>
</html>