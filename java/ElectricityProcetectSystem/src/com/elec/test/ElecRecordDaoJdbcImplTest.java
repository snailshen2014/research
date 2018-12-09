package com.elec.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.elec.domain.ElecRecord;
import com.elec.impl.ElecRecordDaoJdbcImpl;

import dao.ElecRecordDao;

public class ElecRecordDaoJdbcImplTest {
	private ElecRecordDao elecDao = new ElecRecordDaoJdbcImpl();
	@Test
	public void testGetRecords() {
		List<ElecRecord> elecRecords = elecDao.getRecords();
		for (ElecRecord elec : elecRecords) {
			System.out.println(elec);
		}
	}

	@Test
	public void testSave() {
		ElecRecord elec = new ElecRecord();
		elec.setHOST_ID("11235");
		elec.setHOST_NAME("远端5");
		elec.setELEC_VALUE1("89.9");
		elec.setELEC_VALUE2("89.9");
		elec.setSTATUS(0);
		elec.setOPER_DATE(new Date());
		elecDao.save(elec);
	}

	@Test
	public void testDelete() {
		Integer begin = 3;
		Integer end  = 4;
		elecDao.delete(begin, end);
	}

	@Test
	public void testUpdate() {
		Integer begin = 4;
		Integer end  = 6;
		elecDao.update(begin, end);
	}

}
