package com.mvc.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.mvc.dao.CustomerDao;
import com.mvc.domain.Customer;
import com.mvc.impl.CustomerDaoJdbcImpl;

public class CustomerDaoJdbcImplTest {
	private CustomerDao customerDao = new CustomerDaoJdbcImpl();
	@Test
	public void testGetAll() {
		List<Customer> customers = customerDao.getAll();
		System.out.println(customers);
	}

	@Test
	public void testSave() {
		Customer customer = new Customer();
		customer.setName("ShangHai");
		customer.setAddress("JiaDing");
		customer.setPhone("13987612333");
		customerDao.save(customer);
	}

	@Test
	public void testGetInteger() {
		Customer customer = customerDao.get(1);
		System.out.println(customer);
	}

	@Test
	public void testDelete() {
		
		customerDao.delete(1);
	}

	@Test
	public void testGetCountWithName() {
		long count = customerDao.getCountWithName("ShangHai");
		System.out.println(count);
	}

}
