<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		HttpSession session = request.getSession();
		out.print(session.getId());
		out.print("<br>");
		//设置HttpSession 的失效时间，也可以在web.xml里设置超时时间
		session.setMaxInactiveInterval(5);
		out.print(session.getMaxInactiveInterval());
		//使当前session失效
		//session.invalidate();
		//在cookie禁用的时候用URL重写可以跟踪到HttpSession
		
		
	%>
</body>
</html>