package com.ayantsoft.trms.pojo;

import java.io.Serializable;

public class Role implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 4278852220269436933L;
	
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
