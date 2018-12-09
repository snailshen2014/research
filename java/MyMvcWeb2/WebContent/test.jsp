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
	方法一：
	当天加一个请求时，需要修改两处代码 1.改方法名称swithc 2.添加方法
	url中使用method=xxx,暴露了要调用的方法，不安全
	********************************************
	</p>
	<a href="CustomerServlet?method=add"> Add</a>
	<br><br>
	<a href="CustomerServlet?method=query"> Query</a>
	<br><br>
	<a href="CustomerServlet?method=delete"> Delete</a>
	<br><br>
	
	<p>
	方法二：
	1.获取ServletPath  *.do
	2.利用反射，调用ServletPaht对应的方法，
	3.创建对应的方法
	********************************************
	</p>
	<a href="add.do"> Add</a>
	<br><br>
	<a href="query.do"> Query</a>
	<br><br>
	<a href="delete.do"> Delete</a>
	<br><br>
	
	
</body>
</html>