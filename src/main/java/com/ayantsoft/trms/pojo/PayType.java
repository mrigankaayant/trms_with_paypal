package com.ayantsoft.trms.pojo;

import java.io.Serializable;

public class PayType implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7559649117555153098L;
	
	private String id;
	private String type;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
