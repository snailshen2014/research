package com.mvc.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dao.Condition;
import com.mvc.dao.CustomerDao;
import com.mvc.domain.Customer;
import com.mvc.impl.CustomerDaoJdbcImpl;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")

public class CustomerServlet extends HttpServlet {
	private CustomerDao customerDao = new CustomerDaoJdbcImpl();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String method = request.getParameter("method");
//		switch(method) {
//			case "add" : add(request,response);break;
//			case "query" : query(request,response);break;
//			case "delete" : delete(request,response);break;
//		}
		String servletPath = request.getServletPath();
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0,methodName.length() - 3);
		Method method;
		try {
			method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this,request,response);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			//response.sendRedirect("error.jsp");
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(servletPath);
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("add");
	}
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("query");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		Condition con = new Condition( name,address,phone);
		
		//1.调用CustomerDao的getAll()方法获取数据到List<Customer>
//		List<Customer> customers = customerDao.getAll();
		List<Customer> customers = customerDao.getForListWithCondition(con);
		//2. 把list数据存入request请求域中
		request.setAttribute("customers", customers);
		//3.转发到页面index.jsp
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int iId = 0;
		try {
			iId = Integer.parseInt(id);
			customerDao.delete(iId);
		} catch (Exception e) {
			
		}
		//重定向
		response.sendRedirect("query.do");
		System.out.println("delete");
	}
	protected void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		//检查是否被占用
		long count = customerDao.getCountWithName(name);
		if (count > 0) {
			request.setAttribute("NameDubloue","名字："+ name + ",已经被占用。");
			
			System.out.println("名字："+ name + ",已经被占用。");
			request.getRequestDispatcher("newcustomer.jsp").forward(request, response);
			return;
		}
		Customer customer = new Customer(name,address,phone);
		customerDao.save(customer);
		//重定向，使用重定向可以避免表单重复提交
		response.sendRedirect("success.jsp");
	}
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardPath = "/error.jsp";
		//1.获取请求参数id
		String sId = request.getParameter("id");
		
		try {
			Customer customer = customerDao.get(Integer.parseInt(sId));
			if(customer != null) {
				forwardPath = "/updatecustomer.jsp";
				//将customer放入request  中
				request.setAttribute("customer", customer);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//转发，响应updatecustomer.jsp页面
		request.getRequestDispatcher(forwardPath).forward(request, response);
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取表单参数 id,name address,phone,oldName
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String oldName = request.getParameter("oldName");
		//比较oldName和name
		if (!oldName.equalsIgnoreCase(name)) {
			long count = customerDao.getCountWithName(name);
			if (count > 0 ){
				//在updatecustomer.jsp页面显示 名字被占用的提示
				request.setAttribute("NameDouble", "用户名"+ name + "已经被占用，请重新选择！");
				request.getRequestDispatcher("/updatecustomer.jsp").forward(request, response);
				return;
			}
		}
		//若验证通过则封装Customer对象调用dao方法更新
		Customer customer = new Customer(name,address,phone);
		customer.setId(Integer.parseInt(id));
		customerDao.update(customer);
		//重定向到query.do
		response.sendRedirect("query.do");
	}
}
