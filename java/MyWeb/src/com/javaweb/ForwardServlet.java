package com.javaweb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForwardServlet
 */
@WebServlet("/forwardServlet")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForwardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ForwordServlet doGet.");
		request.setAttribute("name", "123");
		System.out.println("FrowordServlet's name:" + request.getAttribute("name"));
		//请求的转发
		//1.调用HttpServletRequest的getRequestDispatcher()方法获取RequestDispatcher对象
		//调用getRequestDispatcher()方法需要传入需要转发的地址
		//2.调用HttpServletRequest的forward(request,response)进行转发
		String path = "testServlet";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/" + path); 
		requestDispatcher.forward(request, response);
	}

}
