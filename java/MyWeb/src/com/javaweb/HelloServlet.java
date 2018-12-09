package com.javaweb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



public class HelloServlet implements Servlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Init TestServlet");
		String user = config.getInitParameter("user");
		System.out.println("user:" + user);
		
		Enumeration<String> names = config.getInitParameterNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			String value = config.getInitParameter(name);
			System.out.println("**" + name + ":" + value);
		}
		//获取ServletContext对象，他是Servlet的环境的所有信息
		ServletContext context =  config.getServletContext();
		String driver = context.getInitParameter("driver");
		System.out.println("Driver:" + driver);
		Enumeration<String> names2 = context.getInitParameterNames();
		while(names2.hasMoreElements()) {
			String name = names2.nextElement();
			String value = context.getInitParameter(name);
			System.out.println("**context" + name + ":" + value);
		}
		
		//获取应用程序发布的绝对路径
		String realPath = context.getRealPath("/data/data.txt");
		System.out.println("realPath:" + realPath);
		//获取当前WEB应用的某一个文件的输入流
		//获取WEB应用名称
		System.out.println("WEB APPLICATION NAME:" + context.getContextPath());
		
		
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = classLoader.getResourceAsStream("jdbc.properties");
		System.out.println("1." + is);
		InputStream is2 = context.getResourceAsStream("WEB-INFO/classes/jdbc.properties");
		System.out.println("2." + is2);
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("TestServlet service");
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("TestServlet destroy");
	}

}
