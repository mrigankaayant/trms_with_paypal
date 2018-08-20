package com.ayantsoft.trms.pojo;

import java.io.Serializable;

public class PreferredLocation implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -1516149101747558799L;
	
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
