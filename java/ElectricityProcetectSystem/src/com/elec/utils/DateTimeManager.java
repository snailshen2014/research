package com.elec.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeManager {
	public static String getSysDateTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

	public static String getCurrentTime(String formater) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(formater);// 可以方便地修改日期格式
		return dateFormat.format(now);
	}
}
