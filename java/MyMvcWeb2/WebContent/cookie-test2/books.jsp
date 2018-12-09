<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<h4> Books Page</h4>
	<a href="book.jsp?book=JavaWeb">Java Web</a><br>
	<a href="book.jsp?book=Java">Java</a><br>
	<a href="book.jsp?book=Oracle">Oracle</a><br>
	<a href="book.jsp?book=Ajax">Ajax</a><br>
	<a href="book.jsp?book=JavaScript">JavaScript</a><br>
	<a href="book.jsp?book=Android">Android</a><br>
	<a href="book.jsp?book=Jbpm">Jbpm</a><br>
	<a href="book.jsp?book=Structs">Structs</a><br>
	<a href="book.jsp?book=Hibernate">Hibernate</a><br>
	<a href="book.jsp?book=Spring">Spring</a>
	
	<br><br>
	___________________________________________________<br>
	
	<%
	Cookie [] cookies = request.getCookies();
	
 	if (cookies != null && cookies.length > 0) {
 		for (Cookie cookie : cookies) {
 			String cookieName = cookie.getName();
 			if (cookieName.startsWith("ATGUIGU_BOOK_")) {
 				out.print(cookie.getValue());
 				out.print("<br>");
 			}
 		}
 	}
	%>
</body>
</html>