package com.ayantsoft.trms.pojo;


public class EnrollmentFormDto {
	
	private int year;
	private int enrollmentFormNo;
	private String courseName;
	private String service;
	private String dateOfEnrollment;
	private String tentiveTrainingStartDate;
	private int totalAmount;
	private String paymentDate;
	private int amountPaid;
	private String transactionId;
	private String dueAmount;
	private String dueDate;
	private String paymentMode;
	private String folderPathWithFile;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getEnrollmentFormNo() {
		return enrollmentFormNo;
	}
	public void setEnrollmentFormNo(int enrollmentFormNo) {
		this.enrollmentFormNo = enrollmentFormNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getDateOfEnrollment() {
		return dateOfEnrollment;
	}
	public void setDateOfEnrollment(String dateOfEnrollment) {
		this.dateOfEnrollment = dateOfEnrollment;
	}
	public String getTentiveTrainingStartDate() {
		return tentiveTrainingStartDate;
	}
	public void setTentiveTrainingStartDate(String tentiveTrainingStartDate) {
		this.tentiveTrainingStartDate = tentiveTrainingStartDate;
	}
	
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(String dueAmount) {
		this.dueAmount = dueAmount;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getFolderPathWithFile() {
		return folderPathWithFile;
	}
	public void setFolderPathWithFile(String folderPathWithFile) {
		this.folderPathWithFile = folderPathWithFile;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	
	public String toString(){
		return "year "+year+"     enrollmentFormNo"+enrollmentFormNo+"     courseName : "+courseName+"       service : "+service+"      dateOfEnrollment : "+dateOfEnrollment+"     "
				+ "tentiveTrainingStartDate :   "+tentiveTrainingStartDate+"       totalAmount : "+totalAmount+"    paymentDate : "+paymentDate+"  "
			    + "     amountPaid : "+amountPaid+"     transactionId :  "+transactionId+"      dueAmount : "+dueAmount+"      paymentMode : "+paymentMode+"   "
			    + "       folderPathWithFile : "+folderPathWithFile;
	}
	
	
	
	
}
