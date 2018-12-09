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
@WebServlet("/ElectricStealDetail")
public class ElectricStealDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElectricStealDetail() {
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
		System.out.println("Electric point id:" + request.getParameter("id"));
		String pointId = request.getParameter("id");
		File dataFile = new File(dataDir + "data/" +  "steal_detail_" + pointId + ".txt");
	
		List<ElecEntity> elecEntitys = file2Text(dataFile);
//		for (ElecEntity entity : elecEntitys) {
//			 System.out.println(entity);
//		}
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
	public  List<ElecEntity> file2Text(File file){
      
        List<ElecEntity> elecEntitys = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            //2018-02-28 10:20:25|18.00|18.00|18.00;2018-02-28 10:20:25|18.00|8.00|18.00
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            	
                String [] record = s.split("\\;");
                for (String rec : record) {
                	String [] elm = rec.split("\\|");
                	ElecEntity entiry = new ElecEntity(elm[0],elm[1],elm[2],elm[3]);
                	elecEntitys.add(entiry);
                	
                }
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return elecEntitys;
    }

}
