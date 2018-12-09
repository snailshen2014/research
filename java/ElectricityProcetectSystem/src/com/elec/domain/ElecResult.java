package com.elec.domain;

public class ElecResult {
	public ElecResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	String HOST_ID;
	Integer RESULT_STATUS;
	Integer ORDER_STATUS;
	public String getHOST_ID() {
		return HOST_ID;
	}
	public void setHOST_ID(String hOST_ID) {
		HOST_ID = hOST_ID;
	}
	public Integer getRESULT_STATUS() {
		return RESULT_STATUS;
	}
	public void setRESULT_STATUS(Integer rESULT_STATUS) {
		RESULT_STATUS = rESULT_STATUS;
	}
	public Integer getORDER_STATUS() {
		return ORDER_STATUS;
	}
	public void setORDER_STATUS(Integer oRDER_STATUS) {
		ORDER_STATUS = oRDER_STATUS;
	}
	@Override
	public String toString() {
		return "ElecResult [HOST_ID=" + HOST_ID + ", RESULT_STATUS=" + RESULT_STATUS + ", ORDER_STATUS=" + ORDER_STATUS
				+ "]";
	}
	
}
