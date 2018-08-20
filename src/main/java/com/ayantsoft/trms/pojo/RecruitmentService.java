package com.ayantsoft.trms.pojo;

import java.io.Serializable;

public class RecruitmentService implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -5379416333815334336L;
	
	private String id;
	private String serviceName;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
