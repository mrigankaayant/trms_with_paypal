package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="employee")
public class Employee implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 8556449834620080735L;
	
	@Id
	private String id;
    private String employeeId;
    private Boolean active;
    private String name;
    private String workMobile;
    private String workEmail;
    private String linkedin;
    private String userId;
    private String supervisorId;
    private String remarks;
    private String phoneExtension;
    private List<Role> roles;
    
    
    
    private LoginCredential loginCredential;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWorkMobile() {
		return workMobile;
	}
	public void setWorkMobile(String workMobile) {
		this.workMobile = workMobile;
	}
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPhoneExtension() {
		return phoneExtension;
	}
	public void setPhoneExtension(String phoneExtension) {
		this.phoneExtension = phoneExtension;
	}
	public LoginCredential getLoginCredential() {
		return loginCredential;
	}
	public void setLoginCredential(LoginCredential loginCredential) {
		this.loginCredential = loginCredential;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
