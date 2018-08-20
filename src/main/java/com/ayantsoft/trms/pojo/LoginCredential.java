package com.ayantsoft.trms.pojo;

import java.io.Serializable;

public class LoginCredential implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -4475258541307257963L;
	
	private String username;
	private String password;
	private Boolean active;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
