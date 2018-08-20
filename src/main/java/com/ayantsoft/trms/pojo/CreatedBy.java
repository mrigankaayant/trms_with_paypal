package com.ayantsoft.trms.pojo;

import java.io.Serializable;

public class CreatedBy implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 833574118497728785L;
	
	
	private String employeeId;
	private String name;
	private String workPhone;
	private String emailId;
	private String supervisorId;
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	} 
}
