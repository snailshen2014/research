<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//域对象 属性相关方法
	//1.pageContext属性的作用范围是当前JSP页面
	//2.request属性的作用范围是当前的请求
	//3.session属性的作用范围是一次会话（浏览器打开直到关闭称之为一次会话,前提是会话不失效）
	//4.application属性的作用范围 限于当前WEB应用，是范围最大的属性范围
	pageContext.setAttribute("pageContextAttr", "pageContextValue");
	request.setAttribute("requestAttr", "requestValue");
	session.setAttribute("sessionAttr", "sessionValue");
	application.setAttribute("applicationAttr", "applicationValue");
%>
	<h2> Attr 1 Page</h2>
	<br> <br>
	pageContextAttr: <%=pageContext.getAttribute("pageContextAttr") %>
	<br> <br>
	requestAttr: <%=request.getAttribute("requestAttr") %>
	<br> <br>
	sessionAttr: <%=session.getAttribute("sessionAttr") %>
	<br> <br>
	applicationAttr: <%=application.getAttribute("applicationAttr") %>
	<br><br>
	<a href="attr_2.jsp"> To Attr2 Page</a>
	
	<br><br>
	<a href="TestAttrServlet"> To Test attr on servlet</a>
	<br><br>
	<%
		request.getRequestDispatcher("/attr_2.jsp").forward(request, response);
	%>
</body>
</html>