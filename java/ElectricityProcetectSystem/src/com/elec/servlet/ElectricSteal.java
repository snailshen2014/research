package com.elec.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.elec.domain.ElecEntity;
import com.elec.utils.JsonResult;

/**
 * Servlet implementation class ElectricityResult
 */
@WebServlet("/ElectricSteal")
public class ElectricSteal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElectricSteal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		
		String classessPath = this.getClass().getResource("/").toString();
		String classessRealPath = classessPath.replace("file:", "");
		// find data directory
		String dataDir = classessRealPath.substring(0, classessRealPath.indexOf("WEB-INF"));
		File dataFile = new File(dataDir + "data/" +  "steal.txt");
		String steal = file2Text(dataFile);
		
		 JsonResult result = new JsonResult();
		 result.setCode("0");
		 result.setData(steal);
		 result.setMessage("正确的获取了结果");
		 response.setContentType("text/xml;charset=UTF-8");
		
	     //返回前端数据  
		 PrintWriter out = response.getWriter();
	     out.write(JSON.toJSONString(result));
	     out.close();
	}
	public  String file2Text(File file){
        StringBuilder result = new StringBuilder();
        List<ElecEntity> elecEntitys = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            	
                result.append(s);
                result.append(",");
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

}
