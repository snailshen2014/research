<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		/* //在JavaWeb规范中，使用Cookie代表cookie
		//1.创建Cookie对象
		Cookie cookie = new Cookie("name","shenyj");
		//2.调用response的方法把Cookie传给客户端。
		response.addCookie(cookie); */
		
		//1.获取Cookie
		Cookie [] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				//获取Cookie的名字和值
				out.print(cookie.getName() + ":" +cookie.getValue());
				out.print("<br>");
			}
		} else {
			out.print("没有一个Cookie,正在创建并返回。");
			//1.创建Cookie对象
			Cookie cookie = new Cookie("name","shenyj");
			//会话Cookie只是会话级别，浏览器关闭，Cookie 就没了。持久化Cookie,需要设置setMaxAge
			//设置Cookie的最大时效
			cookie.setMaxAge(30);
			//2.调用response的方法把Cookie传给客户端。
			response.addCookie(cookie); 
		}
	
	%>
</body>
</html>