package com.mvc.dao;

import java.util.List;

import com.mvc.domain.Customer;
import com.mvc.dao.Condition;

public interface CustomerDao {
	public List<Customer> getForListWithCondition(Condition con);
	public List<Customer> getAll() ;
	public void save(Customer customer);
	public Customer get(Integer id);
	public void delete(Integer id);
	public void update(Customer customer);
	/**
	 * 
	 * getCountWithName:返回名字对应的记录数. <br/> 
	 * @Param: name.<br/> 
	 * 
	 * @author yanjunshen 
	 * @param name
	 * @return 
	 * @since JDK 1.8
	 */
	public long getCountWithName(String name);
	
}
