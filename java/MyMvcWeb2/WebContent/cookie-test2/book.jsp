<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h4>Book Detail Page</h4>
	Book:
	<%= request.getParameter("book") %>
	<br>
	<br>
	<a href="books.jsp"> Return</a>
	<%
 	String book = request.getParameter("book");	
 	//delete Cookie if cookies > 5
 	Cookie [] cookies = request.getCookies();
 	//保存所有的ATGUIGU_BOOK_开头的Cookie
 	List<Cookie> bookCookies = new ArrayList<Cookie>();
 	//保存books.jsp传入的book匹配的那个Cookie
 	Cookie tmpCookie = null;
 	
 	if (cookies != null && cookies.length > 0) {
 		for (Cookie cookie : cookies) {
 			String cookieName = cookie.getName();
 			if (cookieName.startsWith("AIGUIGU_BOOK_")) {
 				bookCookies.add(cookie);
 				if(cookie.getValue().equals(book)) {
 					tmpCookie = cookie;
 				}
 			}
 		}
 	}
 	//add Cookie，页面传入的book 不在ATGUIGU_BOOK_开头的Cookie中，则删除最早那个Cookie
 	if(bookCookies.size() >= 5 && tmpCookie == null) {
 		tmpCookie = bookCookies.get(0);
 	}
 	
 	//如果Cookie在其中，则删除该Cookie
 	if (tmpCookie != null) {
 		tmpCookie.setMaxAge(0);
 		response.addCookie(tmpCookie);
 	}
 	
 	Cookie cookie = new Cookie("ATGUIGU_BOOK_" + book ,book);
 	response.addCookie(cookie);
 
 %>
</body>
</html>