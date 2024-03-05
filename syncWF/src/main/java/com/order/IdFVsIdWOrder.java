package com.order;

import java.io.Serializable;

public class IdFVsIdWOrder implements Serializable{
	private static final long serialVersionUID = 123456789L;
	String idF;
	String idW;
	
	
	public IdFVsIdWOrder() {
		super();
	}
	public IdFVsIdWOrder(String idF, String idW) {
		super();
		this.idF = idF;
		this.idW = idW;
	}
	public String getIdF() {
		return idF;
	}
	public void setIdF(String idF) {
		this.idF = idF;
	}
	public String getIdW() {
		return idW;
	}
	public void setIdW(String idW) {
		this.idW = idW;
	}
	
	

}
