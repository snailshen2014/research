package com.elec.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
@WebServlet("/ElectricityResult")
public class ElectricityResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElectricityResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		System.out.println("Electric meter id:" + request.getParameter("id"));
		String classessPath = this.getClass().getResource("/").toString();
		String classessRealPath = classessPath.replace("file:", "");
		// find data directory
		String dataDir = classessRealPath.substring(0, classessRealPath.indexOf("WEB-INF"));
		File dataFile = new File(dataDir + "data/" + request.getParameter("id") + ".txt");
		List<ElecEntity> elecEntitys = txt2List(dataFile);
		for (ElecEntity entity : elecEntitys) {
			 System.out.println(entity);
		}
		 JsonResult result = new JsonResult();
		 result.setCode("0");
		 result.setData(elecEntitys);
		 result.setMessage("正确的获取了结果");
		 response.setContentType("text/xml;charset=UTF-8");
		
	     //返回前端数据  
		 PrintWriter out = response.getWriter();
	     out.write(JSON.toJSONString(result));
	     out.close();
	}
	public  List<ElecEntity> txt2List(File file){
        StringBuilder result = new StringBuilder();
        List<ElecEntity> elecEntitys = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            	
                String [] record = s.split("\\|");
                ElecEntity entiry = new ElecEntity(record[0],record[1],record[2],record[3]);
                elecEntitys.add(entiry);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return elecEntitys;
    }

}
