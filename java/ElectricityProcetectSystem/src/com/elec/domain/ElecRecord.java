package com.elec.domain;

import java.util.Date;

public class ElecRecord {
	public ElecRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	Integer SEQ_ID;
	String HOST_ID;
	String HOST_NAME;
	String ELEC_VALUE1;
	String ELEC_VALUE2;
	Integer STATUS;
	Date OPER_DATE;
	public Integer getSEQ_ID() {
		return SEQ_ID;
	}
	public void setSEQ_ID(Integer sEQ_ID) {
		SEQ_ID = sEQ_ID;
	}
	public String getHOST_ID() {
		return HOST_ID;
	}
	public void setHOST_ID(String hOST_ID) {
		HOST_ID = hOST_ID;
	}
	public String getHOST_NAME() {
		return HOST_NAME;
	}
	public void setHOST_NAME(String hOST_NAME) {
		HOST_NAME = hOST_NAME;
	}
	public String getELEC_VALUE1() {
		return ELEC_VALUE1;
	}
	public void setELEC_VALUE1(String eLEC_VALUE1) {
		ELEC_VALUE1 = eLEC_VALUE1;
	}
	public String getELEC_VALUE2() {
		return ELEC_VALUE2;
	}
	public void setELEC_VALUE2(String eLEC_VALUE2) {
		ELEC_VALUE2 = eLEC_VALUE2;
	}
	@Override
	public String toString() {
		return "ElecRecord [SEQ_ID=" + SEQ_ID + ", HOST_ID=" + HOST_ID + ", HOST_NAME=" + HOST_NAME + ", ELEC_VALUE1="
				+ ELEC_VALUE1 + ", ELEC_VALUE2=" + ELEC_VALUE2 + ", STATUS=" + STATUS + ", OPER_DATE=" + OPER_DATE
				+ "]";
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
	public Date getOPER_DATE() {
		return OPER_DATE;
	}
	public void setOPER_DATE(Date oPER_DATE) {
		OPER_DATE = oPER_DATE;
	}
	
}
