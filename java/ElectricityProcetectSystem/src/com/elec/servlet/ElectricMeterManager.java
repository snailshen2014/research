package com.elec.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.elec.domain.ElecRecord;
import com.elec.impl.ElecRecordDaoJdbcImpl;
import com.elec.utils.FileManager;
import com.elec.utils.JsonResult;

import dao.ElecRecordDao;

/**
 * Servlet implementation 
 */
@WebServlet("/saveElectricMeter")
public class ElectricMeterManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ElecRecordDao elecRecordDao = new ElecRecordDaoJdbcImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElectricMeterManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//body ElecEntity{"HOST_ID":"xxx";"HOST_NAME":"xxx";"ELEC_VALUE1":"xxx";"ELEC_VALUE2":"xxx"}
		//Header部分
	    System.out.print(request.getHeaderNames());
	    Enumeration<?> enum1 = request.getHeaderNames();
	    while (enum1.hasMoreElements()) {
	      String key = (String) enum1.nextElement();
	      String value = request.getHeader(key);
	      System.out.println(key + "\t" + value);
	    }
	    
	    
		System.out.println("saveElectricMeter servlet running...");
		String elecEntityJson = "";
		String line = "";
		request.setCharacterEncoding("utf-8");
		//read body
		BufferedReader br = request.getReader();
		while ((line = br.readLine()) != null) {
			elecEntityJson  += line;
		}
		System.out.println("Request elec entity:" + elecEntityJson);
		List<String> meters = JSON.parseArray(elecEntityJson, String.class);
		String elecFile = "/usr/local/apache-tomcat-8.5.28/webapps/ElectricityProcetectSystem/data/meters.txt";
		FileManager.deleteFile(elecFile);
		for (String meter : meters) {
			System.out.println(meter);
			FileManager.toFile(elecFile,meter + "\n");
		}
		
		
	}

}
