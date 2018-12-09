<%@ page language="java" import= "java.util.List"  
import="com.myweb.Student"
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		List<Student> students = (List<Student>)request.getAttribute("students");
		
	%>
	<table border="1" cellpadding="10" cellspacing="0">
	<tr>
		<th>id</th>
		<th>name</th>
		<th>email</th>
	</tr>
	<%
		for(Student student : students) {
	%>
		<tr>
			<td> <%= student.getId() %></td>
			<td> <%= student.getName() %></td>
			<td> <%= student.getEmail() %></td>
			<td><a href="DeleteStudent?id=<%= student.getId() %>">Delete </a></td>
		</tr>
	<%
		}
	%>	
	</table>
</body>
</html>