package com.mvc.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * ClassName: JdbcUtils <br/> 
 * Function: jdbc 操作类
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2017年11月21日 下午2:35:20 <br/> 
 * 
 * @author yanjunshen 
 * @version  
 * @since JDK 1.8
 */
public class JdbcUtils {
	private static DataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource("mvcapp");
	}
	public static void releaseConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
