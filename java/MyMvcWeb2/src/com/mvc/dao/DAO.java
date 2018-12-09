package com.mvc.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mvc.db.JdbcUtils;

/**
 * 
 * ClassName: DAO <br/> 
 * Function: 封装了j基本的CRUD方法，以供子类集成使用，当前DAO直接在方法中获取
 * 数据库连接
 * @param <T> :当前DAO处理的实体类型
 * Reason: 
 * date: 2017年11月21日 下午2:27:40 <br/> 
 * 
 * @author yanjunshen 
 * @version @param <T> 
 * @since JDK 1.8
 */
public class DAO<T> {
	private QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;
	
	public DAO() {
		Type superClass = getClass().getGenericSuperclass();
		if(superClass instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType)superClass;
			Type [] typeArgs = paramType.getActualTypeArguments();
			if(typeArgs != null && typeArgs.length > 0) {
				if(typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0];
				}
			}
		}
	}
	/*
	 * 该方法封装了INSERT,UPDATE,DELETE操作
	 * @param sql
	 * @param args
	 */
	public void update(String sql,Object ...args) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			queryRunner.update(connection, sql,args);			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(connection);
		}
	}
	
	/*
	 * 返回T的一个实例对象
	 * @param sql
	 * @param args
	 */
	public T get(String sql,Object ...args) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql,new BeanHandler<>(clazz),args);		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	/*
	 * 返回T的list
	 * @param sql
	 * @param args
	 */
	public List<T> getForList(String sql,Object ...args) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql,new BeanListHandler<>(clazz),args);		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	/*
	 * 返回某个字段的一个值
	 * @param sql
	 * @param args
	 */
	public <E> E getForValue(String sql,Object ...args) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			return (E)queryRunner.query(connection, sql,new ScalarHandler(),args);		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
}
