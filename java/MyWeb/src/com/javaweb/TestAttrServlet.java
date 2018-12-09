package com.javaweb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestAttrServlet
 */
@WebServlet("/TestAttrServlet")
public class TestAttrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestAttrServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =  response.getWriter();
		//1.在servlet中无法得到pageContext
		//2.request
		Object requestAttr = request.getAttribute("requestAttr");
		out.print("requestAttr: " +  requestAttr);
		out.print("<br><br>");
		//3.session
		Object sessionAttr = request.getSession().getAttribute("sessionAttr");
		out.print("sessionAttr: " +  sessionAttr);
		out.print("<br><br>");
		//4.application
		Object applicationAttr = getServletContext().getAttribute("applicationAttr");
		out.print("applicationAttr: " +  applicationAttr);
		
	}

}
