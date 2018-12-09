package com.elec.domain;

public class ElecEntity {
	public ElecEntity(String t,String a, String b, String c,String id ) {
		super();
		this.t = t;
		this.aValue = a;
		this.bValue = b;
		this.cValue = c;
		this.id = id;
	}
	public ElecEntity(String t,String a, String b, String c ) {
		super();
		this.t = t;
		this.aValue = a;
		this.bValue = b;
		this.cValue = c;
		this.id = "";
	}
	String t;
	String aValue;
	String bValue;
	String cValue;
	String id;
	public String getT() {
		return t;
	}
	public void setT(String v) {
		this.t = v;
	}
	public String getaValue() {
		return aValue;
	}
	public void setaValuem(String v) {
		this.aValue = v;
	}
	public String getbValue() {
		return bValue;
	}
	public void setbValuem(String v) {
		this.bValue = v;
	}
	public String getcValue() {
		return cValue;
	}
	public void setcValue(String v) {
		this.cValue = v;
	}
	public String getId() {
		return id;
	}
	public void setId(String v) {
		this.id = v;
	}
	@Override
	public String toString() {
		return "ElecEntity [t=" + t +"aValue=" + aValue + ", bValue=" + bValue + ", cValue=" + cValue + ", id=" + id + "]";
	}
	
}
