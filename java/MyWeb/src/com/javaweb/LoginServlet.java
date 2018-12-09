package com.javaweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginServlet implements Servlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		context =  config.getServletContext();
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(req);
		String user = req.getParameter("user");
		String pwd =  req.getParameter("password");
		System.out.println("user:" + user + " pwd:" + pwd);
		PrintWriter writer = res.getWriter();
		//比对配置的数据和form表单提交的值比对
		if(user.equals(context.getInitParameter("user")) 
				&& pwd.equals(context.getInitParameter("password"))) {
			
			writer.write("Hello " + context.getInitParameter("user"));
		} else {
			writer.write("User:" + user + ",pasword:" + pwd + " no right.");
		}
		String [] interesting = req.getParameterValues("interesting");
		for(String inter : interesting)
			System.out.println("Interesting:" + inter);
		
		Enumeration<String> names = req.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String val = req.getParameter(name);
			System.out.println(" ^^" + name + ":" + val);
		}
		
		Map<String,String[]> map = req.getParameterMap();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + Arrays.asList(entry.getValue()));
		}
		//获取请求的url,方式
		HttpServletRequest httpReq = (HttpServletRequest)req;
		System.out.println("URI:" + httpReq.getRequestURI());
		System.out.println("URL:" + httpReq.getRequestURL());
		System.out.println("Method:" + httpReq.getMethod());
		
		//ServletResponse
//		设置文档类型，查找tomcat 的web.xml
//		res.setContentType("application/msword");
	
		writer.write("Hello chrome.");
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	private ServletContext context; 
}
