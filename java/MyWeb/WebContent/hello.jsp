<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
JSP: java server page,本质是一个Servlet,下面是常用的隐含对象
 -->
	<!-- jsp 隐含对象request,response,pageContext,session,application.. -->
	<%
	 	Date date = new Date();
		System.out.println(date);
	
	%>
	<%
		//http://localhost:8080/MyWeb/hello.jsp?name=shenyj
		String name = request.getParameter("name");
		System.out.println("name:" + name);
		//session 客户端和服务器的一次会话
		System.out.println(session.getId());
		//application :代表当前WEB应用，ServletContext对象
		System.out.println(application.getInitParameter("user"));
		//config 代表当前jsp对应的servlet
		//out JspWriter对象，调用out.println打到页面上
		out.println(request.getParameter("name"));
		//page:指向当前JSP 对应的servlet对象的引用基本不用
	%>
	<!-- JSP表达式 -->
	<%= date %>
	
</body>
</html>