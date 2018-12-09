package com.elec.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.elec.domain.ElecRecord;
import com.elec.utils.HttpMethod;


public class UpLoadElecValueTest {

	@Test
	public void testUploadElecValue() {
		ElecRecord elecRecord = new ElecRecord();
		elecRecord.setHOST_ID("12373189");
		elecRecord.setHOST_NAME("远端11");
		elecRecord.setELEC_VALUE1("999.88");
		elecRecord.setELEC_VALUE2("9987.77");
		elecRecord.setSTATUS(0);
		elecRecord.setOPER_DATE(new Date());
		String jsonData = JSON.toJSONString(elecRecord);
		System.out.println(jsonData);
		String url="http://localhost:8080/ElectricityProcetectSystem/uploadElec";
		HttpMethod.sendPostWithJson(url, jsonData);
	
	}
}
