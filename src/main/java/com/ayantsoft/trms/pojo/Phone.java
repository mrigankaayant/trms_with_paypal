package com.ayantsoft.trms.pojo;

import java.io.Serializable;

public class Phone implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5635105088148278228L;
	
	private String type;
	private String number;
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
