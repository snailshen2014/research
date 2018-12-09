<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@page errorPage="/error.jsp"%>
<!-- 
page指令放在jsp页面前面

session=ture,false 只当前jsp是否使用session
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%
  int i = 10 / 0;

 %>
 <p>
 JSP指令，告诉引擎如何处理页面，
 当前有page,include,taglib三种指令
 page指令定义JSP页面的各种属性，建议把这个指令放到页面起始位置
 page指令常用的属性：
 1.import属性：自定当前jsp页面对应的Servlet需要导入的类
  page import="java.text.DateFormat" 
 2.session属性：取值true,或false,指定当前的session隐藏变量是否可用,是否生成HttpSession对象
 page session="ture"
 3.errorPage 和isErrorPage:当前页面出现错误时，响应的页面是什么。其中 "/"表示WEB应用根目录
 page errorPage="/error.jsp"
 isErrorPage指定当前页面是否是页面错误处理页面，是否可以使用exception隐藏变量
 </p>
</body>
</html>