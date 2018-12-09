package com.elec.impl;

import java.util.List;

import com.elec.domain.ElecRecord;
import dao.DAO;
import dao.ElecRecordDao;

public class ElecRecordDaoJdbcImpl extends DAO<ElecRecord> implements ElecRecordDao {

	@Override
	public List<ElecRecord> getRecords() {
		String sql = "SELECT SEQ_ID,HOST_ID,HOST_NAME,ELEC_VALUE1,ELEC_VALUE2" 
				+" from HOST_ELEC_RECORD_INFO where STATUS=0";
		return getForList(sql);
		
	}

	@Override
	public void save(ElecRecord elec) {
		// TODO Auto-generated method stub
		String sql = "insert into  HOST_ELEC_RECORD_INFO(HOST_ID,HOST_NAME,ELEC_VALUE1,ELEC_VALUE2,STATUS," +
					" OPER_DATE) values(?,?,?,?,?,?)";
		update(sql,elec.getHOST_ID(),elec.getHOST_NAME(),elec.getELEC_VALUE1()
				,elec.getELEC_VALUE2(),elec.getSTATUS(),elec.getOPER_DATE());
	}

	@Override
	public void delete(Integer begin, Integer end) {
		// TODO Auto-generated method stub
		String sql = "delete  from   HOST_ELEC_RECORD_INFO where SEQ_ID between ? and ?";
		update(sql,begin,end);
	}

	@Override
	public void update(Integer begin, Integer end) {
		// TODO Auto-generated method stub
		String sql = "update     HOST_ELEC_RECORD_INFO  set STATUS= 1 where SEQ_ID between ? and ?";
		update(sql,begin,end);
	}

	

}
