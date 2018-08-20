package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import java.util.Date;

public class Immigration implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 2737134448388036032L;
	
	private String immigrationType;
	private Date startDate;
	private Date endDate;
	private String status;
	
	
	public String getImmigrationType() {
		return immigrationType;
	}
	public void setImmigrationType(String immigrationType) {
		this.immigrationType = immigrationType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
