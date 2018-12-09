package com.javaweb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectServlet
 */
@WebServlet("/redirectServlet")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RedirectServlet doGet method");
		request.setAttribute("name", "456");
		System.out.println("RedirectServlet's name:" + request.getAttribute("name"));
		//执行请求的重定向,直接调用response的sendRedirect(path)方法
		String path = "testServlet";
		response.sendRedirect(path);
	}
}
